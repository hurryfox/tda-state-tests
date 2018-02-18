Feature: Prepare storage for testing

  Scenario Outline: Delete street district mappers
    Given Delete district mapper by district id '<District id>' if exists

    Examples:
      | District id |
      | grkT        |
      | staT        |
      | grdT        |

  Scenario Outline: Prepare storage for testing
    Given Delete geo entity '<Entity>' with id '<Id>' if exists

    Examples:
      | Entity   | Id      |
      | street   | rossT   |
      | street   | transpT |
      | street   | tolstoT |

      | district | grkT    |
      | district | staT    |
      | district | grdT    |

      | city     | spaT    |
      | city     | vlaT    |
      | city     | artT    |

      | country  | ruT     |
      | country  | byT     |
      | country  | uaT     |



Feature: Create entities

  Scenario Outline: Create common geo entities
    Given Create geo entity '<Entity>' with parameters '<Parameters>'

    Examples:
      | Entity   | Parameters                       |
      | country  | {"id":"ruT", "name":"РоссияT"}   |
      | country  | {"id":"byT", "name":"БеларусьT"} |
      | country  | {"id":"uaT", "name":"УкраинаT"}  |

      | city     | spaT                             |
      | city     | vlaT                             |
      | city     | artT                             |

      | district | grkT                             |
      | district | staT                             |
      | district | grdT                             |

      | street   | rossT                            |
      | street   | transpT                          |
      | street   | tolstoT                          |



Feature: Edit entities


Feature: T data constraints

  Scenario: Foreign keys

  Scenario: Unique constraints
  











