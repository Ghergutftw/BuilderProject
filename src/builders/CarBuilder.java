package builders;

import cars.Car;
import cars.CarType;
import components.Transmission;

public class CarBuilder implements Builder {
    private CarType type;
    private Integer seats;
    private Double engineVolume;
    private Transmission transmission;
    private Boolean hasTripComputer;
    private Boolean hasGPSNavigator;

    private double price = 0;

    public void setCarType(CarType type) {
        this.type = type;
        switch (type)
        {
            case CITY_CAR:
                this.price += 15000;
                break;
            case SPORTS_CAR:
                this.price += 40000;
                break;
            case SUV:
                this.price += 35000;
                break;
            case CUSTOM:
                this.price += 25000;
                break;
        }
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
        this.price += seats * 200;
    }

    @Override
    public void setEngineVolume(Double engineVolume) {
        this.engineVolume = engineVolume;
        if (engineVolume < 1.5) {
            this.price += 1500;
        } else if (engineVolume < 2.0) {
            this.price += 2500;
        } else {
            this.price += 4000;
        }
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
        if(transmission == Transmission.AUTOMATIC){
            this.price += 2500;
        } else {
            this.price += 1200;
        }
    }

    @Override
    public void setTripComputer(Boolean hasTripComputer) {
        this.hasTripComputer = hasTripComputer;
        if(hasTripComputer){
            this.price += 1500;
        }
    }

    @Override
    public void setGPSNavigator(Boolean hasGPSNavigator) {
        this.hasGPSNavigator = hasGPSNavigator;
        if(hasGPSNavigator){
            this.price += 2000;
        }
    }

    public Car getResult() {
        return new Car(type, seats, engineVolume, transmission, hasTripComputer, hasGPSNavigator, price);
    }
}
