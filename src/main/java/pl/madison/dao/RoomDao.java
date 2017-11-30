package pl.madison.dao;

import org.springframework.data.repository.CrudRepository;
import pl.madison.domain.Room;

public interface RoomDao extends CrudRepository<Room, Long> {

}
