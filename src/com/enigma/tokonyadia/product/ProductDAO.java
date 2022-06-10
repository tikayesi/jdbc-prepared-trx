package com.enigma.tokonyadia.product;

import com.enigma.tokonyadia.config.DBConnector;

import java.sql.*;

public class ProductDAO {

    private Connection connection;

    public void insertProduct(String productName, Integer productPrice,
                              Integer stock) throws SQLException {
        DBConnector dbConnector = new DBConnector();
        connection = dbConnector.connect();
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO mst_product (name, product_price, " +
                        "stock) VALUES ('"+productName+"'"+","+productPrice+","+stock+")";
            // INSERT INTO mst_product (name, product_price, stock) VALUES ('Cha cha', 5000, 3)
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
    }

    public String getProductById(Integer productId) throws SQLException {
        DBConnector dbConnector = new DBConnector();
        connection = dbConnector.connect();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM mst_product WHERE id = " + productId;
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        productId = resultSet.getInt("id");
        String productName = resultSet.getString("name");
        Integer productPrice = resultSet.getInt("product_price");
        Integer stock = resultSet.getInt("stock");
        return String.format("%d %s %d %d", productId, productName, productPrice, stock);
    }


    public void updateProduct(Integer productId, String newName) throws SQLException {
        DBConnector dbConnector = new DBConnector();
        connection = dbConnector.connect();
        String queryUpdate = "UPDATE mst_product SET name = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate);
        preparedStatement.setString(1, newName);
        preparedStatement.setInt(2, productId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }


}
