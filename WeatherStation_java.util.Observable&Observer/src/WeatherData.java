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
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        /* ？？：既然观察者是主动“拉取”数据，
         * 这里为何还要去“推送”？？ */
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
