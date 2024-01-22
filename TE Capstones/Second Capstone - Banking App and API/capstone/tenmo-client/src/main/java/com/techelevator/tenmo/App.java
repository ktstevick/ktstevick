package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final TransferService transferService = new TransferService();
    private final AccountService accountService = new AccountService();
    private final UserService userService = new UserService();

    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);

    private AuthenticatedUser currentUser;
    private final RestTemplate restTemplate = new RestTemplate();


    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection. Returning to Main Menu.");
                // consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);

        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            // consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
        Account currentAccount;
        BigDecimal amount;
        BigDecimal displayAmount = new BigDecimal("0.00");

        try {
            currentAccount = restTemplate.getForObject(API_BASE_URL + "/accounts/" + (currentUser.getUser().getId() + 1000), Account.class);
            amount = currentAccount.getBalance();
            displayAmount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (RestClientResponseException e) {
            // @TODO: all the logging functionality
        }

		System.out.println("Your current account balance is: $" + displayAmount);
	}

	private void viewTransferHistory() {
        consoleService.printTransferList(currentUser);
	}

	private void viewPendingRequests() {
		consoleService.printRequestList(currentUser);
	}

	private void sendBucks() {
        Account currentAccount = restTemplate.getForObject(API_BASE_URL + "/accounts/" + (currentUser.getUser().getId() + 1000), Account.class);
        BigDecimal displayAmountSent = new BigDecimal("0.00");

        // Will Print List Of Users
        consoleService.printUserList();

        // Prompt for relevant information
        int receiverID = consoleService.promptForInt("Enter ID of user you are sending to (0 to cancel): ");

        if (receiverID != 0 ) {
            BigDecimal amount = consoleService.promptForBigDecimal("Enter amount: ");

            if (currentAccount.getBalance().compareTo(amount) >= 0) {
                // Feed that data, create new Transfer object
                Transfer newTransfer = new Transfer(2, 2, currentUser.getUser().getId(), receiverID, amount);
                boolean success = transferService.addTransfer(newTransfer);

                if (!success) {
                    consoleService.printErrorMessage();
                } else if (success) {
                    // Update accounts, flavor text
                    displayAmountSent = newTransfer.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
                    BigDecimal updatedDisplayBalance = currentAccount.getBalance().subtract(amount);
                    accountService.updateAccountsByTransfer(newTransfer);
                    System.out.println("Successfully sent $" + displayAmountSent + " to " + userService.getUserById(receiverID).getUsername()
                                    + ". Remaining balance: $" + updatedDisplayBalance);
                }
            } else if (currentAccount.getBalance().compareTo(amount) < 0) {
                System.out.println("Insufficient funds. Transfer not created.");
            }
        }

        else if (receiverID == 0){
            System.out.println("Transaction Cancelled.");
        }
	}

    // README a little unclear about Request type Transfers appearing in both user history lists. Currently they do,
    // but only after approval. Until then they only appear in the relevant pending list
	private void requestBucks() {
        Account currentAccount = restTemplate.getForObject(API_BASE_URL + "/accounts/" + (currentUser.getUser().getId() + 1000), Account.class);

        // Will Print List Of Users
        consoleService.printUserList();

        // Prompt for relevant information
        int receiverID = consoleService.promptForInt("Enter ID of user you are requesting from (0 to cancel): ");

        if(receiverID != 0) {
            // TODO: CLI checks for matching IDs, non-positive amounts
            BigDecimal amount = consoleService.promptForBigDecimal("Enter amount: ");

            Transfer newTransfer = new Transfer(1, 1, currentUser.getUser().getId(), receiverID, amount);
            boolean success = transferService.addTransfer(newTransfer);

            if (!success) {
                consoleService.printErrorMessage();
            } else if (success) {
                // Update accounts, flavor text
                System.out.println("Successfully requested $" + amount + " from " + userService.getUserById(receiverID).getUsername()
                        + ".");
            }

        } else if (receiverID == 0){
            System.out.println("Transaction Cancelled.");
        }
	}

}
