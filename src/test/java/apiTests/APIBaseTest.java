package apiTests;

import apiReusableUtils.RestUtils;
import com.jayway.jsonpath.JsonPath;
import constants.FileConstants;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import utils.CommonUtilities;

import java.io.IOException;
import java.util.HashMap;

public class APIBaseTest {

    public static String token;
    public static String userId;


    public static void setToken() throws IOException {
        String uri = CommonUtilities.readFileAndReturnAString(FileConstants.URI_FILE_PATH);
        RestAssured.baseURI = JsonPath.read(uri,"$.login.production");
        String credentials = CommonUtilities.readFileAndReturnAString(FileConstants.USER_CONFIG_FILE_PATH);
        String un = JsonPath.read(credentials, "$.production.username");
        String pw = JsonPath.read(credentials, "$.production.password");
        HashMap<String, String> payload = new HashMap<>();
        payload.put("username", un);
        payload.put("password", pw);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        Response res = RestUtils.postRequirements(payload, headers, "/login");
        token = JsonPath.read(res.asString(), "$.[0].token");
        userId = JsonPath.read(res.asString(),"$.[0].userid");
    }

    @BeforeTest
    public void setUp() throws IOException {
        APIBaseTest.setToken();
    }
}

