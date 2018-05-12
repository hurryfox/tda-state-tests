package modules.envelope

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters

/**
 *  Usage examples in self tests
 */
class DateTimeEnvelope {
    private DateTimeEnvelope() {

    }

    static LocalDateTime execute(String command) {
        def result = Eval.xy(Shortcuts, LocalDateTime, "y.${command}")

        if (result instanceof LocalDateTime) {
            result as LocalDateTime
        } else {
            throw new RuntimeException("Wrong envelope result type ${result.class.getTypeName()}")
        }
    }

    private static class Shortcuts {
        static final Map<String, DayOfWeek> WEEKDAYS_MAPPER = [MONDAY   : DayOfWeek.MONDAY,
                                                               TUESDAY  : DayOfWeek.TUESDAY,
                                                               WEDNESDAY: DayOfWeek.WEDNESDAY,
                                                               THURSDAY : DayOfWeek.THURSDAY,
                                                               FRIDAY   : DayOfWeek.FRIDAY,
                                                               SATURDAY : DayOfWeek.SATURDAY,
                                                               SUNDAY   : DayOfWeek.SUNDAY]

        static TemporalAdjuster tempAdjuster(String direction, String dayOfWeek) {
            assert WEEKDAYS_MAPPER[dayOfWeek]: "No such day of week { $dayOfWeek } in mapper"

            Map taMapper = [next          : { TemporalAdjusters.next(it) },
                            nextOrSame    : { TemporalAdjusters.nextOrSame(it) },
                            previous      : { TemporalAdjusters.previous(it) },
                            previousOrSame: { TemporalAdjusters.previousOrSame(it) }]

            taMapper[direction].call(WEEKDAYS_MAPPER[dayOfWeek])
        }

        static int random(Integer min, Integer max) {
            assert (min < max && max in 1..30): 'Not appropriate range of days, use [1..30]'

            new Random().nextInt((max - min) + 1) + min
        }
    }
}



