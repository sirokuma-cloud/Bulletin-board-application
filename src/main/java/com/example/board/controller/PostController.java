package com.example.board.controller;

import com.example.board.entity.Post;
import com.example.board.entity.User;
import com.example.board.repository.PostRepository;
import com.example.board.repository.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class PostController {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    // コンストラクタ
    public PostController(PostRepository postRepository,
                          UserRepository userRepository) {

        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // 投稿一覧表示
    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute(
                "posts",
                postRepository.findAll()
        );

        return "index";
    }

    // 投稿作成フォーム表示
    @GetMapping("/posts/new")
    public String showCreateForm(Model model) {

        model.addAttribute(
                "post",
                new Post()
        );

        return "createPost";
    }

    // 投稿保存
    @PostMapping("/posts")
    public String createPost(@ModelAttribute Post post,
                             Principal principal) {

        // ログイン中ユーザー取得
        User user = userRepository
                .findByUsername(principal.getName())
                .orElseThrow();

        // 投稿にユーザー紐付け
        post.setUser(user);

        // DB保存
        postRepository.save(post);

        return "redirect:/";
    }

    // 編集フォーム表示
    @GetMapping("/posts/edit/{id}")
    public String showEditForm(@PathVariable Long id,
                               Model model,
                               Principal principal) {

        Post post = postRepository
                .findById(id)
                .orElseThrow();

        // 投稿者本人確認
        if (!post.getUser()
                .getUsername()
                .equals(principal.getName())) {

            return "redirect:/";
        }

        model.addAttribute("post", post);

        return "editPost";
    }

    // 投稿更新
    @PostMapping("/posts/update/{id}")
    public String updatePost(@PathVariable Long id,
                             @ModelAttribute Post updatedPost,
                             Principal principal) {

        Post post = postRepository
                .findById(id)
                .orElseThrow();

        // 投稿者本人確認
        if (!post.getUser()
                .getUsername()
                .equals(principal.getName())) {

            return "redirect:/";
        }

        post.setTitle(updatedPost.getTitle());

        post.setContent(updatedPost.getContent());

        postRepository.save(post);

        return "redirect:/";
    }

    // 投稿削除
    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable Long id,
                             Principal principal) {

        Post post = postRepository
                .findById(id)
                .orElseThrow();

        // 投稿者本人確認
        if (!post.getUser()
                .getUsername()
                .equals(principal.getName())) {

            return "redirect:/";
        }

        postRepository.delete(post);

        return "redirect:/";
    }
}