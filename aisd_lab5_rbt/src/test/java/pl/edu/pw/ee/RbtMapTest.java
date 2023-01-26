package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

public class RbtMapTest {

    private RbtMap<Integer, String> tree;

    @Before
    public void setUp() {
        this.tree = new RbtMap<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenSetNullValue() {
        //given
        Integer key = null;
        String value = null;

        //when
        tree.setValue(key, value);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenGetNullValue() {
        //given
        Integer key = null;

        //when
        tree.getValue(key);

        //then
        assert false;
    }
}
