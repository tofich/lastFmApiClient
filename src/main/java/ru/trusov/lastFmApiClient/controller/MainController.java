package ru.trusov.lastFmApiClient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trusov.lastFmApiClient.entity.search.Results;
import ru.trusov.lastFmApiClient.entity.tag.TopTags;
import ru.trusov.lastFmApiClient.entity.artist.Artists;
import ru.trusov.lastFmApiClient.entity.track.Track;
import ru.trusov.lastFmApiClient.entity.track.Tracks;
import ru.trusov.lastFmApiClient.service.MainService;
import ru.trusov.lastFmApiClient.service.ServiceInterface;

@Controller
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    ServiceInterface service;

    @Autowired
    public MainController(MainService mainService) {
        this.service = mainService;
    }

    @GetMapping("/")
    public String showHomePage() {
        LOGGER.info("Start showHomePage() IN /");
        return "index";
    }

    @GetMapping("/chart/toptracks")
    public String showTopTracks(Model model) {
        LOGGER.info("Start showTopTracks() IN /chart/toptracks");
        Tracks tracks = service.getTopTracks();
        model.addAttribute("tracks", tracks);
        return "topTracks";
    }

    @GetMapping("/chart/topartists")
    public String showTopArtists(Model model) {
        LOGGER.info("Start showTopArtists() IN /chart/topartists");
        Artists artists = service.getTopArtists();
        model.addAttribute("artists", artists);
        return "topArtists";
    }

    @GetMapping("/chart/toptags")
    public String showTopTags(Model model) {
        LOGGER.info("Start showTopTags() IN /chart/toptags");
        TopTags toptags = service.getTopTags();
        model.addAttribute("toptags", toptags);
        return "topTags";
    }

    @PostMapping("/track/getinfo")
    public String getTrackInfo(@ModelAttribute("name") String name, @ModelAttribute("artist") String artist, Model model){
        LOGGER.info("Start getTrackInfo() IN /track/getinfo");
        Track track = service.getInfoTrack(name, artist);
        model.addAttribute("track", track);
        return "trackInfo";
    }

    @GetMapping("/track/search")
    public String searchTracks(@RequestParam String search, @RequestParam(required = false) String page, Model model){
        LOGGER.info("Start searchTracks() IN /track/search");
        if (page == null) page="1";
        Results results = service.searchTracks(search , page);
        model.addAttribute("results", results);
        return "searchTrack";
    }

}
