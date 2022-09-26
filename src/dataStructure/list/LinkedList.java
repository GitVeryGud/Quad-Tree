package dataStructure.list;


import java.util.Iterator;
import java.util.Objects;

public class LinkedList<T> implements List<T>{
    private class iteratorList implements Iterator<T>
    {
        private int index = -1;
        Node<T> current = bottom;

        @Override
        public boolean hasNext() {
            return index < size - 1;
        }

        @Override
        public T next() {
            index++;
            T value = current.value;
            current = current.next;
            return value;
        }

        public void remove(){
            LinkedList.this.remove(index);
            index = index - 1;
        }
    }

    @Override
    public Iterator<T> iterator() {return new iteratorList();}

    private static class Node<T>{
        Node<T> previous;
        T value;
        Node<T> next;
    }

    private int size;
    private Node<T> top;
    private Node<T> bottom;

    public LinkedList(){
        size = 0;
    }

    @Override
    public List<T> clear() {
        bottom = null;
        top = null;
        size = 0;
        return this;
    }

    @Override
    public List<T> add(T value) {
        var node = new Node<T>();
        if (top != null)
        {
            node.value = value;
            node.previous = top;
            top.next = node;
            top = node;
        }

        else
        {
            node.value = value;
            top = node;
            bottom = node;
        }

        size++;
        return this;
    }

    @Override
    public List<T> add(int index, T value) {
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("Invalid Index: " + index);
        }

        var node = new Node<T>();

        // Goes from the left if index is on the lower half
        if (index + 1 <= size / 2)
        {

            var nodeToChange = bottom;
            for (int i = 0; i < index; i++){
                nodeToChange = nodeToChange.next;
            }
            node.value = value;
            node.next = nodeToChange;
            node.previous = nodeToChange.previous;
            nodeToChange.previous = node;

            if (node.previous == null)
            {
                bottom = node;
            }

            else {node.previous.next = node;}
        }

        // Goes from the right if the index is on the upper half
        else{
            var nodeToChange = top;
            for (int i = 0; i < size - index; i++){
                nodeToChange = nodeToChange.previous;
            }
            node.value = value;
            node.previous = nodeToChange;
            node.next = nodeToChange.next;
            nodeToChange.next = node;

            if (node.next == null)
            {
                top = node;
            }

            else {node.next.previous = node;}
        }
        size++;
        return this;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Invalid Index: " + index);
        }

        T value = null;

        if (onlyOneElement())
        {
            value = bottom.value;
            clear();
            return value;
        }

        // Goes from the left if index is on the lower half
        if (index + 1 <= size / 2)
        {
            var nodeToChange = bottom;
            for (int i = 0; i < index; i++){
                nodeToChange = nodeToChange.next;
            }

            value = nodeToChange.value;

            if (nodeToChange != bottom)
            {
                nodeToChange.previous.next = nodeToChange.next;
                nodeToChange.next.previous = nodeToChange.previous;
            }

            else
            {
                nodeToChange.next.previous = nodeToChange.previous;
                bottom = nodeToChange.next;
            }
        }

        // Goes from the right if the index is on the upper half
        else{
            var nodeToChange = top;
            for (int i = 0; i < size - index - 1; i++){
                nodeToChange = nodeToChange.previous;
            }

            value = nodeToChange.value;

            if (nodeToChange != top)
            {
                nodeToChange.previous.next = nodeToChange.next;
                nodeToChange.next.previous = nodeToChange.previous;
            }

            else
            {
                nodeToChange.previous.next = nodeToChange.next;
                top = nodeToChange.previous;
            }
        }

        size--;
        return value;
    }

    @Override
    public T get(int index) {

        T value = null;

        // Goes from the left if index is on the lower half
        if (index <= size / 2)
        {
            var nodeToChange = bottom;
            for (int i = 0; i < index; i++){
                nodeToChange = nodeToChange.next;
            }
            value = nodeToChange.value;
        }

        // Goes from the right if the index is on the upper half
        else{
            var nodeToChange = top;
            for (int i = 0; i < size - index - 1; i++){
                nodeToChange = nodeToChange.previous;
            }
            value = nodeToChange.value;
        }

        return value;
    }

    @Override
    public List<T> set(int index, T value) {
        // Goes from the left if index is on the lower half
        if (index <= size / 2)
        {
            var nodeToChange = bottom;
            for (int i = 0; i < index; i++){
                nodeToChange = nodeToChange.next;
            }
            nodeToChange.value = value;
        }

        // Goes from the right if the index is on the upper half
        else{
            var nodeToChange = top;
            for (int i = 0; i < size - index - 1; i++){
                nodeToChange = nodeToChange.previous;
            }
            nodeToChange.value = value;
        }

        return this;
    }

    @Override
    public int indexOf(T value) {
        var currentNode = bottom;
        for (int i = 0; i < size; i++){
            if (Objects.equals(currentNode.value, value)){
                return i;
            }
            currentNode = currentNode.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        var currentNode = top;
        for (int i = 0; i < size; i++){
            if (Objects.equals(currentNode.value, value)){
                return i;
            }
            currentNode = currentNode.previous;
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

    @Override
    public String toString() {
        var sb = new StringBuilder();
        var currentNode = bottom;
        sb.append("[");
        if (size>=1){
            sb.append(currentNode.value);
        }
        for (int i = 1; i < size; i++){
            currentNode = currentNode.next;
            sb.append(", ").append(currentNode.value);
        }
        sb.append("]");
        return sb.toString();
    }

    private boolean onlyOneElement()
    {
        return size == 1;
    }
}
