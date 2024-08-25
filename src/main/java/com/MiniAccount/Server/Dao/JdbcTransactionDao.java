package com.MiniAccount.Server.Dao;

import com.MiniAccount.Server.Models.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcTransactionDao implements TransactionDao {
    public static final String TRANSACTIONS_SELECT = "SELECT id, description, amount, date FROM transactions";
    private final JdbcTemplate jdbcTemplate;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Get all Transactions
    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = TRANSACTIONS_SELECT;

        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
            while (rs.next()) {
                transactions.add(mapRowToTransaction(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }

    // Get Transaction by id
    @Override
    public Transaction getTransactionById(Long id) {
        Transaction transaction = null;
        String sql = TRANSACTIONS_SELECT + " WHERE id = ?";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);
            if (rs.next()) {
                transaction = mapRowToTransaction(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transaction;
    }

    // Add Transaction
    @Override
    public Transaction addTransaction(Transaction transaction) {
        Transaction returnedTransaction = null;
        String sql = "INSERT INTO transactions (description, amount, date) " +
                "VALUES (?, ?, ?)";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, transaction.getDescription(),
                    transaction.getAmount(), transaction.getDate());
            if (rs.next()) {
                returnedTransaction = mapRowToTransaction(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnedTransaction;
    }

    // Update Transaction
    @Override
    public Transaction updateTransaction(Transaction transaction) {
        Transaction updatedTransaction = null;
        String sql = "UPDATE transactions SET description = ?, amount = ?, " +
                "date = ? WHERE id = ?";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, transaction.getDescription(),
                    transaction.getAmount(), transaction.getDate(), transaction.getId());
            if (rs.next()) {
                updatedTransaction = mapRowToTransaction(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updatedTransaction;
    }

    // Delete Transaction
    @Override
    public int deleteTransactionById(Long id) {
        int numberOfRowsDeleted = 0;
        String sql = "DELETE FROM transactions WHERE id = ?";

        try {
            numberOfRowsDeleted = jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (numberOfRowsDeleted == 0) {
            throw new RuntimeException("Transaction not found");
        }
        return numberOfRowsDeleted;
    }

    private Transaction mapRowToTransaction(SqlRowSet rs) {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getLong("id"));
        transaction.setDescription(rs.getString("description"));
        transaction.setAmount(rs.getDouble("amount"));
        transaction.setDate(rs.getDate("date").toLocalDate());
        return transaction;
    }
}
