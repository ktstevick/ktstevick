package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleService {
    private final UserService userService = new UserService();
    private final TransferService transferService = new TransferService();
    private final AccountService accountService = new AccountService();
    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println("------------------------------------------");
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void pause() {
        System.out.println("\nPress Enter to return to Main Menu...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

    public void printUserList(){
        System.out.println("------------------------------------------");
        System.out.println("Users");
        System.out.println("ID          Name");
        System.out.println("------------------------------------------");

        userService.listUsers();

        System.out.println("------------------------------------------");
    }

    public void printTransferList(AuthenticatedUser currentUser) {
        System.out.println("------------------------------------------");
        System.out.println("Transfers");
        System.out.println("ID          From/To   Name       Amount");
        System.out.println("------------------------------------------");

        transferService.listTransfers(currentUser,  2);

        System.out.println("------------------------------------------");
        int enteredId = promptForInt("Please enter Transfer ID to view details (0 to cancel): ");

        if(enteredId > 0) {
            // GET TRANSFER BY ID
            Transfer transfer = transferService.getTransferById(enteredId);

            if(transfer != null) { // COULD be a separate method, but it never gets called anywhere else
                int transferId = transfer.getTransferId();

                int userFromId = transfer.getAccountFrom() - 1000;
                int userToId = transfer.getAccountTo() - 1000;

                User userFrom = userService.getUserById(userFromId);
                User userTo = userService.getUserById(userToId);

                int transferType = transfer.getTransferType();
                int transferStatus = transfer.getTransferStatus();
                BigDecimal amount = (transfer.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);

                // PRINT DETAILS
                System.out.println("------------------------------------------");
                System.out.println("Transfer Details");
                System.out.println("------------------------------------------");

                System.out.println("ID: " + transferId);
                System.out.println("FROM: " + userFrom.getUsername());
                System.out.println("TO: " + userTo.getUsername());
                System.out.println("Type: " + transferService.getTypeString(transferType));
                System.out.println("Status: " + transferService.getStatusString(transferStatus));
                System.out.println("AMOUNT: $" + amount);
                System.out.println("------------------------------------------");
                pause();
            }
            else {
                System.out.println("Sorry, that Transfer doesn't exist. Please enter a valid ID.");
            }
        }
        else if(enteredId == 0) {
            System.out.println("Transaction cancelled, returning to Main Menu.");
        }

        else if(enteredId < 0) {
            System.out.println("Sorry, no negative numbers. Please try again!");
        }
    }


    public void printRequestList(AuthenticatedUser currentUser){
        System.out.println("------------------------------------------");
        System.out.println("Pending Transfers");
        System.out.println("ID         To        Amount");
        System.out.println("------------------------------------------");

        Transfer[] pendingTransfers = transferService.listTransfers(currentUser,  1);

        System.out.println("------------------------------------------");
        int enteredId = promptForInt("Please enter Transfer ID to Approve/Reject (0 to cancel): ");

        if(enteredId > 0) {
            boolean listMatch = false;

            // Confirm transfer exists, then print menu and prompt for action
            for(Transfer transfer: pendingTransfers) {
                if (transfer.getSearchVisible() && transfer.getTransferId() == enteredId) {
                    listMatch = true;
                    break;
                }
            }

            if(listMatch) {
                // Print menu
                System.out.println("------------------------------------------");
                System.out.println("1: Approve");
                System.out.println("2: Reject");
                System.out.println("0: Don't approve or reject"); // Ignore
                System.out.println("------------------------------------------");

                int subMenuOption = promptForInt("Please choose an option: ");
                Transfer pendingTransfer = transferService.getTransferById(enteredId);

                if (subMenuOption == 0) {
                    System.out.println("Transaction cancelled, returning to Main Menu.");
                } else if (subMenuOption == 1) {
                    // CHECK ACCOUNT BALANCE
                    if((pendingTransfer.getAmount()).compareTo(accountService.getAccountById(pendingTransfer.getAccountFrom()).getBalance()) < 0) {
                        accountService.updateAccountsByTransfer(pendingTransfer);
                        pendingTransfer.setTransferStatus(2);
                        transferService.updateTransfer(pendingTransfer);

                        System.out.println("Approved. Successfully sent $" + (pendingTransfer.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP))
                                + " to " + userService.getUserById(pendingTransfer.getAccountFrom() - 1000).getUsername() + ". Returning to Main Menu...");
                    }

                    else {
                        System.out.println("Insufficient funds to Approve. Please try again later.");
                    }

                } else if (subMenuOption == 2) {
                    // IF REJECTED
                    pendingTransfer.setTransferStatus(3);
                    transferService.updateTransfer(pendingTransfer);
                    System.out.println("Rejected. Returning to Main Menu.");
                    // Have to update database
                } else {
                    System.out.println("Please enter a valid ID.");
                }
            } else {
                System.out.println("Please enter a valid ID.");
            }
        } else {
            System.out.println("Please enter a valid ID.");
        }
    }
}
