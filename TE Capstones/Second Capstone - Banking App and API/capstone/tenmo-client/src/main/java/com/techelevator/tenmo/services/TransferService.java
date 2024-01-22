package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class TransferService {
    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();
    private final UserService userService = new UserService();


    public Transfer getTransferById(int id) {
        Transfer transfer = null;
        try {
            transfer = restTemplate.getForObject(API_BASE_URL + "transfers/" + id, Transfer.class);
        } catch (RestClientResponseException e) {
            // :D
        }
        return transfer;
    }
    public boolean updateTransfer(Transfer transfer){
        boolean success = false;
        HttpEntity<Transfer> entity = makeTransferEntity((transfer));
        try {
            restTemplate.put(API_BASE_URL + "transfers/" + transfer.getTransferId(), entity, Transfer.class);
            success = true;
        } catch (RestClientException e) {

        }

        return success;
    }

    public boolean addTransfer(Transfer transfer) {
        HttpEntity<Transfer> entity = makeTransferEntity(transfer);
        boolean success = false;

        try {
            restTemplate.postForObject(API_BASE_URL + "transfers", entity, Transfer.class);
            success = true;
        } catch (RestClientException e) {
            // System.out.println("Now what?");
        }

        return success;
    }

    public Transfer[] listTransfers(AuthenticatedUser currentUser, int searchStatus) {
        Transfer[] transfers = null;

        try {
            transfers = restTemplate.getForObject(API_BASE_URL + "transfers", Transfer[].class);
        } catch (RestClientResponseException e) {
            System.out.println("I'm gonna crash now!");
        }

        // We want to check that the transfer in the array matches the parameter we're looking for
        for (Transfer transfer : transfers) {
            int transferStatus = transfer.getTransferStatus();
            if (transferStatus == searchStatus){
                if(currentUser.getUser().getId() == (transfer.getAccountTo() - 1000) ||
                        currentUser.getUser().getId() == (transfer.getAccountFrom() - 1000) ) { // User ID matches To or From
                    transfer.setSearchVisible(true);
                }
            }
        }

            // PENDING STATUS
            if (searchStatus == 1) {
                for (Transfer transfer : transfers) {
                    if (transfer.getSearchVisible()) {
                        int transferId = transfer.getTransferId();
                        int transferType = transfer.getTransferType();
                        int userFromId = transfer.getAccountFrom() - 1000;
                        String stringId = "" + transferId;

                        if (transferType == 1) {
                            if (transfer.getAccountTo() - 1000 == currentUser.getUser().getId()) {
                                System.out.println(spacedLeft(stringId, 11) + spacedLeft(userService.getUserById(userFromId).getUsername(), 10)
                                        + "$" + transfer.getAmount());
                            }
                        }

                        // No if(transferType == 2), since Send transfers are auto-approved
                    }
                }
            }

            // APPROVED STATUS
            if (searchStatus == 2) {
                for (Transfer transfer : transfers) {
                    if (transfer.getSearchVisible()) {
                        int transferId = transfer.getTransferId();
                        int transferType = transfer.getTransferType(); // SEND or REQUEST
                        String stringId = "" + transferId;

                        // CAN'T think of a way to encapsulate this that's less chaotic
                        if (transferType == 1) { // REQUEST
                            if (transfer.getAccountFrom() - 1000 == currentUser.getUser().getId()) { // Check for User ID
                                System.out.println(spacedLeft(stringId, 12) + spacedLeft("From", 10) +
                                        spacedLeft(userService.getUserById(transfer.getAccountTo() - 1000).getUsername(), 11) +
                                        "$" + transfer.getAmount());

                            } else {
                                System.out.println(spacedLeft(stringId, 12) + spacedLeft("To", 10) +
                                        spacedLeft(userService.getUserById(transfer.getAccountFrom() - 1000).getUsername(), 11) +
                                        "$" + transfer.getAmount());
                            }
                        }
                        if (transferType == 2) { //SEND
                            if (transfer.getAccountTo() - 1000 == currentUser.getUser().getId()) {
                                System.out.println(spacedLeft(stringId, 12) + spacedLeft("From", 10) +
                                        spacedLeft(userService.getUserById(transfer.getAccountFrom() - 1000).getUsername(), 11) +
                                        "$" + transfer.getAmount());
                            } else {
                                System.out.println(spacedLeft(stringId, 12) + spacedLeft("To", 10) +
                                        spacedLeft(userService.getUserById(transfer.getAccountTo() - 1000).getUsername(), 11) +
                                        "$" + transfer.getAmount());
                            }
                        }
                    }
                }
            }

        return transfers;
    }

    public String getStatusString(int id) {
        String response = "";

        if (id == 1) {
            response = "Pending";
        } else if (id == 2) {
            response = "Approved";
        } else if (id == 3) {
            response = "Rejected";
        } else {
            response = "You should never see this message. (INVALID ID)";
        }

        return response;
    }

    public String getTypeString(int id) {
        String response = "";

        if (id == 1) {
            response = "Request";
        } else if (id == 2) {
            response = "Send";
        } else {
            response = "You should never see this message. (INVALID ID)";
        }

        return response;
    }

    public String spacedLeft(String displayText, int distance) {
        return String.format("%" + -(distance) + "s", displayText);
    }

    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(transfer, headers);
    }
}
