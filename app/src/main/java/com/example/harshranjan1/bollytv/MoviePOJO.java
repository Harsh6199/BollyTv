package com.example.harshranjan1.bollytv;

import java.io.Serializable;

public class    MoviePOJO implements Serializable
{


    private String name,rating,plot_sum,img_link,download,trailer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPlot_sum() {
        return plot_sum;
    }

    public void setPlot_sum(String plot_sum) {
        this.plot_sum = plot_sum;
    }

    public String getImg_link() {
        return img_link;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getTrailer() { return trailer;}

    public void setTrailer(String trailer) { this.trailer = trailer;}

    @Override
    public String toString() {
        return "Name = " +name +"Rating = " +rating +"Plot Summary" +plot_sum +"Image Link" +img_link +"Download" +download +"Trailer" +trailer;
    }
}
