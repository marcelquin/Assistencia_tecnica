package baseAPI.API.Sistema.DTO;

import baseAPI.API.Sistema.Enum.Departamento;

import java.time.LocalDate;

public record ColaboradorDTO(String nome, String sobrenome, Long cpf, Long telefone,
                             LocalDate dataNascimento, String email, String Logradouro,
                             String numero, String bairro, Long cep, String cidade,
                             String estado, Departamento departamento, String cargo,
                             String descrisao, Double salario, LocalDate dataEntrada ) {
}
