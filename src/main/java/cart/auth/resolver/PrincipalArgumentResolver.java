package cart.auth.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import cart.auth.interceptor.User;
import cart.auth.resolver.annotation.Principal;
import cart.exception.UnAuthenticationException;

public class PrincipalArgumentResolver implements HandlerMethodArgumentResolver {

	private static final String USER_ATTRIBUTE = "user";

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(Principal.class)
			&& parameter.getParameterType().equals(User.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Object user = webRequest.getAttribute(USER_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
		if (user == null) {
			throw new UnAuthenticationException("인증이 되지 않았습니다.");
		}
		return user;
	}
}

