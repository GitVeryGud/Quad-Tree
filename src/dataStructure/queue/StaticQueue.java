package dataStructure.queue;

public class StaticQueue implements Queue{
    private int top;
    private int[] queue;
    private int bottom;

    public StaticQueue(int capacity){
        if (capacity < 1){
            throw new IllegalArgumentException("Capacity must be higher than 0!");
        }

        queue = new int[capacity];
        top = -1;
        bottom = 0;
    }

    public int circleAdd(int counter){
        return counter = (counter + 1) % queue.length;
    }

    public int circleSub(int counter){
        return counter > 0 ? counter - 1 : queue.length - 1;
    }


    @Override
    public void add(int element) {
        if (isFull()){
            throw new IllegalStateException("Full queue (Overflow)");
        }

        top = circleAdd(top);
        queue[top] = element;
    }

    @Override
    public int remove() {
        if (isEmpty()){
            throw new IllegalStateException("Empty queue (Underflow)");
        }
        int value = queue[bottom];

        if (onlyOneElement()) {
            clear();
        }
        else {
            bottom = circleAdd(bottom);
        }

        return value;
    }

    @Override
    public void show() {
        if (!isEmpty())
            System.out.println(queue[bottom]);
        else
            System.out.println("No element in queue");
    }

    @Override
    public void clear() {
        top = -1;
        bottom = 0;
    }

    @Override
    public int getSize() {
        if (isEmpty()){
            return 0;
        }
        int size = top - bottom + 1;
        return top >= bottom ? size : getCapacity() + size;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull(){
        return !isEmpty() && circleAdd(top) == bottom;
    }

    public int getCapacity(){
        return queue.length;
    }

    private boolean onlyOneElement(){
        return bottom == top;
    }
}

