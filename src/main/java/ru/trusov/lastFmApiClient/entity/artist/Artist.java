package ru.trusov.lastFmApiClient.entity.artist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import ru.trusov.lastFmApiClient.entity.Image;
import ru.trusov.lastFmApiClient.entity.tag.TopTags;

import java.util.List;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {

    private String name;
    private String url;
    private List<Image> image;
    private List<Artist> similar;
    private TopTags topTags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
