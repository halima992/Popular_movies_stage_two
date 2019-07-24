package com.example.popular_movies_stage4.module;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "movieFavorite")
        public class MovieModule {
        @PrimaryKey(autoGenerate = true)
        private int idDatabase;
        private int id;
        private String title;
        private String poster;
        private String posterbig;
        private String vote;
        private String overview;
        private String release;
        public String DBTM_URL = "https://image.tmdb.org/t/p/w92/";
        public String DBTM_URL2 = "https://image.tmdb.org/t/p/w500/";
        @Ignore
        public MovieModule(String title , String poster,String posterbig,String vote,
                           String overview,String release,int id){
        this.title    = title;
        this.poster   = poster;
        this.vote     = vote;
        this.overview = overview;
        this.release  = release;
        this.posterbig=posterbig;
        this.id=id;
        }
        //Todo first change
        /*public MovieModule(int idDatabase,String poster){
                this.idDatabase = idDatabase;
                this.poster = poster;
                //this.position=position;
        }*/
        public MovieModule(String title){
                this.title = title;
                //this.position=position;
        }

        //set

        public void setIdDatabase(int idDatabase) {
                this.idDatabase = idDatabase;
        }
        public int getIdDatabase() {
                return idDatabase;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }
        public void setTitle(String title){
        this.title = title;
        }
        public void setPoster(String poster){
        this.poster = poster;
        }
        public void setPosterbig(String posterbig){
                this.posterbig = posterbig;
        }
        public void setVote(String vote){
        this.vote = vote;
        }
        public void setOverview(String overview){
        this.overview = overview;
        }
        public void setRelease(String release){
        this.release = release;
        }

        //get
        public String getTitle(){
        return title;
        }
        public String getPoster(){
        return poster;}

        public String getOverview(){
        return overview;
        }
        public String getRelease(){
        return release;}
        public String getVote(){
        return vote;}
        public String getPosterbig(){return posterbig;}
        }

