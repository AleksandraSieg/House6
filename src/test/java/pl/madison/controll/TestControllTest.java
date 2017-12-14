package pl.madison.controll;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.madison.dao.RoomDao;
import pl.madison.domain.Room;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TestControllTest {
    @InjectMocks
    private TestControll testControll;

    @Mock
    private RoomDao roomDao;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(testControll).build();
    }

    @Test
    public void show() throws Exception {
        List<Room> rooms = Arrays.asList(Room.builder().owner("x").build());

        Mockito.when(roomDao.findAll()).thenReturn(rooms);

        mockMvc.perform(get("/show")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].owner").value("x"));
    }

    @Test
    public void theLeastAmountOfWindows() throws Exception {
        List<Room> rooms = Arrays.asList(Room.builder().owner("x").amountOfWindows(4).build(),
                Room.builder().owner("y").amountOfWindows(3).build());

        Mockito.when(roomDao.findAll()).thenReturn(rooms);

        mockMvc.perform(get("/showOwnerWhoHasTheLeastAmountOfWindows"))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().string("y"));
    }

    @Test
    public void update() throws Exception {
        Mockito.when(roomDao.findOne(1L)).thenReturn(Room.builder().id(1L).owner("f").amountOfWindows(9).build());

        mockMvc.perform(MockMvcRequestBuilders.put("/update").param("id", "1")
        .param("owner", "f").param("amountOfWindows","9"))
                .andExpect(MockMvcResultMatchers.content().string("Your update has been completed;)"));

    }

    @Test
    public void deleteRoom() throws Exception {
        Mockito.when(roomDao.findOne(1L)).thenReturn(Room.builder().id(1L).build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteRoom")
        .param("id", "1")).andExpect(MockMvcResultMatchers.content()
                .string("You have successfully deleted room from database"));
    }

    @Test
    public void findRoom() throws Exception {
        Room room = Room.builder().owner("x").build();
        Mockito.when(roomDao.findOne(1L)).thenReturn(room);

        mockMvc.perform(get("/findRoom").param("id", "1")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Room(id=null, owner=x, amountOfWindows=0)"));
    }

    @Test
    public void addRoom() throws Exception {
        Room xxx = Room.builder().owner("xxx").amountOfWindows(7).build();

        Mockito.when(roomDao.save(xxx)).thenReturn(xxx);

        mockMvc.perform(MockMvcRequestBuilders.put("/addRoom").param("owner", "xxx")
                .param("amountOfWindows", "7"))
                .andExpect(MockMvcResultMatchers.content().string("You have successfully added new room"));
    }

}