package dataStructure.stack;

public abstract class AbstractArrayStack implements Stack{
    protected int top;
    protected int[] stack;

    public AbstractArrayStack(int capacity){
        if (capacity < 1){
            throw new IllegalArgumentException("Capacity must be higher than 0!");
        }

        stack = new int[capacity];
        top = -1;
    }

    @Override
    public abstract void add(int element);

    @Override
    public int remove(){
        if (top >= 0){
            top--;
            return stack[top+1];
        }

        throw new IllegalStateException("Empty stack (Underflow)");
    }

    @Override
    public void show(){
        if (top >= 0)
            System.out.println(stack[top]);
        else
            System.out.println("No element in stack");
    }

    @Override
    public void clear(){
        top = -1;
    }

    @Override
    public int getSize(){
        return (top + 1);
    }

    @Override
    public boolean isEmpty(){
        return (top < 0);
    }

}
