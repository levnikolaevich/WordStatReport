package ru.levnikolaevich.streams;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MockTest {

    private Mock mock;

    @Before
    public void resetFields() {
        this.mock = new Mock();
//        this.mock.setStreamReader(new StreamReader() {
//            @Override
//            public String read(long id) {
//                if(id == 5)
//                    return "Object";
//                throw new RuntimeException();
//            }
//        });
/*
        this.mock.setStreamReader((StreamReader) Proxy.newProxyInstance(this.mock.getClass().getClassLoader(),
                new Class[]{StreamReader.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if((long)args[0] == 5)
                            return "Object";
                        throw new RuntimeException();
                    }
                }));*/

        StreamReader streamReader = mock(StreamReader.class);
        when(streamReader.read(5)).thenReturn("Object");
        this.mock.setStreamReader(streamReader);
    }


    @Test
    public void testCalculate() {
        String result = this.mock.calculate(5);
        assertEquals(result, "calculated Object");
    }
}
