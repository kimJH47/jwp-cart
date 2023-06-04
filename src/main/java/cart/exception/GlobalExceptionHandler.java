package cart.exception;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cart.dto.response.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<HashMap<String, String>>> handle(MethodArgumentNotValidException e) {
		FieldError fieldError = e.getFieldError();
		assert fieldError != null;
		HashMap<String, String> hashMap = new HashMap<>();
		for (FieldError error : e.getFieldErrors()) {
			hashMap.put(error.getField(), error.getDefaultMessage());
		}
		return ResponseEntity.badRequest()
			.body(Response.createFailedResponse(hashMap, "잘못된 요청입니다."));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response<String>> handle(Exception e) {
		return ResponseEntity.badRequest()
			.body(Response.createFailedResponse(e.getMessage(), "알수 없는 문제가 발생 했습니다"));
	}

	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<Response<String>> handle(ItemNotFoundException e) {
		return ResponseEntity.badRequest()
			.body(Response.createFailedResponse(e.getMessage(), "잘못된 요청입니다"));
	}

	@ExceptionHandler(UnAuthenticationException.class)
	public ResponseEntity<Response<String>> handle(UnAuthenticationException e) {
		return ResponseEntity.status(401)
			.body(Response.createFailedResponse(e.getMessage(), "잘못된 요청입니다"));
	}

}
