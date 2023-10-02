package apiReusableUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;

public class RestUtils {

    public static Response postRequirements(HashMap<String,String> payload,HashMap<String,String> headers,String endPoint){
        return RestAssured.given().headers(headers).when().body(payload).post(endPoint);
    }

    public static Response postRequirements(String payload, HashMap<String, String> headers, String endPoint) {
        return RestAssured.given().headers(headers).when().body(payload).post(endPoint);
    }


    public static Response getRequirements(HashMap<String,String> headers,String endPoint){
        return RestAssured.given().headers(headers).when().get(endPoint);
    }
}
