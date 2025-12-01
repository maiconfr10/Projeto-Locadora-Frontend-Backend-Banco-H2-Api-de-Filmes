package entretenimento.FilmeXYZ.filme.tmdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TmdbService {

    private static final String API_KEY = "41a01132e259e374a052c63d67067377";
    private static final String URL = "https://api.themoviedb.org/3/search/movie?api_key={key}&query={query}";

    @Autowired
    private RestTemplate restTemplate;

    public TmdbMovieDTO buscarFilme(String titulo) {
        TmdbSearchResponse response = restTemplate.getForObject(
                URL,
                TmdbSearchResponse.class,
                API_KEY,
                titulo
        );

        if (response != null && response.results != null && !response.results.isEmpty()) {
            return response.results.get(0); // primeiro resultado
        }

        return null;
    }
}
