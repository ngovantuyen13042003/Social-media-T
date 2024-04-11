package nvt.st.T.exception;


import nvt.st.T.payload.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ExceptionResponse> handleException(Exception exception, WebRequest webRequest, HttpStatus httpStatus){
        ExceptionResponse errorDetails = ExceptionResponse.builder()
                .code(httpStatus.value())
                .message(exception.getMessage())
                .path(webRequest.getDescription(true))
                .time(LocalDateTime.now()).build();
        return new ResponseEntity<>(errorDetails, httpStatus);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        return handleException(exception, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AccountBlockedException.class})
    public ResponseEntity<ExceptionResponse> handleAccountBlockedException(AccountBlockedException exception, WebRequest request) {
        return handleException(exception, request, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler({RefreshTokenException.class})
    public ResponseEntity<ExceptionResponse> handleRefreshTokenException(RefreshTokenException exception, WebRequest request) {
        return handleException(exception, request, HttpStatus.FORBIDDEN);
    }














}
