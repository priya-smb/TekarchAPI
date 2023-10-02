package apiTests;

import apiReusableUtils.RestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import constants.FileConstants;
import io.restassured.RestAssured;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testData.AddUser;
import testData.DeleteUser;
import testData.UpdateUser;
import utils.CommonUtilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class LoginTestDynamic extends APIBaseTest {

    //String token;

    @BeforeClass
    public void setURI() throws IOException {
        String uri = CommonUtilities.readFileAndReturnAString(FileConstants.URI_FILE_PATH);
        RestAssured.baseURI = JsonPath.read(uri, "$.login.production");
        System.out.println(RestAssured.baseURI);
    }

    @Test(enabled = true)
    public void loginTC_01() {
//        RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        HashMap<String, String> logincreds = new HashMap<>();
        logincreds.put("username", "priyanka@tekarch.com");
        logincreds.put("password", "Admin123");

        Response res1 =  RestUtils.postRequirements(logincreds, headers, "/login")
                .then().assertThat().statusCode(201)
                .body(matchesJsonSchema(new File(FileConstants.LOGIN_SCHEMA)))
                .extract().response();

        Assert.assertEquals(res1.statusCode(), 201);

    }

    @Test
    public void getData_tc02() {
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Token", token);
        headers.put("Content-Type", "application/json");

        Response getUserData = RestUtils.getRequirements(headers, "/getdata").then().statusCode(200).extract().response();

        System.out.println(getUserData.asPrettyString());

        List<String> accountNumbers = getUserData.jsonPath().getList("accountno");

        System.out.println(accountNumbers.size());

        assertThat(accountNumbers.size(), greaterThan(10000));
//		System.out.println(getUserData.prettyPrint());
    }

    @Test
    public void addData_tc3() throws JsonProcessingException {
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Token", token);
        headers.put("Content-Type", "application/json");

        AddUser user1 = new AddUser("TA-1111111","1","11111","111111");
        String sPayload = CommonUtilities.serializeObject(user1);
        Response addUserData = RestUtils.postRequirements(sPayload, headers, "/addData");
        
        System.out.println(addUserData.prettyPrint());
    }

    @Test
    public void updateData_tc4() throws JsonProcessingException {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Token",token);
        headers.put("content-Type","application/json");

        UpdateUser updateUser = new UpdateUser("TA-1212121", "2",
                "11111", "111111",userId, "jC7HAPvyfYuIcnxCkAkJ");
        String sPayload = CommonUtilities.serializeObject(updateUser);

        Response updatedResponse = RestAssured.given().headers(headers).when().body(sPayload).put("/updateData");
        System.out.println(updatedResponse.prettyPrint());

       io.restassured.path.json.JsonPath jsonPath = updatedResponse.jsonPath();
       String updatedStatus = jsonPath.get("status");
       Assert.assertEquals(updatedStatus,"success");
    }

    @Test
    public void deleteData_tc5() throws JsonProcessingException {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Token",token);
        headers.put("content-Type","application/json");

        DeleteUser deleteUser = new DeleteUser(userId,"UHzE0Ge0pCjCJ9rFcao6");
        String sPayload = CommonUtilities.serializeObject(deleteUser);

        Response deletedResponse = RestAssured.given().headers(headers).when().body(sPayload).delete("/deleteData");
        System.out.println(deletedResponse.prettyPrint());

        io.restassured.path.json.JsonPath jsonPath = deletedResponse.jsonPath();
        String deletedStatus = jsonPath.get("status");
        Assert.assertEquals(deletedStatus,"success");

    }

}
