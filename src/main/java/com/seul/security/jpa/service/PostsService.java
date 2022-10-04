package com.seul.security.jpa.service;

import com.seul.security.jpa.domain.Member;
import com.seul.security.jpa.domain.Posts;
import com.seul.security.jpa.dto.MemberDto;
import com.seul.security.jpa.dto.PostsDto;
import com.seul.security.jpa.repository.MemberRepository;
import com.seul.security.jpa.repository.PostsRepository;
import com.seul.security.jpa.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(PostsDto.Request dto, String username){
        try {
            Member byId = memberRepository.getByEmail(username);
            dto.setMember(byId);
            Posts posts = dto.toEntity();
            postsRepository.save(posts);
            return posts.getId();
        }catch (IllegalArgumentException e){
            System.out.println("해당 게시글이 존재하지 않습니다.");
        }
        return null;
    }

    @Transactional(readOnly = true)
    public PostsDto.Response findById(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return new PostsDto.Response(posts);
    }

    @Transactional(readOnly = true)
    public List<Posts> findAll() {
        return postsRepository.findAll();
    }

    @Transactional
    public void update(Long id, PostsDto.Request dto){
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        posts.update(dto.getTitle(), dto.getContent());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        postsRepository.delete(posts);
    }

    @Transactional
    public int updateView(Long id){
        return postsRepository.updateView(id);
    }

    @Transactional
    public Page<Posts> pageList(Pageable pageable) {
        return postsRepository.findAll(pageable);
    }
}
