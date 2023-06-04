package cart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cart.auth.interceptor.User;
import cart.auth.resolver.annotation.Principal;
import cart.dto.response.CartSearchDto;
import cart.dto.response.Response;
import cart.service.CartService;

@Controller
@RequestMapping("/api/cart")
public class CartController {

	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping
	public ResponseEntity<Response<CartSearchDto>> findAll(@Principal User user) {
		return ResponseEntity.ok()
			.body(Response.createSuccessResponse(cartService.findByUserEmail(user.getEmail()), "성공적으로 조회가 완료되었습니다."));
	}
}
