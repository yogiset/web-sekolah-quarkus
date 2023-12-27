package repository;

import entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
//    public User findByEmail(String email) {
//        return find("email", email).firstResult();
//    }

    public Optional<User> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }
}
