package api.endpoints;

//endpoints
//base Url- common for all-  "https://petstore.swagger.io/v2"
// POST - "https://petstore.swagger.io/v2/user"
//Get- "https://petstore.swagger.io/v2/user/{username}"
//put - "https://petstore.swagger.io/v2/user/{username}"
//delete - "https://petstore.swagger.io/v2/user/{username}"

public class routes {

    //common for all in that overall docmentation
    public static String base_url="https://petstore.swagger.io/v2";

    //User module
    public static String post_url=base_url+"/user";
    public static String get_url=base_url+"/user/{username}";
    public static String update_url=base_url+"/user/{username}";
    public static String delete_url=base_url+"/user/{username}";

}
