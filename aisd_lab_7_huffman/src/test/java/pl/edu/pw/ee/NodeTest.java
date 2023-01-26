package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;

public class NodeTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenCreatingNodeWithIllegalFrequency() {
        //given
        int frequency = -1;

        //when
        Node node = new Node(frequency, 'A');

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenCreatingNodeWithIllegalCharacter() {
        //given
        char character = 'ń';

        //when
        Node node = new Node(1, character);

        //then
        assert false;
    }

    @Test
    public void should_CorrectlyGetLeftAndRightNode() {
        //given
        Node leftnode = new Node(3, 'L');
        Node rightNode = new Node(4, 'R');

        //when
        Node parentNode = new Node(leftnode.getFrequency() + rightNode.getFrequency(), leftnode, rightNode);

        //then
        Assert.assertEquals(3 + 4, parentNode.getFrequency());
        Assert.assertEquals(leftnode, parentNode.getLeftNode());
        Assert.assertEquals(rightNode, parentNode.getRightNode());
    }

    @Test
    public void testNodeGetFrequencyAndCharacter() {
        //given
        Node node = new Node(3, 'A');

        //when
        int actualFrequency = node.getFrequency();
        int actualCharacter = node.getCharacter();

        //then
        Assert.assertEquals(3, actualFrequency);
        Assert.assertEquals('A', actualCharacter);
    }

}
