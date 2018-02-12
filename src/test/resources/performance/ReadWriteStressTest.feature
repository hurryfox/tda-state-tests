Example
Scenario Outline: Edit -> Refresh -> Execute
Then I add initial '<Initial script>' and concatenate '<Concat script>' Enroll Script to '<Operation type name>' and execute '1000' times

Examples:
| Operation type name     | Initial script                                                | Concat script                       |
| TestOperationTypeGroovy | context.log('Context log info');                              | if(true){"".toString()};            |
| TestOperationTypeJS     | function operation(context){context.log("Context log info")}; | function addition(){"".toString()}; |
