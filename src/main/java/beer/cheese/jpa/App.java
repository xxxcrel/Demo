package beer.cheese.jpa;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import beer.cheese.jpa.model.entity.Post;
import beer.cheese.jpa.model.entity.User;
import beer.cheese.jpa.repository.PostRepository;
import beer.cheese.jpa.repository.UserRepository;

@SpringBootApplication
//@Profile()
public class App {
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class);
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        PostRepository postRepository = ctx.getBean(PostRepository.class);
        User user = new User();
        user.setNickname("wuxc");

        Set<Post> posts = new HashSet<>();
        IntStream.range(1, 10).forEach(i -> {
            Post post = new Post();
            post.setContent("hello im " + i);
            posts.add(post);
        });
        user.setPosts(posts);
        userRepository.save(user);

        userRepository.findByNickname("wuxc").getPosts().forEach(post -> {
            System.out.println(post.getContent());
        });

        System.out.println("-----------");

        userRepository.fetchAllByNickname("wuxc");

        System.out.println("-----------");

        userRepository.getByNickname("wuxc");

        postRepository.findByContent("hello im 1");
    }
}
