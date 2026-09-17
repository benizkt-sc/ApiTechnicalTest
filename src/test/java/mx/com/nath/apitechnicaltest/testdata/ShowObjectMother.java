package mx.com.nath.apitechnicaltest.testdata;

import lombok.experimental.UtilityClass;
import mx.com.nath.apitechnicaltest.client.model.ShowContent;
import mx.com.nath.apitechnicaltest.client.model.ShowWrapperResponse;
import mx.com.nath.apitechnicaltest.model.Show;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@UtilityClass
public class ShowObjectMother {

    public static List<Show> getListShowOneElement() {
        final var show = new Show();
        show.setId(83479);
        show.setName("Necronomico no Cosmic Horror Show");
        show.setChannel(null);
        show.setGenres(Set.of("Comedy", "Anime", "Science-Fiction"));
        show.setSummary(
                """
                        \u003Cp\u003EMiko Kurono, also known as Necronomico, 
                        graduates from middle school and decides to pursue her dreams 
                        of becoming a streamer. One day, as she's with her childhood friend, 
                        Mayu Mayusaka, and her rival, Kanna Kagurasaka, she accepts an offer 
                        to play a new VR game. The game gives Miko the opportunity to take 
                        on mysterious challenges with other unique streamers she meets 
                        within the VR world.\u003C/p\u003E
                        """
        );
        return List.of(show);
    }

    public static List<ShowWrapperResponse> getListShowWrapperResponseOneElement() {
        return List.of(
                new ShowWrapperResponse(
                        new BigDecimal("0.7595862"),
                        new ShowContent(
                                83479,
                                "Necronomico no Cosmic Horror Show",
                                null,
                                """
                                        \u003Cp\u003EMiko Kurono, also known as Necronomico, 
                                        graduates from middle school and decides to pursue her dreams 
                                        of becoming a streamer. One day, as she's with her childhood friend, 
                                        Mayu Mayusaka, and her rival, Kanna Kagurasaka, she accepts an offer 
                                        to play a new VR game. The game gives Miko the opportunity to take 
                                        on mysterious challenges with other unique streamers she meets 
                                        within the VR world.\u003C/p\u003E
                                        """,
                                Set.of("Comedy", "Anime", "Science-Fiction")
                        )
                )
        );
    }


}
