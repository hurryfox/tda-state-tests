package modules.envelope

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.temporal.TemporalAdjusters

/**
 *  Usage examples in self tests
 */
class DateTimeEnvelope {
    LocalDateTime date

    private DateTimeEnvelope() {

    }

    static LocalDateTime execute(String command) {
        Eval.x(new DateTimeEnvelope(), "x.${command}.date") as LocalDateTime
    }

    @Override
    String toString() {
        this.date.toString()
    }

    Map<String, DayOfWeek> weekdaysMapper = [MONDAY   : DayOfWeek.MONDAY,
                                             TUESDAY  : DayOfWeek.TUESDAY,
                                             WEDNESDAY: DayOfWeek.WEDNESDAY,
                                             THURSDAY : DayOfWeek.THURSDAY,
                                             FRIDAY   : DayOfWeek.FRIDAY,
                                             SATURDAY : DayOfWeek.SATURDAY,
                                             SUNDAY   : DayOfWeek.SUNDAY]

    DateTimeEnvelope dateTime(String dateTime) {
        date = LocalDateTime.parse(dateTime)
        this
    }

    DateTimeEnvelope now() {
        date = LocalDateTime.now()
        this
    }

    DateTimeEnvelope minusDays(Integer days) {
        date = date.minusDays(days)
        this
    }

    DateTimeEnvelope plusYears(Integer years) {
        date = date.plusYears(years)
        this
    }

    DateTimeEnvelope next(String dayOfWeek) {
        date = date.with(TemporalAdjusters.next(weekdaysMapper[dayOfWeek]))
        this
    }
}



