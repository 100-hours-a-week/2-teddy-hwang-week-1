package model;

public class Reservation {
    private int reservationId;
    private Movie movie;
    private String seat;

    public Reservation(int reservationId, Movie movie, String seat) {
        this.reservationId = reservationId;
        this.movie = movie;
        this.seat = seat;
    }

    public int getReservationId() { return reservationId; }
    public Movie getMovie() { return movie; }
    public String getSeat() { return seat; }

    @Override
    public String toString() {
        return "🎟 예매번호: " + reservationId + " | 영화: " + movie.getTitle() + " | 좌석: " + seat;
    }
}
