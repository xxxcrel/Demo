package com.cheeseocean.jpa;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import com.cheeseocean.jpa.model.entity.User;
import com.cheeseocean.jpa.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class App {

    //    public static void main(String[] args) {
//        ApplicationContext ctx = SpringApplication.run(App.class);
//        UserRepository userRepository = ctx.getBean(UserRepository.class);
//        PostRepository postRepository = ctx.getBean(PostRepository.class);
//        User user = new User();
//        user.setNickname("wuxc");
//
//        Set<Post> posts = new HashSet<>();
//        IntStream.range(1, 10).forEach(i -> {
//            Post post = new Post();
//            post.setContent("hello im " + i);
//            posts.add(post);
//        });
//        user.setPosts(posts);
//        userRepository.save(user);
//
//        userRepository.findByNickname("wuxc").getPosts().forEach(post -> {
//            System.out.println(post.getContent());
//        });
//
//        System.out.println("-----------");
//
//        userRepository.fetchAllByNickname("wuxc");
//
//        System.out.println("-----------");
//
//        userRepository.getByNickname("wuxc");
//
//        postRepository.findByContent("hello im 1");
//    }
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class);
        UserRepository userRepository = context.getBean(UserRepository.class);
        User user = new User();
        user.setNickname("wuxc");
        user.setCreatedAt(LocalDateTime.now());

        try {
            TimeUnit.SECONDS.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        userRepository.updateNickname("wuxuecheng");
    }
}
