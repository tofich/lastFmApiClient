package ru.trusov.lastFmApiClient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import ru.trusov.lastFmApiClient.entity.search.SearchTracks;
import ru.trusov.lastFmApiClient.entity.tag.TopTags;
import ru.trusov.lastFmApiClient.entity.artist.Artists;
import ru.trusov.lastFmApiClient.entity.track.Track;
import ru.trusov.lastFmApiClient.entity.track.Tracks;
import ru.trusov.lastFmApiClient.service.MainService;
import java.net.UnknownHostException;

@Controller
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    MainService mainService;

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/")
    public String showHomePage() {
        LOGGER.info("Start showHomePage() IN /");
        return "index";
    }

    @GetMapping("/chart/toptracks")
    public String showTopTracks(Model model) {
        LOGGER.info("Start showTopTracks() IN /chart/toptracks");
        Tracks tracks = mainService.getTopTracks();
        model.addAttribute("tracks", tracks);
        return "topTracks";
    }

    @GetMapping("/chart/topartists")
    public String showTopArtists(Model model) {
        LOGGER.info("Start showTopArtists() IN /chart/topartists");
        Artists artists = mainService.getTopArtists();
        model.addAttribute("artists", artists);
        return "topArtists";
    }

    @GetMapping("/chart/toptags")
    public String showTopTags(Model model) {
        LOGGER.info("Start showTopTags() IN /chart/toptags");
        TopTags toptags = mainService.getTopTags();
        model.addAttribute("toptags", toptags);
        return "topTags";
    }

    @PostMapping("/track/getinfo")
    public String getTrackInfo(@ModelAttribute("name") String name, @ModelAttribute("artist") String artist, Model model){
        LOGGER.info("Start getTrackInfo() IN /track/getinfo");
        Track track = mainService.getInfoTrack(name, artist);
        model.addAttribute("track", track);
        return "trackInfo";
    }

    @GetMapping("/track/search")
    public String searchTracks(@RequestParam String search, @RequestParam(required = false) String page, Model model){
        LOGGER.info("Start searchTracks() IN /track/search");
        if (null == page) page="1";
        SearchTracks tracks = mainService.searchTracks(search , page);
        model.addAttribute("tracks", tracks);
        return "searchTrack";
    }

    @ExceptionHandler(UnknownHostException.class)
    public String unknownHostException(UnknownHostException ex, Model model){
        LOGGER.error("UnknownHostException", ex);
        model.addAttribute("ex", ex);
        model.addAttribute("message", "Проверьте соединение с интернетом или повторите позднее.");
        return "errorPage";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String httpClientErrorException(HttpClientErrorException ex, Model model){
        LOGGER.error("HttpClientErrorException", ex);
        LOGGER.warn("-----" + ex.getMessage() + " with code " + ex.getStatusCode().toString());
        model.addAttribute("ex", ex);
        model.addAttribute("message", ex.getMessage());
        return "errorPage";

    }

    @ExceptionHandler(NullPointerException.class)
    public String httpNullPointerException(NullPointerException ex, Model model){
        LOGGER.error("NullPointerException", ex);
        model.addAttribute("ex", ex);
        model.addAttribute("message", "Ничего не найдено, повторите попытку или измените запрос.");
        return "errorPage";
    }

}
