package baseAPI.API.Sistema.DTO;

import java.time.LocalDate;

public record ClienteEdtDTO(String nome, String sobrenome, Long telefone, LocalDate dataNascimento,
                            String email) {
}
