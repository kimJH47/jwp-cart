package cart.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cart.dto.request.ItemSaveRequest;
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
			.body(Response.createSuccessResponse(itemService.save(itemSaveRequest),"성공적으로 저장되었습니다."));
	}


}
