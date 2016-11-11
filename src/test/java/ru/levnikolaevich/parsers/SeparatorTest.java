package ru.levnikolaevich.parsers;

import org.junit.*;

public class SeparatorTest {
    private Separator separator = new Separator();

   // private static Logger log = LoggerFactory.getLogger(Separator.class);



    //private Mockery context;

    @BeforeClass
    public static void beforeTest(){
       // log.info("This is @BeforeClass method");
    }

    @Before
    public void before(){
      //  log.info("This is @Before method");
        this.separator = new Separator();
        //this.context = new JUnit4Mockery();
    }

    @Test(expected = Exception.class)
    public void testDoSome() throws Exception {
      //  log.info("This is testHandle method");
        this.separator.doSome(5);
        //assertThat();

    }

    @Test
    public void testSumm() {
        //  log.info("This is testHandle method");
        Assert.assertEquals(this.separator.summ(5,5), 10);

    }

    @After
    public void after(){
       // log.info("This is @After method");
    }

    @AfterClass
    public static void afterTest(){
      //  log.info("This is @AfterClass method");
    }



}
