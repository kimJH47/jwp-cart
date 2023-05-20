package cart.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import cart.domain.Item;
import cart.dto.request.ItemDeleteRequest;
import cart.dto.request.ItemSaveRequest;
import cart.dto.request.ItemUpdateRequest;
import cart.repository.ItemRepository;
import cart.service.ItemService;

@WebMvcTest(ItemApiController.class)
class ItemApiControllerTest {

	@MockBean
	ItemRepository itemRepository;
	@SpyBean
	ItemService itemService;
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName("/api/items post 요청 시  저장된 item id 와 응답코드 200 이 해더에 담겨서 응답되어야 한다.")
	void save() throws Exception {
		//given
		willReturn(1L)
			.given(itemService)
			.save(any(ItemSaveRequest.class));
		//given(itemService.save(any(ItemSaveRequest.class))).willReturn(1L);
		ItemSaveRequest itemSaveRequest = new ItemSaveRequest("바나나", "none", 10000);
		//expect
		mockMvc.perform(post("/api/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(itemSaveRequest)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message").value("성공적으로 저장되었습니다."))
			.andExpect(jsonPath("$.entity").value(1L));

		then(itemService).should(times(1)).save(any(ItemSaveRequest.class));
	}

	@Test
	@DisplayName("/api/items get 요청 시 전체 item 리스트와 응답코드 200 이 해더에 담겨서 응답 되어야한다.")
	void findAll() throws Exception {
		//give
		ArrayList<Item> items = new ArrayList<>();
		items.add(createItem(1, "바나나", 10000, "none"));
		items.add(createItem(2, "사과", 20000, "nasdsa"));
		items.add(createItem(3, "포도", 25000, "podo"));
		willReturn(items)
			.given(itemService)
			.findAll();

		//expect
		mockMvc.perform(get("/api/items"))
			.andExpect(jsonPath("$.message").value("성공적으로 조회가 완료되었습니다"))
			.andExpect(jsonPath("$.entity[0].id").value(1))
			.andExpect(jsonPath("$.entity[0].name").value("바나나"))
			.andExpect(jsonPath("$.entity[0].price").value(10000))
			.andExpect(jsonPath("$.entity[0].imageUrl").value("none"))

			.andExpect(jsonPath("$.entity[1].id").value(2))
			.andExpect(jsonPath("$.entity[1].name").value("사과"))
			.andExpect(jsonPath("$.entity[1].price").value(20000))
			.andExpect(jsonPath("$.entity[1].imageUrl").value("nasdsa"))

			.andExpect(jsonPath("$.entity[2].id").value(3))
			.andExpect(jsonPath("$.entity[2].name").value("포도"))
			.andExpect(jsonPath("$.entity[2].price").value(25000))
			.andExpect(jsonPath("$.entity[2].imageUrl").value("podo"));

		then(itemService).should(times(1)).findAll();

	}

	@Test
	@DisplayName("/api/items patch 요청 시 업데이트 된 item id 와 응답코드 200이 해더에 담겨서 응답 되어야한다.")
	void update() throws Exception {
		//given
		ItemUpdateRequest itemUpdateRequest = new ItemUpdateRequest(1, "바나나", "none", 20000);
		willReturn(1L)
			.given(itemService)
			.update(any(ItemUpdateRequest.class));

		//expect
		mockMvc.perform(patch("/api/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(itemUpdateRequest)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message").value("성공적으로 업데이트 되었습니다."))
			.andExpect(jsonPath("$.entity").value(1L));

		then(itemService).should(times(1)).update(any(ItemUpdateRequest.class));
	}

	@Test
	@DisplayName("/api/items delete 요청 시 삭제된 item id 와 응답코드 200이 해더에 담겨서 응답되어야한다.")
	void delete() throws Exception {
		//given
		willReturn(1L)
			.given(itemService)
			.delete(anyLong());

		//expect
		ItemDeleteRequest value = new ItemDeleteRequest(1L);
		String content = objectMapper.writeValueAsString(value);
		System.out.println(content);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message").value("성공적으로 삭제 되었습니다."))
			.andExpect(jsonPath("$.entity").value(1L));

		then(itemService).should(times(1)).delete(anyLong());

	}

	private Item createItem(int id, String name, int price, String url) {
		return new Item(id, name, price, url);
	}
}