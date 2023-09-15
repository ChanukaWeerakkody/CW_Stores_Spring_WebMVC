package lk.ijse.spring.advisor;


import lk.ijse.spring.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : Chanuka Weerakkody
 * @since : 20.1.1
 **/

@CrossOrigin
@RestControllerAdvice
public class appWideExceptionHandler {

    /*@ExceptionHandler({Exception.class})
    public ResponseUtil exceptionHandler(Exception e){
        *//*return new ResponseEntity(new ResponseUtil(500,e.getMessage(),null), HttpStatus.INTERNAL_SERVER_ERROR);*//*
        return new ResponseUtil(500,e.getMessage(),null);
    }*/

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class})
    public ResponseUtil exceptionHandler(Exception e){
        /*return new ResponseEntity(new ResponseUtil(500,e.getMessage(),null), HttpStatus.INTERNAL_SERVER_ERROR);*/
        return new ResponseUtil(500,e.getMessage(),null);
    }

}
