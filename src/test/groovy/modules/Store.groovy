package modules

class Store {
    static map = [:]
    static list = []

    def static setOnlyNotNullVar(name, value){
        value.removeIf{it == null}
        map.put(name, value)
    }

    def static setVar(name, value) {
        map.put(name, value)
    }

    def static getVar(value) {
        map.get(value)
    }
}
