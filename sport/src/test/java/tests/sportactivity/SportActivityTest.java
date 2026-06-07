package tests.sportactivity;

import base.BaseTest;
import body.sportactivity.SportActivityBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TokenHelper;
import utils.Utils;

public class SportActivityTest extends BaseTest {

    //token helper (membantu mengambil token)
    private String activityId;

    //Get Token -> ambil dari folder src/resources/json/token.json
    //Create
    @Test(priority = 1)
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
                        "2026-06-08",
                        "09:00",
                        "10:00",
                        "https://maps.app.goo.gl/h1AV4bfB2cojJMxK7").toString())
                .when()
                .post("v1/sport-activities/create")
                .then()
                .extract().response();

        System.out.println("Create Response: " + response.asString());

        //Get Activity from response
        activityId = response.jsonPath().getString("result.id");
        System.out.println("Created Activity ID: " + activityId);

        //Assert
        Assert.assertNotNull(activityId,"Activity ID should not be null");
    }
    //Read

    //GET ACTIVITY BY ID
    @Test(priority = 2)
    public void getActivityById() {
        String token = TokenHelper.getToken();

        Response response = RestAssured.given()
                .header("Authorization","Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get("v1/sport-activities/" + activityId)
                .then()
                .log().all()
                .extract().response();

        System.out.println("Get Response : " + response.asString());
    }

    //GET ACTIVITY
    @Test(priority = 3)
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
                .when()
                .get("v1/sport-activities")
                .then()
                .log().all()
                .extract().response();

        System.out.println("Get Response: " + response.asString());
    }

    //Update
    @Test(priority = 4)
    public void updateSportActivity() {
        SportActivityBody sportActivityBody = new SportActivityBody();
        String token = TokenHelper.getToken();
        String randomName = Utils.getCategoryName();

        //Ngehit endpoint
        Response response = RestAssured.given()
                .header("Authorization","Bearer " + token)
                .header("Content-Type", "application/json")
                .body(sportActivityBody.updateSportActivityData(
                        "66",
                        3172,
                        "Sepakbola",
                        "Tarkam",
                        5,
                        90000,
                        "Lapangan Arcamanik",
                        "2026-06-10",
                        "09:00",
                        "10:00",
                        "https://maps.app.goo.gl/h1AV4bfB2cojJMxK7").toString())
                .when()
                .post("v1/sport-activities/update/" + activityId)
                .then()
                .extract().response();

        System.out.println("Create Response: " + response.asString());
    }

    //Delete
    @Test(priority = 5)
    public void deleteSportActivity(){
        String token = TokenHelper.getToken();

        Response response = RestAssured.given()
                .header("Authorization","Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .delete("v1/sport-activities/delete/" + activityId)
                .then()
                .extract().response();

        System.out.println("Get Response: " + response.asString());
    }
}
