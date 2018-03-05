Feature: Client management test

# Upload rates
  Scenario Outline: Upload rates
    Given Upload rates of type '<Rates type>' from file '<Rates file>'
    Examples:
      | Rates type | Rates file                                                        |
      | price-dtd  | src/test/resources/regression/configuration/content/район.xlsx    |
      | price-ctc  | src/test/resources/regression/configuration/content/пригород.xlsx |
      | price-ctc  | src/test/resources/regression/configuration/content/межгород.xlsx |


# Create clients
  Scenario: Clients test
    When Create document 'client' with properties:
      | login       | +78889991122 |
      | firstName   | Polly        |
      | lastName    | Crocodile    |
      | ridesAmount | 1            |

    Then Create document 'client' with properties and save it to 'clientId':
      | login       | +78889991133 |
      | firstName   | Hannah       |
      | lastName    | Giraffe      |
      | ridesAmount | 7            |

    Then Check client with login '+78889991133' and property 'clientId'

    Then Create document 'client' with properties:
      | login       | +78889991144 |
      | firstName   | Monica       |
      | lastName    | Lion         |
      | ridesAmount | 9            |

    Then Create document 'client' with properties:
      | login       | +78889991155 |
      | firstName   | Jerry        |
      | lastName    | Cat          |
      | ridesAmount | 7            |
      | type        | vip          |


# Price evaluation
    # Check ctc if normal
  Scenario: Rates test
    When Evaluate ride with properties and price '300':
      | client              | +78889991122   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Бусевка        |

    When Evaluate ride with properties and price '370':
      | client              | +78889991122   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Прохоры        |

    When Evaluate ride with properties and price '500':
      | client              | +78889991122   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Кнорринг       |

    When Evaluate ride with properties and price '250':
      | client              | +78889991122   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Дачи солнечные |

    When Evaluate ride with properties and price '250':
      | client              | +78889991122       |
      | rawFromAddress.city | Спасск-Дальний СТА |
      | rawToAddress.city   | Заманиха           |

    When Evaluate ride with properties and price '4200':
      | client              | +78889991122   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Владивосток    |

    When Evaluate ride with properties and price '8700':
      | client              | +78889991122   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Хабаровск      |

    When Evaluate ride with properties and price '5880':
      | client              | +78889991122   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Находка        |


    # Check ctc if vip
    When Evaluate ride with properties and price '300':
      | client              | +78889991155   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Бусевка        |

    When Evaluate ride with properties and price '370':
      | client              | +78889991155   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Прохоры        |

    When Evaluate ride with properties and price '500':
      | client              | +78889991155   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Кнорринг       |


    # Check ctc if free
    When Evaluate ride with properties and price '300':
      | client              | +78889991144   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Бусевка        |

    When Evaluate ride with properties and price '370':
      | client              | +78889991144   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Прохоры        |

    When Evaluate ride with properties and price '500':
      | client              | +78889991144   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Кнорринг       |


    # Check dtd if normal
    When Evaluate ride with properties and price '120':
      | client                  | +78889991122   |
      | rawFromAddress.city     | Спасск-Дальний |
      | rawFromAddress.street   | Горького       |
      | rawFromAddress.building | 24             |
      | rawToAddress.city       | Спасск-Дальний |
      | rawToAddress.street     | Карьерная      |
      | rawToAddress.building   | 15             |

    When Evaluate ride with properties and price '140':
      | client                  | +78889991122   |
      | rawFromAddress.city     | Спасск-Дальний |
      | rawFromAddress.street   | Горького       |
      | rawFromAddress.building | 24             |
      | rawToAddress.city       | Спасск-Дальний |
      | rawToAddress.street     | Карьерная      |
      | rawToAddress.building   | 20             |


    # Check dtd if vip
    # Check dtd if free

    # Check by district if normal
    # Check by district if vip
    # Check by district if free



# Rides creation


  # Create ride
  # Check client

  # Create ride
  # Check client (n times for free ride)

  # Get active rides