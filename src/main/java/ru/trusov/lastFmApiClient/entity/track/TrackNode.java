package ru.trusov.lastFmApiClient.entity.track;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackNode {

    @JsonProperty("track")
    private Track track;

    public TrackNode(Track track) {
        this.track = track;
    }

    public TrackNode() {
    }

    @JsonProperty("track")
    public Track getTrack() {
        return track;
    }

    @JsonProperty("track")
    public void setTrack(Track track) {
        this.track = track;
    }

}
