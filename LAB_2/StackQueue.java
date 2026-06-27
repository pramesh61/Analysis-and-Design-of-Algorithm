
class Stack {

    private final int[] data;
    private int top;
    private static final int MAX = 5;

    public Stack() {
        data = new int[MAX];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == MAX - 1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack Overflow (Full)");
            return;
        }
        data[++top] = value;
        System.out.println("Pushed " + value + " to Stack");
    }

    public void pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow (Empty)");
            return;
        }
        System.out.println("Popped " + data[top--] + " from Stack");
    }
}

class Queue {

    private final int[] data;
    private int front, rear, count;
    private static final int MAX = 5;

    public Queue() {
        data = new int[MAX];
        front = 0;
        rear = -1;
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == MAX;
    }

    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Queue Overflow (Full)");
            return;
        }
        rear = (rear + 1) % MAX;
        data[rear] = value;
        count++;
        System.out.println("Enqueued " + value + " to Queue");
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow (Empty)");
            return;
        }
        System.out.println("Dequeued " + data[front] + " from Queue");
        front = (front + 1) % MAX;
        count--;
    }
}

public class StackQueue {

    public static void main(String[] args) {

        System.out.println("Stack");
        Stack stack = new Stack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.push(60);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();

        System.out.println("\nQueue Demo");
        Queue queue = new Queue();
        queue.enqueue(100);
        queue.enqueue(200);
        queue.enqueue(300);
        queue.enqueue(400);
        queue.enqueue(500);
        queue.enqueue(600);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
    }
}
