package mx.com.nath.apitechnicaltest.service;

import mx.com.nath.apitechnicaltest.model.Show;

import java.util.List;

public interface ShowService {

    List<Show> findShow(String query);

}