package modules.envelope.time

import java.time.LocalDateTime

class DateTimeShortcuts {
    DateTimeTrait now() {
        LocalDateTime.now() as DateTimeTrait
    }

    DateTimeTrait parse(String dateTime) {
        LocalDateTime.parse(dateTime) as DateTimeTrait
    }

    int random(Integer min, Integer max) {
        assert (min < max && max in 1..30): 'Not appropriate range of days, use [1..30]'

        new Random().nextInt((max - min) + 1) + min
    }
}