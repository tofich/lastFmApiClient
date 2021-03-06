package ru.trusov.lastFmApiClient.entity.tag;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import java.io.Serializable;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag implements Serializable {

    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
    @JsonProperty("taggings")
    private String taggings;

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {
    }

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

    public String getTaggings() {
        return taggings;
    }

    public void setTaggings(String taggings) {
        this.taggings = taggings;
    }
}
