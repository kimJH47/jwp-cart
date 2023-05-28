package cart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@GetMapping("/{userId}")
	public ResponseEntity<Response<List<CartSearchDto>>> findAll(@PathVariable String userId) {
		return ResponseEntity.ok()
			.body(Response.createSuccessResponse(cartService.findByUserId(userId), "성공적으로 조회가 완료되었습니다."));
	}
}
