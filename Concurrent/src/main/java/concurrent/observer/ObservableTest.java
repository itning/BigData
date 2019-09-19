package concurrent.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 在 Java 中，内置了 Observable 类和 Observer 接口来实现观察者模式。
 * 使用时，Observable 作为被观察对象，通过调用 addObserver 方法来增加观察者，当状态变化时，
 * 被观察者调用 setChanged 方法来标识状态变化，然后调用 notifyObservers 方法来通知观察者。
 * Observer 接口用于实现观察者，只有一个方法 update，
 * 当状态变化时，观察者的 update 方法被调用，从而获知状态变化。
 *
 * @author itning
 */
public class ObservableTest extends Observable {
    public static void main(String[] args) {
        ObservableTest observable = new ObservableTest();
        observable.addObserver(new ObserverWatch(1));
        observable.addObserver(new ObserverWatch(2));
        observable.addObserver(new ObserverWatch(3));
        System.out.println(String.format("有%d个观察者", observable.countObservers()));
        observable.setChanged();
        observable.notifyObservers("新事件");

    }

    static class ObserverWatch implements Observer {
        private int id;

        ObserverWatch(int id) {
            this.id = id;
        }

        @Override
        public void update(Observable o, Object arg) {
            System.out.println(String.format("%d %s %s", id, o, arg));
        }
    }
}
