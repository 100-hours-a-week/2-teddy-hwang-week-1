package model;

public class SeatManager {
    private int rows;
    private int cols;
    private boolean[][] seats;

    public SeatManager(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.seats = new boolean[rows][cols]; // 좌석 배열 초기화
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }

    public boolean isSeatAvailable(String seat) {
        int row = seat.charAt(0) - 'A';
        int col = Integer.parseInt(seat.substring(1)) - 1;
        return !seats[row][col]; // false면 예약 가능
    }

    public void reserveSeat(String seat) {
        int row = seat.charAt(0) - 'A';
        int col = Integer.parseInt(seat.substring(1)) - 1;
        seats[row][col] = true;
    }

    public void releaseSeat(String seat) {
        int row = seat.charAt(0) - 'A';
        int col = Integer.parseInt(seat.substring(1)) - 1;
        seats[row][col] = false;
    }

    public void printSeatMap() {
        System.out.println("💺 좌석 배치도");
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

}
