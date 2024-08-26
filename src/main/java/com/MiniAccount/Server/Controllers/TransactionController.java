package com.MiniAccount.Server.Controllers;

import com.MiniAccount.Server.Dao.TransactionDao;
import com.MiniAccount.Server.Models.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/transactions")
@RestController
public class TransactionController {

    private final TransactionDao transactionDao;

    public TransactionController(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionDao.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionDao.getTransactionById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = null;

        try {
            createdTransaction = transactionDao.addTransaction(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return createdTransaction;
    }

    @PutMapping
    public Transaction updateTransaction(@RequestBody Transaction transaction) {
        Transaction updatedTransaction = null;

        try {
            updatedTransaction = transactionDao.updateTransaction(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updatedTransaction;
    }

    @DeleteMapping("/{id}")
    public int deleteTransactionById(@PathVariable Long id) {
        return transactionDao.deleteTransactionById(id);
    }
}
