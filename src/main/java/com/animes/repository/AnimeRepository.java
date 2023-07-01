package com.animes.repository;

import com.animes.DB.ConnectionFactory;
import com.animes.entities.Anime;
import com.animes.exception.DBException;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class AnimeRepository {

    private static Connection connection = ConnectionFactory.getConnection();

    public static int save(Anime anime) {
        String sql = "insert into anime (name) values (?);";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, anime.getName());
            log.info("Data {}", true);
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error while trying to insert producer '{}'", anime.getName(), e);
        }
        return 0;
    }

    private static void saveAnimeTransaction(List<Anime> animes) {
        String sql = "insert into anime (name) values (?);";
        for (Anime anime : animes) {
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setString(1, anime.getName());
                log.info("Saving anime {}", anime.getName());
                pst.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                log.error("Error while trying to insert anime '{}'", anime.getName(), e);
            }
        }
    }

    @SneakyThrows
    public static boolean saveTransaction(List<Anime> animeList) {
        try {
            connection.setAutoCommit(false);
            saveAnimeTransaction(animeList);
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            throw new DBException(e.getMessage());
        }
    }

    public static boolean delete(int id) {
        String sql = "delete from anime where id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, id);
            log.info("Data {}", true);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error while trying to delete anime '{}'", id, e);
        }
        return false;
    }

    public static boolean update(Anime anime) {
        String sql = "update anime set name=? where id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, anime.getName());
            pst.setInt(2, anime.getId());
            log.info("Data {}", true);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error while trying to update anime '{}'", anime.getName(), e);
        }
        return false;
    }

    public static List<Anime> findAll() {
        String sql = "select * from anime;";
        List<Anime> animeList = null;
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            animeList = createAnime(pst);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error while consulting the registers{}", e.getMessage());
        }
        return animeList;
    }

    public static List<Anime> findProducerByName(String name) {
        String sql = "select id, name from anime where name like ?;";
        List<Anime> animeList = null;
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, "%" + name);
            animeList = createAnime(pst);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error while consulting the registers{}", e.getMessage());
        }
        return animeList;
    }

    private static List<Anime> createAnime(PreparedStatement pst) throws SQLException {
        ResultSet rs = pst.executeQuery();
        List<Anime> animeList = new ArrayList<>();
        while (rs.next()) {
            animeList.add(Anime.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .build());
        }
        rs.close();
        return animeList;
    }
}


