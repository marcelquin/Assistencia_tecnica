package baseAPI.API.Sistema.Exceptions;

import java.io.Serializable;

public class ClientBlockException extends RuntimeException implements Serializable {
    public ClientBlockException() {
        super("Cliente Bloqueado");
    }

    public ClientBlockException(String message) {
        super(message);
    }
}

