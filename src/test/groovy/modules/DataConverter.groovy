package modules

class DataConverter {
    Map convertFlatMapToNestedMap(Map flatMap) {
        new ConfigSlurper().parse(flatMap as Properties)
    }
}

