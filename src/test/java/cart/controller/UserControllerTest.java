package cart.controller;

import static org.mockito.BDDMockito.*;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import cart.dto.response.UserSearchDto;
import cart.repository.UserRepository;
import cart.service.UserService;

@WebMvcTest(UserController.class)
@Import(MockAuthProviderConfig.class)
class UserControllerTest {

	@MockBean
	UserRepository userRepository;
	@MockBean
	UserService userService;
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName("/api/user get 요청 시 응답코드가 200과 전체 유저가 조회되어 응답되어야한다.")
	void findAll() throws Exception {
		//given
		List<UserSearchDto> response = List.of(new UserSearchDto("id1", "password"),
			new UserSearchDto("id2", "password2"));
		given(userService.findAll()).willReturn(response);

		//expect
		mockMvc.perform(get("/api/user"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message").value("성공적으로 유저가 조회되었습니다."))
			.andExpect(jsonPath("$.entity[0].email").value("id1"))
			.andExpect(jsonPath("$.entity[0].password").value("password"))
			.andExpect(jsonPath("$.entity[1].email").value("id2"))
			.andExpect(jsonPath("$.entity[1].password").value("password2"));

	}
}