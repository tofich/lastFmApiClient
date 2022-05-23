package ru.trusov.lastFmApiClient;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import ru.trusov.lastFmApiClient.entity.artist.Artist;
import ru.trusov.lastFmApiClient.entity.artist.Artists;
import ru.trusov.lastFmApiClient.entity.artist.ArtistsNode;
import ru.trusov.lastFmApiClient.entity.search.Results;
import ru.trusov.lastFmApiClient.entity.search.SearchResult;
import ru.trusov.lastFmApiClient.entity.search.SearchTrack;
import ru.trusov.lastFmApiClient.entity.search.SearchTracks;
import ru.trusov.lastFmApiClient.entity.tag.Tag;
import ru.trusov.lastFmApiClient.entity.tag.TopTags;
import ru.trusov.lastFmApiClient.entity.tag.TopTagsNode;
import ru.trusov.lastFmApiClient.entity.track.Track;
import ru.trusov.lastFmApiClient.entity.track.TrackNode;
import ru.trusov.lastFmApiClient.entity.track.Tracks;
import ru.trusov.lastFmApiClient.entity.track.TracksNode;
import ru.trusov.lastFmApiClient.repository.ApiRepository;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApiRepositoryTest {

    @InjectMocks
    ApiRepository apiRepository;

    @Mock
    RestTemplate restTemplate;

    @BeforeEach
    void init(){
        ReflectionTestUtils.setField(apiRepository, "API_KEY", "123");
        ReflectionTestUtils.setField(apiRepository, "API_URI", "http://testapi/");
    }

    @Test
    @DisplayName("Получение репозиторием чарта треков")
    void getTopTracks(){
        Tracks tracks = new Tracks();
        tracks.setListTracks(Arrays.asList(
                new Track("name1", "mbid1"),
                new Track("name2","mbid2"))
        );
        TracksNode tracksNode = new TracksNode(tracks);

        when(restTemplate.getForObject(Mockito.anyString(),
                ArgumentMatchers.eq(TracksNode.class))).thenReturn(tracksNode);
        Assertions.assertEquals("mbid1",
                apiRepository.getTopTracks().getListTracks().get(0).getMbid());

        when(restTemplate.getForObject(Mockito.anyString(),
                ArgumentMatchers.eq(TracksNode.class))).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, ()->apiRepository.getTopTracks());

        when(restTemplate.getForObject(Mockito.anyString(),
                ArgumentMatchers.eq(TracksNode.class)))
                .thenThrow(RestClientResponseException.class);
        Assertions.assertThrows(RestClientResponseException.class, ()->apiRepository.getTopTracks());

        Mockito.verify(restTemplate, Mockito.times(3))
                .getForObject(Mockito.anyString(), ArgumentMatchers.eq(TracksNode.class));
    }

    @Test
    @DisplayName("Получение репозиторием чарта исполнителей")
    void getTopArtists() {
        Artists artists = new Artists();
        artists.setListArtists(Arrays.asList(
                new Artist("Artist1"),
                new Artist("Artist2")
        ));
        ArtistsNode artistsNode = new ArtistsNode(artists);

        when(restTemplate.getForObject(Mockito.anyString(),
                ArgumentMatchers.eq(ArtistsNode.class))).thenReturn(artistsNode);
        Assertions.assertEquals("Artist1",
                apiRepository.getTopArtists().getListArtists().get(0).getName());

        when(restTemplate.getForObject(Mockito.anyString(),
                ArgumentMatchers.eq(ArtistsNode.class)))
                .thenThrow(RestClientResponseException.class);
        Assertions.assertThrows(RestClientResponseException.class, ()->apiRepository.getTopArtists());

        Mockito.verify(restTemplate, Mockito.times(2))
                .getForObject(Mockito.anyString(), ArgumentMatchers.eq(ArtistsNode.class));

    }

    @Test
    @DisplayName("Получение репозиторием чарта тегов")
    void getTopTags() {
        TopTags topTags = new TopTags();
        topTags.setListTags(Arrays.asList(
                new Tag("TagName1"),
                new Tag("TagName2")
        ));
        TopTagsNode topTagsNode = new TopTagsNode(topTags);

        when(restTemplate.getForObject(Mockito.anyString(),
                ArgumentMatchers.eq(TopTagsNode.class))).thenReturn(topTagsNode);
        Assertions.assertEquals("TagName1",
                apiRepository.getTopTags().getListTags().get(0).getName());

        when(restTemplate.getForObject(Mockito.anyString(),
                ArgumentMatchers.eq(TopTagsNode.class)))
                .thenThrow(RestClientResponseException.class);
        Assertions.assertThrows(RestClientResponseException.class, ()->apiRepository.getTopTags());

        Mockito.verify(restTemplate, Mockito.times(2))
                .getForObject(Mockito.anyString(), ArgumentMatchers.eq(TopTagsNode.class));

    }

    @Test
    @DisplayName("Получение репозиторием результатов поиска")
    void findTracks() {
        SearchTracks searchTracks = new SearchTracks();
        searchTracks.setListTracks(Arrays.asList(
                new SearchTrack("name1", "mbid1"),
                new SearchTrack("name2", "mbid2")
        ));
        Results results = new Results();
        results.setSearchTracks(searchTracks);
        results.setOpensearchTotalResults("2");
        SearchResult searchResult = new SearchResult(results);

        when(restTemplate.getForObject(Mockito.anyString(),
                ArgumentMatchers.eq(SearchResult.class))).thenReturn(searchResult);
        Assertions.assertEquals("name1",
                apiRepository.findTracks(Mockito.anyString(), Mockito.anyString())
                        .getSearchTracks().getListTracks().get(0).getName());

        when(restTemplate.getForObject(Mockito.anyString(),
                ArgumentMatchers.eq(SearchResult.class)))
                .thenThrow(RestClientResponseException.class);
        Assertions.assertThrows(RestClientResponseException.class,
                () -> apiRepository.findTracks(Mockito.anyString(), Mockito.anyString()));

        Mockito.verify(restTemplate, Mockito.times(2))
                .getForObject(Mockito.anyString(), ArgumentMatchers.eq(SearchResult.class));
    }

    @Test
    @DisplayName("Получение репозиторием информации о треке")
    void getInfoTrack() {
        SearchTrack track = new SearchTrack();
        track.setName("testTrack");
        track.setMbid("1000");
        Artist artist = new Artist("testArtist");
        track.setArtist(artist);
        TrackNode trackNode = new TrackNode(track);

        when(restTemplate.getForObject(Mockito.anyString(),
                ArgumentMatchers.eq(TrackNode.class))).thenReturn(trackNode);
        Track infoTrack = apiRepository.getInfoTrack(Mockito.anyString(), Mockito.anyString());
        Assertions.assertEquals("testTrack", infoTrack.getName());
        Assertions.assertEquals("testArtist", infoTrack.getArtist().getName());


        when(restTemplate.getForObject(Mockito.anyString(),
                ArgumentMatchers.eq(TrackNode.class)))
                .thenThrow(RestClientResponseException.class);
        Assertions.assertThrows(RestClientResponseException.class,
                () -> apiRepository.getInfoTrack(Mockito.anyString(), Mockito.anyString()));

        Mockito.verify(restTemplate, Mockito.times(2))
                .getForObject(Mockito.anyString(), ArgumentMatchers.eq(TrackNode.class));
    }

}
