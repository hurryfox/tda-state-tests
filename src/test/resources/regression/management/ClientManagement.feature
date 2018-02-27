Feature: Client management test

  Scenario: Client test
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

    Then Check client with login '+78889991122'

    When Evaluate ride with properties and save result to 'cm.ctc.evaluate':
      | client              | +79147654321   |
      | rawFromAddress.city | Спасск-Дальний |
      | rawToAddress.city   | Бусевка        |

    Then Assert equality of 'cm.ctc.evaluate' and properties:
      | single.value | 130 |




  # Create ride
  # Check client

  # Create ride
  # Check client (n times for free ride)

  # Get active rides