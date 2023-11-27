package com.iftm.sqli.prevention.repository;

import com.iftm.sqli.prevention.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

//    A anotação @Query cria uma consulta parametrizada.
//    Os parâmetros username e comment são automaticamente escapados pelo Spring Data JPA, impedindo a injeção de SQL.
    @Query("SELECT u FROM Users u WHERE u.username = ?1 AND u.comment = ?2")
    List<Users> findByUsernameAndComment(final String username, final String comment);
}
