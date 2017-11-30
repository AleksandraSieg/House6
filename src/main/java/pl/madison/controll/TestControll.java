package pl.madison.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.madison.dao.RoomDao;
import pl.madison.domain.Room;

@RestController
public class TestControll {

    @Autowired
    RoomDao roomDao;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show() {
        String room = "";
        for (Room room1 : roomDao.findAll()) {
            room = room + room1;
        }

        return room;
    }

    @RequestMapping(value = "/showOwnerWhoHasTheLeastAmountOfWindows", method = RequestMethod.GET)
    public String theLeastAmountOfWindows() {
        String temporaryOwner = "";
        int temporaryNumber = 1000;
        Room tempRoom = null;
        for (Room room : roomDao.findAll()) {
            if (temporaryNumber > room.getAmountOfWindows()) {
                tempRoom = room;
            }
        }

        return temporaryOwner + "właściciel, który ma najmniej okien to: " + tempRoom.getOwner()
                + " ilość okien: " + tempRoom.getAmountOfWindows();


    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@RequestParam("id") Long id, @RequestParam("owner") String owner,
                         @RequestParam("amountOfWindows") int amountOfWindows) {
        Room tempRoom = roomDao.findOne(id);
        tempRoom.setOwner(owner);
        tempRoom.setAmountOfWindows(amountOfWindows);
        roomDao.save(tempRoom);

        return "Your update has been completed;)";
    }

    @RequestMapping(value = "/deleteRoom", method = RequestMethod.DELETE)
    public String deleteRoom(@RequestParam("id") Long id) {
        roomDao.delete(roomDao.findOne(id));
        return "You have successfully deleted room from database";
    }

    @RequestMapping(value = "/findRooom", method = RequestMethod.GET)
    public String findRoom(@RequestParam("id") Long id) {

        Room tempRoom = roomDao.findOne(id);
        return "" + tempRoom;
    }

    @RequestMapping(value = "/addRoom", method = RequestMethod.PUT)
    public String addRoom(@RequestParam("owner") String owner, @RequestParam("amountOfWindows") int amountOfWindows){
        Room tempRoom = new Room();
        tempRoom.setOwner(owner);
        tempRoom.setAmountOfWindows(amountOfWindows);
        roomDao.save(tempRoom);
        return "You have successfully added new room";
    }
}
