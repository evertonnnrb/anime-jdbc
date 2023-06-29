package br.anime.tests;

import com.animes.ConnectionFactory;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class TestConnection {
    @Test
    public void testConnector() {
        Assert.assertNotNull(ConnectionFactory.getConnection());

    }
}
