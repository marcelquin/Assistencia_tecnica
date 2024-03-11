package baseAPI.API.Sistema.DTO;

public record EnderecoDTO( String Logradouro, String numero,
                           String bairro, Long cep, String cidade,
                           String estado) {
}
