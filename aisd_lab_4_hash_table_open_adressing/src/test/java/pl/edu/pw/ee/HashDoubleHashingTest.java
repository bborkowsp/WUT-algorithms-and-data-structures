package pl.edu.pw.ee;

import pl.edu.pw.ee.Utils.TestElem;
import pl.edu.pw.ee.services.HashTable;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HashDoubleHashingTest extends CommonTest {

    public HashDoubleHashingTest() {
        super(new HashDoubleHashing());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        HashTable<Double> unusedHash = new HashDoubleHashing<>(initialSize);

        // then
        assert false;
    }

    @Test
    public void should_CorrectlyGetElem_WhenPuttingALotElemsWithSameHashCode() {
        //given
        int initialSize = 1;
        HashTable<TestElem> hash = new HashDoubleHashing<>(initialSize);

        //when
        int numberOfTestElem = 10_000;
        for (int i = 0; i < numberOfTestElem; i++) {
            TestElem elem = new TestElem();
            hash.put(elem);

            //then
            assertEquals(elem, hash.get(elem));
        }
        assertEquals(numberOfTestElem, getNumOfElems(hash));

    }

    @Test
    public void should_CorrectlyDeleteElem_WhenPuttingALotElemsWithSameHashCode() {
        //given
        int initialSize = 2;
        HashTable<TestElem> hash = new HashDoubleHashing<>(initialSize);

        //when
        int numberOfTestElem = 10_000;
        TestElem[] testElems = new TestElem[numberOfTestElem];
        for (int i = 0; i < numberOfTestElem; i++) {
            TestElem elem = new TestElem();
            testElems[i] = elem;
            hash.put(elem);
        }

        //then
        for (int i = 0; i < numberOfTestElem; i++) {
            hash.delete(testElems[i]);
            assertEquals(null, hash.get(testElems[i]));
        }
        assertEquals(0, getNumOfElems(hash));
    }

}
