package service;

import model.Car;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RentalService {

    private final List<Car> cars;

    public RentalService() {
        this.cars = new ArrayList<>();
    }

  
    public void addCar(Car car) {
        if (car != null) {
            cars.add(car);
        }
    }

    
    public List<Car> getAvailableCars() {
        List<Car> availableCars = new ArrayList<>();

        for (Car car : cars) {
            if (car != null && car.isAvailable()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    
    public List<Car> getAllCars() {
        return Collections.unmodifiableList(cars);
    }
}
package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Car;
import service.RentalService;

public class RentalServiceTest {

    @Test
    void integration_addCarAndGetAvailableCars() {
        RentalService service = new RentalService();
        Car car = new Car("AA-111", "BMW", true);

        service.addCar(car);

        assertEquals(1, service.getAvailableCars().size());
        assertEquals(1, service.getAllCars().size());
    }
}

