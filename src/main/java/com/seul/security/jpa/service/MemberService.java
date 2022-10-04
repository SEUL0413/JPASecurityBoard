package com.seul.security.jpa.service;

import com.seul.security.jpa.domain.Member;
import com.seul.security.jpa.dto.MemberDto;
import com.seul.security.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void memberJoin(MemberDto.Request dto) {
        dto.setPassword(encoder.encode(dto.getPassword()));
        memberRepository.save(dto.toEntity());
    }

    @Transactional
    public Member findMember(String email) {
        return memberRepository.getByEmail(email);
    }

}
