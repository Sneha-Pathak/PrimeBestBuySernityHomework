package com.bestbuy.model.cucumber.steps;

import com.bestbuy.model.bestbuyinfo.ProductsSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasValue;

public class ProductMyStepdefs {
    static ValidatableResponse response = null;

    @Steps
    ProductsSteps productsSteps;

    @When("^User sends a get request to product endpoints, they must get back a valid status code 200$")
    public void userSendsAGetRequestToProductEndpointsTheyMustGetBackAValidStatusCode() {
        productsSteps.allProducts().statusCode(200).log().all();
    }

    @When("^I create a new product by providing the information name \"([^\"]*)\" type \"([^\"]*)\" price \"([^\"]*)\" shipping \"([^\"]*)\" upc \"([^\"]*)\" description \"([^\"]*)\" manufacturer \"([^\"]*)\" model \"([^\"]*)\" url \"([^\"]*)\" image \"([^\"]*)\"$")
    public void iCreateANewProductByProvidingTheInformationNameTypePriceShippingUpcDescriptionManufacturerModelUrlImage(String name, String type, int price, String shipping, double upc, String description, String manufacturer, String model, String url, String image) {
        response = productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
    }

    @Then("^I verify that the product with \"([^\"]*)\" is created$")
    public void iVerifyThatTheProductWithIsCreated(String name) {
        //response.statusCode(201).log().all();
        HashMap<String, Object> productInfo = productsSteps.getSingleProductsWithName(name);
        assertThat(productInfo, hasValue(name));
    }
}
