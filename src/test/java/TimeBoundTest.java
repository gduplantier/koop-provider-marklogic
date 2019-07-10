
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class TimeBoundTest extends AbstractFeatureServiceTest {

	@Test
    public void testTimeBound() {

        String path = request2path("gkgTimeBound.json");

        //RestAssured
        Response response = 
        //.given()
        RestAssured.given()
        .when()
            .log().uri()
            .get(path)

        .then()
            .log().ifError()
            .statusCode(200)
            .log().ifValidationFails()
            .extract().response()
        ;
        
        List<Long> jsonResponse = response.jsonPath().getList("features.attributes.urlpubtimedate");
        
        //Assert that each feature returned is within the time bounds
        for (Long item : jsonResponse) {
        	if(item < 1493596800000L || item > 1496275200000L) {
				System.out.println(item + " is NOT between 1493596800000L and 1496275200000L");
			}
        	
        	assertTrue(item >= 1493596800000L);
        	assertTrue(item <= 1496275200000L);
		}
    }
	
	//Support numeric parameters as described in https://doc.arcgis.com/en/operations-dashboard/help/url-parameters.htm
	@Test
    public void testTimeBoundLeftOpen() {

        String path = request2path("gkgTimeBoundLeftOpen.json");

        //RestAssured
        Response response = 
        //.given()
        RestAssured.given()
        .when()
            .log().uri()
            .get(path)

        .then()
            .log().ifError()
            .statusCode(200)
            .log().ifValidationFails()
            .extract().response()
        ;
        
        List<Long> jsonResponse = response.jsonPath().getList("features.attributes.urlpubtimedate");
        
        //Assert that each feature returned is within the time bounds
        for (Long item : jsonResponse) {
        	if(item > 1496275200000L) {
				System.out.println(item + " is NOT less than 1496275200000L");
			}
        	
        	assertTrue(item <= 1496275200000L);
		}
    }
	
	@Test
    public void testTimeBoundRightOpen() {

        String path = request2path("gkgTimeBoundRightOpen.json");

        //RestAssured
        Response response = 
        //.given()
        RestAssured.given()
        .when()
            .log().uri()
            .get(path)

        .then()
            .log().ifError()
            .statusCode(200)
            .log().ifValidationFails()
            .extract().response()
        ;
        
        List<Long> jsonResponse = response.jsonPath().getList("features.attributes.urlpubtimedate");
        
        //Assert that each feature returned is within the time bounds
        for (Long item : jsonResponse) {
        	if(item <= 1493596800000L) {
				System.out.println(item + " is NOT greater than 1493596800000L");
			}
        	
        	assertTrue(item >= 1493596800000L);
		}
    }
	
	@Test
    public void testTimeBoundInstant() {

        String path = request2path("gkgTimeBoundInstant.json");

        //RestAssured
        Response response = 
        //.given()
        RestAssured.given()
        .when()
            .log().uri()
            .get(path)

        .then()
            .log().ifError()
            .statusCode(200)
            .log().ifValidationFails()
            .extract().response()
        ;
        
        List<Long> jsonResponse = response.jsonPath().getList("features.attributes.urlpubtimedate");
        
        //Assert that each feature returned is within the time bounds
        for (Long item : jsonResponse) {
        	if(item != 1495616400000L) {
				System.out.println(item + " is NOT equal to 1493596800000L");
			}
        	
        	assertTrue(item == 1495616400000L);
		}
    }
	
	@Test
    public void testTimeBoundNull() {

        String path = request2path("gkgTimeBoundNull.json");

        //RestAssured
        Response response = 
        //.given()
        RestAssured.given()
        .when()
            .log().uri()
            .get(path)

        .then()
            .log().ifError()
            .statusCode(200)
            .log().ifValidationFails()
            .extract().response()
        ;
        
        List<Long> jsonResponse = response.jsonPath().getList("features.attributes.urlpubtimedate");
        
        assertTrue(jsonResponse.size() >= 20);
    }
}

//"24457","24973","5632","24974","27161","56371","49518","49416","32295","32309","32296","47923","32293","8384","44483","32724","22445","22455","1807","5538"