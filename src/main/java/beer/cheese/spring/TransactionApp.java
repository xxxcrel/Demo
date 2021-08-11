package beer.cheese.spring;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableJpaAuditing
public class TransactionApp {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TransactionApp.class);
        UserService userService = context.getBean(UserService.class);
        User user = new User(1L, "wuxc");
        System.out.println("start test");
        IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(i -> userService.updateUser(user));
    }
}

//@CommonsLog
@Service
class UserService {

    static int cnt = 0;
    @Autowired
    UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public synchronized boolean updateUser(User newUser) {
        Optional<User> old = userRepository.findById(newUser.getId());
        if (old.isPresent()) {
            System.out.println("user not null: " + cnt++);
            return false;
        }
        System.out.println("save user");
        userRepository.save(newUser);
        return true;
    }
}

@Repository
interface UserRepository extends JpaRepository<User, Long> {
}

@Entity
@Table(name = "tbl_user")
class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
