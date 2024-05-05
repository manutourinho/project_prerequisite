package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.Arrays;
import java.util.List;

@Service
public class CarService {

    private final List<Car> carsList;

    public CarService() {
        carsList = Arrays.asList(
                new Car("Silver", "", 1997),
                new Car("Silver", "", 1997),
                new Car("Silver", "", 1997),
                new Car("Silver", "", 1997),
                new Car("Silver", "", 1997)
        );
    }

    public List<Car> getCars(int count) {
        return (count >= carsList.size()) ? carsList : carsList.subList(0, count);
    }
}
