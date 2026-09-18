package mx.com.nath.apitechnicaltest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.nath.apitechnicaltest.client.TvMazeClient;
import mx.com.nath.apitechnicaltest.exception.ShowNotFoundException;
import mx.com.nath.apitechnicaltest.mapper.CommentMapper;
import mx.com.nath.apitechnicaltest.mapper.ShowMapper;
import mx.com.nath.apitechnicaltest.model.CommentDocument;
import mx.com.nath.apitechnicaltest.model.Show;
import mx.com.nath.apitechnicaltest.repository.CommentRepository;
import mx.com.nath.apitechnicaltest.repository.ShowRepository;
import mx.com.nath.apitechnicaltest.service.ShowService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final TvMazeClient tvMazeClient;

    private final ShowRepository showRepository;

    private final CommentRepository commentRepository;

    @Override
    public List<Show> findShow(final String query) {
        log.info("Executing call to find shows with query: {}", query);
        final var shows = this.tvMazeClient.findShow(query)
                .stream()
                .map(ShowMapper.INSTANCE::toShow)
                .collect(Collectors.toCollection(ArrayList::new));
        final var showsIds = shows.stream().map(Show::getId).toList();
        final var comments = this.getComments(showsIds);
        this.addComments(shows, comments);

        return shows;
    }

    @Override
    public Show findShow(final int showId) {
        log.info("Executing call to find show with id: {}", showId);
        final var show = this.showRepository.findById(showId)
                .map(ShowMapper.INSTANCE::toShow)
                .orElseGet(() -> this.findAndSaveShowFromClient(showId));
        final var comments = this.getComments(Collections.singletonList(show.getId()));
        this.addComments(show, comments);
        return show;
    }

    private List<CommentDocument> getComments(final List<Integer> showsIds) {
        return this.commentRepository
                .findAllByShowId(showsIds)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void addComments(final List<Show> shows, final List<CommentDocument> comments) {
        shows.forEach(show -> this.addComments(show, comments));
    }

    private void addComments(final Show show, final List<CommentDocument> comments) {
        final var commentsByShowId = comments.stream()
                .collect(Collectors.groupingBy(
                        CommentDocument::getShowId,
                        Collectors.mapping(
                                CommentMapper.INSTANCE::toComment,
                                Collectors.toList()
                        )
                ));
        show.setComments(commentsByShowId.get(show.getId()));
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