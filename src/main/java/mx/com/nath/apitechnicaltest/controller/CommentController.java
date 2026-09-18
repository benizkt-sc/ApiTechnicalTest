package mx.com.nath.apitechnicaltest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.nath.apitechnicaltest.model.AddCommentRequest;
import mx.com.nath.apitechnicaltest.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/shows/{showId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> addComment(
            @PathVariable("showId") final int showId,
            @RequestBody @Validated final AddCommentRequest request
    ) {
        this.commentService.addComment(showId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
