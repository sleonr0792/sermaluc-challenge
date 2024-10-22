package com.challenge.sermaluc.adapter.database.repository;

import com.challenge.sermaluc.adapter.controller.model.outbound.UserDTO;
import com.challenge.sermaluc.adapter.controller.model.Phones;

import com.challenge.sermaluc.domain.model.entity.User;
import com.challenge.sermaluc.domain.model.entity.enums.UserState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUsername(String username);

    @Query(
            "SELECT new com.challenge.sermaluc.adapter.controller.model.outbound.UserDTO(" +
                    "u.userId, " +
                    "u.name, " +
                    "u.username, " +
                    "u.status) " +
            "FROM User u " +
            "WHERE u.username = :userEmail ")
    UserDTO encontrarUserByEmail(@Param("userEmail") String userEmail);


   @Query(" SELECT new com.challenge.sermaluc.adapter.controller.model.Phones(" +
            "p.number, " +
            "p.cityCode, " +
            "p.countryCode " +
            ") " +
            "FROM Phone p " +
            "WHERE  p.userId = :userId ")
    List<Phones> findPhonesByUserId(@Param("userId") String userId);

}
