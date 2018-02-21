package modules

import wslite.http.auth.HTTPBasicAuthorization
import wslite.rest.ContentType
import wslite.rest.RESTClient

class Requests {
    def static hostName = System.getenv('host.name')
    def static login = System.getenv('login')
    def static password = System.getenv('password')
    def static client = new RESTClient(hostName)

    static {
        client.httpClient.sslTrustAllCerts = false

        if (login && password) {
            client.authorization = new HTTPBasicAuthorization(login, password)
        }
    }

    def static getRequest(path, query) {
        def response = client.get(path: path, query: query)
        response.parsedResponseContent.json
    }

    def static postRequest(path, query) {
        def response = client.post(path: path, query: query)
        response.parsedResponseContent.json
    }

    def static postJsonRequest(path, body) {
        def response = client.post(path: path) {
            type ContentType.JSON
            json body
        }
        response.parsedResponseContent.json
    }

    def static putJsonRequest(path, body) {
        def response = client.put(path: path) {
            type ContentType.JSON
            json body
        }
        response.parsedResponseContent.json
    }

    def static deleteRequest(path, query) {
        def response = client.delete(path: path, query: query)
        response.parsedResponseContent.json
    }
}