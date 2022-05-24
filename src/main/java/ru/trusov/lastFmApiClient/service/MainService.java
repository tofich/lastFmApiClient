package ru.trusov.lastFmApiClient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.trusov.lastFmApiClient.entity.search.Results;
import ru.trusov.lastFmApiClient.entity.tag.TopTags;
import ru.trusov.lastFmApiClient.entity.artist.Artists;
import ru.trusov.lastFmApiClient.entity.track.Track;
import ru.trusov.lastFmApiClient.entity.track.Tracks;
import ru.trusov.lastFmApiClient.repository.ApiRepository;
import ru.trusov.lastFmApiClient.repository.RepositoryInterface;


@Service
public class MainService implements ServiceInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainService.class);

    private RepositoryInterface repository;

    @Autowired
    public MainService(ApiRepository apiRepository) {
        this.repository = apiRepository;
    }

    @Cacheable(value = "lastFmTracks")
    public Tracks getTopTracks(){
        Tracks tracks = repository.getTopTracks();
        LOGGER.info("run apiRepository.getTopTracks()");
        /*--some business logic--*/
        tracks.setDescription("Чарт треков по версии Last.fm");
        return tracks;
    }

    public Track getInfoTrack(String name, String artist){
        Track track = repository.getInfoTrack(name, artist);
        LOGGER.info(String.format("run apiRepository.getInfoTrack(%s, %s)",name, artist));
        return track;
    }

    @Cacheable(value = "lastFmArtists")
    public Artists getTopArtists(){
        Artists artists = repository.getTopArtists();
        LOGGER.info("run apiRepository.getTopArtists()");
        /*--some business logic--*/
        artists.setDescription("Чарт исполнителей по версии Last.fm");
        return artists;
    }

    @Cacheable(value = "lastFmTags")
    public TopTags getTopTags() {
        TopTags toptags = repository.getTopTags();
        LOGGER.info("run apiRepository.getTopTags()");
        /*--some business logic--*/
        toptags.setDescription("Чарт тегов по версии Last.fm");
        return toptags;
    }

    @Cacheable(value = "lastFmSearch", key = "{#searchName,#page}")
    public Results searchTracks(String searchName, String page) {
        Results results = repository.findTracks(searchName, page);
        LOGGER.info(String.format("run apiRepository.findTracks(%s, %s)", searchName, page));
        /*--some business logic--*/
        results.getSearchTracks().setDescription("Результаты поиска \"" + searchName + "\":");
        return results;
    }
}
