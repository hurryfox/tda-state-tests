Feature: Client management test

  Scenario: Client management test
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

    Then Create document 'client' with properties and save it to 'clientId':
      | login       | +78889991144 |
      | firstName   | Monica       |
      | lastName    | Lion         |
      | ridesAmount | 9            |

# Price evaluation
    # Check ctc if normal
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
      | rawToAddress.city   | Заманиха     |

    When Evaluate ride with properties and price 'null':
      | client              | +78889991122   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Владивосток    |

    When Evaluate ride with properties and price 'null':
      | client              | +78889991122   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Хабаровск      |

    When Evaluate ride with properties and price 'null':
      | client              | +78889991122   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Находка        |


    # Check ctc if vip
    # Check ctc if free

    # Check dtd if normal
    # Check dtd if vip
    # Check dtd if free



# Rides creation


  # Create ride
  # Check client

  # Create ride
  # Check client (n times for free ride)

  # Get active rides