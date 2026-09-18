package mx.com.nath.apitechnicaltest.repository;

import mx.com.nath.apitechnicaltest.model.CommentDocument;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.stream.Stream;

public interface CommentRepository extends Repository<CommentDocument, Integer> {

    Stream<CommentDocument> findByShowId(int showId);

    CommentDocument save(CommentDocument comment);

}
