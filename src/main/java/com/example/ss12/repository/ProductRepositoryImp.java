package com.example.ss12.repository;

import com.example.ss12.connection.ConnectionDB;
import com.example.ss12.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImp implements ProductRepository {
    @Override
    public List<Product> findAll() {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<Product> products = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call find_all_product()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getInt("quantity"),
                        rs.getString("image")
                );
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return products;
    }

    @Override
    public Product findById(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        Product product = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call find_product_by_id(?)}");
            cstmt.setInt(1, id);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getInt("quantity"),
                        rs.getString("image")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return product;
    }

    @Override
    public boolean create(Product product) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call create_product(?, ?, ?, ?)}");
            cstmt.setString(1, product.getName());
            cstmt.setBigDecimal(2, product.getPrice());
            cstmt.setInt(3, product.getQuantity());
            cstmt.setString(4, product.getImage());
            cstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return result;
    }

    @Override
    public boolean update(Product product) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call update_product(?, ?, ?, ?, ?)}");
            cstmt.setInt(1, product.getId());
            cstmt.setString(2, product.getName());
            cstmt.setBigDecimal(3, product.getPrice());
            cstmt.setInt(4, product.getQuantity());
            cstmt.setString(5, product.getImage());
            cstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call delete_product(?)}");
            cstmt.setInt(1, id);
            cstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return result;
    }
}
