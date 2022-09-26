package dataStructure.queue;

public interface Queue {
    void add(int element);
    int remove();
    void show();
    void clear();
    int getSize();
    boolean isEmpty();
}
