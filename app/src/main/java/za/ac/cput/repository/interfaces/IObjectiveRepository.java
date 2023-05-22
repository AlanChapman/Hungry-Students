package za.ac.cput.repository.interfaces;

import java.util.List;

import za.ac.cput.domain.Objective;

public interface IObjectiveRepository {
    List<Objective> getAll();
}
