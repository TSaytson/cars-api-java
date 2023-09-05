package com.hellospring.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/")
public class CarController {

  @Autowired
  private CarService carService;

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public Car create(@RequestBody @Valid CarDTO req) {
    return carService.create(new Car(req));
  }

  @GetMapping
  public List<Car> getCars() {
    return carService.findAll();
  }

  @GetMapping("{id}")
  public Optional<Car> getCarById(@PathVariable Long id, HttpServletResponse res) {
    try {
      return carService.findCarById(id);
    } catch (Exception e) {
      res.setStatus(HttpStatus.NOT_FOUND.value());
      return null;
    }
  }

  @PutMapping("{id}")
  public String update(@PathVariable Long id, @RequestBody @Valid CarDTO req, HttpServletResponse res) {
    try {
      carService.update(id, req);
      res.setStatus(HttpStatus.OK.value());
      return "Veículo atualizado";
    } catch (Exception e) {
      res.setStatus(HttpStatus.NOT_FOUND.value());
      return e.getMessage();
    }
  }

  @DeleteMapping("{id}")
  public String delete(@PathVariable Long id, HttpServletResponse res) {
    try {
      carService.deleteById(id);
      res.setStatus(HttpStatus.NO_CONTENT.value());
      return "Veículo apagado";
    } 
    catch (Exception e) {
      res.setStatus(HttpStatus.NOT_FOUND.value());
      return e.getMessage();
    }
  }
}
