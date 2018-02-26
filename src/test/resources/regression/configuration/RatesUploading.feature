Feature: Rates upload testing

  Scenario: Delete all rates
    Given Delete all entities 'price-dtd'
    Given Delete all entities 'price-ctc'


  Scenario Outline: Upload rates
    Given Upload rates of type '<Rates type>' from file '<Rates file>'
    Examples:
      | Rates type | Rates file                                                     |
      | price-dtd  | src/test/resources/regression/configuration/content/район.xlsx    |
      | price-ctc  | src/test/resources/regression/configuration/content/пригород.xlsx |

  Scenario Outline: Check rates
    Given Check rates content of type '<Rates type>' from file '<Expected data file>'
    Examples:
      | Rates type | Expected data file                                             |
      | price-dtd  | src/test/resources/regression/configuration/content/район.json    |
      | price-ctc  | src/test/resources/regression/configuration/content/пригород.json |