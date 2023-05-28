package cart.repository;

import java.util.List;

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
		return jdbcTemplate.query("SELECT id, password FROM USER_INFO", getObject());
	}

	private RowMapper<UserSearchDto> getObject() {
		return (rs, rowNum) -> new UserSearchDto(rs.getString("id"), rs.getString("password"));
	}
}
