package dev.mrkevr.quizapp.api.controller.advice;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.mrkevr.quizapp.api.exception.ResourceNotFoundException;

@ControllerAdvice
@RestController
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);
		return new ResponseEntity<>(body, headers, status);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseEntity<Object> handleConstraintViolation(
			ConstraintViolationException ex, 
			WebRequest request) {
		
		// fetch the path from WebRequest
		String path = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
		
		 //Get all errors
        List<String> errors = ex.getConstraintViolations().stream()
        		.map(x -> x.getMessage()).collect(Collectors.toList());
        
		Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", 422);
		body.put("errors", errors);
		body.put("path", path);
		
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handleProductNotFound(ResourceNotFoundException ex, WebRequest request) {
		
		// fetch the path from WebRequest
		String path = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
		
		Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", 404);
		body.put("error", ex.getMessage());
		body.put("path", path);
		
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}


}
