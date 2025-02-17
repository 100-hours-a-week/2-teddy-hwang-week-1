package service;

import model.Movie;
import repository.MovieRepository;

import java.util.List;

public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void showMovieList() {
        List<Movie> movies = movieRepository.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println("현재 상영 중인 영화가 없습니다.");
            return;
        }
        System.out.println("\n🎬 현재 상영 중인 영화 목록 🎬");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}
