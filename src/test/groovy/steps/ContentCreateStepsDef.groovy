package steps

import groovy.json.JsonSlurper
import modules.Request
import modules.Store

import static cucumber.api.groovy.EN.Given
import static cucumber.api.groovy.EN.Then

Request r = new Request()
List walletsInBO = []
def walletPropertiesInBO = [:]
def clientPropertiesInBO = [:]
def RPPropertiesInBO = [:]

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




// Examples
Given(~/^I get parameters of TESTWallet with ID = '(.*)'$/) { walletId ->
    walletPropertiesInBO.clear()
    clientPropertiesInBO.clear()
    RPPropertiesInBO.clear()

    //Add Wallet information
    walletPropertiesInBO << (r.getRequest('api/wallets/load', [filter: '[{"property":"walletId","value": ' + walletId + ',"operator":"="}]'])[0] as Map)

    //Add client information
    clientPropertiesInBO << (r.getRequest('api/clients/load', [filter: '[{"property":"id","value": ' + walletPropertiesInBO.parentId + ',"operator":"="}]'])[0] as Map)

}

Then(~/^I check TESTparameter '(.*)' for wallet '(.*)' and region '(.*)'$/) { value, walletId, region ->

    println(r.postRequest('api/test/ratePlan/evaluate/withDocumentProperties',
            [documentProperties: '{"id":' + walletPropertiesInBO.id + ',' +
                    ' "parentId":' + clientPropertiesInBO.id + ',' +
                    ' "type":"' + "WALLET" + '",' +
                    ' "properties": {"cur":' + walletPropertiesInBO.cur + ',' +
                    ' "clientId":"' + walletPropertiesInBO.clientId + '",' +
                    ' "regionMark":"' + region + '",' +
                    ' "walletId":' + walletId + '}}',
             rateplanId        : 'rateRulesUser'])["value"] + '  ')
}

Given(~/^I create all target clients$/) { ->
    new JsonSlurper()
            .parseText(new File('src/test/resources/rate_rules_test/resources/clients.json')
            .getText()).list
            .each {
        r.postJSONRequest('api/operation/ADD_CLIENT', ["clientId": "${it.clientId}", "processingId": "testPcId", "dateIn": 20171117033900, "nameProperty": "${it.nameProperty}", "contractNumber": "", "contractDate": null, "additional": "", "holdbackRequired": "N", "holdbackRate": 0, "paymentPeriod": "7", "settlementPeriod": "7", "deferral": "7", "paymentCur": 978, "feeCur": 978])
    }
}

Given(~/^I create all target wallets$/) { ->
    new JsonSlurper()
            .parseText(new File('src/test/resources/rate_rules_test/resources/wallets.json')
            .getText()).list
            .each {
        r.postJSONRequest('api/operation/ADD_WALLET', ["walletId": it.walletId, "dateIn": 20171117034700, "nameProperty": "${it.nameProperty}", "lastCheckedDate": 20171106000000, "clientId": "${it.clientId}", "tspType": "0001", "cur": 203, "additional": "", "holdbackRequired": "N", "holdbackRate": 0, "paymentPeriod": "7", "settlementPeriod": "7", "deferral": "7", "paymentCur": 978, "feeCur": 978])
    }
}

Then(~/^I form new rate plan$/) { ->
    walletsInBO = r.getRequest('api/wallets/load', []) as List

    walletsInBO.each { wallet ->
        def client = (r.getRequest('api/clients/load', [filter: '[{"property":"id","value": ' + wallet.parentId + ',"operator":"="}]'])[0] as Map)

        try {
            [null, 'International', 'EU', 'Domestic'].each { region ->
                String value = r.postRequest('api/test/ratePlan/evaluate/withDocumentProperties',
                        [documentProperties: '{"id":' + wallet.id + ',' +
                                ' "parentId":' + client.id + ',' +
                                ' "type":"' + "WALLET" + '",' +
                                ' "properties": {"cur":' + wallet.cur + ',' +
                                ' "clientId":"' + wallet.clientId + '",' +
                                ' "regionMark":"' + region + '",' +
                                ' "walletId":' + wallet.walletId + '}',
                         rateplanId        : 'trnFeeRate'])["value"]

                if ((value as Integer) != 0) {
                    r.postJSONRequest('api/rateTable/new', [["id": "id==trnFeeRate && walletId==${wallet.walletId} && region==${region}", "value": value]])
                }
            }
        }
        catch (Exception e) {
            ''
        }
    }

}


Given(~/^I get all wallets from BO$/) { ->
    r.getRequest('api/wallets/load', [])
            .each { Store.list.add(it) }
}

Then(~/^I check performance of RP '(.*)'$/) { ratePlan ->

    Store.list.each { wallet ->
        def client = (r.getRequest('api/clients/load', [filter: '[{"property":"id","value": ' + wallet.parentId + ',"operator":"="}]'])[0] as Map)

        [null, 'International', 'EU', 'Domestic'].each { region ->
            r.postRequest('api/test/ratePlan/evaluate/withDocumentProperties',
                    [documentProperties: '{"id":' + wallet.id + ',' +
                            ' "parentId":' + client.id + ',' +
                            ' "type":"' + "WALLET" + '",' +
                            ' "properties": {"cur":' + wallet.cur + ',' +
                            ' "clientId":"' + wallet.clientId + '",' +
                            ' "regionMark":"' + region + '",' +
                            ' "walletId":' + wallet.walletId + '}',
                     rateplanId        : ratePlan])
        }
    }
}