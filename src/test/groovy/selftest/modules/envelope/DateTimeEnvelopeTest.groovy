package selftest.modules.envelope

import modules.envelope.DateTimeEnvelope
import org.junit.Test

import java.time.format.DateTimeFormatter

class DateTimeEnvelopeTest {

    @Test
    void executeTest() {
        String command = "parse('2018-05-15T14:46:32').next('FRIDAY').minusDays(3).plusYears(5)"

        DateTimeEnvelope.execute(command)
            .format(DateTimeFormatter.ISO_DATE_TIME)
            .with { assert it == '2023-05-15T14:46:32' }
    }
}
