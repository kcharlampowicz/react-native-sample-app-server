package app.data;

import app.domain.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ItemDto {
	long id;
	String name;
	String thumbnail;
	Double score;
	String creator;

	public static ItemDto fromItem(Item item) {
		return new ItemDto(item.getId(), item.getName(), //FIXME url
				"http://192.168.10.57:8080/api/items/" + item.getId() + "/thumbnail.png?" + item.getCreate().getTime(),
				item.getScore(), item.getCreator());
	}
}
