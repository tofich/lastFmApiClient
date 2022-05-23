package ru.trusov.lastFmApiClient.entity.track;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TracksNode {

    private Tracks tracks;

    public TracksNode(Tracks tracks) {
        this.tracks = tracks;
    }

    public TracksNode() {
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }
}
