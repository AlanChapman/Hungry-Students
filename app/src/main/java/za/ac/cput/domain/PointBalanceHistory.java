package za.ac.cput.domain;

import java.time.LocalDate;

public class PointBalanceHistory {


    private int pointBalanceHistoryId;

    private LocalDate date;

    private int pointsBalance;

    private int studentId;

    private PointBalanceHistory(){
    }


    public PointBalanceHistory(Builder builder) {
        this.pointBalanceHistoryId = builder.pointBalanceHistoryId;
        this.date = builder.date;
        this.pointsBalance = builder.pointsBalance;
        this.studentId = builder.studentId;
    }



    public int getPointBalanceHistoryId() {
        return pointBalanceHistoryId;
    }

    public void setPointBalanceHistoryId(int pointBalanceHistoryId) {
        this.pointBalanceHistoryId = pointBalanceHistoryId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPointsBalance() {
        return pointsBalance;
    }

    public void setPointsBalance(int pointsBalance) {
        this.pointsBalance = pointsBalance;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "PointBalanceHistory{" +
                "pointBalanceHistoryId=" + pointBalanceHistoryId +
                ", date=" + date +
                ", pointsBalance=" + pointsBalance +
                ", studentId=" + studentId +
                '}';
    }

    public static class Builder {
        private int pointBalanceHistoryId;

        private LocalDate date;

        private int pointsBalance;

        private int studentId;


        public Builder setPointBalanceHistoryId(int pointBalanceHistoryId) {
            this.pointBalanceHistoryId = pointBalanceHistoryId;
            return this;
        }

        public Builder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder setPointsBalance(int pointsBalance) {
            this.pointsBalance = pointsBalance;
            return this;
        }

        public Builder setStudentId(int studentId) {
            this.studentId = studentId;
            return this;
        }

        public PointBalanceHistory build(){return new PointBalanceHistory(this);}
    }
}
