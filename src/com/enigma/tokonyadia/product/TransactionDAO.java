package com.enigma.tokonyadia.product;

import com.enigma.tokonyadia.config.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {

    public void purchasingProduct(Integer trxId, Integer productId, Integer productPrice, Integer quantity) throws SQLException {
        DBConnector dbConnector = new DBConnector();
        Connection connection = dbConnector.connect();
        //membuka trx
        connection.setAutoCommit(false);

        String sqlInsert = "INSERT INTO trx_purchase_detail (transaction_id, product_id," +
                "product_price, quantity) VALUES (?,?,?,?)";
        PreparedStatement statementInsert = connection.prepareStatement(sqlInsert);
        statementInsert.setInt(1, trxId);
        statementInsert.setInt(2, productId);
        statementInsert.setInt(3, productPrice);
        statementInsert.setInt(4, quantity);
        statementInsert.executeUpdate();
        statementInsert.close();

        String sqlGetStock = "SELECT stock FROM mst_product WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlGetStock);
        preparedStatement.setInt(1, productId);
        ResultSet resultSet = preparedStatement.executeQuery();
        //resultSet.next();
        Integer stock = resultSet.getInt("stock");
        preparedStatement.close();

        String sqlUpdate = "UPDATE mst_product SET stock = ? WHERE id = ?";
        PreparedStatement statementUpdate = connection.prepareStatement(sqlUpdate);
        statementUpdate.setInt(1, stock - quantity);
        statementUpdate.setInt(2, productId);
        statementUpdate.executeUpdate();
        statementUpdate.close();
        connection.commit();

        connection.close();

    }
}
