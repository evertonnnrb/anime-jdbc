package br.anime.tests;

import com.animes.entities.Producer;
import com.animes.repository.ProducerRepository;
import com.animes.service.ProducerService;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;

@Log4j2
public class TestProducer {
    private ProducerService service = new ProducerService();

    @Test
    public void testInsertProducer() {
        Producer producer = Producer.builder().name("Picolo").build();
        int save = service.save(producer);
        Assert.assertTrue(save > 0);
        log.info("salvando {}", save);
    }

    @Test
    public void testRemoveProducer() {
        Assert.assertTrue(service.delete(3));
    }

    @Test
    public void testUpdateProducer(){
        Producer producer = Producer.builder().id(4).name("Android 19").build();
        Assert.assertTrue(service.update(producer));
    }

    @Test
    public void testListProducers(){
        service.findAll().forEach(System.out::println);
        Assert.assertNotNull(service.findAll());
    }
}
