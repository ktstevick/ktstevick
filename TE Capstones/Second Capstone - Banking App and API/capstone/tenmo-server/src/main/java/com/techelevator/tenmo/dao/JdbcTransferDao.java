package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.data.relational.core.sql.Update;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {
    JdbcTemplate jdbcTemplate;
    private final String SELECT_TRANSFER_BY_ID_SQL = "SELECT * transfer WHERE transfer_id = ?";
    private final String SELECT_ACCOUNT_BALANCE_SQL = "SELECT_balance FROM accounts WHERE user = ?";
    private final String UPDATE_ACCOUNT_BALANCE_SQL = "UPDATE accounts SET account_balance = ? WHERE user_id = ?";
    private final String INSERT_TRANSFER_SQL = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?,?,?,?,?) RETURNING transfer_id";

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transfer getTransferById(int id) {
        Transfer transfer = null;
        String sql = "SELECT * FROM transfer WHERE transfer_id = ?;"; // DOUBLE CHECK LATER

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                transfer = mapRowToTransfer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return transfer;
    }
    @Override
    public Transfer updateTransferById(Transfer transfer) {

        String sql = "UPDATE transfer SET transfer_status_id = ? WHERE transfer_id = ?;"; // DOUBLE CHECK LATER

        jdbcTemplate.update(sql, transfer.getTransferStatus(), transfer.getTransferId());

        return getTransferById(transfer.getTransferId()); // This looks redutant but guarentees the return transfer comes from the database
    }

    @Override
    public List<Transfer> getTransfers() {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer;";

        try {
            SqlRowSet resultSet = jdbcTemplate.queryForRowSet(sql);
            while (resultSet.next()) {
                Transfer transfer = mapRowToTransfer(resultSet);
                transfers.add(transfer);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return transfers;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        int newTransferId;

        try { // THIS is where we'd have to adjust the User ID vs Account ID interaction
             newTransferId =    jdbcTemplate.queryForObject(INSERT_TRANSFER_SQL, int.class,transfer.getTransferType(),
                                transfer.getTransferStatus(), transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return getTransferById(newTransferId);
    }

    public Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();

        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferType(rs.getInt("transfer_type_id"));
        transfer.setTransferStatus(rs.getInt("transfer_status_id"));
        transfer.setAccountTo(rs.getInt("account_to"));
        transfer.setAccountFrom(rs.getInt("account_from"));
        transfer.setAmount(rs.getBigDecimal("amount"));

        return transfer;
    }
}
