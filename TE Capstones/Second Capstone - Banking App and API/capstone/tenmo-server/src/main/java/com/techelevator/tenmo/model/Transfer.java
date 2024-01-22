package com.techelevator.tenmo.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Transfer {

    int transferId;
    int transferType;
    int transferStatus;
    int accountFrom;
    int accountTo;
    @NotNull
    BigDecimal amount;

    public Transfer() { }

    public Transfer(int transferType, int transferStatus, int accountFrom, int accountTo, BigDecimal amount) {
        this.transferType = transferType;
        this.transferStatus = transferStatus;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
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
}
