package mx.com.nath.apitechnicaltest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.nath.apitechnicaltest.client.TvMazeClient;
import mx.com.nath.apitechnicaltest.exception.ShowNotFoundException;
import mx.com.nath.apitechnicaltest.mapper.ShowMapper;
import mx.com.nath.apitechnicaltest.model.Show;
import mx.com.nath.apitechnicaltest.service.ShowService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final TvMazeClient tvMazeClient;

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
        try {
            return ShowMapper.INSTANCE.toShow(this.tvMazeClient.findShow(showId));
        } catch (final HttpClientErrorException.NotFound exception) {
            log.error("The show with id: {} can't be found, cause: {}", showId, exception.getMessage(), exception);
            throw new ShowNotFoundException("The show with id: " + showId + " wasn't found!");
        }
    }

}