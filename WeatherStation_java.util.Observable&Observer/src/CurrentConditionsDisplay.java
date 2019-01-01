import java.util.Observable;
import java.util.Observer;

public class CurrentConditionsDisplay implements Observer, DisplayElement {

    Observable observable;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(Observable observable) {

        /* 这个被观察者，即经典观察者模式中的“主题”*/
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

        /* o是一个接口类型，不是实现——设计原则2 */
        if (o instanceof WeatherData) {
            /* 针对“超类”编程时，需要将超类向下转换为具体的子类实现，
             * 然后才能调用其有实际内容的成员方法 */
            WeatherData weatherData = (WeatherData)o;

            /* 注意：这里不是被动地等待主题来“推送”通知，
             * 而是主动查询，“拉取”自己想要的更新数据 */
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();

            /* 获取数据进行消费 */
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("Current conditions: "
            + temperature + "F degrees and "
            + humidity + "% humidity");
    }
}
