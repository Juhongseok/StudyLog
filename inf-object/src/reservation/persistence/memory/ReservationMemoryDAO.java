package reservation.persistence.memory;

import reservation.domain.v1.Reservation;
import reservation.persistence.ReservationDAO;

public class ReservationMemoryDAO extends InMemoryDAO<Reservation> implements ReservationDAO {
    @Override
    public void insert(Reservation reservation) {
        super.insert(reservation);
    }
}
