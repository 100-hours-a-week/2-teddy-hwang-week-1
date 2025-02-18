package repository;

import model.Movie;
import model.Screen;
import model.Showtime;
import model.Theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TheaterRepository {
    private List<Theater> theaters = new ArrayList<>();

    public TheaterRepository(MovieRepository movieRepository) {
        List<Movie> movies = movieRepository.getAllMovies(); // 기존 영화 데이터 활용
        Random random = new Random();
        char[] screenNames = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',};

        // 5개의 극장 생성
        for (int i = 1; i <= 5; i++) {
            Theater theater = new Theater("극장 " + i);

            // 극장마다 3~5개의 영화 배정
            List<Movie> assignedMovies = new ArrayList<>(movies.subList(0, random.nextInt(movies.size() - 2) + 2));

            int screenIndex = 0;
            for (Movie movie : assignedMovies) {
                int numberOfScreens = random.nextInt(2) + 1; // 1~2개 상영관 할당
                for (int j = 0; j < numberOfScreens; j++) {
                    if (screenIndex >= screenNames.length) {
                        break; // 최대 상영관 개수 제한
                    }
                    Screen screen = new Screen(screenNames[screenIndex] + "관", movie);
                    screenIndex++;

                    // 영화마다 1~2개의 상영 시간 배정
                    int screenRows = random.nextInt(4) + 6; // 6~10 행
                    int screenCols = random.nextInt(5) + 8; // 8~12 열
                    screen.addShowtime(new Showtime("12:00", screen, screenRows, screenCols));
                    if (random.nextBoolean()) {
                        screen.addShowtime(new Showtime("15:00", screen, screenRows, screenCols));
                    }

                    theater.addScreen(screen);
                }
                theater.addMovie(movie);
            }

            theaters.add(theater);
        }
    }

    public List<Theater> getAllTheaters() {
        return theaters;
    }
}
