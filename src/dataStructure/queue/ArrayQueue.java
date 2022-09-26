package dataStructure.queue;

import java.util.Arrays;

public class ArrayQueue implements Queue{
    private int top;
    private int[] queue;
    private int bottom;
    private double growthRate;

    public ArrayQueue(){
        queue = new int[10];
        top = -1;
        bottom = 0;
        growthRate = 1.5;
    }

    public int circleAdd(int counter){
        return counter = (counter + 1) % getCapacity();
    }

    public int circleSub(int counter){
        return counter > 0 ? counter - 1 : getCapacity() - 1;
    }


    @Override
    public void add(int element) {
        if (isFull()){
            int newCapacity = (int)(getCapacity() * growthRate);
            newCapacity = Math.max(getCapacity() + 1, newCapacity);
            var newQueue = new int[newCapacity];
            for (int i = 0; i < getCapacity(); i++){
                newQueue[i] = remove();
            }
            top = getCapacity() - 1;
            queue = newQueue;
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

    private int getCapacity(){
        return queue.length;
    }

    private boolean onlyOneElement(){
        return bottom == top;
    }

    public void trimToSize(){
        queue = Arrays.copyOf(queue, getSize());
    }
}
