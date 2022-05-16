package ru.trusov.lastFmApiClient.entity.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import ru.trusov.lastFmApiClient.entity.Image;
import ru.trusov.lastFmApiClient.entity.artist.Artist;
import ru.trusov.lastFmApiClient.entity.track.Track;

import java.util.List;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchTrack extends Track {

    @JsonProperty("artist")
    private String nameArtist;

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }
}
