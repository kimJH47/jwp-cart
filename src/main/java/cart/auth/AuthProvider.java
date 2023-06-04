package cart.auth;

import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import cart.auth.interceptor.User;
import cart.exception.UnAuthenticationException;
import cart.repository.UserRepository;

@Component
public class AuthProvider {

	private final UserRepository userRepository;

	public AuthProvider(UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	public User getUser(String authorization) {
		validHeader(authorization);
		String email = authorizationHeaderToEmail(authorization);
		String password = authorizationHeaderToPassword(authorization);
		if (userRepository.isMatchEmailAndPassword(email, password)) {
			throw new UnAuthenticationException("이메일 또는 패스워드가 일치하지 않습니다");
		}
		return new User(email, password);
	}

	private void validHeader(String authorization) {
		if (!StringUtils.hasText(authorization)) {
			throw new UnAuthenticationException("인증헤더가 비어있습니다.");
		}
		if (!authorization.startsWith("Basic ")) {
			throw new UnAuthenticationException("인증방식은 Basic 이여야 합니다.");
		}
	}

	private String authorizationHeaderToEmail(String authorizationHeader) {
		return new String(Base64Utils.decodeFromString(authorizationHeader))
			.split(":")[0];
	}

	private String authorizationHeaderToPassword(String authorizationHeader) {
		return new String(Base64Utils.decodeFromString(authorizationHeader))
			.split(":")[1];
	}

}
