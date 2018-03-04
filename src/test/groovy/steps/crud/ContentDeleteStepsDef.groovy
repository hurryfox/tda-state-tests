package steps.crud

import modules.HttpRequest

import static cucumber.api.groovy.EN.Given

HttpRequest r = new HttpRequest()

Given(~/^Delete all entities '(.*)'$/) { entityType ->
    r.getRequest("api/$entityType", [])
            .each { r.deleteRequest("api/$entityType/$it.id", []) }
}