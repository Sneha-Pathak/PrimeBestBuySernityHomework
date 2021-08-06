package com.bestbuy.model.bestbuyinfo;

import com.bestbuy.model.constants.Path;
import com.bestbuy.model.testbase.TestBase;
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
public class StoresCRUDTestWithSteps extends TestBase
{
    @Before
    public void setUp()
    {
        RestAssured.basePath = Path.STORES;
    }

    static String name = "Shamz";
    static String type = "online store";
    static String address = "34 rugby avanue";
    static String address2 = "ealing road";
    static String city = "Wembley";
    static String state = "middlesex";
    static String zip = "ka1 3kd";
    static int lat = 0;
    static int lng = 0;
    static String hours = "247";
    static HashMap<String,Object> services = new HashMap<>();
    static int storesId;

    @Steps
    StoresSteps storesSteps;

    //Get request
    @Title("Create and Verify the stores was added to the application")
    @Test
    public void test001() {

        HashMap<String, Object> storesValue =
                storesSteps.createStoresData(name, type, address, address2, city, state, zip, lat, lng, hours,services)
                        .log().all()
                        .statusCode(201)
                        .extract()
                        .body().jsonPath().get();
        System.out.println(storesValue);
        assertThat(storesValue, hasValue(name));
        storesId = (int) storesValue.get("id");
    }

    //Put Request
    @Title("Update stores information and verify updated information")
    @Test
    public void test002() {

        name = name + "_Update";
        HashMap<String, Object> storesValue = storesSteps.updateStoresData(storesId, name, type, address, address2, city, state, zip, lat, lng, hours, services)
                .statusCode(200)
                .extract()
                .body().jsonPath().get();
        assertThat(storesValue, hasValue(name));
        System.out.println(storesValue);

    }

    //Patch request
    @Title("Update stores information with patch request and verify information")
    @Test
    public void test003() {

        name = name + "_UpdatePatch";

        HashMap<String, Object> storesValue = storesSteps.updateStoreRecordWithPatch(storesId, name).statusCode(200)
                .extract()
                .body().jsonPath().get();
        assertThat(storesValue, hasValue(name));
        System.out.println(storesValue);

    }

    //Delete Request
    @Title("This will delete the stores information")
    @Test
    public void test004() {

        storesSteps.deleteStoresData(storesId).statusCode(200);
        storesSteps.getSingleStoresData(storesId).statusCode(404);
    }
}
