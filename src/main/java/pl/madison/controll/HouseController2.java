package pl.madison.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.madison.dao.RoomDao;
import pl.madison.domain.Room;

import java.util.List;
import java.util.Map;

@Controller
public class HouseController2 {

    @Autowired
    RoomDao roomDao;

    @RequestMapping(value = "/show2", method = RequestMethod.GET)
    public String show2(Map<String, Object> model){
        List<Room> rooms = (List<Room>)roomDao.findAll();
        model.put("rooms", rooms);
        return "showRooms";
    }

    @RequestMapping(value = "/addRoom2")
    public String add(Map<String, Object> model){
        Room room = new Room();
        model.put("room", room);
        return "addRoom";
    }

    @RequestMapping(value = "/pageAfter", method = RequestMethod.POST)
    public String pA(Room room, Map<String, Object> model){
        Room savedRoom = roomDao.save(room);
        model.put("savedRoom", savedRoom);
        return "pageAfterAdding";
    }
}
