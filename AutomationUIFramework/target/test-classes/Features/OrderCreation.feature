
@tag
Feature: Order Placement Validations

  @tag2
  Scenario Outline: Positive E2E order placement validation
    Given User login to E-commerce website using <Username> and <Password>
    When User adds <Product> to cart
    And User validates the <Product> is added in the cart
    And User plcaes the order from the checkout page
    Then User should get the confirmation message

    Examples: 
      | Username  								| Password 		| Product  				|
      | pritam.debnath@gmail.com 	| Test@1234 	| ADIDAS ORIGINAL |
      | pritamkol1995@gmail.com 	| Test@12345 	| ZARA COAT 3    	|
