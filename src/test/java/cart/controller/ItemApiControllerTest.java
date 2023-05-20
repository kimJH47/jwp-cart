package cart.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cart.dto.request.ItemSaveRequest;
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
}