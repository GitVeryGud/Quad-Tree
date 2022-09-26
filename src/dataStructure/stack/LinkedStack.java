package dataStructure.stack;

public class LinkedStack implements Stack {
    private static class Node{
        public Node previous;
        public int value;
    }

    private Node top;
    private int size;

    public LinkedStack(){
        size = 0;
    }

    @Override
    public void add(int element) {
        var node = new Node();
        node.previous = top;
        node.value = element;
        top = node;
        size++;
    }

    @Override
    public int remove() {
        if (isEmpty()) throw new IllegalStateException("Empty stack (Underflow)");
        int currentValue = top.value;
        top = top.previous;
        size--;
        return currentValue;
    }

    @Override
    public void show() {
        if (top != null)
            System.out.println(top.value);
        else
            System.out.println("No element in stack");
    }

    @Override
    public void clear() {
        top = null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    // Exercise 2)
    @Override
    public String toString() {
        var tempTop = top;
        var sb = new StringBuilder();
        sb.append("[");
        if (size >= 1)
        {
            sb.append(tempTop.value);
            tempTop = tempTop.previous;
        }
        sb = stackElements(sb, tempTop);
        sb.append("]");
        return sb.toString();
    }

    private StringBuilder stackElements(StringBuilder sb, Node tempTop)
    {
        if (tempTop != null) {
            sb.append(", ").append(tempTop.value);
            stackElements(sb, tempTop.previous);
        }
        return sb;
    }

}
