package cart.dto.response;

public class Response<T> {
	private final T Entity;
	private final String message;

	public Response(T entity, String message) {
		Entity = entity;
		this.message = message;
	}

	public static <T> Response<T> createSuccessResponse(T entity, String message) {
		return new Response<>(entity, message);
	}

	public static Response<String> createFailedResponse(String detail, String message) {
		return new Response<>(detail, message);
	}

	public T getEntity() {
		return Entity;
	}

	public String getMessage() {
		return message;
	}
}
