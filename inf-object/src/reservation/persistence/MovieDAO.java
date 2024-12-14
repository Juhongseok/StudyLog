package reservation.persistence;

import reservation.domain.v1.Movie;

public interface MovieDAO {
    Movie selectMovie(Long movieId);

    void insert(Movie movie);
}
