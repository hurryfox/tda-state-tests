package steps.crud

import modules.Request

import static cucumber.api.groovy.EN.Given

Request r = new Request()


Given(~/^Create geo entity '(.*)' with parameters '(.*)'$/) { entity, parameters ->

}

Given(~/^Create non unique geo entity '(.*)' with parameters '(.*)'$/) { entity, parameters ->

}

Given(~/^Edit geo entity '(.*)' with parameters '(.*)'$/) { entity, parameters ->

}

Given(~/^Delete geo entity '(.*)' with id '(.*)' '(.*)'$/) { entity, id, existenceOption ->


}

Given(~/^Delete geo entity '(.*)' with child record with id '(.*)'$/) { entity, id->


}

Given(~/^Delete nonexistent entity '(.*)' with id '(.*)'$/) { entity, id ->


}

Given(~/^Create street district mapper with parameters '(.*)'$/) { districtId ->

}

Given(~/^Delete street district mapper by district id '(.*)' '(.*)'$/) { districtId, existenceOption ->

}













Given(~/^Check existence geo entity '(.*)' with parameters '(.*)'$/) { entity, parameters ->

}