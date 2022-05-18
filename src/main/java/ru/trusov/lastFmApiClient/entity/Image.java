package ru.trusov.lastFmApiClient.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import java.io.Serializable;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image implements Serializable {

    @JsonProperty("#text")
    private String text;
    @JsonProperty("size")
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
