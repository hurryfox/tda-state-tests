package steps

import groovy.json.JsonSlurper
import modules.HttpRequest

import static cucumber.api.groovy.EN.Given

HttpRequest r = new HttpRequest()


Given(~/^Upload rates of type '(.*)' from file '(.*)'$/) { entityType, filePath ->
    r.postMultipartRequest("api/$entityType/upload", new File(filePath), 'application/json', 'file.xlsx')
}

Given(~/^Check rates content of type '(.*)' from file '(.*)'$/) { entityType, filePath ->
    assert r.getRequest("api/$entityType",[]) == new JsonSlurper().parseText(new File(filePath).text)
}