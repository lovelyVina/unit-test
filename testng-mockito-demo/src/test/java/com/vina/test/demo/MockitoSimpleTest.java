package com.vina.test.demo;

import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author fWang
 * @Description:
 * @date 2017/9/26 15:40
 */
public class MockitoSimpleTest extends AbstractTestNGSpringContextTests {

    @Test(expectedExceptions = RuntimeException.class)
    public void testMockListThrowException(){
        // 模拟LinkedList 的一个对象
        LinkedList mockedList = mock(LinkedList.class);
        // 此时调用get方法，会返回null，因为还没有对方法调用的返回值做模拟
        assertThat(mockedList.get(0)).isEqualTo(null);

        // 模拟获取第一个元素时，返回字符串first。  给特定的方法调用返回固定值在官方说法中称为stub。
        when(mockedList.get(0)).thenThrow(new RuntimeException("出现异常啦！"));
        mockedList.get(0);

    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testMockListThrowExceptionWithReturnVoid(){
        // 模拟LinkedList 的一个对象
        LinkedList mockedList = mock(LinkedList.class);

        //when(mockedList.clear()).thenThrow(new RuntimeException("出现异常啦！")); //此种写法编译就不能通过
        doThrow(new RuntimeException("出现异常")).when(mockedList).clear();

        mockedList.clear();

    }

    @Test
    public void testMockWithReturn(){
        // 模拟LinkedList 的一个对象
        LinkedList mockedList = mock(LinkedList.class);
        // 此时调用get方法，会返回null，因为还没有对方法调用的返回值做模拟
        assertThat(null == mockedList.get(0));

        // 模拟获取第一个元素时，第一次返回字符串first，第二次返回first1，之后再调用按最后一次算。  给特定的方法调用返回固定值在官方说法中称为stub。
        when(mockedList.get(0)).thenReturn("first").thenReturn("first1");
        //when(mockedList.get(0)).thenReturn("first", "first1");//上一句可以简写成这句
        assertThat(mockedList.get(0)).isEqualTo("first");
        assertThat(mockedList.get(0)).isEqualTo("first1");

        verify(mockedList, times(3)).get(0);
        //times(2):调用2次
        //never():表示从未被调用
        //atleastOnce():表示至少被调用一次
        //atleast(3):表示至少被调用3次
        //atMost(7):表示最多被调用7次

    }

    @Test
    public void testMockWithMatcher(){
        //模拟LinkedList 的一个对象
        LinkedList mockedList = mock(LinkedList.class);

        //使用内置的anyInt参数匹配器，也可以自定义参数匹配器
        //模拟获取任一元素时，返回字符串element
        when(mockedList.get(anyInt())).thenReturn("element");
        //警告：若方法中的某一个参数使用了matcher，则所有的参数都必须使用matcher：
        doNothing().when(mockedList).add(anyInt(), eq("first"));  //correct
        //doNothing().when(mockedList).add(anyInt(), "first");    //error,will throw exception

        assertThat(mockedList.get(0)).isEqualTo("element");
        assertThat(mockedList.get(22)).isEqualTo("element");

        verify(mockedList, atLeastOnce()).get(0);
    }

    @Test
    public void testWithSpy(){
        //mock对象只能调用打桩方法,不能调用真实方法,
        //Spy可以能够监视一个真实对象,既可以对这个对象的某一个函数打桩返回我们期望的值,也可以去调用真实的方法,
        //创建spy对象的方式和mock类似,不同的一点是spy需要传一个真实对象而不是一个CLass对象.这里以一个List为例
        List spy = spy(new LinkedList());
        //when(spy.get(0)).thenReturn("value1");
        //当使用when去模拟返回值的时候,真是方法会被调用,所以会抛IndexOutRangeException.
        doReturn("value2").when(spy).get(0);
        //用doReturn()去设置的话,则不会去执行真实方法
        //所以谨慎使用spy
    }

    @Test
    public void testWithOrder(){
        //1、验证一个mock对象的函数执行顺序
        List singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");
        singleMock.add("was added third");

        //create an inOrder verifier for a single mock
        // 为该mock对象创建一个inOrder对象
        InOrder inOrder = inOrder(singleMock);

        // 确保add函数首先执行的是add("was added first"),然后才是add("was added second")
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added third");



        //2、验证两个mock对象的函数执行顺序
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //create inOrder object passing any mocks that need to be verified in order
        // 为这两个Mock对象创建inOrder对象
        InOrder inOrder1 = inOrder(firstMock, secondMock);

        // 验证它们的执行顺序
        inOrder1.verify(firstMock).add("was called first");
        inOrder1.verify(secondMock).add("was called second");

    }

    @Test
    public void testWithCallbacks(){
        List mockList = mock(List.class);
        when(mockList.get(anyInt())).thenAnswer(new Answer() {
            public String answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                System.out.println("arguments: " + (Integer)args[0]);
                return "called with arguments: " + (Integer)args[0];
            }
        });

        assertThat(mockList.get(11)).isEqualTo("called with arguments: 11");
    }
}
