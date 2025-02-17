package repository;

import model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private List<Movie> movies = new ArrayList<>();

    public MovieRepository() {
        // 영화 데이터 (ID, 제목, 장르, 상영시간)
        movies.add(new Movie(1, "캡틴 아메리카: 브레이브 뉴 월드", "액션", 118, 8, 10));
        movies.add(new Movie(2, "더 폴: 디렉터스 컷", "드라마", 119, 6, 8));
        movies.add(new Movie(3, "브루탈리스트", "드라마", 215, 10, 12));
        movies.add(new Movie(4, "말할 수 없는 비밀", "판타지", 103, 7, 10));
        movies.add(new Movie(5, "은혼 일국경성편 온 씨어터", "애니메이션", 109, 7, 9));
        movies.add(new Movie(6, "명탐정 코난: 14번째 표적", "애니메이션", 100, 11, 13));
        movies.add(new Movie(7, "극장판 쿠로코의 농구 라스트 게임", "액션", 118, 8, 10));
        movies.add(new Movie(8, "서브스턴스", "스릴러", 141, 6, 8));
        movies.add(new Movie(9, "히트맨2", "코미디", 118, 8, 10));
        movies.add(new Movie(10, "브로큰", "범죄", 99, 9, 10));
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    // 관리자용 영화정보 관리 로직
    public void addMovie(Movie movie) {
        movies.add(movie);
    }
}
