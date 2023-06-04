package cart.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import cart.dto.response.UserSearchDto;

@Repository
public class UserRepository {

	private final SimpleJdbcInsert simpleJdbcInsert;
	private final JdbcTemplate jdbcTemplate;

	public UserRepository(JdbcTemplate jdbcTemplate) {
		this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
			.withTableName("USER_INFO")
			.usingGeneratedKeyColumns("ID");
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<UserSearchDto> findAll() {
		return jdbcTemplate.query("SELECT user_id, password FROM USER_INFO", userRowMapper());
	}

	private RowMapper<UserSearchDto> userRowMapper() {
		return (rs, rowNum) -> new UserSearchDto(rs.getString("user_id"), rs.getString("password"));
	}

	public boolean isMatchEmailAndPassword(String userId, String password) {
		if (!existUserId(userId)) {
			return false;
		}
		return Objects.equals(jdbcTemplate.queryForObject("SELECT password FROM USER_INFO where userId = ? limit 1",
			String.class, userId), password);
	}

	private boolean existUserId(String userId) {
		String sql = "SELECT COUNT(user_id) as count FROM USER_INFO WHERE user_id = ? limit 1";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId);
		return count != null && count == 1;
	}
}
