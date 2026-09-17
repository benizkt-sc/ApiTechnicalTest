package mx.com.nath.apitechnicaltest.service.impl;

import mx.com.nath.apitechnicaltest.client.TvMazeClient;
import mx.com.nath.apitechnicaltest.testdata.ShowObjectMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
}