package com.hellospring.api.dto;

import java.util.Date;

import com.hellospring.api.models.Fabricante;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

public record CarDTO(

    @NotBlank
    String modelo,

    @NotNull
    Fabricante fabricante,

    @Past
    Date dataFabricacao,

    @Positive
    @Min(value = 20000, message = "O valor do veículo deve ser superior a 20 mil reais")
    int valor,

    @Positive
    @Min(value = 1990, message = "O ano mínimo dos veículos é de 1990")
    @Max(value = 2023, message = "O ano máximo para os veículos é 2023")
    int anoModelo) {

}
