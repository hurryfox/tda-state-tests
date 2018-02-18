Feature : Prepare storage for testing

  Scenario Outline: Delete street district mappers
    Given Delete street district mapper by district id '<District id>' 'if exists'
    Examples:
      | District id |
      | grkT        |
      | staT        |
      | grdT        |

  Scenario Outline: Delete common geo entities
    Given Delete geo entity '<Entity>' with id '<Id>' 'if exists'
    Examples:
      | Entity   | Id   |
      | street   | lenT |
      | street   | krsT |
      | street   | tlsT |

      | district | grkT |
      | district | staT |
      | district | grdT |

      | city     | spaT |
      | city     | vlaT |
      | city     | artT |

      | state    | pkT  |
      | state    | hkT  |

      | country  | ruT  |
      | country  | byT  |
      | country  | uaT  |



Feature: Create and check entities

  Scenario Outline: Create common geo entities
    Given Create geo entity '<Entity>' with parameters '<Parameters>'
    Then Check existence geo entity '<Entity>' with parameters '<Parameters>'
    Examples:
      | Entity   | Parameters                                             |
      | country  | {"id":"ruT", "name":"РоссияT"}                         |
      | country  | {"id":"byT", "name":"БеларусьT"}                       |
      | country  | {"id":"uaT", "name":"УкраинаT"}                        |

      | state    | {"id":"pkT", "name":"ПриморскийT", "country":"ruT"}    |
      | state    | {"id":"hkT", "name":"ХабаровскийT", "country":"ruT"}   |

      | city     | {"id":"spdT", "name":"СпасскT", "district":"pkT"}      |
      | city     | {"id":"vlaT", "name":"ВладивостокT", "district":"pkT"} |
      | city     | {"id":"artT", "name":"АртёмT", "district":"pkT"}       |

      | district | {"id":"grkT", "name":"ГоркаT", "city":"spdT"}          |
      | district | {"id":"staT", "name":"СтаT", "city":"spdT"}            |
      | district | {"id":"grdT", "name":"ГородT", "city":"spdT"}          |

      | street   | {"id":"lenT", "name":"ЛенинскаяT", "city":"spdT"}      |
      | street   | {"id":"krsT", "name":"КрестьянскаяT", "city":"spdT"}   |
      | street   | {"id":"tlsT", "name":"ТолстогоT", "city":"spdT"}       |

  Scenario Outline: Create street district mappers
    Given Create street district mapper with parameters '<Parameters>'
    Then Check existence geo entity 'sd-mapper' with parameters '<Parameters>'
    Examples:
      | Parameters                                                 |
      | {"streetId": "lenT", "building": "", "districtId": "grkT"} |
      | {"streetId": "krsT", "building": "", "districtId": "staT"} |
      | {"streetId": "tlsT", "building": "", "districtId": "grdT"} |



Feature: Edit entities

  Scenario Outline: Edit common geo entities
    Given Edit geo entity '<Entity>' with parameters '<Parameters>'
    Then Check existence geo entity '<Entity>' with parameters '<Parameters>'
    Examples:
      | Entity   | Parameters                                                |
      | country  | {"id":"ruT", "name":"РоссияEditedT"}                      |
      | state    | {"id":"pkT", "name":"ПриморскийEditedT", "country":"byT"} |
      | city     | {"id":"spdT", "name":"СпасскEditedT", "district":"pkT"}   |
      | district | {"id":"grkT", "name":"ГоркаEditedT", "city":"vlaT"}       |
      | street   | {"id":"lenT", "name":"ЛенинскаяEditedT", "city":"spdT"}   |



Feature: Test data constraints

  Scenario Outline: Foreign keys
    Given Delete geo entity '<Entity>' with child record with id '<Id>'
    Examples:
      | Entity  | Id   |
      | country | ruT  |
      | state   | hkT  |
      | city    | artT |

  Scenario Outline: Unique constraints
    Given Create non unique geo entity '<Entity>' with parameters '<Parameters>'
    Examples:
      | Entity   | Parameters                                          |
      | country  | {"id":"ruT", "name":"РоссияT"}                      |
      | state    | {"id":"pkT", "name":"ПриморскийT", "country":"ruT"} |
      | city     | {"id":"spdT", "name":"СпасскT", "district":"pkT"}   |
      | district | {"id":"grkT", "name":"ГоркаT", "city":"spdT"}       |
      | street   | {"id":"lenT", "name":"ЛенинскаяT", "city":"spdT"}   |



Feature: Delete entities

  Scenario Outline: Delete street district mappers
    Given Delete street district mapper by district id '<District id>' 'force'
    Examples:
      | District id |
      | grkT        |
      | staT        |
      | grdT        |

  Scenario Outline: Prepare storage for testing
    Given Delete geo entity '<Entity>' with id '<Id>' 'force'
    Examples:
      | Entity   | Id   |
      | street   | lenT |
      | street   | krsT |
      | street   | tlsT |

      | district | grkT |
      | district | staT |
      | district | grdT |

      | city     | spaT |
      | city     | vlaT |
      | city     | artT |

      | state    | pkT  |
      | state    | hkT  |

      | country  | ruT  |
      | country  | byT  |
      | country  | uaT  |



Feature: Delete nonexistent entities

  Scenario Outline: Delete nonexistent entity
    Given Delete nonexistent entity '<Entity>' with id '<Id>'
    Examples:
      | Entity   | Id   |
      | street   | lenT |
      | district | grkT |
      | city     | spaT |
      | state    | pkT  |
      | country  | ruT  |
