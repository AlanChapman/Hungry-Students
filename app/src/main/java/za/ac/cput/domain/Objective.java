package za.ac.cput.domain;

import java.util.Objects;

public class Objective {
    private int objectiveId;
    private String title;
    private String description;
    private int points;

    private Objective() {

    }

    private Objective(Builder builder) {
        this.objectiveId = builder.objectiveId;
        this.title = builder.title;
        this.description = builder.description;
        this.points = builder.points;
    }

    public int getObjectiveId() {
        return objectiveId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Objective{" +
                "objectiveId=" + objectiveId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", points=" + points +
                '}';
    }

    public static class Builder {
        private int objectiveId;
        private String title;
        private String description;
        private int points;

        public Builder setObjectiveId(int objectiveId) {
            this.objectiveId = objectiveId;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPoints(int points) {
            this.points = points;
            return this;
        }

        public Objective build() {
            return new Objective(this);
        }
    }
}
