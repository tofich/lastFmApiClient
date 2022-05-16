package ru.trusov.lastFmApiClient.entity.artist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.List;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artists {

    private String description;

    @JsonProperty("artist")
    private List<Artist> listArtists;

    public List<Artist> getListArtists() {
        return listArtists;
    }

    public void setListArtists(List<Artist> listArtists) {
        this.listArtists = listArtists;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
