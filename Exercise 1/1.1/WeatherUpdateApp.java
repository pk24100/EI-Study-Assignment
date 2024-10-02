
import java.util.*;
// Subject (Observable)
interface WeatherData {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
    double getTemperature(); // Added this method
    double getHumidity(); // Added this method
}

// Observer
interface Observer {
    void update(WeatherData weatherData);
}

// Concrete Subject
class WeatherStation implements WeatherData {
    private List<Observer> observers;
    private double temperature;
    private double humidity;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    public void setMeasurements(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public double getTemperature() { // Implemented this method
        return temperature;
    }

    @Override
    public double getHumidity() { // Implemented this method
        return humidity;
    }
}

// Concrete Observer
class WeatherApp implements Observer {
    private String name;

    public WeatherApp(String name) {
        this.name = name;
    }

    @Override
    public void update(WeatherData weatherData) {
        System.out.println(name + " received update: " + weatherData.getTemperature() + "°C, " + weatherData.getHumidity() + "%");
    }
}

public class WeatherUpdateApp {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        WeatherApp app1 = new WeatherApp("App 1");
        WeatherApp app2 = new WeatherApp("App 2");

        weatherStation.registerObserver(app1);
        weatherStation.registerObserver(app2);

        weatherStation.setMeasurements(25.0, 60.0);
        weatherStation.setMeasurements(28.0, 70.0);
    }
}