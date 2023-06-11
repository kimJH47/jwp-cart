package cart.repository;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import cart.domain.Item;
import cart.dto.response.CartSearchDto;

@Repository
public class CartRepository {

	private final SimpleJdbcInsert simpleJdbcInsert;
	private final JdbcTemplate jdbcTemplate;

	public CartRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
			.withTableName("CART_INFO")
			.usingGeneratedKeyColumns("ID");
	}

	public CartSearchDto findByUserEmail(String email) {
		return jdbcTemplate.queryForObject("SELECT id,name,price,image_url FROM CART_INFO where email = ?",
			carRowMapper(), email);
	}

	private RowMapper<CartSearchDto> carRowMapper() {
		return (rs, rsNum) -> {
			ArrayList<Item> items = new ArrayList<>();
			while (rs.next()) {
				items.add(
					new Item(rs.getLong("id"),
						rs.getString("name"),
						rs.getInt("price"),
						rs.getString("image_url")));
			}
			return new CartSearchDto(items.size(), items);
		};
	}

	public void addItem(String userEmail, String name, String imageUrl, int price) {
		simpleJdbcInsert.execute(Map.of(
			"user_email", userEmail,
			"price", price,
			"name", name,
			"image_url", imageUrl));
	}
}
