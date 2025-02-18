import model.Movie;
import repository.MovieRepository;
import repository.ReservationRepository;
import repository.TheaterRepository;
import service.MovieService;
import service.ReservationService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieRepository movieRepository = new MovieRepository();
        ReservationRepository reservationRepository = new ReservationRepository();
        TheaterRepository theaterRepository = new TheaterRepository(movieRepository);
        MovieService movieService = new MovieService(movieRepository);
        ReservationService reservationService = new ReservationService(theaterRepository, reservationRepository);

        while (true) {
            System.out.println("\n🎥 영화 예매 시스템 🎥");
            System.out.println("1. 영화 목록 보기");
            System.out.println("2. 영화 예매");
            System.out.println("3. 예매 내역 보기");
            System.out.println("4. 예매 취소");
            System.out.println("5. 종료");
            System.out.print("메뉴 선택: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    movieService.showMovieList();
                    break;
                case 2:
                    reservationService.makeReservation();
                    break;
                case 3:
                    reservationService.showReservationList();
                    break;
                case 4:
                    reservationService.cancelReservation();
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("올바른 번호를 입력하세요.");
            }

        }
    }
}