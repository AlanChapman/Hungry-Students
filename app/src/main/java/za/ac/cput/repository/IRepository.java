package za.ac.cput.repository;

import java.util.List;

public interface IRepository<T, ID>{
    T create(T type);
    T read(ID id);
    T update(T type);
    boolean delete(ID id);

}
