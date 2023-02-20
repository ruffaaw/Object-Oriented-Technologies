package strategy;

import event.IAlarm;
import observer.Observer;

public interface IStrategy {
    public void execute(Observer observer, IAlarm alarm);
}
