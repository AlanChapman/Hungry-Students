package za.ac.cput.domain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
    private int transactionId;

    private String title;
    private int recipientId;

    private int studentId;
    private int senderId;
    private TransactionType type;
    private int pointAmount;
    private LocalDateTime createdAt;
    private boolean status;

    public Transaction() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Transaction(int transactionId, String title, int recipientId, int senderId, TransactionType type, int pointAmount, boolean status, int studentId) {
        this.transactionId = transactionId;
        this.title = title;
        this.recipientId = recipientId;
        this.senderId = senderId;
        this.type = type;
        this.pointAmount = pointAmount;
        this.createdAt = LocalDateTime.now();
        this.status = status;
        this.studentId = studentId;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Transaction(int recipientId, String title, int senderId, TransactionType type, int pointAmount, boolean status, int studentId) {
        this.recipientId = recipientId;
        this.title = title;
        this.senderId = senderId;
        this.type = type;
        this.pointAmount = pointAmount;
        this.createdAt = LocalDateTime.now();
        this.status = status;
        this.studentId = studentId;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Transaction(String title, TransactionType type, int pointAmount, LocalDateTime createdAt, boolean status) {
        this.title = title;
        this.type = type;
        this.pointAmount = pointAmount;
        this.createdAt = LocalDateTime.now();
        this.status = status;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getTitle() {
        return title;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getSenderId() {
        return senderId;
    }

    public TransactionType getType() {
        return type;
    }

    public int getPointAmount() {
        return pointAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean getStatus() {
        return status;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setPointAmount(int pointAmount) {
        this.pointAmount = pointAmount;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", title='" + title + '\'' +
                ", recipientId=" + recipientId +
                ", studentId=" + studentId +
                ", senderId=" + senderId +
                ", type=" + type +
                ", pointAmount=" + pointAmount +
                ", createdAt=" + createdAt +
                ", status=" + status +
                '}';
    }
}
