package za.ac.cput.repository;

import java.util.List;

import za.ac.cput.domain.Objective;

public interface IObjectiveRepository extends IRepository<Objective, String> {
    List<Objective> getAll();
}
