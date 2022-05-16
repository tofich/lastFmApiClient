package ru.trusov.lastFmApiClient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trusov.lastFmApiClient.entity.search.SearchTracks;
import ru.trusov.lastFmApiClient.entity.tag.TopTags;
import ru.trusov.lastFmApiClient.entity.artist.Artists;
import ru.trusov.lastFmApiClient.entity.track.Track;
import ru.trusov.lastFmApiClient.entity.track.Tracks;
import ru.trusov.lastFmApiClient.repository.ApiRepository;

@Service
public class MainService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainService.class);

    private ApiRepository apiRepository;

    @Autowired
    public void setApiRepository(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    public Tracks getTopTracks(){
        Tracks tracks = apiRepository.getTopTracks();
        /*--some business logic--*/
        tracks.setDescription("Чарт треков по версии Last.fm");
        return tracks;
    }

    public Track getInfoTrack(String name, String artist){
        Track track = apiRepository.getInfoTrack(name, artist);
        return track;
    }

    public Artists getTopArtists(){
        Artists artists = apiRepository.getTopArtists();
        /*--some business logic--*/
        artists.setDescription("Чарт исполнителей по версии Last.fm");
        return artists;
    }

    public TopTags getTopTags() {
        TopTags toptags = apiRepository.getTopTags();
        /*--some business logic--*/
        toptags.setDescription("Чарт тегов по версии Last.fm");
        return toptags;
    }

    public SearchTracks searchTracks(String searchName, String page) {
        SearchTracks tracks = apiRepository.findTracks(searchName, page);
        /*--some business logic--*/
        tracks.setDescription("Результаты поиска \"" + searchName + "\":");
        return tracks;
    }
}
