package pl.madison;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.madison.dao.RoomDao;
import pl.madison.domain.Room;

//4. Stwórz dom, w którym każdy pokój będzie miał właściciela oraz różną ilość obrazów.
// Wyświetl właściciela pokoju, w którym jest najmniej obrazów.

@SpringBootApplication
public class HouseApplication implements CommandLineRunner{

    @Autowired
    private RoomDao roomRepository;

    public static void main(String[] args) {
        SpringApplication.run(HouseApplication.class, args);

    }

    public void run(String... strings) throws Exception {
        Room room1 = new Room();
        room1.setOwner("Stach");
        room1.setAmountOfWindows(4);
        roomRepository.save(room1);
        Room room2 = new Room();
        room2.setOwner("Maria");
        room2.setAmountOfWindows(6);
        roomRepository.save(room2);
        Room room3 = new Room();
        room3.setOwner("Ingrida");
        room3.setAmountOfWindows(7);
        roomRepository.save(room3);
        Room room4 = new Room();
        room4.setOwner("Marian");
        room4.setAmountOfWindows(1);
        roomRepository.save(room4);
    }

}
