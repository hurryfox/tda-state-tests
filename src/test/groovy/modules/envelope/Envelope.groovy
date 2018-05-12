package modules.envelope

import modules.envelope.time.DateTimeShortcuts
import modules.envelope.time.DateTimeTrait

import java.time.LocalDateTime

class Envelope {
    static LocalDateTime executeDateCommand(String command) {
        def result = Eval.xy(new DateTimeShortcuts(), DateTimeTrait, "x.${command}")

        if (result instanceof DateTimeTrait) {
            result.$delegate as LocalDateTime
        } else if (result instanceof LocalDateTime) {
            result as LocalDateTime
        } else {
            throw new RuntimeException("Wrong envelope result type ${result.class.getTypeName()}")
        }
    }
}