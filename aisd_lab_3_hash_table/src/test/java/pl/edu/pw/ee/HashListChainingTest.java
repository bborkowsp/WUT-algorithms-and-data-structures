package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HashListChainingTest {

    private HashListChaining<String> hashList;
    private final int size = 8;

    @Before
    public void setUp() {
        hashList = new HashListChaining(size);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHashListChainingShouldThrowException() {
        // given
        int wrongSize = 0;

        // when
        hashList = new HashListChaining(wrongSize);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowException() {
        // given
        String nullString = null;

        // when
        hashList.add(nullString);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowException() {
        // given
        String nullString = null;

        // when
        hashList.get(nullString);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteShouldThrowExceptionWhenDeletingNull() {
        // given
        String nullString = null;

        // when
        hashList.delete(nullString);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteShouldThrowExceptionWhenDeletingSameValue() {
        //given
        String[] data = testData();
        for (String s : data) {
            hashList.add(s);
        }

        //when
        hashList.delete(data[2]);
        hashList.delete(data[2]);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteShouldThrowException() {
        //given
        String[] data = testData();
        for (String s : data) {
            hashList.add(s);
        }
        //when
        hashList.delete("x");

        //then 
        assert false;

    }

    @Test
    public void shouldAddCorrectly() {
        //given
        String[] expected = testData();

        //when
        for (String s : expected) {
            hashList.add(s);
        }

        //then
        String[] actuals = new String[expected.length];
        for (int i = 0; i < expected.length; i++) {
            actuals[i] = hashList.get(expected[i]);
        }
        Assert.assertArrayEquals(expected, actuals);

    }

    @Test
    public void shouldAddLargeNumberOfStrings() {
        int SIZE = 100_000;
        for (int i = 0; i < SIZE; i++) {
            String s = Integer.toString(i);
            hashList.add(s);
        }
    }

    @Test
    public void shouldNotAddDuplicates() {
        //given
        String[] data = {"aaa", "abc", "aaa", "bca", "cba", "aaa"};

        //when
        for (String s : data) {
            hashList.add(s);
        }

        //then
        int expected = 4; //Number of unique strings in data
        int actual = hashList.getnElem();
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void shouldAddThenDeleteThenAddThenGet() {
        //given
        String s = "Bob";

        //when
        hashList.add(s);
        hashList.delete(s);
        hashList.add(s);
        String actual = hashList.get(s);

        //then
        String expected = s;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetFirstValue() {
        //given
        HashListChaining<String> hashListChaining = new HashListChaining(1);
        String[] data = testData();
        for (String s : data) {
            hashListChaining.add(s);
        }

        //when
        String actual = hashListChaining.get(data[data.length - 1]);

        //then
        String expected = data[data.length - 1];
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shoudlGetLastElement() {
        //given
        HashListChaining<String> hashListChaining = new HashListChaining(1);
        String[] data = testData();
        for (String s : data) {
            hashListChaining.add(s);
        }

        //when
        String actual = hashListChaining.get(data[0]);

        //then
        String expected = data[0];
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shoudlGetMiddleElement() {
        //given
        HashListChaining<String> hashListChaining = new HashListChaining(1);
        String[] data = testData();
        for (String s : data) {
            hashListChaining.add(s);
        }

        //when
        String actual = hashListChaining.get(data[data.length / 2]);

        //then
        String expected = data[data.length / 2];
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetAllElements() {
        //given
        String[] data = testData();
        for (String s : data) {
            hashList.add(s);
        }

        //when
        String[] actual = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            actual[i] = hashList.get(data[i]);
        }

        //then
        Assert.assertArrayEquals(data, actual);
    }

    @Test
    public void shouldGetNull() {
        //given
        String[] data = testData();
        for (String s : data) {
            hashList.add(s);
        }
        //when
        String actual = hashList.get("x");

        //then
        String expected = null;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shoudlCorrectlyDeleteFirstElement() {
        //given
        HashListChaining<String> hashListChaining = new HashListChaining(1);
        String[] data = testData();
        for (String s : data) {
            hashListChaining.add(s);
        }

        //when
        hashListChaining.delete(data[data.length - 1]);

        //then
        String actual = hashListChaining.get(data[data.length - 1]);
        String expected = null;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shoudlCorrectlyDeleteLastElement() {
        //given
        HashListChaining<String> hashListChaining = new HashListChaining(1);
        String[] data = testData();
        for (String s : data) {
            hashListChaining.add(s);
        }

        //when
        hashListChaining.delete(data[0]);

        //then
        String actual = hashListChaining.get(data[0]);
        String expected = null;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shoudlCorrectlyDeleteMiddleElement() {
        //given
        HashListChaining<String> hashListChaining = new HashListChaining(1);
        String[] data = testData();
        for (String s : data) {
            hashListChaining.add(s);
        }

        //when
        hashListChaining.delete(data[data.length / 2]);

        //then
        String actual = hashListChaining.get(data[data.length / 2]);
        String expected = null;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shoudlCorrectlyDeleteAllElements() {
        //given
        String[] data = testData();
        for (String s : data) {
            hashList.add(s);
        }

        //when
        for (String s : data) {
            hashList.delete(s);
        }

        //then
        String[] actual = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            actual[i] = hashList.get(data[i]);
        }

        String[] expected = new String[data.length];
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testNumberOfElementAfterAdd() {
        //given
        String[] data = testData();
        for (String s : data) {
            hashList.add(s);
        }
        //when
        int actual = hashList.getnElem();

        //then
        int expected = data.length;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testNumberOfElementAfterAddingSameValueTwice() {
        //given
        String s = "Bob";

        //when
        hashList.add(s);
        hashList.add(s);

        //then
        int actual = hashList.getnElem();
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testNumberOfElementsAfterDeletingFirstElement() {
        //given
        HashListChaining<String> hashListChaining = new HashListChaining(1);
        String[] data = testData();
        for (String s : data) {
            hashListChaining.add(s);
        }

        //when
        hashListChaining.delete(data[data.length - 1]);

        //then
        int actual = hashListChaining.getnElem();
        int expected = data.length - 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testNumberOfElementsAfterDeletingLastElement() {
        //given
        HashListChaining<String> hashListChaining = new HashListChaining(1);
        String[] data = testData();
        for (String s : data) {
            hashListChaining.add(s);
        }

        //when
        hashListChaining.delete(data[0]);

        //then
        int actual = hashListChaining.getnElem();
        int expected = data.length - 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testNumberOfElementsAfterDeletingMiddleElement() {
        //given
        HashListChaining<String> hashListChaining = new HashListChaining(1);
        String[] data = testData();
        for (String s : data) {
            hashListChaining.add(s);
        }

        //when
        hashListChaining.delete(data[data.length / 2]);

        //then
        int actual = hashListChaining.getnElem();
        int expected = data.length - 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testNumberOfElementsAfterDeletingEveryElement() {
        //given
        HashListChaining<String> hashListChaining = new HashListChaining(size);
        String[] data = testData();
        for (String s : data) {
            hashListChaining.add(s);
        }

        //when
        for (String s : data) {
            hashListChaining.delete(s);
        }

        //then
        int actual = hashListChaining.getnElem();
        int expected = 0;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCountLoadFactor() {
        //given
        String[] data = testData();
        for (String s : data) {
            hashList.add(s);
        }

        //when
        double actual = hashList.countLoadFactor();

        //then
        double expected = (double) data.length / (double) size;
        Assert.assertEquals(expected, actual, 0.0);
    }

    private String[] testData() {
        String[] data = {"Central African Republic", "Comoros", "Ethiopia"};
        return data;
    }

}
