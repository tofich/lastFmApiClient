package ru.trusov.lastFmApiClient.entity.tag;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopTagsNode {

    @JsonProperty("tags")
    TopTags toptags;

    public TopTags getToptags() {
        return toptags;
    }

    public void setToptags(TopTags toptags) {
        this.toptags = toptags;
    }
}
