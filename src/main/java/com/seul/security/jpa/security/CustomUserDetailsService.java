package com.seul.security.jpa.security;


import com.seul.security.jpa.domain.Member;
import com.seul.security.jpa.dto.MemberDto;
import com.seul.security.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final HttpSession session;

    /* username이 DB에 있는지 확인 */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + email));

        session.setAttribute("member",member);

        return new CustomUserDetails(member);
    }
}
