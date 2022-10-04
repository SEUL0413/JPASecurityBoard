package com.seul.security.jpa.repository;

import com.seul.security.jpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Member getById(Long id);

    Member getByEmail(String Email);

}
