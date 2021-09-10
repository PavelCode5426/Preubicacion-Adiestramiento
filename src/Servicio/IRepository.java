package Servicio;

import java.util.List;

public interface IRepository<T>
{
    public List<T> GetAll() throws Exception;
    public T Get(int PrimaryKey) throws Exception;
    public boolean Create(T objeto) throws Exception;
    public boolean Remove(int PrimaryKey) throws Exception;
    public boolean Update(int PrimaryKey, T objeto) throws Exception;
}
