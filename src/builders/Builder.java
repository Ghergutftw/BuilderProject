package builders;

import cars.CarType;
import components.Transmission;

public interface Builder {
    void setCarType(CarType type);
    void setSeats(int seats);
    void setEngineVolume(Double engineVolume);
    void setTransmission(Transmission transmission);
    void setTripComputer(Boolean hasTripComputer);
    void setGPSNavigator(Boolean hasGPSNavigator);
}