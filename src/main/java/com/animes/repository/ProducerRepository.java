package com.animes.repository;

import com.animes.ConnectionFactory;
import com.animes.entities.Producer;
import lombok.extern.log4j.Log4j2;

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
        String sql = "select * from producer;";
        List<Producer> producers = null;
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            producers = createProducer(pst);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error while consulting the registers{}", e.getMessage());
        }
        return producers;
    }

    public static List<Producer> findProducerByName(String name) {
        String sql = "select id, name from producer where name like ?;";
        List<Producer> producers = null;
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, "%" + name);
            producers = createProducer(pst);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error while consulting the registers{}", e.getMessage());
        }
        return producers;
    }

    private static List<Producer> createProducer(PreparedStatement pst) throws SQLException {
        ResultSet rs = pst.executeQuery();
        List<Producer> producers = new ArrayList<>();
        while (rs.next()) {
            producers.add(Producer.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .build());
        }
        rs.close();
        return producers;
    }
}


