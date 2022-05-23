package ru.trusov.lastFmApiClient.entity.artist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtistsNode {

    private Artists artists;

    public ArtistsNode(Artists artists) {
        this.artists = artists;
    }

    public ArtistsNode() {
    }

    public Artists getArtists() {
        return artists;
    }

    public void setArtists(Artists artists) {
        this.artists = artists;
    }
}
