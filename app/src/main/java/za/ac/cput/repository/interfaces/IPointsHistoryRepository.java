package za.ac.cput.repository.interfaces;

import java.util.List;

import za.ac.cput.domain.PointBalanceHistory;

public interface IPointsHistoryRepository {
    List<PointBalanceHistory> getAll();
}
