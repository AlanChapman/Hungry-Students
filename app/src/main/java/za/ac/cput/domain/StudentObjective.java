package za.ac.cput.domain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

public class StudentObjective {
    private int studentId;
    private int objectiveId;
    private LocalDateTime dateAchieved;

    private StudentObjective() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private StudentObjective(Builder builder) {
        this.studentId = builder.studentId;
        this.objectiveId = builder.objectiveId;
        this.dateAchieved = builder.dateAchieved;
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
                ", studentId=" + studentId +
                ", objectiveId=" + objectiveId +
                ", dateAchieved=" + dateAchieved +
                '}';
    }

    public static class Builder {
        private int studentId;
        private int objectiveId;
        private LocalDateTime dateAchieved;

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
