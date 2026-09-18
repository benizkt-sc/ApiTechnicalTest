package mx.com.nath.apitechnicaltest.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Comment {

    private int showId;

    private String comment;

    private int rating;

}
