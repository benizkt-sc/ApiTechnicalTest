package mx.com.nath.apitechnicaltest.service.impl;

import lombok.RequiredArgsConstructor;
import mx.com.nath.apitechnicaltest.mapper.CommentMapper;
import mx.com.nath.apitechnicaltest.model.AddCommentRequest;
import mx.com.nath.apitechnicaltest.model.Comment;
import mx.com.nath.apitechnicaltest.repository.CommentRepository;
import mx.com.nath.apitechnicaltest.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void addComment(int showId, AddCommentRequest request) {
        final var commentDocument = CommentMapper.INSTANCE.toCommentDocument(showId, request);
        this.commentRepository.save(commentDocument);
    }

    @Override
    public List<Comment> getComments(int showId) {
        return this.commentRepository.findByShowId(showId)
                .map(CommentMapper.INSTANCE::toComment)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}