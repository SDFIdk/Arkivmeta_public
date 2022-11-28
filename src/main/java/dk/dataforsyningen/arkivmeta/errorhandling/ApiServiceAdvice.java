package dk.dataforsyningen.arkivmeta.errorhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @RestControllerAdvice combines @ControllerAdvice and @ResponseBody
 */
@RestControllerAdvice
public class WebRestControllerAdvice {
  private static final Logger logger = LoggerFactory.getLogger(WebRestControllerAdvice.class);

  @ExceptionHandler(EmptyResultDataAccessException.class)
  ResponseEntity<ErrorResponse> handleNotFoundException(EmptyResultDataAccessException ex) {
    ErrorResponse errorResponse = new ErrorResponse("Not Found");

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  ResponseEntity<ErrorResponse> handleIllegalArgumentException(Exception ex) {
    logger.debug("FEJL!", ex);

    ErrorResponse errorResponse = new ErrorResponse("Bad Request: " + ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
