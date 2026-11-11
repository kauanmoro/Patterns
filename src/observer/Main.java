package observer;

import java.util.ArrayList;
import java.util.List;

interface TemperatureObserver {
    void onTemperatureChanged(double temp);
}


class WeatherStation {
    private List<TemperatureObserver> observers = new ArrayList<>();
    private double temperature;

    public void subscribe(TemperatureObserver o) {
        observers.add(o);
    }

    public void unsubscribe(TemperatureObserver o) {
        observers.remove(o);
    }

    public void setTemperature(double temp) {
        this.temperature = temp;
        notifyObservers();
    }

    private void notifyObservers() {
        for (TemperatureObserver o : observers) {
            o.onTemperatureChanged(temperature);
        }
    }
}


class ConsoleDisplay implements TemperatureObserver {
    public void onTemperatureChanged(double temp) {
        System.out.println("Display: temperatura = " + temp);
    }
}

class FanController implements TemperatureObserver {
    public void onTemperatureChanged(double temp) {
        if (temp > 28) {
            System.out.println("Ventilador: LIGAR");
        } else {
            System.out.println("Ventilador: DESLIGAR");
        }
    }
}


public class Main {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        ConsoleDisplay display = new ConsoleDisplay();
        FanController fan = new FanController();

        station.subscribe(display);
        station.subscribe(fan);

        station.setTemperature(26);
        station.setTemperature(30);

        station.unsubscribe(display);
        station.setTemperature(27);
    }
}