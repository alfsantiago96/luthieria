package com.hypeflame.project.repositories;

import com.hypeflame.project.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByName(String name);
    Client findByEmail(String email);
    Client findByCpf(String cpf);



}
