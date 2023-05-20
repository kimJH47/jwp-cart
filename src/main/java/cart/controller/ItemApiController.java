package cart.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cart.domain.Item;
import cart.dto.request.ItemDeleteRequest;
import cart.dto.request.ItemSaveRequest;
import cart.dto.request.ItemUpdateRequest;
import cart.dto.response.Response;
import cart.service.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemApiController {
	private final ItemService itemService;

	public ItemApiController(ItemService itemService) {
		this.itemService = itemService;
	}

	@PostMapping
	public ResponseEntity<Response<Long>> save(@RequestBody @Valid ItemSaveRequest itemSaveRequest) {
		return ResponseEntity.ok()
			.body(Response.createSuccessResponse(itemService.save(itemSaveRequest), "성공적으로 저장되었습니다."));
	}

	@GetMapping
	public ResponseEntity<Response<List<Item>>> findAll() {
		return ResponseEntity.ok()
			.body(Response.createSuccessResponse(itemService.findAll(), "성공적으로 조회가 완료되었습니다"));
	}

	@PatchMapping
	public ResponseEntity<Response<Long>> update(@RequestBody @Valid ItemUpdateRequest itemUpdateRequest) {
		return ResponseEntity.ok()
			.body(Response.createSuccessResponse(itemService.update(itemUpdateRequest), "성공적으로 업데이트 되었습니다."));
	}

	@DeleteMapping
	public ResponseEntity<Response<Long>> delete(@RequestBody ItemDeleteRequest itemDeleteRequest) {
		return ResponseEntity.ok()
			.body(Response.createSuccessResponse(itemService.delete(itemDeleteRequest.getId()), "성공적으로 삭제 되었습니다."));
	}

}
