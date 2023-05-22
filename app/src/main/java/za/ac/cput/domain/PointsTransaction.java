package za.ac.cput.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PointsTransaction {
    private String pointsTransactionId;
    private int studentId;
    private String recipientId;
    private String senderId;
    private TransactionType type;
    private int pointAmount;
    private BigDecimal zarAmount;
    private LocalDateTime dateTime;

    public PointsTransaction(Builder builder) {
        this.pointsTransactionId = builder.pointsTransactionId;
        this.studentId = builder.studentId;
        this.recipientId = builder.recipientId;
        this.senderId = builder.senderId;
        this.type = builder.type;
        this.pointAmount = builder.pointAmount;
        this.zarAmount = builder.zarAmount;
        this.dateTime = builder.dateTime;
    }

    public String getPointsTransactionId() {
        return pointsTransactionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getSenderId() {
        return senderId;
    }

    public TransactionType getType() {
        return type;
    }

    public int getPointAmount() {
        return pointAmount;
    }

    public BigDecimal getZarAmount() {
        return zarAmount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "PointsTransaction{" +
                "pointsTransactionId='" + pointsTransactionId + '\'' +
                ", studentId=" + studentId +
                ", recipientId='" + recipientId + '\'' +
                ", senderId='" + senderId + '\'' +
                ", type=" + type +
                ", pointAmount=" + pointAmount +
                ", zarAmount=" + zarAmount +
                ", dateTime=" + dateTime +
                '}';
    }

    public static class Builder {
        private String pointsTransactionId;
        private int studentId;
        private String recipientId;
        private String senderId;
        private TransactionType type;
        private int pointAmount;
        private BigDecimal zarAmount;
        private LocalDateTime dateTime;

        public Builder setPointsTransactionId(String pointsTransactionId) {
            this.pointsTransactionId = pointsTransactionId;
            return this;
        }

        public Builder setStudentId(int studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder setRecipientId(String recipientId) {
            this.recipientId = recipientId;
            return this;
        }

        public Builder setSenderId(String senderId) {
            this.senderId = senderId;
            return this;
        }

        public Builder setType(TransactionType type) {
            this.type = type;
            return this;
        }

        public Builder setPointAmount(int pointAmount) {
            this.pointAmount = pointAmount;
            return this;
        }

        public Builder setZarAmount(BigDecimal zarAmount) {
            this.zarAmount = zarAmount;
            return this;
        }

        public Builder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public PointsTransaction build() {
            return new PointsTransaction(this);
        }
    }
}
