package com.seul.security.jpa.controller;

import com.seul.security.jpa.dto.MemberDto;
import com.seul.security.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join(){
        return "member/memberJoin";
    }

    @PostMapping("/joinProc")
    public String joinProc(MemberDto.Request dto){
        memberService.memberJoin(dto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "member/memberLogin";
    }
}
