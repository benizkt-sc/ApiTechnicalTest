package mx.com.nath.apitechnicaltest.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"name", "channel", "summary"})
public class Show {

    private int id;

    private String name;

    private String channel;

    private String summary;

    private Set<String> genres;

}