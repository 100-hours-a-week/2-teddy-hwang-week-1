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

    public boolean isSeatAvailable(String seat) {
        int row = seat.charAt(0) - 'A';
        int col = Integer.parseInt(seat.substring(1)) - 1;
        if (row >= rows || col >= cols) {
            return false; // 좌석 범위를 초과하면 예매 불가
        }
        return !seats[row][col]; // false면 예약 가능
    }

    public void reserveSeat(String seat) {
        int row = seat.charAt(0) - 'A';
        int col = Integer.parseInt(seat.substring(1)) - 1;
        seats[row][col] = true;
    }

    public void printSeatMap() {
        System.out.println("\n💺 좌석 배치 (" + title + ")");
        System.out.print("   ");
        for (int i = 1; i <= cols; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print((char) ('A' + i) + "  ");
            for (int j = 0; j < cols; j++) {
                System.out.print(seats[i][j] ? "✕  " : "◯  "); // 예약된 좌석은 '✕', 예약 가능 좌석은 '◯'
            }
            System.out.println();
        }
    }

    public String toString() {
        return "[" + id + "] " + title + " (" + genre + ", " + duration + "분)";
    }
}
