package app.data;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ReviewDto {
	String name;
	Integer score;
}
