Feature: Testing different product request on BestBuy application

  Scenario: Check if the bestbuy application can be accessed by users
    When User sends a get request to product endpoints, they must get back a valid status code 200

  Scenario Outline: Create a new product and verify if the product is added
    When I create a new product by providing the information name "<name>" type "<type>" price "<price>" shipping "<shipping>" upc "<upc>" description "<description>" manufacturer "<manufacturer>" model "<model>" url "<url>" image "<image>"
    Then I verify that the product with "<name>" is created
    Examples:
      | name                           | type     | price | shipping | upc     | description                               | manufacturer | model | url                    | image     |
      | Kottak-AAA Batteries (10-Pack) | HardGood | 2.99  | 1.10     | 0987543 | Compatible with select electronic devices | Kottak       | 001   | http://www.bestbuy.com | test1.jpg |
      | Kottak-AAA Batteries (4-Pack)  | HardGood | 3.99  | 2.10     | 0987088 | Compatible with select electronic devices | Kottak       | 001   | http://www.bestbuy.com | test2.jpg |








