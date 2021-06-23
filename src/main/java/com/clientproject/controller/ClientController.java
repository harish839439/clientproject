package com.clientproject.controller;

import com.clientproject.model.APIResponse;
import com.clientproject.model.Client;
import com.clientproject.repository.ClientRepository;
import com.clientproject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/client")
public class ClientController {

    private ClientService clientService;
    private ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createClient(@RequestBody Client client) throws Exception {
        APIResponse response = new APIResponse();
        try {
            response.setMessage("SUCCESS");
            response.setData(clientService.createClient(client));
            return new ResponseEntity<APIResponse>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setMessage("FAILURE");
            response.setData(e.getMessage());
            return new ResponseEntity<APIResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse> updateClient(@PathVariable(value = "id") long id, @RequestBody Client client) throws Exception {
        APIResponse response = new APIResponse();
        try {
            Optional<Client> clientData = clientRepository.findById(id);
            if(clientData.isPresent()) {
                response.setMessage("SUCCESS");
                response.setData(clientService.createClient(client));
                return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMessage("FAILURE");
            response.setData(e.getMessage());
            return new ResponseEntity<APIResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/firstName/{name}")
    public ResponseEntity<Client> getClientByFirstName(@PathVariable("name") String name) throws Exception {
        Optional<Client> clientData = clientRepository.findByFirstName(name);

        if (clientData.isPresent()) {
            return new ResponseEntity<>(clientData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/phoneNumber/{name}")
    public ResponseEntity<Client> getClientByPhoneNumber(@PathVariable("name") String name) throws Exception {
        Optional<Client> clientData = clientRepository.findByMobileNumber(name);

        if (clientData.isPresent()) {
            return new ResponseEntity<>(clientData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/idNumber/{number}")
    public ResponseEntity<Client> getClientByIdNumber(@PathVariable("name") String number) throws Exception {
        Optional<Client> clientData = clientRepository.findByIdNumber(number);

        if (clientData.isPresent()) {
            return new ResponseEntity<>(clientData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
