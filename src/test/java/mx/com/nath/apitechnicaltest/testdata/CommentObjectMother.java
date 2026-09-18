package mx.com.nath.apitechnicaltest.testdata;

import lombok.experimental.UtilityClass;
import mx.com.nath.apitechnicaltest.model.Comment;
import mx.com.nath.apitechnicaltest.model.CommentDocument;

@UtilityClass
public class CommentObjectMother {

    public static Comment getComment() {
        final var comment = new Comment();
        comment.setShowId(83479);
        comment.setComment("This is a great show!");
        comment.setRating(5);
        return comment;
    }

    public static CommentDocument getCommentDocument() {
        final var commentDocument = new CommentDocument();
        commentDocument.setShowId(83479);
        commentDocument.setComment("This is a great show!");
        commentDocument.setRating(5);
        return commentDocument;
    }


}