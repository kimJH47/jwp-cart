package cart.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cart.dto.response.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<String>> handle(MethodArgumentNotValidException e) {
		FieldError fieldError = e.getFieldError();
		assert fieldError != null;
		return ResponseEntity.badRequest()
			.body(Response.createFailedResponse(fieldError.getDefaultMessage(), "잘못된 요청입니다."));
	}
}
