package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository{
    DataSource ds;

    public ProductsRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Product> findAll() {
        List<Product> prod = new ArrayList<>();
        try {
            Connection conn = ds.getConnection();
            String state = "select * from shop;";
            PreparedStatement pState = conn.prepareStatement(state);
            ResultSet res = pState.executeQuery();
            if (!res.next()){
                return prod;
            }
            while (true){
                Product product = new Product();
                product.setId(res.getLong(1));
                product.setName(res.getString(2));
                product.setCoast(res.getInt(3));
                prod.add(product);
                if (!res.next()){
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prod;
    }

    @Override
    public Optional<Product> findById(Long id) {
        try{
            Connection conn = ds.getConnection();
            String state = "select * from shop where id = ?;";
            PreparedStatement pState = conn.prepareStatement(state);
            pState.setLong(1, id);
            ResultSet res = pState.executeQuery();
            if (!res.next()){
                return Optional.empty();
            }
            Product product = new Product();
            product.setId(res.getLong(1));
            product.setName(res.getString(2));
            product.setCoast(res.getInt(3));
            return Optional.of(product);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) {
        try {
            Connection conn = ds.getConnection();
            String statement;
            ResultSet res;
            statement = "Select * from shop where id = ?";
            PreparedStatement pState = conn.prepareStatement(statement);
            pState.setLong(1, product.getId());
            res = pState.executeQuery();
            if (!res.next()) {
                return;
            }
            if ((res.getLong(1) != product.getId())
                    || (!res.getString(2).equals(product.getName()))
                    || (res.getInt(3) != product.getCoast())) {
                statement = "UPDATE shop SET name = ?, coast = ? where id = ?;";
                pState = conn.prepareStatement(statement);
                pState.setString(1, product.getName());
                pState.setInt(2, product.getCoast());
                pState.setLong(3, product.getId());
                pState.executeUpdate();
                if (pState.executeUpdate() == 0){
                    return;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        try {
            Connection conn = ds.getConnection();
            String statement = "Insert Into shop values (?, ?, ?);";
            PreparedStatement pState = conn.prepareStatement(statement);
            pState.setLong(1, product.getId());
            pState.setString(2, product.getName());
            pState.setInt(3, product.getCoast());

            if (pState.executeUpdate() == 0){
                System.err.println("Arguments are non-valid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection conn = ds.getConnection();
            String statement = "Delete from shop where id = ? ;";
            PreparedStatement pState = conn.prepareStatement(statement);
            pState.setLong(1, id);
            if (pState.executeUpdate() == 0){
                System.err.println("Arguments are non-valid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
