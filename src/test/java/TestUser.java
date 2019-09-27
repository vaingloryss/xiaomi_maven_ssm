import com.vainglory.controller.UserController;
import com.vainglory.pojo.User;
import com.vainglory.service.IUserService;
import com.vainglory.service.serviceImpl.UserServiceImpl;
import org.junit.Test;

public class TestUser {
    @Test
    public void test01(){
        /*IUserService userService = new UserServiceImpl();
        User user = userService.checkUserName("suxing");
        System.out.println(user.toString());*/

        UserController userController = new UserController();
        String result = userController.checkUsername("suxing");
        System.out.println(result);
    }
}
