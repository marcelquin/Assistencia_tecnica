package baseAPI.API.Sistema.DTO;

import java.time.LocalDate;

public record ClienteDTO(String nome, String sobrenome, Long telefone, LocalDate dataNascimento,
                         String email, String Logradouro, String numero,
                         String bairro, Long cep, String cidade, String estado) {
}
