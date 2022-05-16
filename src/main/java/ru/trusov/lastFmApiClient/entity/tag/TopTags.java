package ru.trusov.lastFmApiClient.entity.tag;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import java.util.List;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopTags {

    private String description;

    @JsonProperty("tag")
    private List<Tag> listTags = null;

    public List<Tag> getListTags() {
        return listTags;
    }

    public void setListTags(List<Tag> listTags) {
        this.listTags = listTags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
