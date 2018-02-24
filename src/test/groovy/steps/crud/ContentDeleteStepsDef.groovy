package steps.crud

import modules.Requests

import static cucumber.api.groovy.EN.Given

Requests r = new Requests()

Given(~/^Delete all entities '(.*)'$/) { entityType ->
    r.getRequest("api/$entityType", [])
            .each { r.deleteRequest("api/$entityType/$it.id", []) }
}