package com.dima.githubactions.controller;

import com.dima.githubactions.model.dao.Car;
import com.dima.githubactions.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> findAll() {
        return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable Long id) {
        return new ResponseEntity<>(carService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car car) {
        car = carService.save(car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Car> update(@PathVariable Long id, @RequestBody Car car) {
        car = carService.update(id, car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
