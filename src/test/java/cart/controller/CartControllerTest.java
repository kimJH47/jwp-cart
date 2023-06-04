package cart.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.http.HttpHeaders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import cart.auth.AuthProvider;
import cart.auth.interceptor.User;
import cart.domain.Item;
import cart.dto.response.CartSearchDto;
import cart.repository.CartRepository;
import cart.service.CartService;

@WebMvcTest(CartController.class)
@Import(MockAuthProviderConfig.class)
class CartControllerTest {

	private static final String TOKEN = "Basic Z2xlbmZpZGRpY2hAbmF2ZXIuY29tOjEyMzQ1Ng==";

	@Autowired
	MockMvc mockMvc;

	@MockBean
	CartService cartService;

	@MockBean
	CartRepository cartRepository;

	@Autowired
	AuthProvider basicAuthProvider;


	@Test
	@DisplayName("/api/cart GET 요청을 보내면 응답코드 200과 함깨 사용자의 카트에 담긴 상품들이 조회가 되어야 한다.")
	void find() throws Exception {
		//given

		given(basicAuthProvider.getUser(anyString()))
			.willReturn(new User("name1@naver.com", "1234"));

		CartSearchDto cart = new CartSearchDto(3, List.of(new Item(1, "바나나", 1000, "none"),
			new Item(2, "포도", 2000, "none"),
			new Item(3, "오렌지", 3000, "none")));

		given(cartService.findByUserEmail(anyString()))
			.willReturn(cart);

		//expect
		mockMvc.perform(get("/api/cart")
				.header(AUTHORIZATION, TOKEN))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.entity.totalCount").value(3))

			.andExpect(jsonPath("$.entity.items[0].id").value(1))
			.andExpect(jsonPath("$.entity.items[0].name").value("바나나"))
			.andExpect(jsonPath("$.entity.items[0].price").value(1000))
			.andExpect(jsonPath("$.entity.items[0].imageUrl").value("none"))

			.andExpect(jsonPath("$.entity.items[1].id").value(2))
			.andExpect(jsonPath("$.entity.items[1].name").value("포도"))
			.andExpect(jsonPath("$.entity.items[1].price").value(2000))
			.andExpect(jsonPath("$.entity.items[1].imageUrl").value("none"))

			.andExpect(jsonPath("$.entity.items[2].id").value(3))
			.andExpect(jsonPath("$.entity.items[2].name").value("오렌지"))
			.andExpect(jsonPath("$.entity.items[2].price").value(3000))
			.andExpect(jsonPath("$.entity.items[2].imageUrl").value("none"))
		;
	}
}