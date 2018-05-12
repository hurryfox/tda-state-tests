package modules.envelope

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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
        this.date.format(DateTimeFormatter.ISO_DATE_TIME)
    }

    final Map<String, DayOfWeek> WEEKDAYS_MAPPER = [MONDAY   : DayOfWeek.MONDAY,
                                                    TUESDAY  : DayOfWeek.TUESDAY,
                                                    WEDNESDAY: DayOfWeek.WEDNESDAY,
                                                    THURSDAY : DayOfWeek.THURSDAY,
                                                    FRIDAY   : DayOfWeek.FRIDAY,
                                                    SATURDAY : DayOfWeek.SATURDAY,
                                                    SUNDAY   : DayOfWeek.SUNDAY]

    DateTimeEnvelope parse(String dateTime) {
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
        assert WEEKDAYS_MAPPER[dayOfWeek]: "No such day of week { $dayOfWeek } in mapper"

        date = date.with(TemporalAdjusters.next(WEEKDAYS_MAPPER[dayOfWeek]))
        this
    }
}



