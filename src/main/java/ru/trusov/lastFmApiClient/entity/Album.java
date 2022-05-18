package ru.trusov.lastFmApiClient.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Album implements Serializable {

    @JsonProperty("artist")
    private String artist;
    @JsonProperty("title")
    private String title;
    @JsonProperty("mbid")
    private String mbid;
    @JsonProperty("url")
    private String url;
    @JsonProperty("image")
    private List<Image> image;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }
}
