package com.project.storeapi.repositories;

import com.project.storeapi.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryUserRepository implements UserRepository {

    // DB
    private static final Map<Integer, User> users;

    static AtomicLong idGenerator = new AtomicLong();

    static {
        users = new HashMap<>();

        User Alex = new User(
                idGenerator.incrementAndGet(),
                "Alex",
                "alex@email.com",
                "12345"
        );

        User Jane = new User(
                idGenerator.incrementAndGet(),
                "Jane",
                "jane@email.com",
                "1234"
        );

        users.put(Math.toIntExact(Alex.getId()), Alex);
        users.put(Math.toIntExact(Jane.getId()), Alex);
    }

    @Override
    public void save(User user) {
        user.setId(idGenerator.incrementAndGet());
        users.put(Math.toIntExact(user.getId()), user);
    }

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User findUserByEmail(String email) {
        return users.values()
                .stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
