package com.bestbuy.model.cucumber.steps;

import com.bestbuy.model.bestbuyinfo.ProductsSteps;
import com.bestbuy.model.bestbuyinfo.ServicesSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasValue;

public class MyStepdefs
{
    static ValidatableResponse response = null;

    @Steps
    ServicesSteps servicesSteps;

    @When("^User sends a get request to list endpoints, they must get back a valid status code 200$")
    public void userSendsAGetRequestToListEndpointsTheyMustGetBackAValidStatusCode()
    {
        servicesSteps.allServices().statusCode(200).log().all();
    }

    @When("^I create a new service by providing the information serviceName \"([^\"]*)\"$")
    public void iCreateANewServiceByProvidingTheInformationServiceName(String serviceName)
    {
        response = servicesSteps.createServices(serviceName);
        response.log().all();
    }

    @Then("^I verify that the service with \"([^\"]*)\" is created$")
    public void iVerifyThatTheServiceWithIsCreated(String serviceName)
    {
//        response.statusCode(201).log().all();
        HashMap<String,Object> serviceWithName =  servicesSteps.getSingleServiceWithName(serviceName);
        assertThat(serviceWithName, hasValue(serviceName));
    }

}
