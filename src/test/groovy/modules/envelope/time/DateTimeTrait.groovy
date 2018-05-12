package modules.envelope.time

import java.time.DayOfWeek
import java.time.temporal.TemporalAdjusters

trait DateTimeTrait {
    static final Map<String, DayOfWeek> WEEKDAYS_MAPPER = [MONDAY   : DayOfWeek.MONDAY,
                                                           TUESDAY  : DayOfWeek.TUESDAY,
                                                           WEDNESDAY: DayOfWeek.WEDNESDAY,
                                                           THURSDAY : DayOfWeek.THURSDAY,
                                                           FRIDAY   : DayOfWeek.FRIDAY,
                                                           SATURDAY : DayOfWeek.SATURDAY,
                                                           SUNDAY   : DayOfWeek.SUNDAY]

    static final Map<String, Closure> TEMP_ADJ_MAPPER = [next          : { TemporalAdjusters.next(it) },
                                                         nextOrSame    : { TemporalAdjusters.nextOrSame(it) },
                                                         previous      : { TemporalAdjusters.previous(it) },
                                                         previousOrSame: { TemporalAdjusters.previousOrSame(it) }]


    DateTimeTrait move(String direction, String dayOfWeek) {
        assert WEEKDAYS_MAPPER[dayOfWeek]: "No such day of week { $dayOfWeek } in mapper"

        WEEKDAYS_MAPPER[dayOfWeek]
                .with(TEMP_ADJ_MAPPER[direction])
                .with(this.&with)
                .with { it as DateTimeTrait }
    }

    DateTimeTrait minusWeeks(Integer weeks) {
        this.$delegate.minusWeeks(weeks) as DateTimeTrait
    }
}