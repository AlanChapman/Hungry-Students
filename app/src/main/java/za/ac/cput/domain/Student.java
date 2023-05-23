package za.ac.cput.domain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Student {
    private int studentId;
    private String fullName;
    private String emailAddress;
    private LocalDate dateOfBirth;
    private LocalDate createdAt;
    private long pointBalance;
    private String password;

    public Student() {}


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Student(String fullName, String emailAddress, LocalDate dateOfBirth, String password) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = LocalDate.now();
        this.password = password;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Student(int studentId, String fullName, String emailAddress, LocalDate dateOfBirth, LocalDate createdAt, long pointBalance) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = LocalDate.now();
        this.pointBalance = pointBalance;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFullName() { return fullName; }

    public void setFullName(String firstName) { this.fullName = fullName; }

    public String getEmailAddress() { return emailAddress; }

    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress;}

    public LocalDate getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(LocalDate dataOfBirth) { this.dateOfBirth = dataOfBirth; }

    public LocalDate getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    public float getPointBalance() { return pointBalance; }

    public void setPointBalance(long pointBalance) { this.pointBalance = pointBalance; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", createdAt=" + createdAt +
                ", pointBalance=" + pointBalance +
                ", password='" + password + '\'' +
                '}';
    }
}
