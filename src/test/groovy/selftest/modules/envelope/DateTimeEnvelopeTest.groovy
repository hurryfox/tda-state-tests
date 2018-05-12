package selftest.modules.envelope

import modules.envelope.DateTimeEnvelope
import org.junit.Test

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeEnvelopeTest {

    @Test
    void customCommandsTest() {
        String command = "parse('2018-05-15T14:46:32').next('FRIDAY').minusRandomDays(1, 5)"

        LocalDateTime after = LocalDateTime.parse('2018-05-13T14:46:32')
        LocalDateTime before = LocalDateTime.parse('2018-05-17T14:46:32')

        DateTimeEnvelope.execute(command)
            .with { assert it.isAfter(after) && it.isBefore(before) }
    }

    @Test
    void mixCommandsTest() {
        String command = "parse('2018-05-15T14:46:32').next('FRIDAY').date.minusWeeks(3).plusYears(5)"

        DateTimeEnvelope.execute(command)
                .format(DateTimeFormatter.ISO_DATE_TIME)
                .with { assert it == '2023-04-27T14:46:32' }
    }
}
