package mx.com.nath.apitechnicaltest.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Getter
@Setter
@Document(collection = "comments")
public class CommentDocument {

    @Id
    @Field(name = "id")
    private ObjectId id;

    @Field(name = "showId")
    private int showId;

    @Field(name = "comment")
    private String comment;

    @Field("rating")
    private int rating;

    @Override
    public boolean equals(final Object obj) {
        if (Objects.isNull(obj)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommentDocument commentDocument)) {
            return false;
        }
        return Objects.equals(this.showId, commentDocument.showId)
                && Objects.equals(this.comment, commentDocument.comment)
                && Objects.equals(this.rating, commentDocument.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.showId,
                this.comment,
                this.rating
        );
    }

}