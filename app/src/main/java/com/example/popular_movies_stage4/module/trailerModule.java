package com.example.popular_movies_stage4.module;

public class trailerModule {
    private String name;
    private  String key;

   public trailerModule(){}
    public trailerModule(String name,String key){
       this.key=key;
       this.name=name;
    }
    //setter

    //getter
    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
