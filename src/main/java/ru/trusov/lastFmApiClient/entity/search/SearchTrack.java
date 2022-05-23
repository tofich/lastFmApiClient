package ru.trusov.lastFmApiClient.entity.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import ru.trusov.lastFmApiClient.entity.track.Track;
import java.io.Serializable;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchTrack extends Track implements Serializable {

    @JsonProperty("artist")
    private String nameArtist;

    public SearchTrack(String name, String mbid) {
        super(name, mbid);
    }

    public SearchTrack() {
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }
}
