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
public class CategoriesCRUDTestWithSteps extends TestBase
{
    @Before
    public void setUp()
    {
        RestAssured.basePath = Path.CATEGORIES;
    }

    static String name = "Radios" + TestUtils.getRandomValue();
    static String id = "abc123" + TestUtils.getRandomValue();

    @Steps
    CategoriesSteps categoriesSteps;

    //Get Request
    @Title("Create and Verify if the categories was added to the application")
    @Test
    public void test001()
    {
        HashMap<String, Object> categoriesValue =
                categoriesSteps.createCategories(id, name).statusCode(201)
                        .extract()
                        .body().jsonPath().get();
        System.out.println(categoriesValue);
        assertThat(categoriesValue, hasValue(name));
        id = (String) categoriesValue.get("id");
    }

    //Put Request
    @Title("Update categories information and verify the updated information")
    @Test
    public void test002() {

        name = name + "_Updated";
        HashMap<String, Object> categoriesValue = categoriesSteps.updateCategories(id, name)
                .statusCode(200)
                .extract()
                .body().jsonPath().get();
        assertThat(categoriesValue, hasValue(name));
        System.out.println(categoriesValue);
    }

    //Patch Request
    @Title("Update categories information with patch request and verify the updated information")
    @Test
    public void test003() {

        name = name + "_UpdatedPatch";

        HashMap<String, Object> categoriesValue = categoriesSteps.updateCategoriesWithPatch(id, name)
                .statusCode(200)
                .extract()
                .body().jsonPath().get();
        assertThat(categoriesValue, hasValue(name));
        System.out.println(categoriesValue);

    }


    //Delete Request
    @Title("Delete categories information with id and verify it")
    @Test
    public void test004()
    {
        categoriesSteps.deleteCategories(id)
                .log().all()
                .statusCode(200);

        categoriesSteps.getSingleCategoriesWithId(id)
                .statusCode(404);
    }
}
