package cart.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import cart.domain.Item;
import cart.dto.request.ItemSaveRequest;
import cart.dto.request.ItemUpdateRequest;
import cart.exception.ItemNotFoundException;

@Repository
public class ItemRepository {
	private final SimpleJdbcInsert simpleJdbcInsert;
	private final JdbcTemplate jdbcTemplate;

	public ItemRepository(JdbcTemplate jdbcTemplate) {
		this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
			.withTableName("ITEM_INFO")
			.usingGeneratedKeyColumns("ID");
		this.jdbcTemplate = jdbcTemplate;
	}

	public long save(ItemSaveRequest itemSaveRequest) {
		return simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(itemSaveRequest))
			.longValue();
	}

	public long update(ItemUpdateRequest itemUpdateRequest) {
		validExistId(itemUpdateRequest.getId());
		String sql = "UPDATE ITEM_INFO set name = ?, image_url = ?, price = ? where id = ?";
		jdbcTemplate.update(sql, itemUpdateRequest.getName(),
			itemUpdateRequest.getImageUrl(), itemUpdateRequest.getPrice(), itemUpdateRequest.getId());
		return itemUpdateRequest.getId();
	}

	public List<Item> findAll() {
		String sql = "select * from ITEM_INFO";
		return jdbcTemplate.query(sql, itemRowMapper());
	}

	private RowMapper<Item> itemRowMapper() {
		return (rs, rowNum) -> new Item(
			rs.getLong("id"),
			rs.getString("name"),
			rs.getInt("price"),
			rs.getString("image_url")
		);
	}

	public long delete(long id) {
		validExistId(id);
		String sql = "DELETE FROM ITEM_INFO where id = ?";
		jdbcTemplate.update(sql, id);
		return id;
	}

	private void validExistId(long id) {
		String sql = "SELECT COUNT(ID) as count FROM ITEM_INFO WHERE id = ? limit 1";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
		if (count == null || count != 1) {
			throw new ItemNotFoundException("상품의 ID 가 존재하지 않습니다.");
		}
	}

}
