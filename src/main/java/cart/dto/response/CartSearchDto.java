package cart.dto.response;

import java.util.List;

import cart.domain.Item;

public class CartSearchDto {
	private int totalCount;
	private List<Item> items;

	public CartSearchDto(int totalCount, List<Item> items) {
		this.totalCount = totalCount;
		this.items = items;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public List<Item> getItems() {
		return items;
	}
}
