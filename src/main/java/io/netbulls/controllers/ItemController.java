package io.netbulls.controllers;

import io.netbulls.data.ItemDto;
import io.netbulls.data.ReviewDto;
import io.netbulls.domain.Item;
import io.netbulls.services.ItemService;
import io.netbulls.tools.ImageTool;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class ItemController {
	@Autowired
	private ItemService itemService;

	@RequestMapping(method = RequestMethod.GET)
	public List<ItemDto> getItems() {
		return itemService.getItems().stream().map(ItemDto::fromItem).collect(Collectors.toList());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ItemDto addItem(@RequestBody ItemDto itemDto) {
		return ItemDto.fromItem(itemService.addItem(itemDto.getName(), itemDto.getCreator(), itemDto.getThumbnail()));
	}

	@RequestMapping(value = "/{id}/review", method = RequestMethod.POST)
	public void addReview(@PathVariable("id") Long itemId, @RequestBody ReviewDto reviewDto) {
		itemService.addReview(itemId, reviewDto.getName(), reviewDto.getScore());
	}

	@RequestMapping(value = "/{id}/thumbnail", method = RequestMethod.GET, produces = "image/png")
	public byte[] getThumbnail(@PathVariable Long id) {
		Item item = itemService.getItem(id);
		byte[] thumbnail = Base64.decodeBase64(item.getThumbnail());
		return ImageTool.resizeImage(thumbnail, 200, 200);
	}
}
