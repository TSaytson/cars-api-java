package com.hellospring.api.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hellospring.api.dto.CarDTO;
import com.hellospring.api.models.Car;
import com.hellospring.api.services.CarService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class CarController {

  @Autowired
  private CarService carService;

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Car create(@RequestBody @Valid CarDTO req) {
    return carService.create(new Car(req));
  }

  @GetMapping
  public Page<Car> getCars(@PageableDefault(page = 0, size = 10) Pageable pageable) {
    return carService.findAll(pageable);
  }

  @GetMapping("{id}")
  public ResponseEntity<Car> getCarById(@PathVariable Long id) throws IOException {
    try {
      Car car = carService.findCarById(id);
      return ResponseEntity.ok().body(car);
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("{id}")
  public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid CarDTO req) {
    try {
      carService.update(id, req);
      return ResponseEntity.ok().body("Veículo atualizado");
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> delete(@PathVariable Long id, HttpServletResponse res) {
    try {
      carService.deleteById(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }
}
