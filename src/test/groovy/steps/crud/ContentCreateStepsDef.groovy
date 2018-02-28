package steps.crud

import cucumber.api.DataTable
import modules.Requests
import modules.Store

import static cucumber.api.groovy.EN.Given
import static cucumber.api.groovy.EN.When

Requests r = new Requests()
Store s = new Store()

When(~/^Create document '(.*)' with properties(:| and save it to '(.*)':)$/) { documentType, action, varName, DataTable rawData ->
    Map data = [:] << rawData.asMap(String.class, String.class)

    if (documentType == 'client') {
        data.ridesAmount = data.ridesAmount as Integer
    }

    r.putJsonRequest("api/$documentType", data)

    if (varName) {
        s.map << [("$varName" as String): data]
    }
}

When(~/^Check client with login '(.*)' and property '(.*)'$/) { login, varName ->
    def expectedProperties = s.map["$varName"]
    expectedProperties << [rideFree: false, previousRides: []]

    def clientData = r.getRequest("api/client/check/$login", [])

    assert expectedProperties == expectedProperties.intersect(clientData)
}

Given(~/^Create '(.*)' rides with state '(.*)'$/) { long ridesAmount, String state ->

    ridesAmount.times {
        r.putJsonRequest('api/ride', [
                clientLogin: "+79147654321",
                fromAddress: [
                        country : "Russia",
                        state   : "Primorskiy",
                        city    : "Vladivostok",
                        street  : "Svetlanskaya",
                        building: "64"
                ],
                toAddress  : [
                        country : "Russia",
                        state   : "Primorskiy",
                        city    : "Vladivostok",
                        street  : "Lugovaya",
                        building: "74/8"
                ],
                dateIn     : "2017-11-13T12:45:30",
                carId      : 5,
                menInCar   : 2,
                state      : state
        ])
    }
}