package project_0_test.com.revature.models;

public class Book{
    // add parameters for the items to be sold
    public String title;
    public String genre;
    public boolean forSale;
    public double price;

    public Book(String title, String genre, boolean forSale, double price){
        this.title = title;
        this.genre = genre;
        this.forSale = forSale;
        this.price = price;
    }


}