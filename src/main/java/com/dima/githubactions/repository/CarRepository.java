package com.dima.githubactions.repository;

import com.dima.githubactions.model.dao.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findById(Long id);
}
