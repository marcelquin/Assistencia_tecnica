package baseAPI.API.Sistema.DTO;

import baseAPI.API.Sistema.Enum.Departamento;

import java.time.LocalDate;

public record CargoDTO(Departamento departamento, String nome, String descrisao, Double salario) {
}
