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
public class ProductsCRUDTestWithSteps extends TestBase
{
    static String name = "Kottak-AAA Batteries (10-Pack)" + TestUtils.getRandomValue();
    static String type = "HardGood";
    static double price = 2.99;
    static double shipping = 1.10;
    static String upc = "0987543" + TestUtils.getRandomValue();
    static String description = "Compatible with select electronic devices; 10 pack";
    static String manufacturer = "Kottak";
    static String model = "001" + TestUtils.getRandomValue();
    static String url = "http://www.bestbuy.com";
    static String image = TestUtils.getRandomValue() + ".jpg";
    static int productsId;

    @Before
    public void setUp(){

        RestAssured.basePath= Path.PRODUCTS;
    }

    @Steps
    ProductsSteps productsSteps;

    //Get Request
    @Title("Create and Verify if the products was added to the application")
    @Test
    public void test001() {


        HashMap<String,Object> pValue=productsSteps.createProduct(name,type,price,upc,shipping,description,manufacturer,model,url,image)
                .log().all()
                .statusCode(201).extract().response().body().jsonPath().get();
        System.out.println(pValue);
        assertThat(pValue,hasValue(name));
        productsId= (int) pValue.get("id");
        System.out.println(productsId);
    }

    //Put Request
    @Title("Update products information and verify the updated information")
    @Test
    public void test002(){

        name=name+"_Updated";
        HashMap<String,Object> pValue= productsSteps.updateProducts(productsId,name,type,price,upc,shipping,description,manufacturer,model,url,image)
                .statusCode(200).extract().response().body().jsonPath().get();

        assertThat(pValue,hasValue(name));
        System.out.println(pValue);

    }

    //Patch Request
    @Title("Update product information with patch and verify it")
    @Test
    public void test003(){


        name=name+"_patch";
        price=price+1;

        HashMap<String,Object> pValue= productsSteps.updateProductsWithPatch(productsId,name,price)
                .statusCode(200).extract().response().body().jsonPath().get();

        assertThat(pValue,hasValue(name));
        System.out.println(pValue);

    }

    //Delete Request
    @Title("Delete products information with id and verify it")
    @Test
    public void test004(){
        productsSteps.deleteProducts(productsId)
                .log().all()
                .statusCode(200);

        productsSteps.getSingleProductsWithId(productsId)
                .statusCode(404);

    }
}
