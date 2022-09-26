package dataStructure.list;

import java.util.Iterator;
import java.util.Objects;

public class StaticList<T> implements List<T>{

    private class iteratorList implements Iterator<T>
    {
        private int index = -1;

        @Override
        public boolean hasNext() {
            return index < size - 1;
        }

        @Override
        public T next() {
            index++;
            return list[index];
        }

        public void remove(){
            StaticList.this.remove(index);
            index = index - 1;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new iteratorList();
    }

    private T[] list;
    private int size;

    public StaticList(int capacity){
        list = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    public List<T> clear() {
        while(!isEmpty()){
            remove(0);
        }
        return this;
    }

    @Override
    public List<T> add(T value) {
        if (isFull()){
            throw new IllegalStateException("Full list (Overflow)");
        }
        list[size] = value;
        size ++;
        return this;
    }

    @Override
    public List<T> add(int index, T value) {
        if (isFull()){
            throw new IllegalStateException("Full list (Overflow)");
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
        list[size] = null;
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
            if (Objects.equals(list[i], value)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        for (int i = size - 1; i >= 0; i--){
            if (Objects.equals(list[i], value)){
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

    public boolean isFull(){
        return size == getCapacity();
    }

    public int getCapacity(){
        return list.length;
    }


    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("[");
        if (size>=1){
            sb.append(list[0]);
        }
        for (int i = 1; i < size; i++){
            sb.append(", ").append(list[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
