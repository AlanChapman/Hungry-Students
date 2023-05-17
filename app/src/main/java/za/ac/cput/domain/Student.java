package za.ac.cput.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private LocalDate dataOfBirth;
    private LocalDateTime createdAt;
    private float pointBalance;

    public Student() {}

    public Student(String studentId, String firstName, String lastName, String emailAddress, LocalDate dataOfBirth, LocalDateTime createdAt, float pointBalance) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.dataOfBirth = dataOfBirth;
        this.createdAt = createdAt;
        this.pointBalance = pointBalance;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmailAddress() { return emailAddress; }

    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress;}

    public LocalDate getDataOfBirth() { return dataOfBirth; }

    public void setDataOfBirth(LocalDate dataOfBirth) { this.dataOfBirth = dataOfBirth; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public float getPointBalance() { return pointBalance; }

    public void setPointBalance(float pointBalance) { this.pointBalance = pointBalance; }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", dataOfBirth=" + dataOfBirth +
                ", createdAt=" + createdAt +
                ", pointBalance=" + pointBalance +
                '}';
    }
}
