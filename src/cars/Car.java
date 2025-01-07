package cars;

import components.Transmission;

public class Car {
    private final CarType carType;
    private final Integer seats;
    private final Double engineVolume;
    private final Transmission transmission;
    private final Boolean hasTripComputer;
    private final Boolean hasGPSNavigator;
    private double price;

    public Car(CarType carType, Integer seats, Double engineVolume, Transmission transmission, Boolean hasTripComputer, Boolean hasGPSNavigator, double price) {
        this.carType = carType;
        this.seats = seats;
        this.engineVolume = engineVolume;
        this.transmission = transmission;
        this.hasTripComputer = hasTripComputer;
        this.hasGPSNavigator = hasGPSNavigator;
        this.price = price;
    }

    public CarType getCarType() {
        return carType;
    }

    public Integer getSeats() {
        return seats;
    }

    public Double getEngineVolume() {
        return engineVolume;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public Boolean getTripComputer() {
        return hasTripComputer;
    }

    public Boolean getGpsNavigator() {
        return hasGPSNavigator;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carType=" + carType +
                ", seats=" + seats +
                ", engineVolume=" + engineVolume +
                ", transmission=" + transmission +
                ", hasTripComputer=" + hasTripComputer +
                ", hasGPSNavigator=" + hasGPSNavigator +
                ", price=" + price +
                '}';
    }
}
