package tests.sportactivity;

import base.BaseTest;
import body.sportactivity.SportActivityBody;
import body.sportcategory.SportCategoryBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TokenHelper;
import utils.Utils;

public class SportActivityTest extends BaseTest {

    //token helper (membantu mengambil token)
    private String categoryId;

    //Get Token -> ambil dari folder src/resources/json/token.json
    //Create
    @Test
    public void createSportActivity(){
        SportActivityBody sportActivityBody = new SportActivityBody();
        String token = TokenHelper.getToken();
        String randomName = Utils.getCategoryName();

        //Ngehit endpoint
        Response response = RestAssured.given()
                .header("Authorization","Bearer " + token)
                .header("Content-Type", "application/json")
                .body(sportActivityBody.createSportActivityData(
                        "22",
                        3172,
                        "Futsal",
                        "Tarkam",
                        1,
                        70000,
                        "Lapangan Sulaiman",
                        "2026-06-04",
                        "09:00",
                        "10:00",
                        "https://maps.app.goo.gl/h1AV4bfB2cojJMxK7").toString())
                .when()
                .post("v1/sport-activities/create")
                .then()
                .extract().response();

        System.out.println("Create Response: " + response.asString());

        //Assert
        //Get Activity from response
        System.out.println("Created Category ID: " + categoryId);
        categoryId = response.jsonPath().getString("result.id");
        Assert.assertNotNull(categoryId,"Category ID should not be null");
    }
    //Read
    @Test
    public void getSportActivity(){
        String token = TokenHelper.getToken();
//curl --location 'https://sport-reservation-api-bootcamp.do.dibimbing.id/api/v1/sport-activities?is_paginate=true&per_page=10&page=1
        Response response = RestAssured.given()
                .header("Authorization","Bearer " + token)
                .header("Content-Type", "application/json")
                .queryParam("is_paginate","true")
                .queryParam("per_page",10)
                .queryParam("page",1)
                .queryParam("search","")
                .queryParam("sport_category_id")
                .queryParam("city_id")
                .when()
                .get("v1/sport-activities")
                .then()
                .extract().response();

        System.out.println("Get Response: " + response.asString());
    }

    //GET ACTIVITY BY ID
    @Test
    public void getActivityById() {
        String token = TokenHelper.getToken();

        Response response = RestAssured.given()
                .header("Authorization","Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get("v1/sport-activities/1")
                .then()
                .extract().response();

        System.out.println("Get Response : " + response.asString());
    }

    //Update
    //Delete
    @Test
    public void deleteSportCategory(){
        String token = TokenHelper.getToken();

        Response response = RestAssured.given()
                .header("Authorization","Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .delete("v1/sport-categories/delete" + categoryId)
                .then()
                .extract().response();

        System.out.println("Get Response: " + response.asString());
    }
}
