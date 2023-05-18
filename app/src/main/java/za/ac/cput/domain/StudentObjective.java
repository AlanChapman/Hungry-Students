package za.ac.cput.domain;

import java.time.LocalDateTime;

public class StudentObjective {
    private int studentObjectiveId;
    private int studentId;
    private int objectiveId;
    private LocalDateTime dateAchieved;

    public StudentObjective() {

    }

    public StudentObjective(int studentObjectiveId, int studentId, int objectiveId, LocalDateTime dateAchieved) {
        this.studentObjectiveId = studentObjectiveId;
        this.studentId = studentId;
        this.objectiveId = objectiveId;
        this.dateAchieved = dateAchieved;
    }

    public int getStudentObjectiveId() {
        return studentObjectiveId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getObjectiveId() {
        return objectiveId;
    }

    public LocalDateTime getDateAchieved() {
        return dateAchieved;
    }

    @Override
    public String toString() {
        return "StudentObjective{" +
                "studentObjectiveId=" + studentObjectiveId +
                ", studentId=" + studentId +
                ", objectiveId=" + objectiveId +
                ", dateAchieved=" + dateAchieved +
                '}';
    }
}
