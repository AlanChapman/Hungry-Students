package za.ac.cput.domain;

import java.time.LocalDateTime;

public class Transaction {
    private String title;
    private String recipientId;
    private String recipientType;
    private String senderId;
    private String senderType;
    private String transactionType;
    private double amount;
    private int points;
    private LocalDateTime createdAt;
    private boolean isSuccessful;

    public Transaction() {

    }

    public Transaction(String title, String recipientId, String recipientType, String senderId, String senderType, String transactionType, double amount, int points, LocalDateTime createdAt, boolean isSuccessful) {
        this.title = title;
        this.recipientId = recipientId;
        this.recipientType = recipientType;
        this.senderId = senderId;
        this.senderType = senderType;
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

    public String getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(String recipientType) {
        this.recipientType = recipientType;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
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
                ", recipientType='" + recipientType + '\'' +
                ", senderId='" + senderId + '\'' +
                ", senderType='" + senderType + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", points=" + points +
                ", createdAt=" + createdAt +
                ", isSuccessful=" + isSuccessful +
                '}';
    }
}
