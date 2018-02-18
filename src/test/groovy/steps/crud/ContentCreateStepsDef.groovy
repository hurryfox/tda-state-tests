package steps.crud

import modules.Request

import static cucumber.api.groovy.EN.Given

Request r = new Request()

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