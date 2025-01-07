package director;

import builders.Builder;
import cars.CarType;
import components.Transmission;

public class Director {

    public void constructSportsCar(Builder builder) {
        builder.setCarType(CarType.SPORTS_CAR);
        System.out.println("Builder pentru un Sports Car");
        builder.setSeats(2);
        builder.setEngineVolume(3.0);
        builder.setTransmission(Transmission.SEMI_AUTOMATIC);
        builder.setTripComputer(true);
        builder.setGPSNavigator(true);
    }

    public void constructCityCar(Builder builder) {
        builder.setCarType(CarType.CITY_CAR);
        System.out.println("Builder pentru un City Car");
        builder.setSeats(4);
        builder.setEngineVolume(1.2);
        builder.setTransmission(Transmission.AUTOMATIC);
        builder.setTripComputer(false);
        builder.setGPSNavigator(true);
    }


    public void constructSUV(Builder builder) {
        builder.setCarType(CarType.SUV);
        System.out.println("Builder pentru un SUV");
        builder.setSeats(4);
        builder.setEngineVolume(2.5);
        builder.setTransmission(Transmission.MANUAL);
        builder.setGPSNavigator(true);
        builder.setTripComputer(false);
    }
}