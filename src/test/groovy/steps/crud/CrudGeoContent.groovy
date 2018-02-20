package steps.crud

import modules.Requests

import static cucumber.api.groovy.EN.Given

Requests r = new Requests()


Given(~/^Create geo entity '(.*)' with parameters '(.*)'$/) { entity, parameters ->

}

Given(~/^Create non unique geo entity '(.*)' with parameters '(.*)'$/) { entity, parameters ->

}

Given(~/^Edit geo entity '(.*)' with parameters '(.*)'$/) { entity, parameters ->

}

Given(~/^Delete geo entity '(.*)' with id '(.*)'$/) { entity, id ->
    def getTargetEntity = { r.getRequest("api/geo/$entity/$id", []) }

    getTargetEntity.call().each { r.deleteRequest("api/geo/$entity/$it.id", []) }
    assert getTargetEntity.call() == null
}

Given(~/^Delete geo entity '(.*)' with child record with id '(.*)'$/) { entity, id ->


}

Given(~/^Delete nonexistent entity '(.*)' with id '(.*)'$/) { entity, id ->


}

Given(~/^Create street district mapper with parameters '(.*)'$/) { districtId ->

}

Given(~/^Delete street district mapper by district id '(.*)'$/) { districtId ->
    def getTargetMappers = {
        r.getRequest('api/geo/sd-mapper', [])
                .findAll { it.districtId == districtId }
    }

    getTargetMappers.call().each { r.deleteRequest("api/geo/sd-mapper/$it.id", []) }
    assert getTargetMappers.call().empty
}