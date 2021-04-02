package com.hypeflame.project.services;

import com.hypeflame.project.domain.exception.DomainException;
import com.hypeflame.project.entities.Client;
import com.hypeflame.project.repositories.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

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
        Client obj = client;
        Client clientEmail = clientRepository.findByEmail(obj.getEmail());
        Client clientCpf = clientRepository.findByCpf(obj.getCpf());
        if (clientEmail == null && clientCpf == null) {
            obj.setAdm(false);
            clientRepository.save(obj);
        } else {
            throw new RuntimeException();
        }
    }

    public void delete(Long id){
        try {
            findById(id);
            clientRepository.deleteById(id);
        } catch (Exception e){
        }

    }

    public Client update(Long id, Client client){
        client.setId(id);
        Optional<Client> entity = clientRepository.findById(id);
        client.setAdm(false);
        updateData(client, entity);
        clientRepository.save(client);
        return client;
    }
    private void updateData(Client client, Optional entity) {
        modelMapper.map(client, entity);
    }



}
