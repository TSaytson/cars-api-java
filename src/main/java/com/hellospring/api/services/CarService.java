package com.hellospring.api.services;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellospring.api.dto.CarDTO;
import com.hellospring.api.models.Car;
import com.hellospring.api.repositories.CarRepository;

@Service
public class CarService {

  @Autowired
  private CarRepository carRepository;

  public Car create(Car data) {
    return carRepository.save(data);
  }

  public List<Car> findAll() {
    return carRepository.findAll();
  }

  public Optional<Car> findCarById(Long id) throws FileNotFoundException {
    Optional<Car> car = carRepository.findById(id);
    if (car.isEmpty())
      throw new FileNotFoundException("Veículo não encontrado");
    else
      return car;
  }

  public void update(Long id, CarDTO data) throws FileNotFoundException {
    if (!carRepository.findById(id).isEmpty())
      carRepository.findById(id).map(car -> {
        car.setAnoModelo(data.anoModelo());
        car.setDataFabricacao(data.dataFabricacao());
        car.setFabricante(data.fabricante().getFabricante());
        car.setModelo(data.modelo());
        car.setValor(data.valor());
        return carRepository.save(car);
      });
      else 
        throw new FileNotFoundException("Veículo não existente");
  }

  public boolean deleteById(Long id) throws FileNotFoundException {
    if (!carRepository.findById(id).isEmpty()) {
      carRepository.deleteById(id);
      return true;
    } else {
      throw new FileNotFoundException("Veículo não encontrado");
    }
  }
}
