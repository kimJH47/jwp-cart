package cart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cart.domain.Item;
import cart.dto.request.ItemSaveRequest;
import cart.dto.request.ItemUpdateRequest;
import cart.repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public long save(ItemSaveRequest itemSaveRequest) {
        return itemRepository.save(itemSaveRequest);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public long update(ItemUpdateRequest itemUpdateRequest) {
        return itemRepository.update(itemUpdateRequest);
    }

    public long delete(long id) {
        return itemRepository.delete(id);
    }

}
