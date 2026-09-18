package mx.com.nath.apitechnicaltest.mapper;

import mx.com.nath.apitechnicaltest.model.AddCommentRequest;
import mx.com.nath.apitechnicaltest.model.Comment;
import mx.com.nath.apitechnicaltest.model.CommentDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment toComment(CommentDocument commentDocument);

    @Mapping(target = "showId", source = "showId")
    CommentDocument toCommentDocument(int showId, AddCommentRequest request);

}