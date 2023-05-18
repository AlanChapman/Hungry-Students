package za.ac.cput.repository;

import za.ac.cput.domain.Student;

public interface IStudentRepository {

    boolean register(Student student);
    boolean login(String emailAddress, String password);
    int getCurrentStudentId(String emailAddress);
    String getCurrentStudentFirstName(int studentId);

    Student getStudentDetails(int studentId);

    String getCurrentStudentEmailAddress(int studentId);
}
