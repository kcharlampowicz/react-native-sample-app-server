package app.domain;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item extends AbstractEntity {
	private String name;
	private Date create;
	private String creator;
	@Lob
	private byte[] thumbnail;
	@OneToMany(mappedBy = "item")
	private List<Review> reviews = Collections.emptyList();

	public Item(String name, String creator, byte[] thumbnail) {
		this.name = name;
		this.create = new Date();
		this.creator = creator;
		this.thumbnail = thumbnail;
	}

	public Date getCreate() {
		return create;
	}

	public String getName() {
		return name;
	}

	public Double getScore() {
		if (reviews.isEmpty()) {
			return 5.0;
		}
		return (double) reviews.stream().mapToInt(Review::getScore).sum() / reviews.size();
	}

	public String getCreator() {
		return creator;
	}

	public byte[] getThumbnail() {
		return thumbnail;
	}

	@SuppressWarnings("unused")
	protected Item() {
	}
}
