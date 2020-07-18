package io.lucifier.moviecatalogservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice//handles exception globally
public class GlobalException {
	
	@ExceptionHandler(value=MovieException.class)
	public ResponseEntity<Object> exception(MovieException exception) {
	      return new ResponseEntity<Object>("id not found", HttpStatus.NOT_FOUND);
	   } 

}
