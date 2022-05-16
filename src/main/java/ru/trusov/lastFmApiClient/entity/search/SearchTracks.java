package ru.trusov.lastFmApiClient.entity.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import ru.trusov.lastFmApiClient.entity.track.Track;

import java.util.List;

@ToString
public class SearchTracks {

    private String description;

    @JsonProperty("track")
    private List<SearchTrack> listTracks;

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
