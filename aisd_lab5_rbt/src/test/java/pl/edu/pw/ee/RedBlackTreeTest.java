package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class RedBlackTreeTest {

    private RedBlackTree<Integer, String> tree;

    
    @Before
    public void setUp() {
        this.tree = new RedBlackTree<>();
    }

    @Test
    public void should_GetCorrectlyPut_WhenOnlyOneNode() {
        //given
        tree.put(0, "A");

        //when
        String actual = tree.get(0);

        //then
        assertEquals("A", actual);
    }

    @Test
    public void should_CorrectlySetNewValue_WhenPutNodeWithSameKey() {
        tree.put(0, "A");
        assertEquals("A", tree.get(0));

        tree.put(0, "B");
        assertEquals("B", tree.get(0));

    }
    
        @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhengetNullValue() {
        //given
        Integer key = null;
        String value = null;

        //when
        tree.put(0, "A");
        tree.get(null);

        //then
        assert false;
    }
    
            @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenputtNullValue() {
        //given
        Integer key = null;
        String value = null;

        //when
        tree.put(key,value);


        //then
        assert false;
    }
    
    @Test
    public void should_CorrectlyPreOrder(){
       tree.put(0,"A");
       tree.put(2,"AA");
   
       tree.getPreOrder();
       assertEquals("2:AA 0:A",tree.getPreOrder());
    }
        @Test
    public void should_CorrectlyPostOrder(){
       tree.put(0,"A");
       tree.put(2,"AA");

       
       assertEquals("0:A 2:AA",tree.getPostOrder());
    }

        @Test
    public void should_CorrectlyInOrder(){
       tree.put(0,"A");
       tree.put(2,"AA");
       System.out.println(tree.getInOrder());
       
       assertEquals("0:A 2:AA",tree.getInOrder());
    }


}
