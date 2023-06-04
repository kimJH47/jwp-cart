package cart.dto.response;

public class UserSearchDto {
	private final String email;
	private final String password;

	public UserSearchDto(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
