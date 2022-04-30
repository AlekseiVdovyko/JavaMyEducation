package ru.edu;

import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.sql.Connection;
import java.sql.DriverManager;

import static ru.edu.ProductCategory.TECHNIC;
import static ru.edu.ProductCategory.FOOD;
import static ru.edu.ProductCategory.CHEMICAL;
import static ru.edu.ProductCategory.COSMETICS;

public class ProductRepositoryTest {

    String jdbcURL = "jdbc:sqlite:C:\\tempsber\\homework12a\\database.db";
    Connection connection = DriverManager.getConnection(jdbcURL);

    ProductRepositoryImpl repository = new ProductRepositoryImpl(connection);


    public ProductRepositoryTest() throws SQLException {}

    @Test
    public void createTableTest() {
        repository.createTable();
        Assert.assertEquals(true, repository.toString().startsWith("ProductRepositoryImpl"));
    }

    @Test
    public void dropTableTest() {
        repository.dropTable();
        Assert.assertEquals(true, repository.toString().startsWith("ProductRepositoryImpl"));
    }

    @Test
    public void saveCreateTest() {
        List<Product> productList = repository.findAll();
        repository.save(new Product(UUID.randomUUID(), "Product_1", "Oil",
                COSMETICS, LocalDate.now(), "Nivea", true, 3));
        repository.save(new Product(UUID.randomUUID(), "Product_2", "Tide",
                CHEMICAL, LocalDate.now(), "Unilever", true, 5));
        repository.save(new Product(UUID.randomUUID(), "Product_3", "BigMac",
                FOOD, LocalDate.now(), "McDonald`s", true, 2));
        repository.save(new Product(UUID.fromString("00000000-0000-0000-0000-000000000007"), "Product_4", "Vaio",
                TECHNIC, LocalDate.now(), "Sony", false, 1));
        Assert.assertEquals(true, productList.toString().contains("Product_1"));
        Assert.assertEquals(true, productList.toString().contains("Product_2"));
        Assert.assertEquals(true, productList.toString().contains("Product_3"));
        Assert.assertEquals(true, productList.toString().contains("Product_4"));
        Assert.assertEquals(false, productList.toString().contains("Product_5"));

    }

    @Test
    public void saveUpdateByIdTest() {
        List<Product> productList = repository.findAll();
        repository.save(new Product(UUID.fromString("00000000-0000-0000-0000-000000000007"),
                "TestName", "TestDescription", FOOD, LocalDate.now(),
                "TestManufacture", false, 0));
        Assert.assertEquals(true, productList.toString().contains("TestName"));
    }

    @Test
    public void findByIdTest() {
        List<Product> productList = repository.findAll();
        repository.findById(UUID.fromString("00000000-0000-0000-0000-000000000007"));
        Assert.assertEquals(true, productList.toString().contains("00000000-0000-0000-0000-000000000007"));
    }

    @Test
    public void deleteByIdTest() {
        repository.deleteById(UUID.fromString("00000000-0000-0000-0000-000000000007"));
        List<Product> productList = repository.findAll();
        Assert.assertEquals(false, productList.toString().contains("00000000-0000-0000-0000-000000000007"));
    }

    @Test
    public void findAllByCategoryTest() {
        List<Product> productList = repository.findAllByCategory(FOOD);
        Assert.assertEquals(true, productList.toString().contains("McDonald`s"));
        Assert.assertEquals(false, productList.toString().contains("Nivea"));
        Assert.assertEquals(false, productList.toString().contains("Tide"));
        Assert.assertEquals(false, productList.toString().contains("Product_4"));
    }

}
