/* 超类Observable代为管理注册/注销观察者 */
import java.util.Observable;


public class WeatherData extends Observable {

    private float temperature;
    private float humidity;
    private float pressure;

    /* 有了超类，主题的构造函数就
     * 不再需要为了记录观察者而建立数据结构了 */
    public WeatherData() {}

    public void measurementsChanged() {

        /* 调用notifyObservers()之前，要先调用setChanged()
         * 表示指示状态已经改变 */
        setChanged();

        /* 没有参数的notifyObservers()会调用Observer接口的update(...),
         * Observer接口的实现者需要在update(...)中使用getter()方法来
         * 主动拉取更新数据 */
        notifyObservers();

        /* 另有notifyObservers(Object arg)会像经典观察者模式那样
         * 去推送给观察者更新的数据 */
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    /* 这一组方法，供观察者主动“拉取”数据用 */
    public float getTemperature() {
        return temperature;
    }
    public float getHumidity() {
        return humidity;
    }
    public float getPressure() {
        return pressure;
    }
}
