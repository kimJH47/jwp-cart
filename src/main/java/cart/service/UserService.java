package cart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cart.dto.response.UserSearchDto;
import cart.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserSearchDto> findAll() {
		return userRepository.findAll();
	}
}
