Feature: Testing different service request on BestBuy application

  Scenario: Check if the bestbuy application can be accessed by users
    When User sends a get request to list endpoints, they must get back a valid status code 200

  Scenario Outline: Create a new service and verify if the service is added
    When I create a new service by providing the information serviceName "<serviceName>"
    Then I verify that the service with "<serviceName>" is created

    Examples:
      | serviceName    |
      | Micro Services |
      | Software Hub   |







