package model;

public class Showtime {
    private String time; // "14:00"
    private Screen screen;
    private SeatManager seatManager;

    public Showtime(String time, Screen screen, int rows, int cols) {
        this.time = time;
        this.screen = screen;
        this.seatManager = new SeatManager(rows, cols);
    }

    public String getTime() { return time; }
    public Screen getScreen() { return screen; }
    public SeatManager getSeatManager() { return seatManager; }

    public void showSeatMap() {
        System.out.println("\n📍 " + screen.getName() + " | " + time);
        seatManager.printSeatMap();
    }
}
