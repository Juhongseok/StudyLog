package reservation.persistence;

import reservation.domain.v1.Reservation;

public interface ReservationDAO {
    void insert(Reservation reservation);
}
