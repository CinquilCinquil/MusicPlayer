package repository;

import java.util.ArrayList;

public interface IRepository<E>
{
    public ArrayList<E> getAll();
    public E getOne(int id);
    public void create(E entity);
    public void update(E entity, int id);
    public void delete(int id);
}
