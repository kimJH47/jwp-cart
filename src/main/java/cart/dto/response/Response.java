package cart.dto.response;

public class Response<T> {
	private final T entity;
	private final String message;

	public Response(T entity, String message) {
		this.entity = entity;
		this.message = message;
	}

	public static <T> Response<T> createSuccessResponse(T entity, String message) {
		return new Response<>(entity, message);
	}

	public static <T> Response<T> createFailedResponse(T detail, String message) {
		return new Response<>(detail, message);
	}

	public T getEntity() {
		return entity;
	}

	public String getMessage() {
		return message;
	}
}
