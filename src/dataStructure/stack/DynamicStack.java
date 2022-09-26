package dataStructure.stack;

import java.util.Arrays;

public class DynamicStack extends AbstractArrayStack{
    private double growthRate;

    /**
     * Dynamic stack makes an array that increases automatically whenever necessary
     * @param capacity The amount of pre-allocated slot for the array elements
     * @param growthRate The rate at which the array increases in size when necessary.
     *                   To increase the array by 30% use 0.3.
     *                   To increment the array 1 by 1 use 0 as the growthRate.
     */

    public DynamicStack(int capacity, double growthRate){
        super(capacity);
        if (growthRate < 0){
            throw new IllegalArgumentException("Capacity must be equal or higher to zero!");
        }

        this.growthRate = 1 + growthRate;
    }

    public DynamicStack (int capacity){
        this (capacity, 0.5);
    }

    public DynamicStack (){
        this (10, 0.5);
    }

    public void add(int element){
        if (top + 1 >= stack.length){
            int newCapacity = (int)(stack.length * growthRate);
            newCapacity = Math.max(stack.length + 1, newCapacity);
            var newStack = new int[newCapacity];
            for (int i = 0; i < stack.length; i++){
                newStack[i] = stack[i];
            }
            stack = newStack;
        }
        top++;
        stack[top] = element;
    }

    public void trimToSize(){
        stack = Arrays.copyOf(stack, getSize());
    }

    public void increment(double increment){
        if (growthRate < 0){
            throw new IllegalArgumentException("Capacity must be equal or higher than zero!");
        }
        this.growthRate = 1 + increment;
    }
}
