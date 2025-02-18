package service;

import model.*;
import repository.ReservationRepository;
import repository.TheaterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationService {
    private ReservationRepository reservationRepository;
    private TheaterRepository theaterRepository;
    private Scanner scanner;

    public ReservationService(TheaterRepository theaterRepository, ReservationRepository reservationRepository) {
        this.theaterRepository = theaterRepository;
        this.reservationRepository = reservationRepository;
        this.scanner = new Scanner(System.in);
    }

    public void makeReservation() {
        List<Theater> theaters = theaterRepository.getAllTheaters();

        // 1. 극장 선택
        System.out.println("\n🏠 극장을 선택하세요:");
        for (int i = 0; i < theaters.size(); i++) {
            System.out.println((i + 1) + ". " + theaters.get(i).getName());
        }
        System.out.print("극장 번호 입력: ");
        int theaterChoice = scanner.nextInt() - 1;
        Theater selectedTheater = theaters.get(theaterChoice);


        // 2. 영화 선택
        System.out.println("\n🎬 예매할 영화를 선택하세요:");
        System.out.println("\n📋 상영 영화 목록:");
        selectedTheater.showTheaterInfo();
        System.out.print("영화 번호 입력: ");
        int movieChoice = scanner.nextInt() - 1;
        Movie selectedMovie = selectedTheater.getMovies().get(movieChoice);


        // 3. 상영관 선택
        System.out.println("\n🏛 상영관을 선택하세요:");
        List<Screen> availableScreens = new ArrayList<>();
        for (Screen screen : selectedTheater.getScreens()) {
            if (screen.getMovie() == selectedMovie) {
                availableScreens.add(screen);
                System.out.println((availableScreens.size()) + ". " + screen.getName());
            }
        }
        System.out.print("상영관 번호 입력: ");
        int screenChoice = scanner.nextInt() - 1;
        Screen selectedScreen = availableScreens.get(screenChoice);

        // 4. 상영시간 선택
        System.out.println("\n⏰ 상영시간을 선택하세요:");
        for (Showtime showtime : selectedScreen.getShowtimes()) {
            System.out.println("  " + showtime.getTime());
        }
        System.out.print("상영 시간 입력 (예: 14:00): ");
        String selectedTime = scanner.next();

        Showtime chosenShowtime = null;
        for (Showtime showtime : selectedScreen.getShowtimes()) {
            if (showtime.getTime().equals(selectedTime)) {
                chosenShowtime = showtime;
                break;
            }
        }

        // 5. 좌석 선택 (null 위험 있음)
        chosenShowtime.showSeatMap();
        String seat;

        // 좌석 유효여부 확인
        while (true) {
            System.out.print("좌석 번호 입력 (예: A1, B3): ");
            seat = scanner.next().toUpperCase();

            if (seat.matches("[A-Z][0-9]+")) {
                int row = seat.charAt(0) - 'A';
                int col = Integer.parseInt(seat.substring(1)) - 1;

                if (row < chosenShowtime.getSeatManager().getRows() && col < chosenShowtime.getSeatManager().getCols()) {
                    if (chosenShowtime.getSeatManager().isSeatAvailable(seat)) {
                        chosenShowtime.getSeatManager().reserveSeat(seat);
                        break;
                    } else {
                        System.out.println("⚠️ 이미 예약된 좌석입니다. 다른 좌석을 선택하세요.");
                    }
                } else {
                    System.out.println("❌ 유효하지 않은 좌석입니다. (해당 영화의 좌석 범위 초과)");
                }
            } else {
                System.out.println("❌ 잘못된 좌석 번호입니다. (예: A1, B3)");
            }
        }

        int reservationId = reservationRepository.generateReservationId();
        Reservation reservation = new Reservation(reservationId, chosenShowtime, selectedMovie, seat);
        reservationRepository.addReservation(reservation);
        System.out.println("✅ 예매 완료!");
        System.out.println(reservation);
    }

    public void showReservationList() {
        List<Reservation> reservations = reservationRepository.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("❌ 현재 예매된 내역이 없습니다.");
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

        // 취소할 예매정보 찾기
        Reservation reservationToCancel = null;
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                reservationToCancel = reservation;
                break;
            }
        }

        if (reservationToCancel == null) {
            System.out.println("❌ 예매 번호를 찾을 수 없습니다.");
            return;
        }

        // 예매 정보(상영 시간, 좌석 정보)
        Showtime showtime = reservationToCancel.getShowtime();
        String seat = reservationToCancel.getSeat();

        // 좌석 사용 가능 상태로 변경
        showtime.getSeatManager().releaseSeat(seat);

        boolean isRemoved = reservationRepository.removeReservation(reservationId);
        if (isRemoved) {
            System.out.println("✅ 예매 취소 완료! (예매 번호: " + reservationId + ", 좌석: " + seat + ")");
        } else {
            System.out.println("❌ 예매 취소 중 오류가 발생했습니다.");
        }
    }

}
