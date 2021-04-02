package com.hypeflame.project.resources;

import com.hypeflame.project.dto.*;
import com.hypeflame.project.entities.Client;
import com.hypeflame.project.services.ClientService;
import com.hypeflame.project.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    private ClientService clientService;
    private ModelMapper modelMapper;

    private OrderResource orderResource;
    private OrderService orderService;

    @Autowired
    public ClientResource(ClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseModel>> findAll(){
        try {
            List<Client> clientList = clientService.findAll();
            return ResponseEntity.ok().body(toCollectionModel(clientList));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientFullResponseModel> findById(@PathVariable Long id){
        try {
            Client client = clientService.findById(id);
            return ResponseEntity.ok().body(toFullModel(client));
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{idClient}/orders")
    public ResponseEntity<ClientOrdersListResponseModel> findClientOrders(@PathVariable Long idClient){
        Client client = clientService.findById(idClient);
        return ResponseEntity.ok().body(toClientOrders(client));
    }

    @PostMapping
    public ResponseEntity<ClientResponseModel> insert(@Valid @RequestBody ClientRequestModel clientRequestModel){
        Client client = toEntity(clientRequestModel);
        clientService.insert(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(toModel(client));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @Valid @RequestBody Client client){
        try {
            Client entity = clientService.update(id, client);
            return ResponseEntity.ok().body(entity);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try {
            clientService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
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
    public ClientOrdersListResponseModel toClientOrders(Client client){
        return modelMapper.map(client, ClientOrdersListResponseModel.class);
    }
    public List<ClientResponseModel> toCollectionModel(List<Client> clientList){
        return clientList.stream()
                .map(client -> toModel(client))
                .collect(Collectors.toList());
    }
}
