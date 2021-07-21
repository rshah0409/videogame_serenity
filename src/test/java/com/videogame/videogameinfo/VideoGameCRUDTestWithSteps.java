package com.videogame.videogameinfo;

import com.videogame.testbase.TestBase;
import com.videogame.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class VideoGameCRUDTestWithSteps extends TestBase {
    static int id = 2 +TestUtils.getRandomValueInt();
    static String name = "Super Mario" + TestUtils.getRandomValue();
    static String releaseDate = "2021-06-16T09:14:42.993Z";
    static int reviewScore = 99 + TestUtils.getRandomValueInt();
    static String category = "Kids" ;
    static String rating = "PG-13" ;


    @Steps
    VideoGameSteps videoGameSteps;

    @Title("This will create new videogame")
    @Test
    public void test001() {
        videoGameSteps.createVideoGame( id, name, releaseDate, reviewScore, category, rating ).log().all().statusCode( 200 )
                .extract().response()
                .body().jsonPath();
        System.out.println("product id is : " + id);
    }
@Title( "This will get single videogame information" )
    @Test
    public void test002(){

  // id = videoGameSteps.getAllVideoGames().extract().path("data[0].id");
    videoGameSteps.getSingleVideoGameByid(id).statusCode( 200 );

}
@Title( "This will update Single videogame" )
    @Test
    public void test003(){
        name = name +"_updated";
        category = category +"_changed";
    videoGameSteps.updatesingleVideoGameById( id,name,releaseDate,reviewScore,category,rating ).statusCode( 200 );
    //    videoGameSteps.getSingleVideoGameByid( id ).body( "name",equalTo(name) );
    }
    @Title( "This will delete videogame by id" )
    @Test
public void test004(){

        videoGameSteps.deleteGameById( id ).statusCode( 200 );
        videoGameSteps.getSingleVideoGameByid( id ).log().all().statusCode( 500 );



    }




}
