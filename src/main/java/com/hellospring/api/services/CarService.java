package com.hellospring.api.services;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

  public Page<Car> findAll(Pageable pageable) {
    int page = Integer.parseInt(pageable.getPageParameter());
    int size = Integer.parseInt(pageable.getSizeParameter());
    
    PageRequest pageRequest = PageRequest.of(page, size);

    return new PageImpl<>(carRepository.findAll(), pageRequest, size);
  }

  public Car findCarById(Long id) throws FileNotFoundException {
    return carRepository.findById(id)
        .map(car -> car).orElse(null);
    // if (!car.isEmpty())
    //   return car;
    // else
    //   throw new FileNotFoundException("Veículo não encontrado");
  }

  public CarDTO update(Long id, CarDTO data) throws FileNotFoundException {
    if (!carRepository.findById(id).isEmpty()) {
      carRepository.findById(id).map(car -> {
        car.setAnoModelo(data.anoModelo());
        car.setDataFabricacao(data.dataFabricacao());
        car.setFabricante(data.fabricante().getFabricante());
        car.setModelo(data.modelo());
        car.setValor(data.valor());
        return carRepository.save(car);
      });
      return data;
    } else
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
