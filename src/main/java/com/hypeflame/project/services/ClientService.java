package com.hypeflame.project.services;

import com.hypeflame.project.entities.Client;
import com.hypeflame.project.repositories.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ModelMapper modelMapper;


    public List<Client> findAll(){
        List<Client> clientList = clientRepository.findAll();
        return clientList;
    }

    public Client findById(Long id){
        Optional<Client> client = clientRepository.findById(id);
        return client.orElseThrow();
    }

    public void insert(Client client) {
            clientRepository.save(client);
    }

    public void delete(Long id){
        clientRepository.deleteById(id);
    }

    public Client update(Long id, Client client){
        Optional<Client> entity = clientRepository.findById(id);
        updateData(client, entity);
        clientRepository.save(client);
        return client;
    }
    private void updateData(Client client, Optional entity) {
        modelMapper.map(client, entity);
    }



}
