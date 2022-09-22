package com.demo.ControllerTest;



import static org.junit.jupiter.api.Assertions.assertThrows;



import java.text.ParseException;
import java.util.NoSuchElementException;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;



import com.demo.controller.UserEntityController;
import com.demo.entities.UserEntity;
import com.demo.exception.DuplicateRecordException;
import com.demo.exception.RecordNotFoundException;
import com.demo.service.UserService;



@ActiveProfiles("test")
@SpringBootTest
class UserEntityControllerTest {
    
    @Autowired
    private UserService userService;



   @MockBean
    private UserEntityController userCon;
    
    @Test
    void deleteUserTest() throws ParseException, DuplicateRecordException {



       UserEntity testUserEntity = new UserEntity("Divya","Sharma","DIVSH12","Divsh@123","8735469021","divya.sharma@gmail.com",123);
        UserEntity testUserEntitySaved = userService.add(testUserEntity);
        long userEntityTestId = testUserEntitySaved.getId();
        userService.deleteUserById(userEntityTestId);
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            userService.findById(userEntityTestId);
        });



   }



   @Test
    void saveUserTest() throws ParseException, DuplicateRecordException {



       UserEntity testUserEntity = new UserEntity();
        UserEntity testUserEntitySaved = userService.add(testUserEntity);
        long userEntityTestId = testUserEntitySaved.getId();



       UserEntity testUserEntityDB = userService.findById(userEntityTestId);
        Assertions.assertNotNull(testUserEntityDB);



       userService.deleteUserById(userEntityTestId);
    }



   @Test
    void TestRecordDeliveryByIdShouldThrowDelIdException() throws RecordNotFoundException {
        assertThrows(RecordNotFoundException.class, () -> {
            UserEntity d = new UserEntity();
            userService.update(d);
        });
    }




}