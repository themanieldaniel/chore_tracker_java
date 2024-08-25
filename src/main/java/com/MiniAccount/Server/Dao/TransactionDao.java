package com.MiniAccount.Server.Dao;

import com.MiniAccount.Server.Models.Transaction;

import java.util.List;

/*
Transaction table

Stores Transaction information

| Field         | Description                     |
| ------------- | ------------------------------- |
| id            | unique id                       |
| description   | description of Transaction      |
| amount        | amount of money earned by Chore |
| date          | date of Transaction             |

 */
public interface TransactionDao {

    // Get all Transactions
    List<Transaction> getAllTransactions();

    // Get Transaction by id
    Transaction getTransactionById(Long id);

    // Add Transaction
    Transaction addTransaction(Transaction transaction);

    // Update Transaction
    Transaction updateTransaction(Transaction transaction);

    // Delete Transaction
    int deleteTransactionById(Long id);
}
