package observer;

public interface IObserver {
    public void send(boolean isFalseAlarm);

    public void handle();
}
