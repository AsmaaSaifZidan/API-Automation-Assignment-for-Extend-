package DataBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import reporting.ExtentManager;

public class DeleteUserTest {
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setup() {
        extent = ExtentManager.getInstance();
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void deleteUserTest() {
        test = extent.createTest("Delete User Test");

        Response response = RestAssured.given()
                .log().all()
                .delete("/users/2")
                .then()
                .log().all()
                .statusCode(204)
                .extract()
                .response();

        test.log(Status.INFO, "Request: DELETE /users/2");
        test.log(Status.INFO, "Response: " + response.asString());

        try {
            Assert.assertEquals(response.getStatusCode(), 204);
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
