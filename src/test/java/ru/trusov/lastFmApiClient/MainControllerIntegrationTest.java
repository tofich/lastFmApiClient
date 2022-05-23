package ru.trusov.lastFmApiClient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.trusov.lastFmApiClient.config.Config;
import ru.trusov.lastFmApiClient.controller.MainController;
import ru.trusov.lastFmApiClient.entity.artist.Artist;
import ru.trusov.lastFmApiClient.entity.artist.Artists;
import ru.trusov.lastFmApiClient.entity.search.Results;
import ru.trusov.lastFmApiClient.entity.search.SearchTrack;
import ru.trusov.lastFmApiClient.entity.search.SearchTracks;
import ru.trusov.lastFmApiClient.entity.tag.Tag;
import ru.trusov.lastFmApiClient.entity.tag.TopTags;
import ru.trusov.lastFmApiClient.entity.track.Track;
import ru.trusov.lastFmApiClient.entity.track.Tracks;
import ru.trusov.lastFmApiClient.service.MainService;
import javax.servlet.ServletContext;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { Config.class })
@TestPropertySource(locations = {"/testapplication.properties"})
@WebAppConfiguration
public class MainControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MainController mainController;

    @MockBean
    private MainService mainService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @DisplayName("Проверка получения WebAppContext и наличие в нем контроллера")
    public void testWebAppContext_andProvidesMainController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assertions.assertNotNull(servletContext);
        Assertions.assertTrue(servletContext instanceof MockServletContext);
        Assertions.assertNotNull(webApplicationContext.getBean("mainController"));
    }

    @Test
    @DisplayName("Получение главной страницы")
    public void testShowIndex() throws Exception{

        RequestBuilder rb = MockMvcRequestBuilders.get("/");
        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.view().name("index"));

    }

    @Test
    @DisplayName("Получение страницы чарта треков")
    void testShowTopTracks() throws Exception{
        Tracks tracks = new Tracks();
        tracks.setListTracks(Arrays.asList(
                new Track("name1", "mbid1"),
                new Track("name2", "mbid2")
                )
        );
        tracks.setDescription("TestDescription");

        when(mainService.getTopTracks()).thenReturn(tracks);

        RequestBuilder rb = MockMvcRequestBuilders.get("/chart/toptracks");
        MvcResult mvcResult = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.view().name("topTracks"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tracks"))
                .andReturn();

        Assertions.assertNotNull(mvcResult.getModelAndView().getModel().get("tracks"));
    }

    @Test
    @DisplayName("Получение страницы чарта исполнителей")
    void testShowTopArtists() throws Exception{
        Artists artists = new Artists();
        artists.setListArtists(Arrays.asList(
                new Artist("Artist1"),
                new Artist("Artist2")
        ));

        when(mainService.getTopArtists()).thenReturn(artists);

        RequestBuilder rb = MockMvcRequestBuilders.get("/chart/topartists");
        MvcResult mvcResult = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.view().name("topArtists"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("artists"))
                .andReturn();

        Assertions.assertNotNull(mvcResult.getModelAndView().getModel().get("artists"));
    }

    @Test
    @DisplayName("Получение страницы чарта тегов")
    void testShowTopTags() throws Exception{
        TopTags topTags = new TopTags();
        topTags.setListTags(Arrays.asList(
                new Tag("TagName1"),
                new Tag("TagName2")
        ));

        when(mainService.getTopTags()).thenReturn(topTags);

        RequestBuilder rb = MockMvcRequestBuilders.get("/chart/toptags");
        MvcResult mvcResult = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.view().name("topTags"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("toptags"))
                .andReturn();

        Assertions.assertNotNull(mvcResult.getModelAndView().getModel().get("toptags"));
    }

    @Test
    @DisplayName("Получение страницы с описанием трека")
    void testGetTrackInfo() throws Exception{
        Track track = new Track();
        track.setName("testTrack");
        track.setMbid("1000");
        Artist artist = new Artist("testArtist");
        track.setArtist(artist);

        when(mainService.getInfoTrack(Mockito.anyString(), Mockito.anyString())).thenReturn(track);

        RequestBuilder rb = MockMvcRequestBuilders.post("/track/getinfo");
        MvcResult mvcResult = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.view().name("trackInfo"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("track"))
                .andReturn();

        Assertions.assertNotNull(mvcResult.getModelAndView().getModel().get("track"));

    }

    @Test
    @DisplayName("Получение страницы с информацией о треке")
    void testSearchTracks() throws Exception{
        SearchTracks searchTracks = new SearchTracks();
        searchTracks.setListTracks(Arrays.asList(
                new SearchTrack("name1", "mbid1"),
                new SearchTrack("name2", "mbid2")
        ));
        Results results = new Results();
        results.setSearchTracks(searchTracks);

        when(mainService.searchTracks("name1", "2")).thenReturn(results);

        RequestBuilder rb = MockMvcRequestBuilders.get("/track/search")
                .param("search","name1")
                .param("page","2");
        MvcResult mvcResult = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.view().name("searchTrack"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("results"))
                .andReturn();

        Assertions.assertNotNull(mvcResult.getModelAndView().getModel().get("results"));
    }


}
