package cart.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import cart.auth.AuthProvider;


@Component
public class LoginInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	public LoginInterceptor(AuthProvider authProvider) {
		this.authProvider = authProvider;
	}

	private final AuthProvider authProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		log.debug("authorization Header is {}", authorization);
		User user = authProvider.getUser(authorization);
		request.setAttribute("user", user);
		return true;
	}
}
