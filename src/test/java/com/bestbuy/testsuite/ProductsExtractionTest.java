package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void test001(){
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is : " + limit);
    }
    //2. Extract the total
    @Test
    public void test002(){
        int total = response.extract().path("total");
        System.out.println("The total value is : " + total);
    }
    //3. Extract the name of 5th product
    @Test
    public void test003(){
        String productName = response.extract().path("data[4].name");
        System.out.println("The 5th product name is : " + productName);
    }
    //4. Extract the names of all the products
    @Test
    public void test004(){
        List<String> listOfProducts = response.extract().path("data.name");
        System.out.println("List names of all products are : " + listOfProducts);
    }
    //5. Extract the productId of all the products
    @Test
    public void test005(){
        List<Integer> listOfIds = response.extract().path("data.id");
        System.out.println("List of Id of products are : " + listOfIds);
    }
    //6. Print the size of the data list
    @Test
    public void test006(){
        List<Integer> sizeD = response.extract().path("data");
        System.out.println("Size of data are : " + sizeD.size());
    }
    //7. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test007(){
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("The values of the Energizer - MAX Batteries AA (4-Pack) is: " + values);
    }
    //8. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test008(){
        List<HashMap<String, ?>> model = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("The model of the product Energizer - N Cell E90 Batteries (2-Pack) is: " + model);
    }
    //9. Get all the categories of 8th products
    @Test
    public void test009(){
        List<String> categories = response.extract().path("data[8].categories");
        System.out.println("All categories of 8th product are: " + categories);
    }
    //10. Get categories of the store where product id = 150115
    @Test
    public void test010(){
        List<String> productCategories = response.extract().path("data[3].categories");
        System.out.println("Categories of the store where product id = 150115 are : " + productCategories);
    }
    //11.Get all the descriptions of all the products
    @Test
    public void test011(){
        List<String> description = response.extract().path("data.description");
        System.out.println("All descriptions of all products are: " + description);
    }
    //12. Get id of all the all categories of all the products
    @Test
    public void test012(){
        List<Integer> id = response.extract().path("data.categories.id");
        System.out.println("Id of categories are: " + id);
    }
    //13. Find the product names Where type = HardGood
    @Test
    public void test013(){
        List<String> productName = response.extract().path("data.findAll {it.type == 'HardGood'}.name");
        System.out.println("Get product names Where type = HardGood " + productName);
    }
    //14. Find the Total number of categories for the product where product name = Duracell - AA
    // 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014(){
        List<Integer> cat = response.extract().path("data[1].categories");
        System.out.println("The total number of categories for the product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack) are: " +cat.size());
    }
    //15. Find the createdAt for all products whose price < 5.49
    @Test
    public void test015(){
        List<String> productName = response.extract().path("data.findAll {it.price < 5.49}.createdAt");
        System.out.println("the createdAt for all products whose price < 5.49 are: " + productName);
    }
    //16. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test016(){
        List<String> name = response.extract().path("data.findAll {it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("The name of all categories Where products name = “Energizer - MAX Batteries AA (4-Pack)” are: " +name);
    }
    //17. Find the manufacturer of all the products
    @Test
    public void test017(){
        List<String> listOfManufacturer = response.extract().path("data.manufacturer");
        System.out.println("The list of all the manufacturers are : " +listOfManufacturer);
    }
    //18. Find the imge of products whose manufacturer is = Energizer
    @Test
    public void test018(){
        List<String> image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("The image of product whose manufacturer is = Energizer is : " +image);
    }
    //19. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test019(){
        List<String> category = response.extract().path("data.findAll {it.price > 5.99}.categories.createdAt");
        System.out.println("the createdAt for all categories products whose price > 5.99 are: " + category);
    }
    //20. Find the uri of all the products
    @Test
    public void test020(){
        List<String> listUrl =  response.extract().path("data.url");
        System.out.println("The list of url of all the products : " +listUrl);
    }

}
