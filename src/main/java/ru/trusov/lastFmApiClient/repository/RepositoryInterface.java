package ru.trusov.lastFmApiClient.repository;

import ru.trusov.lastFmApiClient.entity.artist.Artists;
import ru.trusov.lastFmApiClient.entity.search.Results;
import ru.trusov.lastFmApiClient.entity.tag.TopTags;
import ru.trusov.lastFmApiClient.entity.track.Track;
import ru.trusov.lastFmApiClient.entity.track.Tracks;

public interface RepositoryInterface {

    public Tracks getTopTracks();

    public Track getInfoTrack(String name, String artist);

    public Artists getTopArtists();

    public TopTags getTopTags();

    public Results findTracks(String searchName, String page);

}
