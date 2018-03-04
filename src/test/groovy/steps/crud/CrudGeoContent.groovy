package steps.crud

import groovy.json.JsonSlurper
import modules.HttpRequest
import wslite.rest.RESTClientException

import static cucumber.api.groovy.EN.Given

HttpRequest r = new HttpRequest()

Given(~/^Create geo entity '(.*)' with parameters '(.*)'$/) { entity, parameters ->
    def parametersMap = new JsonSlurper().parseText(parameters)
    def createResponse = r.putRequest("api/geo/$entity", parametersMap)

    assert r.getRequest("api/geo/$entity/$createResponse.id", []) != null
}

Given(~/^Edit geo entity '(.*)' with parameters '(.*)'$/) { entity, parameters ->
    def parametersMap = new JsonSlurper().parseText(parameters)
    def createResponse = r.putRequest("api/geo/$entity", parametersMap)

    assert r.getRequest("api/geo/$entity/$createResponse.id", []) == parametersMap
}

Given(~/^Delete geo entity '(.*)' with id '(.*)'$/) { entity, id ->
    def getTargetEntity = { r.getRequest("api/geo/$entity/$id", []) }

    getTargetEntity()?.with { r.deleteRequest("api/geo/$entity/$it.id", []) }
    assert getTargetEntity() == null
}

Given(~/^Delete geo entity '(.*)' with child record with id '(.*)'$/) { entity, id ->
    def getTargetEntity = { r.getRequest("api/geo/$entity/$id", []) }

    try {
        getTargetEntity.call().with { r.deleteRequest("api/geo/$entity/$it.id", []) }
        assert false: 'Exception should be thrown'
    } catch (RESTClientException e) {
        assert new String(e.response.data).contains('could not execute statement; SQL [n/a]; constraint')
    }
}

Given(~/^Delete nonexistent entity '(.*)' with id '(.*)'$/) { entity, id ->
    try {
        r.deleteRequest("api/geo/$entity/$id", [])
        assert false: 'Exception should be thrown'
    } catch (RESTClientException e) {
        assert new String(e.response.data).contains('entity with such id exists!')
    }
}

Given(~/^Delete street district mapper by district id '(.*)'$/) { districtId ->
    def getTargetMappers = {
        r.getRequest('api/geo/sd-mapper', [])
                .findAll { it.districtId == districtId }
    }

    getTargetMappers().each { r.deleteRequest("api/geo/sd-mapper/$it.id", []) }
    assert getTargetMappers().empty
}