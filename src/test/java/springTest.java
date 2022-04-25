import com.lin.service.UserService;
import com.lin.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.rmi.runtime.NewThreadAction;

/**
 * @author :lzm
 * @date :2022/4/22 13:20
 * @description :
 * @modyified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class springTest {

    @Autowired
   private UserService userService;


    @Test
    public void test(){

        System.out.println(userService.findAllUsers(new UserVo()));

    }
}
