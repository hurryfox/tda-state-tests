package steps.crud

import cucumber.api.DataTable
import modules.DataConverter
import modules.HttpRequest
import modules.Store

import static cucumber.api.groovy.EN.Given

HttpRequest request = new HttpRequest()
Store store = new Store()
DataConverter converter = new DataConverter()

Given(~/^Check client with login '(.*)' and property '(.*)'$/) { login, varName ->
    def expectedProperties = store.map["$varName"]
    expectedProperties << [rideFree: false, previousRides: []]

    def clientData = request.getRequest("api/client/check/$login", [])

    assert expectedProperties == expectedProperties.intersect(clientData)
}

Given(~/^Evaluate ride with properties and price '(.*)':$/) { price, DataTable rawData ->
    Map data = rawData.asMap(String.class, String.class)
            .with(converter.&convertFlatMapToNestedMap)

    assert request.postRequest('api/ride/evaluate', data).price as String == price
}