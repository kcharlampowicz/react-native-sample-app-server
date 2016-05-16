package app.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class Review extends AbstractEntity {
	private String name;
	private Integer score;
	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false)
	private Item item;

	public Review(String name, Integer score, Item item) {
		this.name = name;
		this.score = score;
		this.item = item;
	}

	public Integer getScore() {
		return score;
	}

	@SuppressWarnings("unused")
	protected Review() {
	}
}
