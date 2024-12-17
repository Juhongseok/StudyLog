package test;

import generic.Money;
import reservation.domain.v2.AmountDiscountPolicy;
import reservation.domain.v2.Movie;
import reservation.domain.v2.NoneDiscountPolicy;
import reservation.domain.v2.PercentDiscountPolicy;
import reservation.domain.v2.PeriodCondition;
import reservation.domain.v2.Screening;
import reservation.domain.v2.SequenceCondition;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MovieTest {

    public static void main(String[] args) {
        비율할인정책_계산하기();
        금액할인정책_계산하기();
        비할인정책_계산하기();
    }

    public static void 비율할인정책_계산하기() {
        Movie movie = new Movie(
                Money.wons(10000),
                new PercentDiscountPolicy(
                        0.1,
                        new SequenceCondition(1),
                        new SequenceCondition(3),
                        new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 10))
                )
        );

        Screening screening = new Screening(movie, 1, LocalDateTime.of(2024, 12, 11, 18, 0));
        Money fee = movie.calculateFee(screening);

        System.out.println(fee.equals(Money.wons(9000)));
    }

    public static void 금액할인정책_계산하기() {
        Movie movie = new Movie(
                Money.wons(10000),
                new AmountDiscountPolicy(
                        Money.wons(1000),
                        new SequenceCondition(1),
                        new SequenceCondition(3),
                        new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 10))
                )
        );

        Screening screening = new Screening(movie, 1, LocalDateTime.of(2024, 12, 11, 18, 0));
        Money fee = movie.calculateFee(screening);

        System.out.println(fee.equals(Money.wons(9000)));
    }

    public static void 비할인정책_계산하기() {
        Movie movie = new Movie(
                Money.wons(10000),
                new NoneDiscountPolicy()
        );

        Screening screening = new Screening(movie, 1, LocalDateTime.of(2024, 12, 11, 18, 0));
        Money fee = movie.calculateFee(screening);

        System.out.println(fee.equals(Money.wons(10000)));
    }

}
