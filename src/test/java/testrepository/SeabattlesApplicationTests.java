package testrepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seabattles.entity.User;
import seabattles.repository.UserJpaRepo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SeabattlesApplicationTests {
	@Autowired
	private UserJpaRepo userRepository;
	@Test
	public void storeLoadMessage() {
		User user = new User();
		user.setName("Hello User");

		userRepository.save(user);

		List<User> users = (List<User>) userRepository.findAll();

		assertAll(
				() -> assertEquals(1, users.size()),
				() -> assertEquals("Hello User", users.getFirst().getName()));
	}

}
