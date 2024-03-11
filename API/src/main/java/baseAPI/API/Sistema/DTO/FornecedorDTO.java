package baseAPI.API.Sistema.DTO;

import java.time.LocalDate;

public record FornecedorDTO(String nome, String razaoSocial,Long telefone,
                            Long cnpj, String email, String Logradouro, String numero,
                            String bairro, Long cep, String cidade, String estado) {
}
