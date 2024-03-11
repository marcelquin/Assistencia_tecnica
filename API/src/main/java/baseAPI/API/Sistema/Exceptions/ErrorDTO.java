package baseAPI.API.Sistema.Exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class ErrorDTO {

    private Instant timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
