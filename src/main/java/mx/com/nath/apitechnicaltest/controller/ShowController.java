package mx.com.nath.apitechnicaltest.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.nath.apitechnicaltest.client.model.ShowContent;
import mx.com.nath.apitechnicaltest.model.Show;
import mx.com.nath.apitechnicaltest.service.ShowService;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @GetMapping
    ResponseEntity<List<Show>> searchShow(
            @RequestParam(name = "search_query") final String searchQuery) {
        return ok(this.showService.findShow(searchQuery));
    }

    @GetMapping("/{showId}")
    public ResponseEntity<Void> getShow(@PathVariable("showId") final int showId) {
        return null;
    }

}
