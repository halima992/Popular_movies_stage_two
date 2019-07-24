package com.example.popular_movies_stage4.module;

public class ReviewModule {
    String theAuthor;
    String theContent;
    public ReviewModule(){}
    public ReviewModule(String theAuthor,String theContent){
        this.theAuthor=theAuthor;
        this.theContent=theContent;
    }
    //set

    public void setTheAuthor(String theAuthor) {
        this.theAuthor = theAuthor;
    }
    public void setTheContent(String theContent) {
        this.theContent = theContent;
    }

    //get
    public String getTheAuthor() {
        return theAuthor;
    }

    public String getTheContent() {
        return theContent;
    }
}
