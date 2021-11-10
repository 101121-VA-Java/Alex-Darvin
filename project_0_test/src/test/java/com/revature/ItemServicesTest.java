//package com.revature.services;
//
//import com.revature.models.Item;
//import com.revature.repositories.ItemPostgres;
//import com.revature.services.ItemServices;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ItemServiceTest {
//    private static ItemServices is;
//    private static ItemPostgres ip;
//
//    @BeforeAll
//    public static void setup(){
//        is = new ItemServices();
//        ip = new ItemPostgres();
//    }
//
//    @Test
//    void getAll() {
//        List<Item> actual = ip.getAll();
//        assertAll(() -> assertEquals(5, actual.get(0).getPrice()),
//                () -> assertEquals("apple", actual.get(0).getName())
//                );
//    }
//
//    @Test
//    void addUnownedItemForSale() {
//        int expected = is.getAll().size();
//        is.addUnownedItemForSale(new Item("test", "14", "meh"));
//        int actual = is.getAll().size();
//        is.removeItemByName("test");
//        assertNotEquals(expected, actual);
//    }
//
//    @Test
//    void removeItemByName() {
//        int expected = is.getAll().size();
//        String name = String.valueOf(Math.random());
//        is.addUnownedItemForSale(new Item(name, "14", "meh"));
//        is.removeItemByName(name);
//        int actual = is.getAll().size();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void printItemsForSale() {
//        int numberOfItemsForSale = is.getAllItems().size();
//        assertNotEquals(0, numberOfItemsForSale);
//    }
//}