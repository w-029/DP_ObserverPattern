package Observer;

import Subject.Subject;

/* 这是一个订阅者（客户），
 * 他实现了Observ接口，可是通过接口内的update方法来获得数据更新的通知（收到短信：邮件已到）
 * 他还实现了DisplayElement接口，这个接口用来消费更新的数据（使用这个邮件）*/

/* 注意：标准的观察者模式是订阅者（观察者）等待主题的通知，只有主题有数据更新时，
 * 才会收到主题的通知，而不是订阅者主动查询（因为查询结果可能是：尚未更新——白查一次）*/
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    /* 这两个成员变量用来存储更新的数据 */
    private float temperatuer;
    private float humidity;
    /* 这个主题对象用来注册/注销邮件（相当于快递公司电话，可以打电话订阅邮件/取消订阅）*/
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        /* 创建订阅者对象时，一定要给他一个快递公司的电话，
         * 同时让他注册为订阅者（下单订阅邮件） */
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperatuer, float humidity, float pressure) {
        /* 接到通知，就把邮件存储起来，
         * 注意：所有订阅者都会收到相同一套数据，
         * 但每个订阅者可以仅使用自己感兴趣的数据，其他数据无视*/
        this.temperatuer = temperatuer;
        this.humidity = humidity;
        /* 同时调用方法消费 */
        display();
    }

    @Override
    public void display() {
        /* 只有订阅者收到数据更新通知后，才会对更新的数据进行显示 */
        System.out.println("Current conditions: "
                + temperatuer + "F degrees and"
                + humidity + "% humidity");
    }
}
