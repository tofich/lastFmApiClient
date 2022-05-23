package ru.trusov.lastFmApiClient.entity.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;

@ToString
public class SearchTracks implements Serializable {

    private String description;

    @JsonProperty("track")
    private List<SearchTrack> listTracks;

    public SearchTracks(List<SearchTrack> listTracks) {
        this.listTracks = listTracks;
    }

    public SearchTracks() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SearchTrack> getListTracks() {
        return listTracks;
    }

    public void setListTracks(List<SearchTrack> listTracks) {
        this.listTracks = listTracks;
    }
}
