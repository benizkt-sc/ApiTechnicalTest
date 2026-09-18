package mx.com.nath.apitechnicaltest.service.impl;

import mx.com.nath.apitechnicaltest.client.TvMazeClient;
import mx.com.nath.apitechnicaltest.exception.ShowNotFoundException;
import mx.com.nath.apitechnicaltest.model.ShowDocument;
import mx.com.nath.apitechnicaltest.repository.CommentRepository;
import mx.com.nath.apitechnicaltest.repository.ShowRepository;
import mx.com.nath.apitechnicaltest.testdata.ShowObjectMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShowServiceImplTests {

    @Mock
    private TvMazeClient tvMazeClient;

    @Mock
    private ShowRepository showRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private ShowServiceImpl showService;

    @Test
    @DisplayName("Return a list with shows successfully")
    void findShowAndReturnAListWithElements() {
        final var query = "cosmic horror";
        final var responseList =
                ShowObjectMother.getListShowWrapperResponseOneElement();

        when(this.tvMazeClient.findShow(eq(query)))
                .thenReturn(responseList);

        when(this.commentRepository.findAllByShowId(any()))
                .thenReturn(Stream.empty());

        final var result = this.showService.findShow(query);

        assertEquals(
                ShowObjectMother.getListShowOneElement(),
                result
        );

        verify(this.tvMazeClient, times(1))
                .findShow(eq(query));

        verify(this.commentRepository, times(1))
                .findAllByShowId(any());
    }

    @Test
    @DisplayName("Return an empty list when no shows are found")
    void findShowWithoutResults() {
        final var query = "cosmic horror";

        when(this.tvMazeClient.findShow(eq(query)))
                .thenReturn(java.util.List.of());

        final var result = this.showService.findShow(query);

        assertEquals(java.util.List.of(), result);

        verify(this.tvMazeClient, times(1))
                .findShow(eq(query));

        verify(this.commentRepository, times(1))
                .findAllByShowId(any());
    }

    @Test
    @DisplayName("Return a show and save it to cache")
    void findShowSuccessfullyFromTvMaze() {

        final var id = 83479;
        final var response = ShowObjectMother.getShowContent();
        final var expectedShow = ShowObjectMother.getShow();

        when(this.showRepository.findById(eq(id)))
                .thenReturn(Optional.empty());

        when(this.tvMazeClient.findShow(eq(id)))
                .thenReturn(response);

        when(this.showRepository.save(any(ShowDocument.class)))
                .thenReturn(ShowObjectMother.getShowDocument());

        when(this.commentRepository.findAllByShowId(eq(java.util.List.of(id))))
                .thenReturn(Stream.empty());

        final var result = this.showService.findShow(id);

        assertEquals(expectedShow, result);

        verify(this.showRepository, times(1))
                .findById(eq(id));

        verify(this.tvMazeClient, times(1))
                .findShow(eq(id));

        verify(this.showRepository, times(1))
                .save(any(ShowDocument.class));

        verify(this.commentRepository, times(1))
                .findAllByShowId(eq(java.util.List.of(id)));
    }

    @Test
    @DisplayName("Return a show by id from cache")
    void findShowSuccessfullyFromCache() {

        final var id = 83479;
        final var document = ShowObjectMother.getShowDocument();
        final var expectedShow = ShowObjectMother.getShow();

        when(this.showRepository.findById(eq(id)))
                .thenReturn(Optional.of(document));

        when(this.commentRepository.findAllByShowId(eq(java.util.List.of(id))))
                .thenReturn(Stream.empty());

        final var result = this.showService.findShow(id);

        assertEquals(expectedShow, result);

        verify(this.showRepository, times(1))
                .findById(eq(id));

        verify(this.tvMazeClient, never())
                .findShow(anyInt());

        verify(this.showRepository, never())
                .save(any());

        verify(this.commentRepository, times(1))
                .findAllByShowId(eq(java.util.List.of(id)));
    }

    @Test
    @DisplayName("Throw a ShowNotFoundException when the show doesn't exist")
    void findShowThrowsAShowNotFoundException() {

        final var id = -1;

        when(this.showRepository.findById(eq(id)))
                .thenReturn(Optional.empty());

        when(this.tvMazeClient.findShow(eq(id)))
                .thenThrow(HttpClientErrorException.create(
                        HttpStatus.NOT_FOUND,
                        "Not Found",
                        HttpHeaders.EMPTY,
                        null,
                        null
                ));

        assertThrows(
                ShowNotFoundException.class,
                () -> this.showService.findShow(id)
        );

        verify(this.showRepository, times(1))
                .findById(eq(id));

        verify(this.tvMazeClient, times(1))
                .findShow(eq(id));

        verify(this.showRepository, never())
                .save(any());

        verify(this.commentRepository, never())
                .findAllByShowId(any());
    }
}