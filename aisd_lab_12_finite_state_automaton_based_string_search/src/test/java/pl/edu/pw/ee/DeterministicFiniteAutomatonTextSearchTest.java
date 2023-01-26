package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DeterministicFiniteAutomatonTextSearchTest {

    private final String pattern = "ABACAB";

    @Test
    public void shouldFindFirstInTheBeggining() {
        // given
        String inputStr = "ABACABBBACXYXZ";

        // when
        DeterministicFiniteAutomatonTextSearch DFATS = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actual = DFATS.findFirst(inputStr);

        // then
        assertEquals(0, actual);
    }

    @Test
    public void shouldFindFirstInTheEnd() {
        // given
        String inputStr = "BBCAABACABX";

        // when
        DeterministicFiniteAutomatonTextSearch DFATS = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actual = DFATS.findFirst(inputStr);

        // then
        assertEquals(4, actual);
    }

    @Test
    public void shouldFindFirstInTheMiddle() {
        // given
        String inputStr = "BBXCABACABBBXC";

        // when
        DeterministicFiniteAutomatonTextSearch DFATS = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actual = DFATS.findFirst(inputStr);

        // then
        assertEquals(4, actual);
    }

    @Test
    public void shouldNotFindFirstInCuttedPattern() {
        // given
        String inputStr = "ABAXXCAB";

        // when
        DeterministicFiniteAutomatonTextSearch DFATS = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actual = DFATS.findFirst(inputStr);

        // then
        assertEquals(-1, actual);
    }

    @Test
    public void shouldFindAll_WhenPatternsInTheBegginingAndAtTheEnd() {
        // given
        String inputStr = "ABACABXABACAB";

        // when
        DeterministicFiniteAutomatonTextSearch DFATS = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actual = DFATS.findAll(inputStr);

        // then
        int[] expected = {0, 7};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAll_WhenOnlyOnePattern() {
        // given
        String inputStr = "ABACAB";

        // when
        DeterministicFiniteAutomatonTextSearch DFATS = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actual = DFATS.findAll(inputStr);

        // then
        int[] expected = {0};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindAllInCuttedPattern() {
        // given
        String inputStr = "ABAXXCAB";

        // when
        DeterministicFiniteAutomatonTextSearch DFATS = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actual = DFATS.findAll(inputStr);

        // then
        int[] expected = {-1};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindAllWhenInputStrIsCompltetlyDifferent() {
        // given
        String inputStr = "XXXXX";

        // when
        DeterministicFiniteAutomatonTextSearch DFATS = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actual = DFATS.findAll(inputStr);

        // then
        int[] expected = {-1};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAllWhenThreePatterns() {
        // given
        String inputStr = "ABACABABACABABACAB";

        // when
        DeterministicFiniteAutomatonTextSearch DFATS = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actual = DFATS.findAll(inputStr);

        // then
        int[] expected = {0, 6, 12};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAllWhenTwoPatternsAndLastIsCutted() {
        // given
        String inputStr = "ABACABABACABABAXCAB";

        // when
        DeterministicFiniteAutomatonTextSearch DFATS = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actual = DFATS.findAll(inputStr);

        // then
        int[] expected = {0, 6};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAllWhenInputIsEmpty() {
        // given
        String inputStr = "";

        // when
        DeterministicFiniteAutomatonTextSearch DFATS = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actual = DFATS.findAll(inputStr);

        // then
        int[] expected = {-1};
        assertArrayEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFindAllWhenPatternIsEmpty() {
        // given
        String inputStr = "CXA";
        String emptyPattern = "";
        // when
        DeterministicFiniteAutomatonTextSearch DFATS = new DeterministicFiniteAutomatonTextSearch(emptyPattern);
        int[] actual = DFATS.findAll(inputStr);

        // then
        assert false;
    }

    @Test
    public void shouldFindFirstWhenInputIsEmpty() {
        // given
        String inputStr = "";

        // when
        DeterministicFiniteAutomatonTextSearch DFATS = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actual = DFATS.findFirst(inputStr);

        // then
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindFirstForSpecialStr() {
        // given
        String inputStr = "ABABABABA";
        String specialPattern = "ABABA";
        // when
        DeterministicFiniteAutomatonTextSearch DFATS = new DeterministicFiniteAutomatonTextSearch(specialPattern);
        int actual = DFATS.findFirst(inputStr);

        // then
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindAllForSpecialStr() {
        // given
        String inputStr = "ABABABABA";
        String specialPattern = "ABABA";

        // when
        DeterministicFiniteAutomatonTextSearch DFATS = new DeterministicFiniteAutomatonTextSearch(specialPattern);
        int[] actual = DFATS.findAll(inputStr);

        // then
        int[] expected = {0, 2, 4};
        assertArrayEquals(expected, actual);
    }

}
