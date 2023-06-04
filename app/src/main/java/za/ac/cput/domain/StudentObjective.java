package za.ac.cput.domain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

public class StudentObjective {
    private int studentId;
    private int objectiveId;

    private String objectiveTitle;
    private LocalDateTime dateAchieved;

    private StudentObjective() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private StudentObjective(Builder builder) {
        this.studentId = builder.studentId;
        this.objectiveId = builder.objectiveId;
        this.dateAchieved = builder.dateAchieved;
        this.objectiveTitle = builder.objectiveTitle;
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

    public String getObjectiveTitle() {
        return objectiveTitle;
    }

    @Override
    public String toString() {
        return "StudentObjective{" +
                "studentId=" + studentId +
                ", objectiveId=" + objectiveId +
                ", objectiveTitle='" + objectiveTitle + '\'' +
                ", dateAchieved=" + dateAchieved +
                '}';
    }

    public static class Builder {
        private int studentId;
        private int objectiveId;
        private LocalDateTime dateAchieved;
        private String objectiveTitle;

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

        public Builder setObjectiveTitle(String objectiveTitle) {
            this.objectiveTitle = objectiveTitle;
            return this;
        }


        @RequiresApi(api = Build.VERSION_CODES.O)
        public StudentObjective build() {
            return new StudentObjective(this);
        }
    }
}
