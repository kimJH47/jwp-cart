package cart.repository;

import static org.assertj.core.api.Assertions.assertThat;

import cart.domain.Item;
import cart.dto.ItemSaveRequest;
import cart.dto.ItemUpdateRequest;
import java.util.List;
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
    void save() {
        //given
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("바나나", "ignore", 2000);
        //when
        long id = itemRepository.save(itemSaveRequest);
        Item actual = itemRepository.findAll().get(0);
        //then
        assertThat(actual.getId()).isEqualTo(id);
        assertThat(actual.getName()).isEqualTo(itemSaveRequest.getName());
        assertThat(actual.getPrice()).isEqualTo(itemSaveRequest.getPrice());
        assertThat(actual.getImageUrl()).isEqualTo(itemSaveRequest.getImageUrl());

    }

    @Test
    @DisplayName("ItemUpdateRequest 를 받아서 성공적으로 업데이트 후 저장된 pk 값을 반환해야한다.")
    void update() {
        //given
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("망고", "ignore", 30000);
        long id = itemRepository.save(itemSaveRequest);
        ItemUpdateRequest itemUpdateRequest = new ItemUpdateRequest(id, "사과", "https://chat.openai.com", 10000);
        //when
        long update = itemRepository.update(itemUpdateRequest);
        Item actual = itemRepository.findAll().get(0);
        //then
        assertThat(actual.getId()).isEqualTo(update);
        assertThat(actual.getName()).isEqualTo(itemUpdateRequest.getName());
        assertThat(actual.getPrice()).isEqualTo(itemUpdateRequest.getPrice());
        assertThat(actual.getImageUrl()).isEqualTo(itemUpdateRequest.getImageUrl());
    }

    @Test
    @DisplayName("item pk 를 인자로 받아 성공적으로 삭제 후 삭제된 pk 가 반환되어야한다.")
    void delete() {
        //given
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("포도", "ignore", 5000);
        long expected = itemRepository.save(itemSaveRequest);
        //when
        long actual = itemRepository.delete(expected);
        List<Item> items = itemRepository.findAll();
        //then
        assertThat(actual).isEqualTo(expected);
        assertThat(items).isEmpty();

    }

}