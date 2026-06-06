package api.tests;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    User payload;
    @BeforeClass
    public void setUp(){
        faker=new Faker();
        payload=new User();

        payload.setId(faker.idNumber().hashCode());
        payload.setUsername(faker.name().fullName());
        payload.setFirstName(faker.name().firstName());
        payload.setLastName(faker.name().lastName());
        payload.setEmail(faker.internet().emailAddress());
        payload.setPassword(faker.internet().password(3,9));
        payload.setPhone(faker.phoneNumber().cellPhone());
//        System.out.println(payload.getEmail());

    }

    @Test(priority = 1)
    public void createUserTest(){
        System.out.println("Name print from Ceatingg : "+payload.getUsername());
        Response res= UserEndpoints.createUser(payload);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(),200);
        System.out.println(res.getBody().asString());
    }

    @Test(priority = 2)
    public void getUser(){
        System.out.println("Name print from Gettinggg : "+payload.getUsername());
        Response res=UserEndpoints.getUser(this.payload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(),200);
//        System.out.println(res);
    }

    @Test(priority = 3)
    public void updateUser(){
        //here why we settng again means we need to pdate so new data needed so setting
        //then the payload is
        System.out.println("Payload data Email Before: "+payload.getEmail());
        payload.setFirstName(faker.name().firstName());
        payload.setLastName(faker.name().lastName());
        payload.setEmail(faker.internet().emailAddress());
        Response res=UserEndpoints.updateUser(this.payload.getUsername(),payload);
        res.then().log().all();

        System.out.println("Payload data Email: "+payload.getEmail());
        Assert.assertEquals(res.getStatusCode(),200);

        Response afterUpdate=UserEndpoints.getUser(this.payload.getUsername());
        afterUpdate.then().log().all();
        Assert.assertEquals(afterUpdate.getStatusCode(),200);
        System.out.println("After pdate Email :: "+payload.getEmail());
        System.out.println("Name print from pdate : "+payload.getUsername());

    }

    @Test(priority = 4)
    public void delete(){
        System.out.println("Name print from Delete : "+payload.getUsername());
        Response res=UserEndpoints.deleteUser(payload.getUsername());

        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(),200);

    }

}
