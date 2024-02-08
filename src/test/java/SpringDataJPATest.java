import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import seabattles.entity.User;
import seabattles.repository.UserJpaRepo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = {"seabattles"})
public class SpringDataJPATest extends Assert {

    @Autowired
    private UserJpaRepo messageRepository;

    @Test
    public void storeLoadMessage() {
        User user = new User();
        user.setName("Hello World from Spring Data JPA!");

        messageRepository.save(user);

        List<User> users = (List<User>)messageRepository.findAll();

        assertAll(
        () -> assertEquals(1, users.size()),
        () -> assertEquals("Hello World from Spring Data JPA!", users.get(0).getName()));
    }
}