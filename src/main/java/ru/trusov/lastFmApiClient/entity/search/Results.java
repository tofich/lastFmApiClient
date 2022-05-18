package ru.trusov.lastFmApiClient.entity.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Results implements Serializable {

    @JsonProperty("opensearch:totalResults")
    private String opensearchTotalResults;
    @JsonProperty("opensearch:startIndex")
    private String opensearchStartIndex;
    @JsonProperty("opensearch:itemsPerPage")
    private String opensearchItemsPerPage;
    @JsonProperty("trackmatches")
    private SearchTracks searchTracks;

    public String getOpensearchTotalResults() {
        return opensearchTotalResults;
    }

    public void setOpensearchTotalResults(String opensearchTotalResults) {
        this.opensearchTotalResults = opensearchTotalResults;
    }

    public String getOpensearchStartIndex() {
        return opensearchStartIndex;
    }

    public void setOpensearchStartIndex(String opensearchStartIndex) {
        this.opensearchStartIndex = opensearchStartIndex;
    }

    public String getOpensearchItemsPerPage() {
        return opensearchItemsPerPage;
    }

    public void setOpensearchItemsPerPage(String opensearchItemsPerPage) {
        this.opensearchItemsPerPage = opensearchItemsPerPage;
    }

    public SearchTracks getSearchTracks() {
        return searchTracks;
    }

    public void setSearchTracks(SearchTracks searchTracks) {
        this.searchTracks = searchTracks;
    }
}
