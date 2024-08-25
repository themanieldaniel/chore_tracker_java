package com.MiniAccount.demo.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "chores")
public class Chore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double amount;
    private Boolean completed;
    private LocalDate dateCompleted;


    public Chore(String description, Double amount) {
        this.description = description;
        this.amount = amount;
        this.completed = false;
        this.dateCompleted = null;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
}