package mx.com.nath.apitechnicaltest.repository;

import mx.com.nath.apitechnicaltest.model.ShowDocument;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ShowRepository extends Repository<ShowDocument, Integer> {

    ShowDocument save(ShowDocument document);

    Optional<ShowDocument> findById(int id);

}