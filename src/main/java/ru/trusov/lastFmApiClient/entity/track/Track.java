package ru.trusov.lastFmApiClient.entity.track;

import lombok.ToString;
import ru.trusov.lastFmApiClient.entity.Album;
import ru.trusov.lastFmApiClient.entity.Image;
import ru.trusov.lastFmApiClient.entity.artist.Artist;
import ru.trusov.lastFmApiClient.entity.tag.TopTags;
import ru.trusov.lastFmApiClient.entity.Wiki;
import java.util.List;

@ToString
public class Track {

    private String name;
    private String mbid;
    private String duration;
    private String playcount;
    private String listeners;
    private String url;
    private Artist artist;
    private Album album;
    private TopTags toptags;
    private Wiki wiki;
    private List<Image> image;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }

    public TopTags getToptags() {
        return toptags;
    }

    public void setToptags(TopTags toptags) {
        this.toptags = toptags;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }
}
