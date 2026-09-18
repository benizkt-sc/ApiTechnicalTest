package mx.com.nath.apitechnicaltest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.nath.apitechnicaltest.client.TvMazeClient;
import mx.com.nath.apitechnicaltest.exception.ShowNotFoundException;
import mx.com.nath.apitechnicaltest.mapper.ShowMapper;
import mx.com.nath.apitechnicaltest.model.Show;
import mx.com.nath.apitechnicaltest.repository.ShowRepository;
import mx.com.nath.apitechnicaltest.service.ShowService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final TvMazeClient tvMazeClient;

    private final ShowRepository showRepository;

    @Override
    public List<Show> findShow(final String query) {
        log.info("Executing call to find shows with query: {}", query);
        return this.tvMazeClient.findShow(query)
                .stream().map(ShowMapper.INSTANCE::toShow)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Show findShow(final int showId) {
        log.info("Executing call to find show with id: {}", showId);
        return this.showRepository.findById(showId)
                .map(ShowMapper.INSTANCE::toShow)
                .orElseGet(() -> this.findAndSaveShowFromClient(showId));
    }

    private Show findAndSaveShowFromClient(final int showId) {
        try {
            log.info("Calling API (shows) for showId: {}", showId);
            final var showSaved = this.showRepository
                    .save(ShowMapper.INSTANCE
                            .toShowDocument(this.tvMazeClient.findShow(showId)));
            log.info("The Show with showId: {} is saved now!", showId);
            return ShowMapper.INSTANCE.toShow(showSaved);
        } catch (final HttpClientErrorException.NotFound exception) {
            log.error("The show with id: {} can't be found, cause: {}", showId, exception.getMessage(), exception);
            throw new ShowNotFoundException("The show with id: " + showId + " wasn't found!");
        }
    }

}