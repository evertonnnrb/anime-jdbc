package com.s.service;

import com.animes.entities.Anime;
import com.animes.repository.AnimeRepository;

import java.util.List;
public class AnimeService {

    public int save(Anime anime) {
        return AnimeRepository.save(anime);
    }

    public boolean delete(int id) {
        requiredValidId(id);
        return AnimeRepository.delete(id);
    }

    public boolean update(Anime anime) {
        requiredValidId(anime.getId());
        return AnimeRepository.update(anime);
    }

    public List<Anime> findAll() {
        return AnimeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        if (name.equals("")) {
            throw new IllegalArgumentException("Invalid data for consult");
        }
        return AnimeRepository.findProducerByName(name);
    }

    private void requiredValidId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid value for id");
        }
    }

    public boolean saveTransaction(List<Anime> animeList) {
        return AnimeRepository.saveTransaction(animeList);
    }
}
