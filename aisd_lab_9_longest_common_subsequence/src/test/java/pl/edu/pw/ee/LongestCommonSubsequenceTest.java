package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.Assert;
import org.junit.Test;

public class LongestCommonSubsequenceTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenTopStrIsNull() {
        //given
        String topStr = null;
        String leftStr = "Not null";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenLeftStrIsNull() {
        //given
        String topStr = "Not null";
        String leftStr = null;

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenBothStringsAreNull() {
        //given
        String topStr = null;
        String leftStr = null;

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenTopStrIsEmpty() {
        //given
        String topStr = "";
        String leftStr = "ipsum Lorem";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenLeftStrIsEmpty() {
        //given
        String topStr = "Ipsum Lorem";
        String leftStr = "";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);

        //then
        assert false;
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenBothStringsAreEmpty() {
        //given
        String topStr = "";
        String leftStr = "";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);

        //then
        assert false;
    }

    @Test
    public void should_CorrrectlyFindLCS_WhenWordsHaveNewLineCharacterAtTheBeginning() {
        //given
        String topStr = "\nPOMIDOR";
        String leftStr = "\nAPOLITYCZNY RUMOR";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "\nPOIOR";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrrectlyFindLCS_WhenWordsHaveCarriageReturnCharacterAtTheBeginning() {
        //given
        String topStr = "\rPOMIDOR";
        String leftStr = "\rAPOLITYCZNY RUMOR";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "\rPOIOR";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrrectlyFindLCS_WhenWordsHaveTabCharacterAtTheBeginning() {
        //given
        String topStr = "\tPOMIDOR";
        String leftStr = "\tAPOLITYCZNY RUMOR";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "\tPOIOR";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrrectlyFindLCS_WhenWordsHaveNewLineCharacterAtTheEnd() {
        //given
        String topStr = "POMIDOR\n";
        String leftStr = "APOLITYCZNY RUMOR\n";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "POIOR\n";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrrectlyFindLCS_WhenWordsHaveCarriageReturnCharacterAtTheEnd() {
        //given
        String topStr = "POMIDOR\r";
        String leftStr = "APOLITYCZNY RUMOR\r";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "POIOR\r";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrrectlyFindLCS_WhenWordsHaveTabCharacterAtTheEnd() {
        //given
        String topStr = "POMIDOR\t";
        String leftStr = "APOLITYCZNY RUMOR\t";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "POIOR\t";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrrectlyFindLCS_WhenWordsHaveNewLineCharacterInTheMiddle() {
        //given
        String topStr = "POMI\nDOR";
        String leftStr = "APOLITY\nCZNY RUMOR";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "POI\nOR";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrrectlyFindLCS_WhenWordsHaveCarriageReturnCharacterInTheMiddle() {
        //given
        String topStr = "POMI\rDOR";
        String leftStr = "APOLI\rTYCZNY RUMOR";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "POI\rOR";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrrectlyFindLCS_WhenWordsHaveMultipleNewLineCharacter() {
        //given
        String topStr = "\nK\nI\nA\n";
        String leftStr = "\n\nI\nXA\n";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "\n\nI\nA\n";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrrectlyFindLCS_WhenWordsHaveMultipleFormFeedCharacter() {
        //given
        String topStr = "\fK\fI\fA\f";
        String leftStr = "\fX\fI\fXA\f";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "\f\fI\fA\f";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrrectlyFindLCS_WhenWordsHaveMultipleBackspaceCharacter() {
        //given
        String topStr = "\bK\bI\bA\b";
        String leftStr = "\b\bIR\bXA\b";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "\b\bI\bA\b";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrrectlyFindLCS_WhenWordsHaveMultipleTabCharacter() {
        //given
        String topStr = "\tPO\tMI\t\tDOR\t";
        String leftStr = "APO\tLIT\tYC\tZNY";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "PO\tI\t\t";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrrectlyFindLCS_WhenWordsHaveMultipleCarriageReturnCharacter() {
        //given
        String topStr = "P\rOMI\rDO\r\rR\r";
        String leftStr = "APO\rLI\rTYC\r\rZNY";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "PO\r\r\r\r";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrrectlyFindLCS_WhenWordsHaveMultipleSpecialCharacters() {
        //given
        String topStr = "P\b\bO\tLIT\bE\tCH\nN\fIKA";
        String leftStr = "W\rARS\t\tZAW\nSKA\f";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "\t\t\nKA";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyFindLCS_WhenStringsHaveNonAsciiCharacters() {
        //given
        String topStr = "Zażółć gęślą jaźń.";
        String leftStr = "żółtoróżowość";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "żółś";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyFindLCS_WhenSameCharacterButDifferentSize() {
        //given
        String topStr = "xABCy";
        String leftStr = "XabcY";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyFindLCS_WhenMultipleCorrectLCSExists() {
        //given
        String topStr = "BBBBAAAA1111";
        String leftStr = "1111AAAABBBB";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "1111";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyFindLCS_WhenOneStringIsInsideSecond() {
        //given
        String topStr = "konstantynopolitańczykowianeczka";
        String leftStr = "politańczyk";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "politańczyk";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyFindLCS_WhenStringsAreEqual() {
        //given
        String topStr = "konstantynopolitańczykowianeczka mieszka w Konstantynopolu";
        String leftStr = topStr;

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = topStr;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyFindLCS_WhenSingle_CharacterWords() {
        //given
        String topStr = "A";
        String leftStr = "A";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "A";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyFindLCS_WhenTwo_CharacterWords() {
        //given
        String topStr = "AA";
        String leftStr = "AA";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "AA";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyFindLCS_WhenThree_CharacterWords() {
        //given
        String topStr = "ACA";
        String leftStr = "ACA";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "ACA";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyFindLCS_WhenStringsAreCompletlyDifferent() {
        //given
        String topStr = "VVV";
        String leftStr = "AAA";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyFindLCS_test1() {
        //given
        String topStr = "KANAPKI";
        String leftStr = "NAPISY";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "NAPI";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyFindLCS_test2() {
        //given
        String topStr = "ACCGGTCGAGTGCGCGGAAGCCGGCCGAA";
        String leftStr = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "GTCGTCGGAAGCCGGCCGAA";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyFindLCS_test3() {
        //given
        String topStr = "Ow-029+==-*2";
        String leftStr = "08dH=2yh";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = "0=2";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyFindLCS_WhenLongStrings() {
        //given
        String topStr = prepareLongString();
        String leftStr = topStr;

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        String actual = lcs.findLCS();

        //then
        String expected = topStr;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyFindLCS_AfterDisplay() {
        //given
        String topStr = "KANAPKI";
        String leftStr = "NAPISY";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        lcs.findLCS();
        lcs.display();
        String actual = lcs.findLCS();

        //then
        String expected = "NAPI";
        Assert.assertEquals(expected, actual);
    }

    private final ByteArrayOutputStream stdoutDisplay = new ByteArrayOutputStream();

    @Test
    public void should_CorrectlyDisplay() throws FileNotFoundException, IOException, ClassNotFoundException {
        //given 
        String topStr = "często_z_odkrywaniem";
        String leftStr = "rzeczy_nie_trzeba\n_się_spieszyć";

        //when
        System.setOut(new PrintStream(stdoutDisplay));

        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        lcs.display();

        //then
        String expected = expectedDisplay("\\expectedDisplay.txt");
        String actual = stdoutDisplay.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyDisplay_WhenSpecialCharacters() throws IOException {
        //given 
        String topStr = "\r\f\b\n\t";
        String leftStr = "\f\b\r\t\n";

        //when
        System.setOut(new PrintStream(stdoutDisplay));

        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        lcs.display();

        //then
        String expected = expectedDisplay("\\expectedDisplayWhenSpecialChar.txt");
        String actual = stdoutDisplay.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyDisplay_WhenLongStrings() throws IOException {
        //given 
        String topStr = prepareLongString();
        String leftStr = topStr;

        //when
        System.setOut(new PrintStream(stdoutDisplay));

        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        lcs.display();

        //then
        String expected = expectedDisplay("\\expectedDisplayWhenLongStrings.txt");
        String actual = stdoutDisplay.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyDisplay_WhenOneCharStrings() throws IOException {
        String topStr = "a";
        String leftStr = "a";

        //when
        System.setOut(new PrintStream(stdoutDisplay));

        LongestCommonSubsequence lcs = new LongestCommonSubsequence(topStr, leftStr);
        lcs.display();

        //then
        String expected = expectedDisplay("\\expectedDisplayWhenOneCharStrings.txt");
        String actual = stdoutDisplay.toString();
        Assert.assertEquals(expected, actual);
    }

    private String prepareLongString() {
        String s = "";
        for (int i = 1; i < 123; i++) {
            s += 'A';
        }
        return s;
    }

    private String expectedDisplay(String fileName) throws IOException {
        String pathToRootDir = new File("src\\test\\testFiles").getAbsolutePath();
        File file = new File(pathToRootDir + fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String expected = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                expected += line;
                expected += "\n";
            }
            return expected;
        } catch (IOException e) {
            throw new IOException("Cannot read from expectedDisplay file!");
        }
    }

}
