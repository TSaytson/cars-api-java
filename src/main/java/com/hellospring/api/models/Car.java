package com.hellospring.api.models;

import java.util.Date;

import com.hellospring.api.dto.CarDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Car {

  public Car(
    CarDTO req
  ) {
    this.anoModelo = req.anoModelo();
    this.dataFabricacao = req.dataFabricacao();
    this.fabricante = req.fabricante().getFabricante();
    this.modelo = req.modelo();
    this.valor = req.valor();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 20, nullable = false)
  private String modelo;

  @Column(length = 15, nullable = false)
  private String fabricante;

  @Column(length = 10, nullable = false)
  private Date dataFabricacao;

  @Column(nullable = false)
  private int valor;

  @Column(nullable = false)
  private int anoModelo;
}
