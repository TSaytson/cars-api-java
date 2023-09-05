package com.hellospring.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hellospring.api.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
  
}
