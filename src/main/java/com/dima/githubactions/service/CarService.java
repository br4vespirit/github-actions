package com.dima.githubactions.service;

import com.dima.githubactions.model.dao.Car;
import com.dima.githubactions.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car save(Car car) {
        carRepository.save(car);
        return car;
    }

    public void delete(Long id) {
        Car car = carRepository.findById(id).orElseThrow();
        carRepository.delete(car);
    }

    public Car update(Long id, Car car) {
        Car car1 = carRepository.findById(id).orElseThrow();
        car1.setName(car.getName());
        car1.setWeight(car.getWeight());
        carRepository.save(car1);
        return car1;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(Long id) {
        return carRepository.findById(id).orElse(null);
    }
}
