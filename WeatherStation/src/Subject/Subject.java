package Subject;

import Observer.Observer;

/* 主题接口：规定了每一个主题（被观察者）都必须实现的三件事：
 * 接受订阅者报名、执行订阅者退订、通知订阅者更新观测数据。 */
public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    /* 具体要观察哪些数据，由观察者Observer接口来指定，
     * 也就是说：更新观察数据的方法是要求订阅者Observer接口提供一个统一的方法update(...)，
     * 通知订阅者时只需调用这个update方法即可*/
    public void notifyObservers();
}
