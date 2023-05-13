package cart.repository;

import static org.assertj.core.api.Assertions.assertThat;

import cart.dto.ItemSaveRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class ItemRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    ItemRepository itemRepository;

    @BeforeEach
    void init() {
        itemRepository = new ItemRepository(jdbcTemplate);
    }
    @Test
    @DisplayName("ItemSaveRequest 를 받아서 성공적으로 저장 후 저장된 pk 값을 반환해야한다.")
    void save(){
        //given
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("바나나", "ignore", 2000);
        //when
        long actual = itemRepository.save(itemSaveRequest);
        //then
        assertThat(actual).isEqualTo(1);
    }

}