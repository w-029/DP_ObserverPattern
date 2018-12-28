import Observer.CurrentConditionsDisplay;
import Subject.WeatherData;

/* 这里给主题送数据，验证观察者是否收到数据并消费 */
public class WeatherStation {
    public static void main(String[] args) {

        /* 先创建一个主题对象（创建一个快递公司）*/
        WeatherData weatherData = new WeatherData();

        /* 再创建一个订阅者（让他与快递公司发生业务关系——构造函数中注册为订阅者） */
        CurrentConditionsDisplay currentDisplay =
                new CurrentConditionsDisplay(weatherData);

        /* 给主题设置更新数据，通过“通知——更新——消费”可以发现：
         * 订阅者会打印自己关心的更新数据 */
        weatherData.setMeasurementsChanged(80,65,30.4f);
        weatherData.setMeasurementsChanged(82,70,29.2f);
        weatherData.setMeasurementsChanged(78,90,29.2f);
    }
}
