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
}
