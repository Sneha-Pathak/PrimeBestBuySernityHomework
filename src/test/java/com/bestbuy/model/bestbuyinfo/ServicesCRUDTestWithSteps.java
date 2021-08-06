package com.bestbuy.model.bestbuyinfo;


import com.bestbuy.model.constants.Path;
import com.bestbuy.model.testbase.TestBase;
import com.bestbuy.model.utils.TestUtils;
import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

//@RunWith(SerenityRunner.class)
public class ServicesCRUDTestWithSteps extends TestBase
{
    @Before
    public void setUp()
    {
        RestAssured.basePath = Path.SERVICES;
    }

    static String name = "Micro Services" + TestUtils.getRandomValue();
    static int servicesID;

    @Steps
    ServicesSteps servicesSteps;

    //Get Request
    @Title("Create and Verify the services was added to the application")
    @Test
    public void test001() {

        HashMap<String,Object> servicesValue=
                servicesSteps.createServices(name).statusCode(201)
                        .extract().body().jsonPath().get();
        assertThat(servicesValue,hasValue(name));
        System.out.println(servicesValue);
        servicesID= (int) servicesValue.get("id");
    }

    //Put Request
    @Title("Update services information and verify the updated information")
    @Test
    public void test002(){

        name=name+"_Updated";

        HashMap<String,Object> servicesValue= servicesSteps.updateServices(servicesID,name)
                .statusCode(200).extract().body().jsonPath().get();
        assertThat(servicesValue,hasValue(name));
        System.out.println("Updated name : "+name);
        System.out.println(servicesValue);

    }

    //Delete Request
    @Title("Delete services information with id and verify it")
    @Test
    public void test003(){
        servicesSteps.deleteServices(servicesID)
                .log().all()
                .statusCode(200);
        servicesSteps.getSingleServicesWithId(servicesID)
                .statusCode(404);

    }
}
