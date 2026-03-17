package api.endpoints;

import api.payloads.User;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndpoints2 {
    static ResourceBundle getAll(){
        ResourceBundle resourceBundle=ResourceBundle.getBundle("routes");
        return resourceBundle;
    }

    public static Response createUser(User payload){
        String postlink=getAll().getString("POST");
        Response response=given().contentType("application/json")
                .body(payload).when().post(postlink);
        return response;
    }
    public static Response getUser(String username){
        String getlink=getAll().getString("Get");
        Response response=given().pathParam("username",username)
                .when().get(getlink);
        return response;
    }
    public static Response update(String username,User payload){
        String updatelink=getAll().getString("put");
        Response response=given().pathParam("username",username).body(payload).contentType("application/json")
                .when().put(updatelink);
        return response;
    }
    public static Response delete(String username){
        String delete=getAll().getString("delete");
        Response response=given().pathParam("username",username)
                .when().delete(delete);
        return response;
    }
}
