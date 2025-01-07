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
                this.price += 15000; // Adjusted for a more realistic base price
                break;
            case SPORTS_CAR:
                this.price += 40000; // More realistic for sports cars
                break;
            case SUV:
                this.price += 35000; // Adjusted for a typical SUV price
                break;
            case CUSTOM:
                this.price += 25000; // Adjusted for a typical van price
                break;
        }
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
        this.price += seats * 200; // Adjusted price per seat to reflect real-world pricing
    }

    @Override
    public void setEngineVolume(Double engineVolume) {
        this.engineVolume = engineVolume;
        if (engineVolume < 1.5) {
            this.price += 1500; // More reasonable price increase for smaller engines
        } else if (engineVolume < 2.0) {
            this.price += 2500; // Moderate price increase for medium engines
        } else {
            this.price += 4000; // Larger price increase for larger engines
        }
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
        if(transmission == Transmission.AUTOMATIC){
            this.price += 2500; // Slightly higher price for automatic transmission
        } else {
            this.price += 1200; // More realistic price for manual transmission
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
