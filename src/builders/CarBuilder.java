package builders;

import cars.Car;
import cars.CarType;
import components.*;

public class CarBuilder implements Builder {
    private CarType type;
    private Integer seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    private double price = 0;

    public void setCarType(CarType type) {
        this.type = type;
        switch (type)
        {
            case CITY_CAR:
                this.price += 10000;
                break;
            case SPORTS_CAR:
                this.price += 50000;
                break;
            case SUV:
                this.price += 30000;
                break;
            case VAN:
                this.price += 70000;
                break;
        }

    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
        this.price += seats * 100;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
        if(engine.getVolume() < 1.5){
            this.price += 1000;
        } else if (engine.getVolume() < 2.0){
            this.price += 2000;
        } else {
            this.price += 3000;
        }
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
        if(transmission == Transmission.AUTOMATIC){
            this.price += 2000;
        } else {
            this.price += 1000;
        }
    }

    @Override
    public void setTripComputer(TripComputer tripComputer) {
        this.tripComputer = tripComputer;
        this.price += 1000;
    }

    @Override
    public void setGPSNavigator(GPSNavigator gpsNavigator) {
        this.gpsNavigator = gpsNavigator;
        this.price += 1500;
    }

    public Car getResult() {
        return new Car(type, seats, engine, transmission, tripComputer, gpsNavigator,price);
    }
}
