package org.fasttrackit.onlineshop.persistance;

import org.fasttrackit.onlineshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {



}
