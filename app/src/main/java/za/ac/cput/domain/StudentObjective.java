package za.ac.cput.domain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

public class StudentObjective {
    private int studentObjectiveId;
    private int studentId;
    private int objectiveId;
    private LocalDateTime dateAchieved;

    private StudentObjective() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private StudentObjective(Builder builder) {
        this.studentObjectiveId = builder.studentObjectiveId;
        this.studentId = builder.studentId;
        this.objectiveId = builder.objectiveId;
        this.dateAchieved = builder.dateAchieved;
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

    public static class Builder {
        private int studentObjectiveId;
        private int studentId;
        private int objectiveId;
        private LocalDateTime dateAchieved;

        public Builder setStudentObjectiveId(int studentObjectiveId) {
            this.studentObjectiveId = studentObjectiveId;
            return this;
        }

        public Builder setStudentId(int studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder setObjectiveId(int objectiveId) {
            this.objectiveId = objectiveId;
            return this;
        }

        public Builder setDateAchieved(LocalDateTime dateAchieved) {
            this.dateAchieved = dateAchieved;
            return this;
        }


        @RequiresApi(api = Build.VERSION_CODES.O)
        public StudentObjective build() {
            return new StudentObjective(this);
        }
    }
}
