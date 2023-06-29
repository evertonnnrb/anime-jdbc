package com.animes.repository;

import com.animes.ConnectionFactory;
import com.animes.entities.Producer;
import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ProducerRepository {

    private static Connection connection = ConnectionFactory.getConnection();

    public static int save(Producer producer) {
        String sql = "insert into producer (name) values (?);";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, producer.getName());
            log.info("Data {}", true);
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error while trying to insert producer '{}'", producer.getName(), e);
        }
        return 0;
    }

    public static boolean delete(int id) {
        String sql = "delete from producer where id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, id);
            log.info("Data {}", true);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error while trying to delete producer '{}'", id, e);
        }
        return false;
    }

    public static boolean update(Producer producer) {
        String sql = "update producer set name=? where id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, producer.getName());
            pst.setInt(2, producer.getId());
            log.info("Data {}", true);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error while trying to update producer '{}'", producer.getName(), e);
        }
        return false;
    }

    public static List<Producer> findAll() {
        String sql = "select id, name from producer;";
        List<Producer> producers = null;
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            producers = new ArrayList<>();
            while (rs.next()) {
                producers.add(Producer.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error while consulting the registers{}", e.getMessage());
        }
        return producers;
    }

}
