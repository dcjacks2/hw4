package com.hw4.hw4.Models;
import javax.persistence.*;

@Entity
@Table(name = "savedquotes")
public class Savedquote {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "quotessubmitted")
    private String Quotessubmitted;


    public Savedquote(){

    }

    public Savedquote(String id, String Quotessubmitted){
        this.id = id;
        this.Quotessubmitted = Quotessubmitted;

    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getQuotessubmitted(){return Quotessubmitted;}

    public void setQuotessubmitted(String Quotessubmitted){this.Quotessubmitted = Quotessubmitted;}

}
