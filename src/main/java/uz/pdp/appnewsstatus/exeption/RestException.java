package uz.pdp.appnewsstatus.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import uz.pdp.appnewsstatus.payload.ErrorData;

@Getter
public class RestException extends RuntimeException{
    private String message;
    private HttpStatus status;

    private ErrorData error;

    public RestException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public RestException(HttpStatus status, ErrorData error) {
        this.status = status;
        this.error = error;
    }

    public static RestException maker(String message, HttpStatus status){
        return new RestException(
                message,
                status
        );
    }

    public static RestException restThrow(String userMsg) {
        return new RestException(userMsg, HttpStatus.BAD_REQUEST);
    }

    public static RestException alreadyExist(String msg){
        msg += " already exists";
        return new RestException(msg,HttpStatus.BAD_REQUEST);
    }

    public static RestException notFound(String msg){
        msg += " not found";
        return new RestException(msg,HttpStatus.NOT_FOUND);
    }

    public static RestException notValid(ErrorData error){
        return new RestException(HttpStatus.BAD_REQUEST,error);
    }

    public static RestException forbidden() {
        return new RestException(
                "Forbidden",
                HttpStatus.FORBIDDEN
        );
    }
}
