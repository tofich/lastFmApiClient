package ru.trusov.lastFmApiClient.entity.track;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.trusov.lastFmApiClient.entity.track.Track;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackNode {

    @JsonProperty("track")
    private Track track;

    @JsonProperty("track")
    public Track getTrack() {
        return track;
    }

    @JsonProperty("track")
    public void setTrack(Track track) {
        this.track = track;
    }

}
