package mx.com.nath.apitechnicaltest.client;

import mx.com.nath.apitechnicaltest.client.model.ShowWrapperResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange(url = "${api-tv-maze-url}")
public interface TvMazeClient {

    @GetExchange("/search/shows")
    List<ShowWrapperResponse> findShow(@RequestParam("q") String q);

}
