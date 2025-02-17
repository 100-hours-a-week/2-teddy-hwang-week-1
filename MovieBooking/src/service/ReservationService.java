package service;

import model.Movie;
import model.Reservation;
import repository.MovieRepository;
import repository.ReservationRepository;

import java.util.List;
import java.util.Scanner;

public class ReservationService {
    private MovieRepository movieRepository;
    private ReservationRepository reservationRepository;
    private Scanner scanner;

    public ReservationService(MovieRepository movieRepository, ReservationRepository reservationRepository) {
        this.movieRepository = movieRepository;
        this.reservationRepository = reservationRepository;
        this.scanner = new Scanner(System.in);
    }

    public void makeReservation() {
        List<Movie> movies = movieRepository.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println("현재 예매 가능한 영화가 없습니다.");
            return;
        }

        // 영화 선택
        System.out.println("\n🎬 예매할 영화를 선택하세요:");
        for (Movie movie : movies) {
            System.out.println(movie.getId() + ". " + movie.getTitle());
        }
        System.out.print("영화 번호 입력: ");
        int movieId = scanner.nextInt();

        // 영화 유효여부 확인
        Movie selectedMovie = null;
        for (Movie movie : movies) {
            if (movie.getId() == movieId) {
                selectedMovie = movie;
                break;
            }
        }

        if (selectedMovie == null) {
            System.out.println("❌ 잘못된 영화 번호입니다.");
            return;
        }

        // 좌석 선택(좌석 상태 출력)
        selectedMovie.printSeatMap();
        String seat;

        // 좌석 유효여부 확인
        while (true) {
            System.out.print("좌석 번호 입력 (예: A1, B3): ");
            seat = scanner.next().toUpperCase();

            if (seat.matches("[A-Z][0-9]+")) {
                int row = seat.charAt(0) - 'A';
                int col = Integer.parseInt(seat.substring(1)) - 1;

                if (row < selectedMovie.getRows() && col < selectedMovie.getCols()) {
                    if (selectedMovie.isSeatAvailable(seat)) {
                        break; // 예약 가능하면 루프 종료
                    } else {
                        System.out.println("❌ 해당 좌석은 이미 예약되었습니다. 다른 좌석을 선택하세요.");
                    }
                } else {
                    System.out.println("❌ 유효하지 않은 좌석입니다. (해당 영화의 좌석 범위 초과)");
                }
            } else {
                System.out.println("❌ 잘못된 좌석 번호입니다. (예: A1, B3)");
            }
        }

        // 좌석 예약
        selectedMovie.reserveSeat(seat);
        int reservationId = reservationRepository.generateReservationId();
        Reservation reservation = new Reservation(reservationId, selectedMovie, seat);
        reservationRepository.addReservation(reservation);

        System.out.println("✅ 예매 완료! ");
        System.out.println(reservation);
    }

    public void showReservationList() {
        List<Reservation> reservations = reservationRepository.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("현재 예매된 내역이 없습니다.");
            return;
        }
        System.out.println("\n✦ 예매한 영화 목록 ✦");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public void cancelReservation() {
        List<Reservation> reservations = reservationRepository.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("❌ 예매 내역이 없습니다.");
            return;
        }

        // 예매 내역 출력
        showReservationList();

        // ✅ 예외처리 필요!
        System.out.print("취소할 예매 번호를 입력하세요: ");
        int reservationId = scanner.nextInt();

        boolean isRemoved = reservationRepository.removeReservation(reservationId);
        if (isRemoved) {
            System.out.println("✅ 예매 취소 완료! (예매 번호: " + reservationId + ")");
        } else {
            System.out.println("❌ 예매 번호를 찾을 수 없습니다.");
        }
    }

}
