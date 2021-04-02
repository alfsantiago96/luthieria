package com.hypeflame.project.config;

import com.hypeflame.project.entities.Client;
import com.hypeflame.project.entities.Item;
import com.hypeflame.project.entities.Order;
import com.hypeflame.project.entities.Payment;
import com.hypeflame.project.entities.enums.OrderStatus;
import com.hypeflame.project.entities.enums.PaymentStatus;
import com.hypeflame.project.repositories.ClientRepository;
import com.hypeflame.project.repositories.ItemRepository;
import com.hypeflame.project.repositories.OrderRepository;
import com.hypeflame.project.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.Date;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void run(String... args) throws Exception {


        /*Client c1 = new Client(null, "André Santiago", "alfsantiago96@gmail.com", "99725098", "03691937098", true);
        Client c2 = new Client(null, "Alex Green", "alex@gmail.com", "88888888", "000000000", false);
        clientRepository.saveAll(Arrays.asList(c1,c2));

        Order o1 = new Order(null, new Date(), OrderStatus.FINISHED, c1);
        Order o2 = new Order(null, new Date(), OrderStatus.BUDGET_ANALYSING, c1);
        Order o3 = new Order(null, new Date(), OrderStatus.PROCESSING, c2);
        orderRepository.saveAll(Arrays.asList(o1,o2,o3));

        c1.getOrderList().add(o1);
        c1.getOrderList().add(o1);
        c2.getOrderList().add(o3);
        clientRepository.saveAll(Arrays.asList(c1,c2));

        Item i1 = new Item(null,"Regulagem", "Guitarra","Gibson Les Paul","Ajuste da altura das cordas", 50.0, o2);
        Item i2 = new Item(null,"Manutenção", "Violão","Takamine","Troca da pestana", 15.0, o3);
        Item i3 = new Item(null,"Revisão", "Bateria","Pearl Perstige","Inspesionamento da ferragem", 100.0, o1);
        Item i4 = new Item(null,"Limpeza", "Gaita","Yamaha","Higienização", 325.0, o1);
        itemRepository.saveAll(Arrays.asList(i1,i2,i3));
        o1.getItemList().add(i3);
        o2.getItemList().add(i1);
        o3.getItemList().add(i2);
        orderRepository.saveAll(Arrays.asList(o1,o2,o3));
        o1.getItemList().add(i4);
        orderRepository.save(o1);

        Payment p1 = new Payment(null, new Date(), PaymentStatus.PAGO, o1);
        Payment p2 = new Payment(null, new Date(0), PaymentStatus.ABERTO, o2);
        Payment p3 = new Payment(null, new Date(0), PaymentStatus.PROCESSANDO, o3);
        o1.setPayment(p1);
        o2.setPayment(p2);
        o3.setPayment(p3);
        orderRepository.saveAll(Arrays.asList(o1,o2,o3));*/


    }
}
