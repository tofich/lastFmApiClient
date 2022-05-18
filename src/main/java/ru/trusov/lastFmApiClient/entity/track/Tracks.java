package ru.trusov.lastFmApiClient.entity.track;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Tracks implements Serializable {

    private String description;

    @JsonProperty("track")
    private List<Track> listTracks;

    @JsonProperty("track")
    public List<Track> getListTracks() {
        return listTracks;
    }

    @JsonProperty("track")
    public void setListTracks(List<Track> listTracks) {
        this.listTracks = listTracks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Tracks{" +
                "description='" + description + '\'' +
                ", track=" + Arrays.toString(listTracks.toArray()) +
                '}';
    }

}
