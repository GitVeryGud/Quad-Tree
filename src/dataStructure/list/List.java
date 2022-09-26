package dataStructure.list;

public interface List<T> extends Iterable<T> {
    List<T> clear();
    List<T> add(T value);
    List<T> add(int index, T value);
    T remove(int index);
    T get(int index);
    List<T> set(int index, T value);
    int indexOf(T value);
    int lastIndexOf(T value);
    boolean contains(T value);
    int getSize();
    boolean isEmpty();
}
