package com.clientproject.service;

import com.clientproject.model.Client;
import com.clientproject.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientServiceImpl implements ClientService{

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) throws Exception {
        Client clientResponse = null;
        if(Objects.nonNull(client)) {
            if(!isValidIdNumber(client.getIdNumber())) {
                throw new Exception("Invalid South Africa Id number");
            }
            if(!isDuplicate(client)) {
                clientResponse = clientRepository.save(
                        new Client(client.getFirstName(),client.getLastName(), client.getMobileNumber()
                                ,client.getIdNumber(),client.getPhysicalAddress()));
            } else {
                throw new Exception("Duplicate Id Number or Mobile Number");
            }
        }
        return clientResponse;
    }

    private Boolean isDuplicate(Client client) {
        Optional<Client>  idNumber = clientRepository.findByIdNumber(client.getIdNumber());
        Optional<Client>  mobileNumber = clientRepository.findByMobileNumber(client.getMobileNumber());
        if(idNumber.isPresent() && mobileNumber.isPresent()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean isValidIdNumber(String idNumber) {
        String regExpression = "([0-9][0-9])((?:[0][1-9])|(?:[1][0-2]))((?:[0-2][0-9])|(?:[3][0-1]))([0-9])([0-9]{3})([0-9])([0-9])([0-9])";
        Pattern pattern = Pattern.compile(regExpression);

        Matcher matcher = pattern.matcher(idNumber);

        if (matcher.matches()) {

            int tot1 = 0;
            for (int i = 0; i < idNumber.length() - 1; i += 2) {
                tot1 = tot1 + Integer.parseInt(idNumber.substring(i, i + 1));
            }

            StringBuffer field1 = new StringBuffer("");
            for (int i = 1; i < idNumber.length(); i += 2) {
                field1.append(idNumber.substring(i, i + 1));
            }

            String evenTotStr = (Long.parseLong(field1.toString()) * 2) + "";
            int tot2 = 0;
            for (int i = 0; i < evenTotStr.length(); i++) {
                tot2 = tot2 + Integer.parseInt(evenTotStr.substring(i, i + 1));
            }

            int lastD = (10 - ((tot1 + tot2) % 10)) % 10;
            int checkD = Integer.parseInt(idNumber.substring(12, 13));

            if (checkD == lastD) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


}
