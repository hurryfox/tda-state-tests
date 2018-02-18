package steps.crud

import modules.Request

import static cucumber.api.groovy.EN.Given

Request r = new Request()

Given(~/^Delete geo entity '(.*)' with id '(.*)' if exists$/) { entity, id ->


}


Given(~/^Delete district mapper by district id '(.*)' if exists$/) { districtId ->

}

Given(~/^Create geo entity '(.*)' with parameters '(.*)'$/) { entity, parameters ->

}