package cart.dto.request;

import javax.validation.constraints.NotNull;

public class ItemDeleteRequest {
	@NotNull(message = "상품의 id 는 필수 입니다.")
	private long id;

	public ItemDeleteRequest(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
}
