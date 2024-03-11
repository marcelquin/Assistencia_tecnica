package baseAPI.API.Sistema.Exceptions;

import java.io.Serializable;

public class ClientBlockException extends RuntimeException implements Serializable {
    public ClientBlockException(String msg)
    {
        super();
    }

    public String getMessage() {
        return "Cliente Bloqueado por não Finalização de Ordem de Serviço anterior";
    }
}

