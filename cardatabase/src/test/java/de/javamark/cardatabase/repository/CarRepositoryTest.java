package de.javamark.cardatabase.repository;

import de.javamark.cardatabase.domain.Car;
import de.javamark.cardatabase.domain.CarRepository;
import de.javamark.cardatabase.domain.OwnerRepository;
import de.javamark.cardatabase.domain.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CarRepositoryTest {

    @Rule
    public PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    public void saveCar() {
        final Car car = new Car("Tesla", "Model X", "White", "ABC-1234", 2017, 86000, null);
        entityManager.persistAndFlush(car);
        assertThat(car.getId()).isNotNull();
    }

    @Test
    public void deleteCars() {
        entityManager.persistAndFlush(new Car("Tesla", "Model X", "White", "ABC-1234", 2017, 86000, null));
        entityManager.persistAndFlush(new Car("Mercedes", "B-Class", "Blue", "DEG-1234", 2014, 16000, null));

        assertThat(carRepository.findAll()).isNotEmpty();

        carRepository.deleteAll();

        assertThat(carRepository.findAll()).isEmpty();
    }

    @Before
    public void setUp() throws Exception {
        carRepository.deleteAll();
        ownerRepository.deleteAll();
        userRepository.deleteAll();
    }
}
