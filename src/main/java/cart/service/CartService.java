package cart.service;

import org.springframework.stereotype.Service;

import cart.dto.response.CartSearchDto;
import cart.repository.CartRepository;

@Service
public class CartService {

	private final CartRepository cartRepository;

	public CartService(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	public CartSearchDto findByUserEmail(String userId) {
		return cartRepository.findByUserEmail(userId);
	}
}
