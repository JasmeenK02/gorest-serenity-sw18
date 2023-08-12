package com.gorest.crudtest;


import com.gorest.steps.PostSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class PostTests extends TestBase {
    String id = TestUtils.getRandomValue();
    String user_id = TestUtils.getRandomValue();
    String title = "Prime" + TestUtils.getRandomValue();
    String body = "abc";

    @Steps
    PostSteps postsSteps;

    @Title("This will create a new post")
    @Test
    public void test001() {
        ValidatableResponse response = postsSteps.createPost(id,user_id,title,body);
        response.log().all();
        //id = response.extract().path("id");
    }

    @Title("Update post information")
    @Test
    public void test003() {
        title = "Prime" + " Updated";
        ValidatableResponse response = postsSteps.updatePost(id,user_id,title,body);
    }

    @Title("This will get info by ID")
    @Test
    public void test004() {
        ValidatableResponse response = postsSteps.getPostId(id);
        response.log().all();
    }

    @Title("This will Delete the post information")
    @Test
    public void test005() {
        postsSteps.deletePostId(id)
                .statusCode(200);
        postsSteps.getPostId(id)
                .statusCode(404);
    }

}
