package Kolokvium_2.WeatherApplication;

import java.util.*;

public class WeatherApplication {

    public static void main(String[] args) {
        WeatherDispatcher weatherDispatcher = new WeatherDispatcher();

        CurrentConditionsDisplay currentConditions = new CurrentConditionsDisplay(weatherDispatcher);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherDispatcher);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            weatherDispatcher.setMeasurements(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
            if(parts.length > 3) {
                int operation = Integer.parseInt(parts[3]);
                if(operation==1) {
                    weatherDispatcher.remove(forecastDisplay);
                }
                if(operation==2) {
                    weatherDispatcher.remove(currentConditions);
                }
                if(operation==3) {
                    weatherDispatcher.register(forecastDisplay);
                }
                if(operation==4) {
                    weatherDispatcher.register(currentConditions);
                }

            }
        }
    }
}
class WeatherDispatcher{
    float temperature;
    float humidity;
    float pressure;
    List<Observer> observerList;
    public WeatherDispatcher() {
        this.observerList=new LinkedList<>();
    }
    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;

        observerList
                .stream()
                .sorted(Comparator.comparing(Observer::getType))
                .forEach(observer -> observer.update(temperature, humidity, pressure));
        System.out.println();
    }
    public void register(Observer obeserver){
        if(observerList.contains(obeserver)) return;
        observerList.add(obeserver);
    }
    public void remove(Observer observer){
        observerList.remove(observer);
    }
}
interface Observer{
    public int getType();
    public void display();
    public void update(float temperature, float humidity, float pressure);
}
class CurrentConditionsDisplay implements Observer{
    float temperature;
    float humidity;
    WeatherDispatcher weatherDispatcher;
    public CurrentConditionsDisplay(WeatherDispatcher weatherDispatcher) {
        this.weatherDispatcher=weatherDispatcher;
        weatherDispatcher.register(this);
        this.temperature=0;
        this.humidity=0;
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public void display() {
        System.out.printf("Temperature: %.1fF\nHumidity: %.1f%%\n", temperature, humidity);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature=temperature;
        this.humidity=humidity;
        display();
    }
}
class ForecastDisplay implements Observer{
    float pressure;
    float previousPressure;
    WeatherDispatcher weatherDispatcher;
    public ForecastDisplay(WeatherDispatcher weatherDispatcher) {
        this.weatherDispatcher=weatherDispatcher;
        weatherDispatcher.register(this);
        this.pressure=0;
        this.previousPressure=0;
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public void display() {
        System.out.print("Forecast: ");
        if(previousPressure<pressure){
            System.out.println("Improving");
        }else if(previousPressure==pressure){
            System.out.println("Same");
        }else{
            System.out.println("Cooler");
        }
//        System.out.println();
    }
    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.previousPressure = this.pressure;
        this.pressure=pressure;
        display();
    }

}