package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1.Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is : " + limit);
    }

    //2.Extract the Total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("The total value is : " + total);
    }

    //3.Extract the name of the 5th Store
    @Test
    public void test003() {
        String storeName = response.extract().path("data[4].name");
        System.out.println("The store name is : " + storeName);
    }

    //4.Extract the names of all the store
    @Test
    public void test004() {
        List<String> listOfStores = response.extract().path("data.name");
        System.out.println("List names of stores are : " + listOfStores);
    }

    //5.Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> listOfIds = response.extract().path("data.id");
        System.out.println("List of Id of stores are : " + listOfIds);
    }

    //6.Print the size of the data list
    @Test
    public void test006() {
        List<Integer> sizeD = response.extract().path("data");
        System.out.println("Size of data are : " + sizeD.size());
    }

    //7.Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("The values of the store St Cloud are: " + values);
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<HashMap<String, ?>> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("The values of the store St Cloud are: " + address);
    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {
        List<String> services = response.extract().path("data[7].services");
        System.out.println("All services of 8th store are: " + services);
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        List<String> storeServices = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices");
        System.out.println("Store services of Window Store are: " + storeServices);
    }

    //11. Get all the storeId of all the store
    @Test
    public void test011() {
        List<Integer> id = response.extract().path("data.services.storeservices.findAll{it.storeId}.storeId");
        System.out.println("All Id of all stores are: " + id);
    }

    //12. Get id of all the store
    @Test
    public void test012() {
        List<Integer> id = response.extract().path("data.id");
        System.out.println("Id of stores are: " + id);
    }

    //13. Find the store names Where state = ND
    @Test
    public void test013() {
        List<String> storeName = response.extract().path("data.findAll {it.state == 'ND'}.name");
        System.out.println("Get store names Where state = ND " + storeName);
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<List<Integer>> storeName = response.extract().path("data.findAll {it.name == 'Rochester'}.services.id");
        System.out.println("The total number of services for the store where store name : " + storeName.get(0).size());
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {
        List<String> createdAt = response.extract().path("data.find{it.services}.services.findAll {it.name == 'Windows Store'}.createdAt");
        System.out.println("the createdAt for all services whose name = “Windows Store” is: " + createdAt);
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
        List<String> name = response.extract().path("data.findAll {it.name == 'Fargo'}.services.name");
        System.out.println("The name of all services Where store name = “Fargo” : " +name);
    }

    //17. Find the zip of all the store
    @Test
    public void test017() {
        List<String> zipStore = response.extract().path("data.zip");
        System.out.println("The zip of all the store are : " +zipStore);
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test018() {
        List<String> zipStore = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("The zip of store name = Roseville is : " +zipStore);
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<String> storeServiceName =  response.extract().path("data.find{it.services}.services.findAll{it.name='Magnolia Home Theater'}");
        System.out.println("The storeservices details of the service name = Magnolia Home Theater : " +storeServiceName);
    }

    //20. Find the lat of all the stores
    @Test
    public void test020() {
        List<String> listLat =  response.extract().path("data.lat");
        System.out.println("The list of lat of all the stores : " +listLat);
    }
}
