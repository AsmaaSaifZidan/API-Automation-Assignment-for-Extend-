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

public class CreateUserTest {
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setup() {
        extent = ExtentManager.getInstance();
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void createUserTest() {
        test = extent.createTest("Create User Test");

        // Creating JSON request payload
        Map<String, String> requestPayload = new HashMap<>();
        requestPayload.put("id", "6");
        requestPayload.put("first_name", "Asmaa");
        requestPayload.put("last_name", "Zidan");
        requestPayload.put("email", "tracey.ramos@reqres.in");
        requestPayload.put("avatar", "https://reqres.in/img/faces/6-image.jpg");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .log().all()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .response();

        test.log(Status.INFO, "Request: " + requestPayload.toString());
        test.log(Status.INFO, "Response: " + response.asString());

        try {
            Assert.assertEquals(response.getStatusCode(), 201);
            Assert.assertEquals(response.jsonPath().getString("name"), "Asmaa");
            Assert.assertEquals(response.jsonPath().getString("job"), "Tester");
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
