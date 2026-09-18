package mx.com.nath.apitechnicaltest.repository;

import mx.com.nath.apitechnicaltest.model.CommentDocument;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.stream.Stream;

public interface CommentRepository extends Repository<CommentDocument, Integer> {

    Stream<CommentDocument> findByShowId(int showId);

    @Query("{ 'showId': { $in: ?0 } }")
    Stream<CommentDocument> findAllByShowId(List<Integer> showIds);

    CommentDocument save(CommentDocument comment);

}
