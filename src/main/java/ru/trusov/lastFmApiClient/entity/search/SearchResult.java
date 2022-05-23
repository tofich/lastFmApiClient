package ru.trusov.lastFmApiClient.entity.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult implements Serializable {

    @JsonProperty("results")
    private Results results;

    public SearchResult(Results results) {
        this.results = results;
    }

    public SearchResult() {
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
