package mx.com.nath.apitechnicaltest.service.impl;

import mx.com.nath.apitechnicaltest.client.TvMazeClient;
import mx.com.nath.apitechnicaltest.exception.ShowNotFoundException;
import mx.com.nath.apitechnicaltest.testdata.ShowObjectMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ShowServiceImplTests {

    @Mock
    private TvMazeClient tvMazeClient;

    @InjectMocks
    private ShowServiceImpl showService;

    @Test
    @DisplayName("Return a list with shows successfully")
    void findShowAndReturnAListWithElements() {
        final var query = "cosmic horror";
        final var responseList = ShowObjectMother.getListShowWrapperResponseOneElement();
        when(this.tvMazeClient.findShow(eq(query))).thenReturn(responseList);

        final var result = this.showService.findShow(query);

        assertEquals(ShowObjectMother.getListShowOneElement(), result);
        verify(this.tvMazeClient, times(1)).findShow(eq(query));
    }

    @Test
    @DisplayName("Return an empty list of shows successfully")
    void findShowWithoutResults() {
        final var query = "cosmic horror";
        final var responseList = ShowObjectMother.getListShowWrapperResponseOneElement();
        when(this.tvMazeClient.findShow(eq(query))).thenReturn(responseList);

        final var result = this.showService.findShow(query);

        assertEquals(ShowObjectMother.getListShowOneElement(), result);
        verify(this.tvMazeClient, times(1)).findShow(eq(query));
    }


    @Test
    @DisplayName("Return a show by id successfully")
    void findShowSuccessfully() {
        final var id = 83479;
        final var data = ShowObjectMother.getShowContent();
        when(this.tvMazeClient.findShow(eq(id))).thenReturn(data);

        final var result = this.showService.findShow(id);

        assertEquals(ShowObjectMother.getShow(), result);
        verify(this.tvMazeClient, times(1)).findShow(eq(id));
    }

    @Test
    @DisplayName("Throw a ShowNotFoundException if the Show can't be found")
    void findShowThrowsAShowNotFoundException() {
        final var id = -1;
        when(this.tvMazeClient.findShow(eq(id)))
                .thenThrow(new ShowNotFoundException(
                        "The show with id: " + id + " wasn't found!"));

        assertThrows(ShowNotFoundException.class, () -> this.showService.findShow(id));
    }

}