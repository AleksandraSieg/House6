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

    public Room createRoom(String owner, int amountOfWindows){
        return Room.builder().owner(owner).amountOfWindows(amountOfWindows).build();
    }

    public void run(String... strings) throws Exception {
        Room room1 = Room.builder().owner("Stach").amountOfWindows(4).build();
        Room room2 = Room.builder().owner("Maria").amountOfWindows(6).build();
        Room room3 = Room.builder().owner("Ingrida").amountOfWindows(7).build();
        Room room4 = Room.builder().owner("Marian").amountOfWindows(1).build();
        roomRepository.save(room1);
        roomRepository.save(room2);
        roomRepository.save(room3);
        roomRepository.save(room4);
    }

}
