package model;

public class Movie {
    private int id;
    private String title;
    private String genre;
    private int duration; // 상영 시간(분)
    private int rows;
    private int cols;
    private boolean[][] seats; // 영화별 좌석 배열; true: 예약됨

    public Movie(int id, String title, String genre, int duration, int rows, int cols) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rows = rows;
        this.cols = cols;
        this.seats = new boolean[rows][cols]; // 행 x 열 크기로 좌석 배열 생성
    }

    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getGenre() {return genre;}
    public int getDuration() {return duration;}
    public int getRows() { return rows; }
    public int getCols() { return cols; }

    public String toString() {
        return "[" + id + "] " + title + " (" + genre + ", " + duration + "분)";
    }
}
