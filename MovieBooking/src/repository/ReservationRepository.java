package repository;

import model.Reservation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReservationRepository {
    private List<Reservation> reservations = new ArrayList<>();
    private int reservationCounter = 1;

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }

    public int generateReservationId() {
        return reservationCounter++;
    }

    public boolean removeReservation(int reservationId) {
            Iterator<Reservation> iterator = reservations.iterator();
            while (iterator.hasNext()) {
                Reservation reservation = iterator.next();
                if (reservation.getReservationId() == reservationId) {
                    iterator.remove();
                    return true;
                }
            }
            return false; // 일치하는 예매 ID가 존재하지 않음
    }

}
