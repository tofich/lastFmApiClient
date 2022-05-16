package ru.trusov.lastFmApiClient.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import java.time.LocalDateTime;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wiki {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy, HH:mm", locale = "en")
    private LocalDateTime published;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("content")
    private String content;

    public LocalDateTime getPublished() {
        return published;
    }

    public void setPublished(LocalDateTime published) {
        this.published = published;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
