package com.hemanshu;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="review")
public class Review {







    @Id
    private int ID;
    private String reviews ;
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getReviews(){return reviews;};
    public void setReviews(String newReview){this.reviews = newReview;};






}

