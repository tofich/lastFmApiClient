package ru.trusov.lastFmApiClient;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trusov.lastFmApiClient.entity.artist.Artist;
import ru.trusov.lastFmApiClient.entity.artist.Artists;
import ru.trusov.lastFmApiClient.entity.search.Results;
import ru.trusov.lastFmApiClient.entity.search.SearchTrack;
import ru.trusov.lastFmApiClient.entity.search.SearchTracks;
import ru.trusov.lastFmApiClient.entity.tag.TopTags;
import ru.trusov.lastFmApiClient.entity.tag.Tag;
import ru.trusov.lastFmApiClient.entity.track.Track;
import ru.trusov.lastFmApiClient.entity.track.Tracks;
import ru.trusov.lastFmApiClient.repository.ApiRepository;
import ru.trusov.lastFmApiClient.service.MainService;
import java.util.Arrays;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MainServiceTest {

    @InjectMocks
    MainService mainService;

    @Mock
    ApiRepository apiRepository;

    @Test
    @DisplayName("Получение сервисом сведений о треке")
    void getInfoTrack() {
        Track track = new Track();
        track.setName("testTrack");
        track.setMbid("1000");
        Artist artist = new Artist("testArtist");
        track.setArtist(artist);

        when(apiRepository.getInfoTrack("testTrack", "testArtist")).thenReturn(track);

        Assertions.assertEquals("testTrack",
                mainService.getInfoTrack("testTrack", "testArtist").getName());
        Assertions.assertEquals("1000",
                mainService.getInfoTrack("testTrack", "testArtist").getMbid());

        when(apiRepository.getInfoTrack("", "")).thenReturn(null);

        Assertions.assertNull(mainService.getInfoTrack("", ""));

        Mockito.verify(apiRepository, Mockito.times(3))
                .getInfoTrack(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    @DisplayName("Получение сервисом чарта треков")
    void getTopTracks(){
        Tracks tracks = new Tracks();
        tracks.setListTracks(Arrays.asList(
                new Track("name1", "mbid1"),
                new Track("name2","mbid2"))
        );

        when(apiRepository.getTopTracks()).thenReturn(tracks);
        Assertions.assertEquals("mbid1", mainService.getTopTracks().getListTracks().get(0).getMbid());

        when(apiRepository.getTopTracks()).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, ()->mainService.getTopTracks());

        when(apiRepository.getTopTracks()).thenThrow(NullPointerException.class);
        Assertions.assertThrows(NullPointerException.class, ()->mainService.getTopTracks());

        Mockito.verify(apiRepository, Mockito.times(3)).getTopTracks();
    }

    @Test
    @DisplayName("Получение сервисом чарта исполнителей")
    void getTopArtists(){
        Artists artists = new Artists();
        artists.setListArtists(Arrays.asList(
                new Artist("Artist1"),
                new Artist("Artist2")
        ));

        when(apiRepository.getTopArtists()).thenReturn(artists);
        Assertions.assertEquals("Artist1",
                mainService.getTopArtists().getListArtists().get(0).getName());

        when(apiRepository.getTopArtists()).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, ()->mainService.getTopArtists());

        when(apiRepository.getTopArtists()).thenThrow(NullPointerException.class);
        Assertions.assertThrows(NullPointerException.class, ()->mainService.getTopArtists());

        Mockito.verify(apiRepository, Mockito.times(3)).getTopArtists();
    }

    @Test
    @DisplayName("Получение сервисом чарта тегов")
    void getTopTags(){
        TopTags topTags = new TopTags();
        topTags.setListTags(Arrays.asList(
                new Tag("TagName1"),
                new Tag("TagName2")
        ));

        when(apiRepository.getTopTags()).thenReturn(topTags);
        Assertions.assertEquals("TagName1", mainService.getTopTags().getListTags().get(0).getName());

        when(apiRepository.getTopTags()).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, ()->mainService.getTopTags());

        when(apiRepository.getTopTags()).thenThrow(NullPointerException.class);
        Assertions.assertThrows(NullPointerException.class, ()->mainService.getTopTags());

        Mockito.verify(apiRepository, Mockito.times(3)).getTopTags();

    }

    @Test
    @DisplayName("Получение сервисом результатов поиска")
    void searchTracks(){
        SearchTracks searchTracks = new SearchTracks();
        searchTracks.setListTracks(Arrays.asList(
                new SearchTrack("name1", "mbid1"),
                new SearchTrack("name2", "mbid2")
        ));
        Results results = new Results();
        results.setSearchTracks(searchTracks);
        results.setOpensearchTotalResults("2");

        when(apiRepository.findTracks(eq("name"), Mockito.anyString())).thenReturn(results);

        Results resTest = mainService.searchTracks("name", "1");
        Assertions.assertEquals("name1", resTest.getSearchTracks().getListTracks().get(0).getName());
        Assertions.assertEquals("2", resTest.getOpensearchTotalResults());

        when(apiRepository.findTracks(eq(""), eq(""))).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, ()->mainService.searchTracks("", ""));

        when(apiRepository.findTracks(eq(""), eq(""))).thenThrow(NullPointerException.class);
        Assertions.assertThrows(NullPointerException.class, ()->mainService.searchTracks("", ""));

        Mockito.verify(apiRepository, Mockito.times(3))
                .findTracks(Mockito.anyString(), Mockito.anyString());

    }

}