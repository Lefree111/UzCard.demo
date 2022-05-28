package com.company.repository;

import com.company.ClientStatus;
import com.company.entity.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity,Integer> {

    Optional<ClientEntity> findByPhone(String phone);


    List<ClientEntity> findAllByStatusAndProfileName(ClientStatus status,String profileName);

    Optional<ClientEntity> findByProfileName(String profileName);

    Page<ClientEntity> findByStatus(ClientStatus status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update ClientEntity set status = :status where phone = :phone")
    int updateStatus(@Param("status") ClientStatus status,
                      @Param("phone") String phone);


}
