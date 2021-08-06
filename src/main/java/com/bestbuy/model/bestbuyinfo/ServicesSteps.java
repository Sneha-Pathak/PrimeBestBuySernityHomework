package com.bestbuy.model.bestbuyinfo;


import com.bestbuy.model.constants.EndPoints;
import com.bestbuy.model.model.ServicesPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class ServicesSteps
{
    ServicesPojo servicesPojo=new ServicesPojo();

    @Step("Get all Services")
    public ValidatableResponse allServices() {

        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then();
    }

    @Step("Get single services with id : {0}")
    public ValidatableResponse getSingleServicesWithId(int servicesId){

        return SerenityRest.rest()
                .given()
                .pathParam("id",servicesId)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICES_BY_ID)
                .then();
    }

    @Step("Get Services with parameter limit : {0},skip : {1}")
    public ValidatableResponse getServicesWithParameters(int limit,int skip){

        HashMap<String,Integer> qParams=new HashMap<>();
        qParams.put("$limit",limit);
        qParams.put("$skip",skip);
        return SerenityRest.rest()
                .given()
                .queryParams(qParams)
                .when()
                .get()
                .then();
    }

    @Step("Create new Services with  name : {0}")
    public ValidatableResponse createServices(String name){


        servicesPojo.setName(name);
        return SerenityRest.rest()
                .given()
                .header("Content-Type","application/json")
                .body(servicesPojo)
                .when()
                .post()
                .then();
    }

    @Step("Update services with servicesId : {0} name : {1}")
    public ValidatableResponse updateServices(int servicesId,String name){
        servicesPojo.setName(name);
        return SerenityRest.rest()
                .given()
                .header("Content-Type","application/json")
                .pathParam("id",servicesId)
                .body(servicesPojo)
                .when()
                .put(EndPoints.UPDATE_SERVICES_BY_ID)
                .then();

    }


    @Step("Delete the services information  with ServicesId : {0}")
    public ValidatableResponse deleteServices(int servicesId){

        return SerenityRest.rest()
                .given()
                .pathParam("id",servicesId)
                .when()
                .delete(EndPoints.DELETE_SERVICES_BY_ID)
                .then();
    }

    @Step("Get single services with name : {0}")
    public HashMap<String,Object> getSingleServiceWithName(String serviceName)
    {
        String p1 = "data.findAll{it.name=='";
        String p2 = "'}";

        return SerenityRest.rest()
                .given().log().all()
                .header("Content-Type","application/json")
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + serviceName + p2);
    }
}
