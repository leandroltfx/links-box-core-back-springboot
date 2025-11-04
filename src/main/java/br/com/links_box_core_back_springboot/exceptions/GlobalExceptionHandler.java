package br.com.links_box_core_back_springboot.exceptions;

import br.com.links_box_core_back_springboot.dtos.ErrorFieldDTO;
import br.com.links_box_core_back_springboot.dtos.ResponseErrorDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.net.ConnectException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorDTO> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException methodArgumentNotValidException
    ) {

        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO("Dados inválidos.");

        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(err -> {
            ErrorFieldDTO errorFieldDto = new ErrorFieldDTO(err.getField(), err.getDefaultMessage());
            responseErrorDTO.getDetails().add(errorFieldDto);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseErrorDTO);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<ResponseErrorDTO> userFoundExceptionHandler() {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO("Este e-mail e/ou nome de usuário já está em uso.");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseErrorDTO);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ResponseErrorDTO> connectExceptionHandler() {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO("Ocorreu um erro interno, tente novamente.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseErrorDTO);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseErrorDTO> authenticationExceptionHandler() {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO("E-mail e/ou senha inválidos.");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseErrorDTO);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseErrorDTO> userNotFoundException() {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO("E-mail e/ou senha inválidos.");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseErrorDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseErrorDTO> entityNotFoundException() {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO("Ocorreu um erro interno, tente novamente.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseErrorDTO);
    }

}

