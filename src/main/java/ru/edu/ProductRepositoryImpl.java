package ru.edu;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ProductRepositoryImpl implements ProductRepository {

    private final Connection connection;

    ProductRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Product findById(UUID id) {

        try (Statement statement = connection.createStatement()){

            boolean hasResultSet = statement.execute("select * from product where id = '" + id + "'");

            try (ResultSet resultSet = statement.getResultSet()){

                if (resultSet.next() == false) {
                    return null;
                }

                UUID productId = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                ProductCategory category = ProductCategory.valueOf(resultSet.getString("category"));
                LocalDate manufactureDateTime = LocalDate.parse(resultSet.getString("manufactureDateTime"));
                String manufacturer = resultSet.getString("manufacturer");
                boolean hasExpiryTime = resultSet.getBoolean("hasExpiryTime");
                int stock = resultSet.getInt("stock");

                System.out.println("findById completed  id=" + id + " hasResultSet=" + hasResultSet);
                return new Product(id, name, description, category, manufactureDateTime,
                        manufacturer, hasExpiryTime, stock);
            }

        } catch (Exception ex) {
            throw new RuntimeException("Failed to findById id=" + id + " error=" + ex.toString(), ex);
        }
    }


    @Override
    public void deleteById(UUID id) {

        try (PreparedStatement statement = connection.prepareStatement("delete from product where id = ?")) {

            statement.setObject(1, id);
                int affectedRows = statement.executeUpdate();
                System.out.println("deleteById completed id=" + id + " affectedRows=" + affectedRows);

            } catch (Exception ex) {
                throw new RuntimeException("Failed to deleteById id=" + id + " error=" + ex.toString(), ex);
            }
        }


    @Override
    public Product save(Product product) {

        try (Statement statement = connection.createStatement()) {

            Product foundId = findById(product.getId());
            if (foundId == null) {
                foundId = new Product(UUID.randomUUID(), "", "",
                        product.getCategory(), LocalDate.now(), "", true, 0);
            }
            UUID uuid = foundId.getId();

                if (product.getId().equals(uuid)) {
                    int affectedRows = statement.executeUpdate("update product set name = '" + product.getName() +
                        "', description = '" + product.getDescription() +
                        "', category = '" + product.getCategory() +
                        "', manufactureDateTime = '" + product.getManufactureDateTime() +
                        "', manufacturer = '" + product.getManufacturer() +
                        "', hasExpiryTime = " + product.isHasExpiryTime() +
                        ", stock = '" + product.getStock() +
                        "' where id ='" + product.getId() + "';");
                    System.out.println("changeName completed affectedRows=" + affectedRows);

            } else {
                    String sql = String.format("insert into product ('id', 'name', 'description', 'category', 'manufactureDateTime', 'manufacturer', 'hasExpiryTime', 'stock') VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                            product.getId(), product.getName(), product.getDescription(), product.getCategory(), product.getManufactureDateTime().toString(), product.getManufacturer(), product.isHasExpiryTime(), product.getStock());
                    boolean resultSet = statement.execute(sql);
                    int affectedRows = statement.getUpdateCount();
                    System.out.println("addProduct completed resultSet=" + resultSet + ", affectedRows=" + affectedRows);
                }

        } catch (Exception ex) {
            throw new RuntimeException("Failed to addProduct product=" + product + " error=" + ex.toString(), ex);
        }

        return product;
    }


    @Override
    public List<Product> findAllByCategory(ProductCategory category) {

        try (Statement statement = connection.createStatement()) {

            String sql = "select * from product where category = '" + category + "'";
            List<Product> products = new ArrayList<>();
            try (ResultSet resultSet = statement.executeQuery(sql)){
                while (resultSet.next() == true) {

                    UUID id = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    ProductCategory productCategory = ProductCategory.valueOf(resultSet.getString("category"));
                    LocalDate manufactureDateTime = LocalDate.parse(resultSet.getString("manufactureDateTime"));
                    String manufacturer = resultSet.getString("manufacturer");
                    boolean hasExpiryTime = resultSet.getBoolean("hasExpiryTime");
                    int stock = resultSet.getInt("stock");

                    Product product = new Product(id, name, description, productCategory, manufactureDateTime,
                            manufacturer, hasExpiryTime, stock);
                    products.add(product);
                }
            }

            return products;

        } catch (Exception ex) {
            throw new RuntimeException("Failed to findAll error=" + ex.toString(), ex);
        }
    }


    public void createTable() {

        try (Statement statement = connection.createStatement()){

            String sql = "create table product (" +
                    " id generateUUIDv4(32)," +
                    " name varchar(32)," +
                    " description varchar(32)," +
                    " category varchar(32)," +
                    " manufactureDateTime varchar(32)," +
                    " manufacturer varchar(32)," +
                    " hasExpiryTime varchar(32)," +
                    " stock varchar(32)" +
                    ");";
            int affectedRows = statement.executeUpdate(sql);
            System.out.println("createTable completed affectedRows=" + affectedRows);

        } catch (Exception ex) {
            throw new RuntimeException("Failed to createTable error=" + ex.toString(), ex);
        }
    }


    public void dropTable() {

        try (Statement statement = connection.createStatement()){

            int affectedRows = statement.executeUpdate("drop table product");
            System.out.println("dropTable completed affectedRows=" + affectedRows);

        } catch (Exception ex) {
            throw new RuntimeException("Failed to dropTable error=" + ex.toString(), ex);
        }
    }


    public List<Product> findAll() {

        try (Statement statement = connection.createStatement()) {

            String sql = "select * from product";
            List<Product> products = new ArrayList<>();
            try (ResultSet resultSet = statement.executeQuery(sql)){
                while (resultSet.next() == true) {

                    UUID productId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    ProductCategory category = ProductCategory.valueOf(resultSet.getString("category"));
                    LocalDate manufactureDateTime = LocalDate.parse(resultSet.getString("manufactureDateTime"));
                    String manufacturer = resultSet.getString("manufacturer");
                    boolean hasExpiryTime = resultSet.getBoolean("hasExpiryTime");
                    int stock = resultSet.getInt("stock");

                    Product product = new Product(productId, name, description, category, manufactureDateTime,
                            manufacturer, hasExpiryTime, stock);
                    products.add(product);
                }
            }

            return products;

        } catch (Exception ex) {
            throw new RuntimeException("Failed to findAll error=" + ex.toString(), ex);
        }
    }

    @Override
    public String toString() {
        return "ProductRepositoryImpl{" +
                "connection=" + connection +
                '}';
    }
}
