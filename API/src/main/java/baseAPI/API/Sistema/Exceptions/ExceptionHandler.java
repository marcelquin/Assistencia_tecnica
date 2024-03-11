package baseAPI.API.Sistema.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ClientBlockException.class)
    public ResponseEntity<ErrorDTO> ClientBlockException(ClientBlockException e, HttpServletRequest request)
    {
        ErrorDTO dto = new ErrorDTO();
        dto.setTimeStamp(Instant.now());
        dto.setStatus(HttpStatus.NOT_FOUND.value());
        dto.setError("Cliente Bloqueado");
        dto.setMessage(e.getMessage());
        dto.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }
}
