package com.challenge.sermaluc.usuarios.adapter.persistence.repository;

import com.challenge.sermaluc.usuarios.adapter.web.dto.output.UserResponse;
import com.challenge.sermaluc.usuarios.adapter.web.dto.output.PhoneResponse;

import com.challenge.sermaluc.usuarios.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaUserRepository extends JpaRepository<User, String> {

    User findUserByUsername(String username);

    @Query(
            "SELECT new com.challenge.sermaluc.usuarios.adapter.web.dto.output.UserResponse(" +
                    "u.userId, " +
                    "u.name, " +
                    "u.username, " +
                    "u.status) " +
            "FROM User u " +
            "WHERE u.username = :userEmail ")
    UserResponse encontrarUserByEmail(@Param("userEmail") String userEmail);


   @Query(" SELECT new com.challenge.sermaluc.usuarios.adapter.web.dto.output.PhoneResponse(" +
            "p.number, " +
            "p.cityCode, " +
            "p.countryCode " +
            ") " +
            "FROM Phone p " +
            "WHERE  p.userId = :userId ")
    List<PhoneResponse> findPhonesByUserId(@Param("userId") String userId);

}
