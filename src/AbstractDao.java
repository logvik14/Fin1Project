import java.util.Set;

public interface AbstractDao<T>  {

    public T save (T t) throws Exception;

    public T delete (T t) throws Exception;

    public Set<T> getAll();
}

