package cart.controller;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import cart.auth.AuthProvider;

@TestConfiguration
public class MockAuthProviderConfig {

	@Bean
	public AuthProvider basicAuthProvider() {
		return Mockito.mock(AuthProvider.class);
	}

}
