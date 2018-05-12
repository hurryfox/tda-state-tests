package modules.envelope

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

/**
 *  Usage examples in self tests
 */
class DateTimeEnvelope {
    final Map<String, DayOfWeek> WEEKDAYS_MAPPER = [MONDAY   : DayOfWeek.MONDAY,
                                                    TUESDAY  : DayOfWeek.TUESDAY,
                                                    WEDNESDAY: DayOfWeek.WEDNESDAY,
                                                    THURSDAY : DayOfWeek.THURSDAY,
                                                    FRIDAY   : DayOfWeek.FRIDAY,
                                                    SATURDAY : DayOfWeek.SATURDAY,
                                                    SUNDAY   : DayOfWeek.SUNDAY]

    LocalDateTime date

    private DateTimeEnvelope() {

    }

    static LocalDateTime execute(String command) {
        def result = Eval.x(new DateTimeEnvelope(), "x.${command}")

        if(result instanceof LocalDateTime) {
            result as LocalDateTime
        } else if(result instanceof DateTimeEnvelope) {
            result.date
        } else {
            throw new RuntimeException("Wrong envelope result type ${result.class.getTypeName()}")
        }
    }

    @Override
    String toString() {
        this.date.format(DateTimeFormatter.ISO_DATE_TIME)
    }

    DateTimeEnvelope parse(String dateTime) {
        date = LocalDateTime.parse(dateTime)
        this
    }

    DateTimeEnvelope now() {
        date = LocalDateTime.now()
        this
    }

    DateTimeEnvelope next(String dayOfWeek) {
        assert WEEKDAYS_MAPPER[dayOfWeek]: "No such day of week { $dayOfWeek } in mapper"

        date = date.with(TemporalAdjusters.next(WEEKDAYS_MAPPER[dayOfWeek]))
        this
    }

    DateTimeEnvelope minusRandomDays(Integer min, Integer max) {
        assert (min < max && max in 1..30): 'Not appropriate range of days, use [1..30]'

        date = date.minusDays(new Random().nextInt((max - min) + 1) + min)
        this
    }
}



