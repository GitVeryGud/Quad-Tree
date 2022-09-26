package dataStructure.list;

import java.util.Iterator;

public class ArrayListNew<T> implements List<T>{
    private T[] list;
    private int size;
    private final double growthRate;

    public ArrayListNew(){
        list = (T[]) new Object[10];
        size = 0;
        growthRate = 1.5;
    }

    @Override
    public List<T> clear() {
        size = 0;
        return this;
    }

    @Override
    public List<T> add(T value) {
        if (isFull()){
            int newCapacity = (int)(list.length * growthRate);
            newCapacity = Math.max(list.length + 1, newCapacity);
            var newList = (T[]) new Object[newCapacity];
            for (int i = 0; i < list.length; i++){
                newList[i] = list[i];
            }
            list = newList;
        }
        list[size] = value;
        size ++;
        return this;
    }

    @Override
    public List<T> add(int index, T value) {
        if (isFull()){
            int newCapacity = (int)(list.length * growthRate);
            newCapacity = Math.max(list.length + 1, newCapacity);
            var newList = (T[]) new Object[newCapacity];
            for (int i = 0; i < list.length; i++){
                newList[i] = list[i];
            }
            list = newList;
        }

        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("Invalid Index: " + index);
        }
        for (int i = size - 1; i >= index; i--){
            list[i + 1] = list[i];
        }
        list[index] = value;
        size++;
        return this;
    }

    @Override
    public T remove(int index) {
        if (isEmpty()){
            throw new IllegalStateException("Empty list (Underflow)");
        }

        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Invalid Index: " + index);
        }

        T value = list[index];
        for (int i = index; i < size - 1; i++){
            list[i] = list[i+1];
        }
        size--;
        return value;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Invalid Index: " + index);
        }
        return list[index];
    }

    @Override
    public List<T> set(int index, T value) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Invalid Index: " + index);
        }
        list[index] = value;
        return this;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < size; i++){
            if (list[i] == value){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        for (int i = size - 1; i >= 0; i--){
            if (list[i] == value){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull(){
        return size == getCapacity();
    }

    private int getCapacity(){
        return list.length;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("[");
        if (size>1){
            sb.append(list[0]);
        }
        for (int i = 1; i < size; i++){
            sb.append(", ").append(list[i]);
        }
        sb.append("]");
        return sb.toString();


    }
}
