import org.junit.Assert;
import org.junit.Test;
public class CalTest {

    @Test
    public void test1() {
        Assert.assertEquals(89, Cal.cal(1,1,3,31,1700));
    }

    @Test
    public void test2() {
        Assert.assertEquals(0, Cal.cal(1,1,1,1,1600));
    }

    @Test
    public void test3() {
        Assert.assertEquals(90, Cal.cal(1,1,3,31,396));
    }

    @Test
    public void test4() {
        Assert.assertEquals(29, Cal.cal(2,2,3,2,1600));
    }

    @Test
    public void test5() {
        Assert.assertEquals(221, Cal.cal(2, 1, 9, 10, -1));
    }

    @Test
    public void test6() {
        Assert.assertEquals(89, Cal.cal(1,1,3,31,1701));
    }

    @Test
    public void test7() {
        Assert.assertEquals(89, Cal.cal(1,1,3,31,-4));
    }

    @Test
    public void test8() {
        // Assert.assertEquals(89, Cal.cal(8,7,7,1,-4));
        Assert.assertEquals(29, Cal.cal(2,1,3,2,-1604));
    }

    @Test
    public void test9() {
        Assert.assertEquals(31, Cal.cal(2, 2, 3, 5, -500));
    }

    @Test
    public void test10() {
        Assert.assertEquals(29, Cal.cal(4, 4, 3, 3, 1600));
    }


}