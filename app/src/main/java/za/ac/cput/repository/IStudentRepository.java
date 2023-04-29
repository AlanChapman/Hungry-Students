package za.ac.cput.repository;

import za.ac.cput.domain.Student;

public interface IStudentRepository extends IRepository<Student, String> {
    Boolean login(Student student);
    Student updateStudentDetails(Student student);
    Student getStudentDetails(String emailAddress);
    long getCurrentStudentId(String emailAddress);
    String getCurrentStudentEmailAddress(long userId);
}
