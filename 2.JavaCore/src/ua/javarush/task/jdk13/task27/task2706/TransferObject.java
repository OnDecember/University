package ua.javarush.task.jdk13.task27.task2706;

public class TransferObject {
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    public synchronized int get() {
        while (!isValuePresent) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Got: " + value);
        isValuePresent = false;
        notifyAll();
        return value;
    }

    public synchronized void put(int value) {
        while (isValuePresent) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        this.value = value;
        System.out.println("Put: " + value);
        isValuePresent = true;
        notifyAll();
    }
}
