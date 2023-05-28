package cart.dto.response;

public class UserSearchDto {
	private final String id;
	private final String password;

	public UserSearchDto(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
}
