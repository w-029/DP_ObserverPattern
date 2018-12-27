package Subject;

import Observer.Observer;
import java.util.ArrayList;

/* OO观察者模式——定义了对象之间的一对多依赖，
 * 当一个对象改变状态时，他的所有依赖者都会收到通知并自动更新。
 * 被观察者称为主题，是主动发出通知的“一”，
 * 它相当于一个快递公司，当收到工厂来件（更新主题的成员数据）后
 *（这里省略了实际生活这的淘宝卖家），
 * 会按照订货单依次通知客户收件（调用客户的更新数据功能update）*/

/* 这是一个快递公司，他有三个任务：
 * 一、接收/取消客户订阅（相当于接受/取消网购下单）；
 * 二、接受数据更新（设置成员变量，相当于收到邮件）；
 * 三、通知客户取件（调用客户的update方法，相当于派件）*/
public class WeatherData implements Subject {

    /* 这是订阅者花名册（相当于客户名单记录本）*/
    private ArrayList observers;
    /* 这是需要被观察的数据（相当于客户订阅的邮件） */
    private float temperature;
    private float humidity;
    private float pressure;


    public WeatherData() {
        /* 构造器里创建订阅者花名册 */
        observers = new ArrayList();
    }

    @Override
    public void registerObserver(Observer o) {
        /* 将注册的订阅者录入花名册，相当于记录网购下单者 */
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        /* 如果申请注销的订阅者在花名册内，测注销他，
         * 相当于取消网购 */
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override   /* 这是一个快递员 */
    public void notifyObservers() {
        /* 他负责在需要的时候按单通知每一个订阅者接收邮件，
         * 因为每一个订阅者都实现了统一的Observer接口，
         * 所以他们都会有统一的方法update来接收邮件（更新的数据）
         *（就像每一个小区都会有“速递易”收件柜一样）*/

        /* OO设计原则4——为了交互对象之间的松耦合设计而努力。
         * 即：我不管你是谁，只要实现了update的方法，就可以收到我的通知。
         * 这就是主题（快递公司）与订阅者（客户）的松耦合（没有太多关联条件限制）*/
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer)observers.get(i);
            observer.update(temperature, humidity, pressure);
        }
    }

    /* 这是快递公司调度员 */
    public void measurementsChanged() {
        /* 他会在公司收到邮件（收到更新的数据）时，
         * 调用快递员去给通知每一个订阅者查收邮件*/
        notifyObservers();
    }

    /* 这是快递公司收货部，他有两项职责 */
    public void setMeasurementsChanged(float temperature, float humidity, float pressure) {

        /* 职责一：负责接收邮件，并将其存储到对应的仓库 */
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        /* 职责二：调用公司调度员，让他安排快递员去送快递（通知订阅者取件）*/
        measurementsChanged();
    }

    /* 注：快递公司可以根据业务需要，设置其他成员方法 */

}
