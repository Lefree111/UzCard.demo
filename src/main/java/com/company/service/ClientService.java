package com.company.service;

import com.company.ClientStatus;
import com.company.dto.ClientDTO;
import com.company.entity.ClientEntity;
import com.company.exp.ItemNotFoundException;
import com.company.exp.PhoneAlreadyExistsExseption;
import com.company.exp.PhoneNotFoundException;
import com.company.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.company.ClientStatus.*;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;


    public ClientDTO create(ClientDTO dto){
        Optional<ClientEntity> optional = clientRepository.findByPhone(dto.getPhone());
        if (optional.isPresent()){
            throw new PhoneAlreadyExistsExseption("Phone yaratilib bo'lganku mazgi qayta kirmagin 32 qator");
        }

        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddleName(dto.getMiddleName());
        entity.setPhone(dto.getPhone());
        entity.setProfileName(dto.getProfileName());
        entity.setStatus(ClientStatus.NO_ACTIVE);
        entity.setCreatedDate(LocalDateTime.now());
        clientRepository.save(entity);
        dto.setId(entity.getId());
        return toDTO(entity);
    }

    public ClientDTO update(String phone,ClientDTO dto){
        ClientEntity entity = clientRepository.findByPhone(phone).orElseThrow(()->{
            throw new PhoneNotFoundException("tel raqam topilmadi 50 qator");
        });
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddleName(dto.getMiddleName());
        entity.setUpdatedDate(LocalDateTime.now());
        clientRepository.save(entity);
        return toDTO(entity);
    }

    public Boolean updateStatus(String phone){
        ClientEntity entity = clientRepository.findByPhone(phone).orElseThrow();
        if (entity.getStatus().equals(ACTIVE)){
            throw new ItemNotFoundException("do'stim seni statusing active emas shuni to'g'rla 63 qator");
        }
        int n =  clientRepository.updateVisible(ACTIVE, phone);
        return n > 0;
    }

    public String updatePhone(String findphone,ClientDTO dto){
        ClientEntity entity = clientRepository.findByPhone(findphone).orElseThrow(()->{
            throw new ItemNotFoundException("Phone not found");
        });
        if (entity.getStatus().equals(ACTIVE)){
            entity.setPhone(dto.getPhone());
        }
        clientRepository.save(entity);
        return "Success";
    }

    public List<ClientDTO> paginationList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<ClientDTO> list = new ArrayList<>();
        clientRepository.findByStatus(ClientStatus.ACTIVE, pageable).forEach(courseEntity -> {
            list.add(toDTO(courseEntity));
        });
        if (list.isEmpty()) {
            throw new ItemNotFoundException("List bo'm bo'sh ku Mazgi");
        }
        return list;
    }

    public ClientDTO getById(String phone){
        ClientEntity entity = clientRepository.findByPhone(phone).orElseThrow(()->{
            throw new ItemNotFoundException("tel raqam topilmadi 97 qator");
        });
        if (!entity.getStatus().equals(ACTIVE)){
            throw new ItemNotFoundException("do'stim seni statusing active emas shuni to'g'rla");
        }
        return toDTO(entity);
    }

    public List<ClientDTO> AllClient_viz_profileName(String profileName) {
        List<ClientDTO> list = new ArrayList<>();
        clientRepository.findAllByStatusAndProfileName(ACTIVE,profileName).forEach(entity -> {
            list.add(toDTO(entity));
        });
        if (list.isEmpty()) {
            throw new ItemNotFoundException("Not Found!");
        }
        return list;
    }









    public ClientDTO toDTO(ClientEntity entity){
        ClientDTO dto = new ClientDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setMiddleName(entity.getMiddleName());
        dto.setPhone(entity.getPhone());
        dto.setStatus(entity.getStatus());
        dto.setProfileName(entity.getProfileName());
        dto.setUpdateDate(entity.getUpdatedDate());
        dto.setCreateDate(entity.getCreatedDate());
        return dto;
    }

}
