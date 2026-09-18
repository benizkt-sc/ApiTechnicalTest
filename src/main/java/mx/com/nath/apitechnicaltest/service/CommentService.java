package mx.com.nath.apitechnicaltest.service;

import mx.com.nath.apitechnicaltest.model.AddCommentRequest;
import mx.com.nath.apitechnicaltest.model.Comment;

import java.util.List;

public interface CommentService {

    void addComment(int showId, AddCommentRequest request);

    List<Comment> getComments(int showId);

}
