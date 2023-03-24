package za.ac.cput.domain;

import java.util.Objects;

public class Objective {
    private String title;
    private String description;
    private int points;
    private boolean isAchieved;

    public Objective() {

    }

    public Objective(String title, String description, int points, boolean isAchieved) {
        this.title = title;
        this.description = description;
        this.points = points;
        this.isAchieved = isAchieved;
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

    public boolean isAchieved() {
        return isAchieved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objective objective = (Objective) o;
        return points == objective.points && isAchieved == objective.isAchieved && title.equals(objective.title) && description.equals(objective.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, points, isAchieved);
    }

    @Override
    public String toString() {
        return "Objective{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", points=" + points +
                ", isAchieved=" + isAchieved +
                '}';
    }
}
