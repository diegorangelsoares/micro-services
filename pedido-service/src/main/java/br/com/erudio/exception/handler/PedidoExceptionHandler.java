package br.com.erudio.exception.handler;

import br.com.erudio.exception.PedidoNotFoundException;
import br.com.erudio.exception.model.ApiError;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class PedidoExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        return buildResponseEntity(status,
                "Malformed JSON body and/or field error.",
                ((ServletWebRequest) request).getRequest().getRequestURI(),
                Collections.singletonList(exception.getLocalizedMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        var errors = new ArrayList<String>();

        exception.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errors.add(
                        String.format("Field %s %s", fieldError.getField(), fieldError.getDefaultMessage())));

        exception.getBindingResult().getGlobalErrors()
                .forEach(globaError -> errors.add(
                        String.format("Object %s %s", globaError.getObjectName(), globaError.getDefaultMessage())));

        return buildResponseEntity(status, "Informed argument(s) validation error(s).",
                ((ServletWebRequest) request).getRequest().getRequestURI(),
                errors);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleThrowable(Throwable e, HttpServletRequest request) {

        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                request.getRequestURI(),
                Collections.singletonList(e.getLocalizedMessage()));
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception,
                                                                       HttpServletRequest request) {

        return buildResponseEntity(HttpStatus.NOT_FOUND,
                "Resource not found",
                request.getRequestURI(),
                Collections.singletonList(exception.getLocalizedMessage()));
    }

    @ExceptionHandler({PedidoNotFoundException.class})
    public ResponseEntity<Object> handleClienteNotFoundException(PedidoNotFoundException exception,
                                                                 HttpServletRequest request) {

        return buildResponseEntity(HttpStatus.NOT_FOUND,
                "Resource not found",
                request.getRequestURI(),
                Collections.singletonList(exception.getLocalizedMessage()));
    }

//    @ExceptionHandler({InvalidCardKeyException.class})
//    public ResponseEntity<Object> handleInvalidCardKeyException(InvalidCardKeyException exception,
//                                                                HttpServletRequest request) {
//
//        return buildResponseEntity(HttpStatus.BAD_REQUEST,
//                "Resource not found",
//                request.getRequestURI(),
//                Collections.singletonList(exception.getLocalizedMessage()));
//    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, String message,
                                                       String path, List<String> errors) {
        var apiError = ApiError.builder()
                .code(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .path(path)
                .errors(errors)
                .message(message).build();
        return ResponseEntity.status(httpStatus).body(apiError);
    }

}
