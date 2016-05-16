package app.services;

import app.domain.Review;
import app.domain.repository.ItemRepository;
import app.domain.repository.ReviewRepository;
import app.domain.Item;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ReviewRepository reviewRepository;

	public Item getItem(Long id) {
		return itemRepository.findOne(id);
	}

	public List<Item> getItems() {
		return itemRepository.findAll().stream()
				.sorted((first, second) -> first.getScore() <= second.getScore() ? -1 : 1)
				.collect(Collectors.toList());
	}

	public Item addItem(String name, String creator, String base64photo) {
		byte[] bytes = base64photo != null ? base64photo.getBytes(Charset.forName("UTF-8")) : "".getBytes();
		Item item = new Item(name, creator, bytes);
		return itemRepository.save(item);
	}

	public void addReview(Long itemId, String name, Integer score) {
		Item item = itemRepository.findOne(itemId);
		reviewRepository.save(new Review(name, score, item));
	}
}
