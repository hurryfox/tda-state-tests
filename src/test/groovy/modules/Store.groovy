package modules

class Store {
    static vars = new HashMap<String, Object>()
    static list = new ArrayList<Object>()

    def static setOnlyNotNullVar(name, value){
        value.removeIf{it == null}
        vars.put(name, value)
    }

    def static setVar(name, value) {
        vars.put(name, value)
    }

    def static getVar(value) {
       vars.get(value)
    }
}
