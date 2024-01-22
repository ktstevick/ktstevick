package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    int transferId;
    int transferType;
    int transferStatus;
    int accountFrom;
    int accountTo;
    BigDecimal amount;
    public boolean searchVisible = false;

    public Transfer() { }

    public Transfer(int transferType, int transferStatus, int userAccountId, int receiverId, BigDecimal amount) {
        this.transferType = transferType;
        this.transferStatus = transferStatus;
        this.accountFrom = userAccountId + 1000; // !!!
        this.accountTo = receiverId + 1000;
        this.amount = amount;
    }

    public int getTransferId() {
        return transferId;
    }
    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }
    public int getTransferType() {
        return transferType;
    }
    public void setTransferType(int transferType) {
        this.transferType = transferType;
    }
    public int getTransferStatus() {
        return transferStatus;
    }
    public void setTransferStatus(int transferStatus) {
        this.transferStatus = transferStatus;
    }
    public int getAccountFrom() {
        return accountFrom;
    }
    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }
    public int getAccountTo() {
        return accountTo;
    }
    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setSearchVisible(boolean searchVisible) {
        this.searchVisible = searchVisible;
    }

    public boolean getSearchVisible() {
        return searchVisible;
    }
}
