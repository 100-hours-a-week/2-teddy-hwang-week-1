package model;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private String name;
    private Movie movie;
    private List<Showtime> showtimes;

    public Screen(String name, Movie movie) {
        this.name = name;
        this.movie = movie;
        this.showtimes = new ArrayList<>();
    }


    public String getName() { return name; }
    public Movie getMovie() { return movie; }
    public List<Showtime> getShowtimes() { return showtimes; }

    // 상영시간 관리
    public void addShowtime(Showtime showtime) {
        showtimes.add(showtime);
    }

    public void showScreenInfo() {
        System.out.println("\n🎬 영화: " + movie.getTitle() + " | 🏛상영관: " + name);
        System.out.println("  ⏰ 상영 시간: ");
        for (Showtime showtime : showtimes) {
            System.out.println("  - " + showtime.getTime());
        }
    }
}
