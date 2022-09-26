package dataStructure.stack;

public class StaticStack extends AbstractArrayStack {
    public StaticStack(int capacity){
        super(capacity);
    }

    public void add(int element){
        if (top + 1 >= stack.length){
            throw new IllegalStateException("Full stack (Overflow)");
        }
        top++;
        stack[top] = element;
    }

    public int getCapacity(){
        return stack.length;
    }
}
