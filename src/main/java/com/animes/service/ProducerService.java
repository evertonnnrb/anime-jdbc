package com.animes.service;

import com.animes.entities.Producer;
import com.animes.repository.ProducerRepository;

import java.util.List;

public class ProducerService {

    public int save(Producer producer) {
        return ProducerRepository.save(producer);
    }

    public boolean delete(int id){
        requiredValidId(id);
        return ProducerRepository.delete(id);
    }

    public boolean update(Producer producer){
        requiredValidId(producer.getId());
        return ProducerRepository.update(producer);
    }

    public List<Producer> findAll(){
        return ProducerRepository.findAll();
    }

    public List<Producer> findByName(String name){
        if (name.equals("")){
            throw new IllegalArgumentException("Invalid data for consult");
        }
        return ProducerRepository.findProducerByName(name);
    }
    private void requiredValidId(Integer id){
        if (id == null || id <= 0){
            throw new IllegalArgumentException("Invalid value for id");
        }
    }

    public boolean saveTransaction(List<Producer> producersList){
        return ProducerRepository.saveTransaction(producersList);
    }
}
