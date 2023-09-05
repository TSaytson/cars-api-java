package com.hellospring.api.models;

public enum Fabricante {
  FIAT("Fiat"),
  VOLKSWAGEN("Volkswagen"),
  CHEVROLET("Chevrolet"),
  PORSCHE("Porsche"),
  AUDI("Audi"),
  JAGUAR("Jaguar"),
  BMW("BMW"),
  MERCEDES("Mercedes");

  private String fabricante;

  Fabricante(String fabricante) {
    this.fabricante = fabricante;
  }
  
  public String getFabricante() {
    return this.fabricante;
  }
}
