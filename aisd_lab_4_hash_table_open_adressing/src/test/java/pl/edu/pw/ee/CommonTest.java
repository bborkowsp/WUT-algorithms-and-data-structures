package pl.edu.pw.ee;

import java.lang.reflect.Field;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pl.edu.pw.ee.services.HashTable;

public abstract class CommonTest {

    protected HashTable<String> hash;

    public CommonTest(HashTable hash) {
        this.hash = hash;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenPuttingNullValue() {
        //given
        String s = null;

        //when
        hash.put(s);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenGettingNullValue() {
        //given
        String s = null;

        //when
        hash.get(s);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenDeletingNullValue() {
        //given
        String s = null;

        //when
        hash.delete(s);

        //then
        assert false;
    }

    @Test
    public void should_ReturnNull_WhenGettingDeletedValue() {
        //given
        String s = "John";
        hash.put(s);

        //when
        hash.delete(s);

        //then
        assertEquals(null, hash.get(s));
    }

    @Test
    public void should_CorrectlyPutGetDelete() {
        //given
        int size = 10_000;

        //when
        for (int i = 0; i <= size; i++) {
            hash.put(Integer.toString(i));
        }

        //then
        for (int i = size; i >= 0; i--) {
            String s = Integer.toString(i);

            assertEquals(s, hash.get(s));

            hash.delete(s);
            int nOfElems = getNumOfElems(hash);

            assertEquals(i, nOfElems);
            assertEquals(null, hash.get(s));
        }
    }

    @Test
    public void should_NotDecraseNumberOfElems_WhenDeletingSameObjectTwice() {
        //given
        String s = "Alicia";
        int nOfElemsBeforePut = getNumOfElems(hash);
        hash.put(s);
        int nOfElemsAfterPut = getNumOfElems(hash);

        //when
        hash.delete(s);
        int nOfElemsAfterFirstDelete = getNumOfElems(hash);
        hash.delete(s);
        int nOfElemsAfterSecondDelete = getNumOfElems(hash);

        //then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
        assertEquals(0, nOfElemsAfterFirstDelete);
        assertEquals(0, nOfElemsAfterSecondDelete);
    }

    @Test
    public void should_NotIncreaseNumberOfElems_WhenPuttingDuplicate() {
        //given
        String s = "Bob";

        //when
        int nOfElemsBeforePut = getNumOfElems(hash);
        hash.put(s);
        int nOfElemsAfterFirstPut = getNumOfElems(hash);
        hash.put(s);
        int nOfElemsAfterSecondPut = getNumOfElems(hash);

        //then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterFirstPut);
        assertEquals(1, nOfElemsAfterSecondPut);

    }

    @Test
    public void should_CorrectlyPutElems_WhenPuttingElemsWithSameHashCode() {
        //given
        String[] elemsWithSameHashCode = {"Aa", "C#", "BB"}; // Based on Object.hashCode() implementation 

        //when
        for (String elem : elemsWithSameHashCode) {
            hash.put(elem);
        }

        //then
        for (String elem : elemsWithSameHashCode) {
            assertEquals(elem, hash.get(elem));
        }
        assertEquals(elemsWithSameHashCode.length, getNumOfElems(hash));
    }

    @Test
    public void should_CorrectlyDeleteFirstElem_WhenPuttingElemsWithSameHashCode() {
        //given
        String[] elemsWithSameHashCode = {"Aa", "C#", "BB"}; // Based on Object.hashCode() implementation 

        //when
        for (String elem : elemsWithSameHashCode) {
            hash.put(elem);
        }

        //then
        hash.delete(elemsWithSameHashCode[0]);
        assertEquals(null, hash.get(elemsWithSameHashCode[0]));
        assertEquals(elemsWithSameHashCode.length - 1, getNumOfElems(hash));

    }

    @Test
    public void should_CorrectlyDeleteMiddleElem_WhenPuttingElemsWithSameHashCode() {
        //given
        String[] elemsWithSameHashCode = {"Aa", "C#", "BB"}; // Based on Object.hashCode() implementation 

        //when
        for (String elem : elemsWithSameHashCode) {
            hash.put(elem);
        }

        //then
        hash.delete(elemsWithSameHashCode[1]);
        assertEquals(null, hash.get(elemsWithSameHashCode[1]));
        assertEquals(elemsWithSameHashCode.length - 1, getNumOfElems(hash));

    }

    @Test
    public void should_CorrectlyDeleteLastElem_WhenPuttingElemsWithSameHashCode() {
        //given
        String[] elemsWithSameHashCode = {"Aa", "C#", "BB"}; // Based on Object.hashCode() implementation 

        //when
        for (String elem : elemsWithSameHashCode) {
            hash.put(elem);
        }

        //then
        hash.delete(elemsWithSameHashCode[2]);
        assertEquals(null, hash.get(elemsWithSameHashCode[2]));
        assertEquals(elemsWithSameHashCode.length - 1, getNumOfElems(hash));

    }

    @Test
    public void should_CorrectlyDeleteAllElem_WhenPuttingElemsWithSameHashCode() {
        //given
        String[] elemsWithSameHashCode = {"Aa", "C#", "BB"}; // Based on Object.hashCode() implementation 

        //when
        for (String elem : elemsWithSameHashCode) {
            hash.put(elem);
        }

        //then
        for (String elem : elemsWithSameHashCode) {
            hash.delete(elem);
        }
        for (String elem : elemsWithSameHashCode) {
            assertEquals(null, hash.get(elem));
        }
        assertEquals(0, getNumOfElems(hash));

    }

    @Test
    public void should_NotDeleteElem_WhenPuttingElemsWithSameHashCode() {
        //given
        String toPut = "Aa";
        String toDelete = "C#";
        /* Strings toPut and toDelete have same hashCode */

        //when
        hash.put(toPut);
        hash.delete(toDelete);
        //then
        assertEquals(1, getNumOfElems(hash));
        assertEquals(toPut, hash.get(toPut));
    }

    @Test
    public void should_CorrectlyPutNewElem_WhenHashIsNotEmpty() {
        //given
        String[] someStrings = {"Bob", "John", "Patrick"};

        //when
        for (String s : someStrings) {
            hash.put(s);
        }
        String newElem = "Emma";
        hash.put(newElem);

        //then
        assertEquals(newElem, hash.get(newElem));
        assertEquals(someStrings.length + 1, getNumOfElems(hash));
    }

    @Test
    public void should_CorrectlyPutNewElem_WhenHashWasEmpty() {
        // given
        String newElem = "nothing special";

        // when
        int nOfElemsBeforePut = getNumOfElems(hash);
        hash.put(newElem);
        int nOfElemsAfterPut = getNumOfElems(hash);

        // then
        assertEquals(newElem, hash.get(newElem));
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    public int getNumOfElems(HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        try {
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
