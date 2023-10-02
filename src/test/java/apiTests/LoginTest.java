package apiTests;

import apiReusableUtils.RestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class LoginTest extends APIBaseTest{

    String token;

    @Test
    public void loginTc_01() {
        RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";

        HashMap<String,String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        HashMap<String,String> loginInfo = new HashMap<>();
        loginInfo.put("userName","priyanka@tekarch.com");
        loginInfo.put("password","Admin123");

        Response res1 =  RestUtils.postRequirements(loginInfo, headers, "/login");

        Assert.assertEquals(res1.statusCode(), 201);

        token = res1.jsonPath().getString("token").replace("[", "").replace("]", "");

    }

    //	@Test(dependsOnMethods = "loginTC_01")
    public void getData_TC02() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Token", token);
        headers.put("Content-Type", "application/json");

        Response getUserData = RestUtils.getRequirements(headers, "/getdata");

        System.out.println(getUserData.prettyPrint());
    }


    //	@Test(dependsOnMethods = "getData_TC02")
    public void addData_TC02() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Token", token);
        headers.put("Content-Type", "application/json");

        HashMap<String, String> payload = new HashMap<>();
        payload.put("accountNo", "");
        payload.put("departmentNo", "");
        payload.put("salary", "");
        payload.put("pinCode", "");

        Response addUserData = RestUtils.postRequirements(payload, headers, "/addData");

        System.out.println(addUserData.prettyPrint());

    }

}
