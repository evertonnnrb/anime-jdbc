package br.anime.tests;

import com.animes.DB.ConnectionFactory;
import org.junit.Assert;
import org.junit.Test;

public class TestConnection {
    @Test
    public void testConnector() {
        Assert.assertNotNull(ConnectionFactory.getConnection());
    }

    @Test
    public void testConnectorRowSet() {
        Assert.assertNotNull(ConnectionFactory.getJDBCRowSet());
    }
}
