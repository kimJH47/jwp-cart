package cart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cart.dto.response.Response;
import cart.dto.response.UserSearchDto;
import cart.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<Response<List<UserSearchDto>>> findAll() {
		return ResponseEntity.ok()
			.body(Response.createSuccessResponse(userService.findAll(), "성공적으로 유저가 조회되었습니다."));
	}

}
