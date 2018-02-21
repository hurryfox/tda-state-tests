Feature: Geo testing

  Scenario Outline: Delete street district mappers
    Given Delete street district mapper by district id '<District id>'
    Examples:
      | District id |
      | grkT        |
      | staT        |
      | grdT        |

  Scenario Outline: Delete common geo entities
    Given Delete geo entity '<Entity>' with id '<Id>'
    Examples:
      | Entity   | Id   |
      | street   | lenT |
      | street   | krsT |
      | street   | tlsT |

      | district | grkT |
      | district | staT |
      | district | grdT |

      | city     | spdT |
      | city     | vlaT |
      | city     | artT |

      | state    | pkT  |
      | state    | hkT  |

      | country  | ruT  |
      | country  | byT  |
      | country  | uaT  |

  Scenario Outline: Create common geo entities
    Given Create geo entity '<Entity>' with parameters '<Parameters>'
    Examples:
      | Entity   | Parameters                                           |
      | country  | {"id":"ruT", "name":"РоссияT"}                       |
      | country  | {"id":"byT", "name":"БеларусьT"}                     |
      | country  | {"id":"uaT", "name":"УкраинаT"}                      |

      | state    | {"id":"pkT", "name":"ПриморскийT", "country":"ruT"}  |
      | state    | {"id":"hkT", "name":"ХабаровскийT", "country":"ruT"} |

      | city     | {"id":"spdT", "name":"СпасскT", "state":"pkT"}       |
      | city     | {"id":"vlaT", "name":"ВладивостокT", "state":"pkT"}  |
      | city     | {"id":"artT", "name":"АртёмT", "state":"pkT"}        |

      | district | {"id":"grkT", "name":"ГоркаT", "city":"spdT"}        |
      | district | {"id":"staT", "name":"СтаT", "city":"spdT"}          |
      | district | {"id":"grdT", "name":"ГородT", "city":"spdT"}        |

      | street   | {"id":"lenT", "name":"ЛенинскаяT", "city":"spdT"}    |
      | street   | {"id":"krsT", "name":"КрестьянскаяT", "city":"spdT"} |
      | street   | {"id":"tlsT", "name":"ТолстогоT", "city":"spdT"}     |

  Scenario Outline: Create street district mappers
    Given Create geo entity 'sd-mapper' with parameters '<Parameters>'
    Examples:
      | Parameters                                                   |
      | {"streetId": "lenT", "building": null, "districtId": "grkT"} |
      | {"streetId": "krsT", "building": null, "districtId": "staT"} |
      | {"streetId": "tlsT", "building": null, "districtId": "grdT"} |

  Scenario Outline: Edit common geo entities
    Given Edit geo entity '<Entity>' with parameters '<Parameters>'
    Examples:
      | Entity   | Parameters                                                |
      | country  | {"id":"ruT", "name":"РоссияEditedT"}                      |
      | state    | {"id":"pkT", "name":"ПриморскийEditedT", "country":"byT"} |
      | city     | {"id":"spdT", "name":"СпасскEditedT", "state":"pkT"}      |
      | district | {"id":"grkT", "name":"ГоркаEditedT", "city":"vlaT"}       |
      | street   | {"id":"lenT", "name":"ЛенинскаяEditedT", "city":"spdT"}   |

  Scenario Outline: Foreign keys
    Given Delete geo entity '<Entity>' with child record with id '<Id>'
    Examples:
      | Entity  | Id   |
      | country | ruT  |
      | state   | pkT  |
      | city    | spdT |


  Scenario Outline: Delete street district mappers
    Given Delete street district mapper by district id '<District id>'
    Examples:
      | District id |
      | grkT        |
      | staT        |
      | grdT        |

  Scenario Outline: Delete common geo entities
    Given Delete geo entity '<Entity>' with id '<Id>'
    Examples:
      | Entity   | Id   |
      | street   | lenT |
      | street   | krsT |
      | street   | tlsT |

      | district | grkT |
      | district | staT |
      | district | grdT |

      | city     | spdT |
      | city     | vlaT |
      | city     | artT |

      | state    | pkT  |
      | state    | hkT  |

      | country  | ruT  |
      | country  | byT  |
      | country  | uaT  |

  Scenario Outline: Delete nonexistent entity
    Given Delete nonexistent entity '<Entity>' with id '<Id>'
    Examples:
      | Entity   | Id   |
      | street   | lenT |
      | district | grkT |
      | city     | spaT |
      | state    | pkT  |
      | country  | ruT  |
