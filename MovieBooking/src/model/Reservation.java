package model;

public class Reservation {
    private int reservationId;
    private Showtime showtime;
    private Movie movie;
    private String seat;


    public Reservation(int reservationId, Showtime showtime, Movie movie, String seat) {
        this.reservationId = reservationId;
        this.showtime = showtime;
        this.movie = movie;
        this.seat = seat;
    }

    public int getReservationId() { return reservationId; }
    public Showtime getShowtime() { return showtime; }
    public Movie getMovie() { return movie; }
    public String getSeat() { return seat; }

    @Override
    public String toString() {
        return "🎟 예매번호: " + reservationId + " | 영화: " + movie.getTitle() + " | 상영관: " + showtime.getScreen().getName() + " | 상영시간: " + showtime.getTime() + " | 좌석: " + seat;
    }
}
