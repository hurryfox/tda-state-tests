package selftest.modules.envelope

import modules.envelope.Envelope
import org.junit.Test

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeEnvelopeTest {
    @Test
    void test() {
        String command = "parse('2018-05-15T14:46:32').move('next', 'FRIDAY').minusDays(x.random(1, 5))"

        LocalDateTime after = LocalDateTime.parse('2018-05-13T14:46:32')
        LocalDateTime before = LocalDateTime.parse('2018-05-17T14:46:32')

        Envelope.executeDateCommand(command)
            .with { assert it.isAfter(after) && it.isBefore(before) }
    }

    @Test
    void test2() {
        String command = "parse('2018-05-15T14:46:32').move('next', 'FRIDAY').minusWeeks(3).plusYears(5)"

        Envelope.executeDateCommand(command)
                .format(DateTimeFormatter.ISO_DATE_TIME)
                .with { assert it == '2023-04-27T14:46:32' }
    }

    @Test
    void test3() {
        String command = "parse('2018-05-15T14:46:32').minusWeeks(3).move('next', 'FRIDAY').plusYears(5)"

        Envelope.executeDateCommand(command)
                .format(DateTimeFormatter.ISO_DATE_TIME)
                .with { assert it == '2023-04-27T14:46:32' }
    }
}
