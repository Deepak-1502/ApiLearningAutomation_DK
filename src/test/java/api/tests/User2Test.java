package api.tests;

import api.endpoints.UserEndpoints2;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class User2Test {

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
    @Test(priority = 2)
    public void createUser(){
        Response res= UserEndpoints2.createUser(payload);
        res.then().statusCode(200).log().all();
    }
    @Test(priority = 3)
    public void getUser(){
        Response res=UserEndpoints2.getUser(this.payload.getUsername());
        res.then().statusCode(200).log().all();
    }
    @Test(priority = 4)
    public void updateser(){
        System.out.println(payload.getEmail());
        payload.setFirstName(faker.name().firstName());
        payload.setLastName(faker.name().lastName());
        payload.setEmail(faker.internet().emailAddress());

        Response response=UserEndpoints2.update(this.payload.getUsername(),payload);
        response.then().log().all();

        System.out.println(payload.getEmail());
        Assert.assertEquals(response.getStatusCode(),200);

        Response afterchanges=UserEndpoints2.getUser(payload.getUsername());
        afterchanges.then().log().all();
        Assert.assertEquals(afterchanges.getStatusCode(),200);
    }

    @Test(priority = 5)
    public void delete(){
        Response res=UserEndpoints2.delete(payload.getUsername());
        res.then().statusCode(200).log().all();

    }
}
