package com.seul.security.jpa.controller;

import com.seul.security.jpa.domain.Member;
import com.seul.security.jpa.domain.Posts;
import com.seul.security.jpa.dto.PostsDto;
import com.seul.security.jpa.security.CustomUserDetails;
import com.seul.security.jpa.service.MemberService;
import com.seul.security.jpa.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostIndexController {
    private final PostsService postService;
    private final MemberService memberService;
    private final HttpSession session;

    @GetMapping("/posts")
    public String list(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails,
                       @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Member writer = memberService.findMember(customUserDetails.getUsername());
        model.addAttribute("writer", writer);

        Page<Posts> lists = postService.pageList(pageable);

        model.addAttribute("lists", lists);
        model.addAttribute("maxPage", 10);
        return "posts/postsList";
    }

    @GetMapping("/posts/write")
    public String write(Model model){
        Member member = (Member) session.getAttribute("member");
        if (member != null) {
            model.addAttribute("member", member);
        }
        return "posts/postsWrite";
    }

    @GetMapping("/posts/update/{id}")
    public String update(@PathVariable Long id, Model model){
        PostsDto.Response dto = postService.findById(id);
        model.addAttribute("posts", dto);
        return "posts/postsUpdate";
    }

    @GetMapping("/posts/read/{id}")
    public String read(@PathVariable Long id, Model model){
        PostsDto.Response dto = postService.findById(id);
        postService.updateView(id);
        model.addAttribute("posts", dto);
        return "posts/postsRead";
    }

}
