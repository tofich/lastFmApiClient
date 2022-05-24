package ru.trusov.lastFmApiClient.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.trusov.lastFmApiClient.entity.search.Results;
import ru.trusov.lastFmApiClient.entity.search.SearchResult;
import ru.trusov.lastFmApiClient.entity.tag.TopTags;
import ru.trusov.lastFmApiClient.entity.artist.Artists;
import ru.trusov.lastFmApiClient.entity.artist.ArtistsNode;
import ru.trusov.lastFmApiClient.entity.tag.TopTagsNode;
import ru.trusov.lastFmApiClient.entity.track.Track;
import ru.trusov.lastFmApiClient.entity.track.TrackNode;
import ru.trusov.lastFmApiClient.entity.track.Tracks;
import ru.trusov.lastFmApiClient.entity.track.TracksNode;

@Repository
public class ApiRepository implements RepositoryInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiRepository.class);

    @Value("${api_uri}")
    private String API_URI;

    @Value("${api_key}")
    String API_KEY;

    private RestTemplate restTemplate;

    @Autowired
    public ApiRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Tracks getTopTracks(){
        String url = UriComponentsBuilder
                .fromUriString(API_URI)
                .queryParam("method", "chart.gettoptracks")
                .queryParam("api_key", API_KEY)
                .queryParam("format", "json")
                .build().toString();
        LOGGER.info("-------getTopTracks url: " + url);
        Tracks tracks = restTemplate.getForObject(url, TracksNode.class).getTracks();
        LOGGER.info(tracks.toString());
        return tracks;
    }

    public Track getInfoTrack(String name, String artist){
        String url = UriComponentsBuilder
                .fromUriString("http://ws.audioscrobbler.com/2.0/")
                .queryParam("method", "track.getInfo")
                .queryParam("api_key", API_KEY)
                .queryParam("artist", artist)
                .queryParam("track", name)
                .queryParam("format", "json")
                .build().toString();
        LOGGER.info("-------getInfoTrack url: " + url);
        Track track = restTemplate.getForObject(url, TrackNode.class).getTrack();
        LOGGER.info(track.toString());
        return track;
    }

    public Artists getTopArtists() {
        String url = UriComponentsBuilder
                .fromUriString(API_URI)
                .queryParam("method", "chart.gettopartists")
                .queryParam("api_key", API_KEY)
                .queryParam("format", "json")
                .build().toString();
        LOGGER.info("-------getTopArtists url: " + url);
        ArtistsNode artists = restTemplate.getForObject(url, ArtistsNode.class);
        LOGGER.info(artists.toString());
        return artists.getArtists();
    }

    public TopTags getTopTags() {
        String url = UriComponentsBuilder
                .fromUriString(API_URI)
                .queryParam("method", "chart.gettoptags")
                .queryParam("api_key", API_KEY)
                .queryParam("format", "json")
                .build().toString();
        LOGGER.info("-------getTopTags url: " + url);
        TopTags toptags = restTemplate.getForObject(url, TopTagsNode.class).getToptags();
        LOGGER.info(toptags.toString());
        return toptags;
    }

    public Results findTracks(String searchName, String page) {
        String url = UriComponentsBuilder
                .fromUriString(API_URI)
                .queryParam("method", "track.search")
                .queryParam("track", searchName)
                .queryParam("page", page)
                .queryParam("api_key", API_KEY)
                .queryParam("format", "json")
                .build().toString();
        LOGGER.info("-------findTracks url: " + url);
        Results results = restTemplate.getForObject(url, SearchResult.class).getResults();
        LOGGER.info(results.toString());
        return results;
    }
}
