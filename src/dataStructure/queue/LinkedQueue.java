package dataStructure.queue;

public class LinkedQueue implements Queue{
    private static class Node{
        public Node next;
        public int value;
    }

    private Node top;
    private Node bottom;
    private int size;

    public LinkedQueue(){
        size = 0;
    }

    @Override
    public void add(int element) {
        var node = new Node();
        node.value = element;
        if (top != null) top.next = node;
        else {bottom = node;}
        top = node;
        size++;
    }

    @Override
    public int remove() {
        if (isEmpty()) throw new IllegalStateException("Empty stack (Underflow)");
        int currentValue = bottom.value;
        bottom = bottom.next;
        if (bottom == null) {top = null;}
        size--;
        return currentValue;
    }

    @Override
    public void show() {
        if (bottom != null)
            System.out.println(bottom.value);
        else
            System.out.println("No element in stack");
    }

    @Override
    public void clear() {
        top = null; bottom = null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {return size <= 0;}
}

