package sample.test.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sample.test.domain.UserDO;
import sample.test.mapper.UserMapper;
import sample.test.web.SampleApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/** 
* UserServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>九月 26, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SampleApplication.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @Before
    public void before() throws Exception {
        // 初始化测试用例类中由Mockito的注解标注的所有模拟对象
        MockitoAnnotations.initMocks(this);
        //这句话执行以后，userMapper自动注入到userService中。
        //在这之后，你就可以放心大胆地使用when().then()等进行更详细的设置。
    } 

    @After
    public void after() throws Exception { 
    } 

    /** 
     * 
     * Method: addUser(UserDO user) 
     * 
     */ 
    @Test
    public void testAddUser() throws Exception {
        UserDO user = initUserData();
        when(userMapper.addUser(user)).thenReturn(1, 2, 3);

        assertThat(userService.addUser(user)).isEqualTo(1);//第一次调用
        assertThat(userService.addUser(user)).isEqualTo(2);//第二次调用
        assertThat(userService.addUser(user)).isEqualTo(3);//第三次调用

        //验证mock对象的交互行为，也可以不验证
        verify(userMapper, atLeast(1)).get(1L);
        verify(userMapper, times(3)).addUser(user);
    }

    /**
     * 
     * Method: get(Long userId) 
     * 
     */ 
    @Test
    public void testGet() throws Exception { 
        //TODO: Test goes here... 
    }

    /** 
     * 
     * Method: getByName(String name) 
     * 
     */ 
    @Test
    public void testGetByName() throws Exception { 
        //TODO: Test goes here... 
    }

    private UserDO initUserData() {
        UserDO user = new UserDO();
        user.setUserID(1L);
        user.setUserName("xiaoxiao");
        return user;
    }
    
} 
