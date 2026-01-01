package com.roadmap.my.myroadmapback.repository;

import com.roadmap.my.myroadmapback.model.Token;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends CrudRepository<Token,Long> {
    Optional<Token> findByToken(String token);

    @Query(value = """
      select t from tokens t inner join t.user u\s
      where u.id = :id and (t.expired = false and t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(Long id);

}
