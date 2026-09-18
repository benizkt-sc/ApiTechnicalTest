package mx.com.nath.apitechnicaltest.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Document(collection = "shows")
public class ShowDocument {

    @Id
    @Field("id")
    private int id;

    @Field(name = "name")
    private String name;

    @Field(name = "channel")
    private String channel;

    @Field(name = "summary")
    private String summary;

    @Field(name = "genres")
    private Set<String> genres;

    @Override
    public boolean equals(final Object obj) {
        if (Objects.isNull(obj)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShowDocument showDocument)) {
            return false;
        }
        return Objects.equals(this.name, showDocument.name)
                && Objects.equals(this.channel, showDocument.channel)
                && Objects.equals(this.summary, showDocument.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.name,
                this.channel,
                this.summary
        );
    }

}