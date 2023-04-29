package za.ac.cput.domain;

import java.time.LocalDateTime;

public class Transaction {
    private String title;
    private String recipientId;
    private String senderId;
    private String transactionType;
    private double amount;
    private int points;
    private LocalDateTime createdAt;
    private boolean isSuccessful;

    public Transaction() {

    }

    public Transaction(String title, String recipientId, String senderId, String transactionType, double amount, int points, LocalDateTime createdAt, boolean isSuccessful) {
        this.title = title;
        this.recipientId = recipientId;
        this.senderId = senderId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.points = points;
        this.createdAt = createdAt;
        this.isSuccessful = isSuccessful;
    }

    public Transaction(String title, int points, LocalDateTime createdAt, boolean isSuccessful) {
        this.title = title;
        this.points = points;
        this.createdAt = createdAt;
        this.isSuccessful = isSuccessful;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "title='" + title + '\'' +
                ", recipientId='" + recipientId + '\'' +
                ", senderId='" + senderId + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", points=" + points +
                ", createdAt=" + createdAt +
                ", isSuccessful=" + isSuccessful +
                '}';
    }
}
