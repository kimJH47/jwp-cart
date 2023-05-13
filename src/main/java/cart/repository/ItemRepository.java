package cart.repository;

import cart.dto.ItemSaveRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

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
}
