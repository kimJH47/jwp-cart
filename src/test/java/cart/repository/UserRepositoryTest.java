package cart.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import cart.dto.response.UserSearchDto;

@JdbcTest
class UserRepositoryTest {
	@Autowired
	JdbcTemplate jdbcTemplate;
	UserRepository userRepository;

	@BeforeEach
	void init() {
		userRepository = new UserRepository(jdbcTemplate);
	}

	@Test
	@DisplayName("전체 유저가 조회 된 후 dto 에 담겨 반환되어야 한다.")
	void findAll(){
		//given
		jdbcTemplate.update("insert into user_info values(3,'id3','password3')");

		//when
		List<UserSearchDto> expect = userRepository.findAll();

		//then
		assertThat(expect).hasSize(3);
		assertThat(expect.get(2).getEmail()).isEqualTo("id3");
		assertThat(expect.get(2).getPassword()).isEqualTo("password3");
	}
}
