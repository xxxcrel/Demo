//package com.cheeseocean.spring;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.Optional;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//import java.util.stream.IntStream;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cglib.core.DebuggingClassWriter;
//import org.springframework.cglib.core.ReflectUtils;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Isolation;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//@Repository
//interface UserRepository extends JpaRepository<User, Long> {
//}
//
//@SpringBootApplication
//@EnableJpaAuditing
//public class TransactionApp {
//    public static void main(String[] args) {
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, System.getProperty("user.home") + "/transaction");
//        ApplicationContext context = SpringApplication.run(TransactionApp.class);
//        UserService userService = context.getBean(UserService.class);
//        User user = new User(1L, "wuxc");
////        try {
////            final Method updateUser = ReflectUtils.findDeclaredMethod(UserService.class, "updateUser", new Class[]{User.class});
////            IntStream.rangeClosed(1, 10)
////                    .parallel()
////                    .forEach(i -> {
////                        try {
////                            updateUser.invoke(userService, user);
////                        } catch (IllegalAccessException e) {
////                            e.printStackTrace();
////                        } catch (InvocationTargetException e) {
////                            e.printStackTrace();
////                        }
////                    });
////        } catch (NoSuchMethodException e) {
////            System.out.println("no such method");
////            e.printStackTrace();
////        }
//        IntStream.rangeClosed(1, 10)
//                .parallel()
//                .forEach(i -> userService.updateUser(user));
//        System.out.println("start test");
//
//    }
//}
//
////@CommonsLog
//@Service
//class UserService {
//
//    static int cnt = 0;
//    Lock lock = new ReentrantLock();
//    @Autowired
//    UserRepository userRepository;
//
//    @Transactional(isolation = Isolation.READ_COMMITTED)
//    public synchronized boolean updateUser(User newUser) {
//        try {
////            System.out.println("lock");
////            TimeUnit.SECONDS.sleep(2);
////            lock.lock();
//            Optional<User> old = userRepository.findById(newUser.getId());
//            if (old.isPresent()) {
//                System.out.println("user not null: " + cnt++);
////                lock.unlock();
//                return false;
//            }
//            System.out.println("save user");
//            User user = userRepository.saveAndFlush(newUser);
////            System.out.println(user);
////            lock.unlock();
////            System.out.println("unlock");
//            return true;
//        } catch (Exception e) {
//            System.out.println("catch exeception");
//            e.printStackTrace();
//        }
//        return false;
//    }
//}
//
//@Entity
//@Table(name = "tbl_user")
//class User {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    private String username;
//
//    public User(Long id, String username) {
//        this.id = id;
//        this.username = username;
//    }
//
//    public User() {}
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                '}';
//    }
//}
