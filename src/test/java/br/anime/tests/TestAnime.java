package br.anime.tests;

import com.animes.entities.Anime;
import com.animes.entities.Producer;
import com.animes.service.ProducerService;
import com.s.service.AnimeService;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class TestAnime {
    private com.s.service.AnimeService service = new AnimeService();

    @Test
    public void testInsertAnime() {
        Anime anime = Anime.builder().name("Picolo").build();
        int save = service.save(anime);
        Assert.assertTrue(save > 0);
        log.info("salvando {}", save);
    }

    @Test
    public void testRemoveAnime() {
        Assert.assertTrue(service.delete(3));
    }

    @Test
    public void testUpdateAnime() {
        Anime anime = Anime.builder().id(4).name("Android 19").build();
        Assert.assertTrue(service.update(anime));
    }

    @Test
    public void testListAnimes() {
        service.findAll().forEach(System.out::println);
        Assert.assertNotNull(service.findAll());
    }

    @Test
    public void testListAnimesByName() {
        service.findByName("a").forEach(System.out::println);
        Assert.assertNotNull(service.findByName("A").parallelStream());
    }

    @Test
    public void testSaveTransaction() {
        List<Anime> animes = new ArrayList<>();
        Anime anime = Anime.builder().name("Gotranks").build();
        Anime anime1 = Anime.builder().name("Madinbu").build();
        Anime anime2 = Anime.builder().name("Pikolo").build();
        Anime anime3 = Anime.builder().name("Mestre caio").build();
        animes.add(anime1);
        animes.add(anime2);
        animes.add(anime3);
        animes.add(anime);
        Assert.assertTrue(service.saveTransaction(animes));
    }
}
