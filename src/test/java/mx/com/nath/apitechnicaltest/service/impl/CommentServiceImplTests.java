package mx.com.nath.apitechnicaltest.service.impl;

import mx.com.nath.apitechnicaltest.model.AddCommentRequest;
import mx.com.nath.apitechnicaltest.repository.CommentRepository;
import mx.com.nath.apitechnicaltest.testdata.CommentObjectMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTests {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    @DisplayName("The comment is saved successfully")
    void addCommentSuccessfully() {
        final var showId = 83479;
        final var request = new AddCommentRequest("This is a great show!", 5);
        final var expectedDocument = CommentObjectMother.getCommentDocument();
        this.commentService.addComment(showId, request);
        verify(this.commentRepository, times(1)).save(eq(expectedDocument));
    }

    @Test
    @DisplayName("Return comments successfully")
    void getCommentsSuccessfully() {
        final var showId = 83479;
        final var commentDocument = CommentObjectMother.getCommentDocument();
        final var expectedComment = CommentObjectMother.getComment();
        when(this.commentRepository.findByShowId(eq(showId))).thenReturn(Stream.of(commentDocument));

        final var result = this.commentService.getComments(showId);

        assertEquals(List.of(expectedComment), result);
        verify(this.commentRepository, times(1)).findByShowId(eq(showId));
    }

    @Test
    @DisplayName("Return an empty list without comments")
    void getCommentsReturnsEmptyList() {
        final var showId = 83479;
        when(this.commentRepository.findByShowId(eq(showId))).thenReturn(Stream.empty());

        final var result = this.commentService.getComments(showId);

        assertEquals(List.of(), result);
        verify(this.commentRepository, times(1)).findByShowId(eq(showId));
    }

}