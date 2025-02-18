package model;

import java.util.ArrayList;
import java.util.List;

public class Theater {
    private String name;
    private List<Movie> movies;
    private List<Screen> screens; // 극장 내 상영관 목록

    public Theater(String name) {
        this.name = name;
        this.movies = new ArrayList<>();
        this.screens = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<Movie> getMovies() { return movies; }
    public List<Screen> getScreens() { return screens; }

    // 극장 관리(영화, 상영관)
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }

    public void showTheaterInfo() {
        System.out.println("🏠 극장: " + name);
        for (Movie movie : movies) {
            System.out.println("  🎬 " + movie.getId() + ".영화: " + movie.getTitle());
            for (Screen screen : screens) {
                if (screen.getMovie() == movie) {
                    System.out.print("    🏛 " + screen.getName() + " | 상영 시간: ");
                    for (Showtime showtime : screen.getShowtimes()) {
                        System.out.print(showtime.getTime() + "  ");
                    }
                    System.out.println();
                }
            }
        }
    }

}
