package com.enigma.tokonyadia;

import com.enigma.tokonyadia.product.TransactionDAO;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            TransactionDAO transactionDAO = new TransactionDAO();
            transactionDAO.purchasingProduct(5, 1000,
                    1000, 2);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
