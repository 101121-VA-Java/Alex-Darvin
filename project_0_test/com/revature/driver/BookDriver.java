package project_0_test.com.revature.driver;

import project_0_test.com.revature.models.Book;

public class BookDriver{
    public static void main(String[] args){
        Book test = new Book("Wonderful Worlds", "Fantasy", false, 19.99);
        System.out.println("test");
        System.out.println("Title: " + test.title + ", Genre: " + test.genre + ", For Sale: " + test.forSale + ", Price: " + test.price);
    }
}