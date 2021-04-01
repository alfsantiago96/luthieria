package com.hypeflame.project.resources;

import com.hypeflame.project.dto.ClientFullResponseModel;
import com.hypeflame.project.dto.ClientRequestModel;
import com.hypeflame.project.dto.ClientResponseModel;
import com.hypeflame.project.dto.ClientOrdersResponseModel;
import com.hypeflame.project.entities.Client;
import com.hypeflame.project.entities.Order;
import com.hypeflame.project.services.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    private ClientService clientService;
    private ModelMapper modelMapper;

    @Autowired
    public ClientResource(ClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseModel>> findAll(){
        List<Client> clientList = clientService.findAll();
        return ResponseEntity.ok().body(toCollectionModel(clientList));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientFullResponseModel> findById(@PathVariable Long id){
        Client client = clientService.findById(id);
        return ResponseEntity.ok().body(toFullModel(client));
    }

    @GetMapping(value = "/{id}/orders")
    public ResponseEntity<ClientOrdersResponseModel> findClientOrders(@PathVariable Long id){
        Client client = clientService.findById(id);
        return ResponseEntity.ok().body(toClientOrders(client));
    }

    @PostMapping
    public ResponseEntity<ClientResponseModel> insert(@RequestBody ClientRequestModel clientRequestModel){
        Client client = toEntity(clientRequestModel);
        clientService.insert(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(toModel(client));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client){
        Client entity = clientService.update(id, client);
        return ResponseEntity.ok().body(entity);
    }

    //ModelMapper
    public ClientResponseModel toModel(Client client){
        return modelMapper.map(client, ClientResponseModel.class);
    }
    public Client toEntity(ClientRequestModel clientRequestModel){
        return modelMapper.map(clientRequestModel, Client.class);
    }
    public ClientFullResponseModel toFullModel(Client client){
        return modelMapper.map(client, ClientFullResponseModel.class);
    }
    public ClientOrdersResponseModel toClientOrders(Client client){
        return modelMapper.map(client, ClientOrdersResponseModel.class);
    }
    public List<ClientResponseModel> toCollectionModel(List<Client> clientList){
        return clientList.stream()
                .map(client -> toModel(client))
                .collect(Collectors.toList());
    }
}
