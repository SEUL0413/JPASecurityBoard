package com.seul.security.jpa.controller;


import com.seul.security.jpa.dto.PostsDto;
import com.seul.security.jpa.security.CustomUserDetails;
import com.seul.security.jpa.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostsService postService;

    @PostMapping("/posts/postCreate")
    public ResponseEntity save(@RequestBody PostsDto.Request dto, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        return ResponseEntity.ok(postService.save(dto, customUserDetails.getUsername()));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity read(@PathVariable Long id){
        return ResponseEntity.ok(postService.findById(id));
    }


    @PutMapping("/posts/postUpdate/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody PostsDto.Request dto){
        postService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/posts/postDelete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        postService.delete(id);
        return ResponseEntity.ok(id);
    }

}
