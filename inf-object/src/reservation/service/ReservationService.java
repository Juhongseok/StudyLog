package reservation.service;

import generic.Money;
import reservation.domain.DiscountCondition;
import reservation.domain.DiscountPolicy;
import reservation.domain.Movie;
import reservation.domain.Reservation;
import reservation.domain.Screening;
import reservation.persistence.DiscountConditionDAO;
import reservation.persistence.DiscountPolicyDAO;
import reservation.persistence.MovieDAO;
import reservation.persistence.ReservationDAO;
import reservation.persistence.ScreeningDAO;

import java.util.List;

public class ReservationService {

    private ScreeningDAO screeningDAO;
    private MovieDAO movieDAO;
    private DiscountPolicyDAO discountPolicyDAO;
    private DiscountConditionDAO discountConditionDAO;
    private ReservationDAO reservationDAO;

    public ReservationService(ScreeningDAO screeningDAO, MovieDAO movieDAO, DiscountPolicyDAO discountPolicyDAO, DiscountConditionDAO discountConditionDAO, ReservationDAO reservationDAO) {
        this.screeningDAO = screeningDAO;
        this.movieDAO = movieDAO;
        this.discountConditionDAO = discountConditionDAO;
        this.discountPolicyDAO = discountPolicyDAO;
        this.reservationDAO = reservationDAO;
    }

    public Reservation reserveScreening(Long customerId, Long screeningId, Integer audienceCount) {
        Screening screening = screeningDAO.selectScreening(screeningId);
        Movie movie = movieDAO.selectMovie(screening.getMovieId());
        DiscountPolicy policy = discountPolicyDAO.selectDiscountPolicy(movie.getId());
        boolean found = policy.findDiscountCondition(screening);

        Money fee;
        if (found) {
            fee = movie.getFee().minus(policy.calculateDiscount(movie));
        } else {
            fee = movie.getFee();
        }

        Reservation reservation = makeReservation(customerId, screeningId, audienceCount, fee);
        reservationDAO.insert(reservation);

        return reservation;
    }

    private Reservation makeReservation(Long customerId, Long screeningId, Integer audienceCount, Money fee) {
        return new Reservation(customerId, screeningId, audienceCount, fee.times(audienceCount));
    }

}
