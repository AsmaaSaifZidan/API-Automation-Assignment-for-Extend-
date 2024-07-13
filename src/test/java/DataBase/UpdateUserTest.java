package DataBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import reporting.ExtentManager;

import java.util.HashMap;
import java.util.Map;

public class UpdateUserTest {
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setup() {
        extent = ExtentManager.getInstance();
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void updateUserTest() {
        test = extent.createTest("Update User Test");

        // Creating JSON request payload
        Map<String, String> requestPayload = new HashMap<>();
        requestPayload.put("first_name", "Asmaa Saif");
        requestPayload.put("last_name", "Zidan");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .log().all()
                .put("/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        test.log(Status.INFO, "Request: PUT /users/2");
        test.log(Status.INFO, "Request Body: " + requestPayload.toString());
        test.log(Status.INFO, "Response: " + response.asString());

        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertEquals(response.jsonPath().getString("first_name"), "Asmaa Saif");
            Assert.assertEquals(response.jsonPath().getString("last_name"), "Zidan");
            test.pass("Test passed");
        } catch (AssertionError e) {
            test.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        extent.flush();
    }
}
