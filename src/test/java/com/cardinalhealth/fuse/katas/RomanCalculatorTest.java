package com.cardinalhealth.fuse.katas;

import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RomanCalculatorTest {
    private RomanCalculator subject;

    @Before
    public void setup() throws Exception {
        subject = new RomanCalculator();
    }

    @Test
    public void iExist() throws Exception {
        assertNotNull(subject);
    }

    @Test
    public void theBasicsOfSteppingUp() throws Exception {
        assertRomanCalculatorAddition("I", "I", "II");
        assertRomanCalculatorAddition("I", "II", "III");
        assertRomanCalculatorAddition("II", "III", "V");
        assertRomanCalculatorAddition("II", "V", "VII");
        assertRomanCalculatorAddition("III", "III", "VI");
        assertRomanCalculatorAddition("V", "V", "X");
        assertRomanCalculatorAddition("X", "X", "XX");
        assertRomanCalculatorAddition("XV", "X", "XXV");
        assertRomanCalculatorAddition("XX", "XXX", "L");
        assertRomanCalculatorAddition("L", "L", "C");
        assertRomanCalculatorAddition("CC", "CCC", "D");
        assertRomanCalculatorAddition("D", "D", "M");
    }

    @Test
    public void lesserBeforeBiggerEntered() throws Exception {
        assertRomanCalculatorAddition("IV", "IV", "VIII");
        assertRomanCalculatorAddition("XL", "X", "L");
        assertRomanCalculatorAddition("XXIV", "I", "XXV");
        assertRomanCalculatorAddition("XLIV", "VI", "L");
        assertRomanCalculatorAddition("XC", "CX", "CC");
        assertRomanCalculatorAddition("C", "CD", "D");
        assertRomanCalculatorAddition("DCCCCXC", "X", "M");
    }

    @Test
    public void lesserBeforeBiggerReturned() throws Exception {
        assertRomanCalculatorAddition("V", "IV", "IX");
        assertRomanCalculatorAddition("XX", "XX", "XL");
        assertRomanCalculatorAddition("XXV", "XXIV", "XLIX");
        assertRomanCalculatorAddition("XX", "IX", "XXIX");
        assertRomanCalculatorAddition("XI", "III", "XIV");
        assertRomanCalculatorAddition("CD", "LXXXXIV", "CDXCIV");
    }

    @Test
    public void theWholeKitchenSink() throws Exception {
        URL resource = getClass().getClassLoader().getResource("RomanNumerals-OneThousandPermutations.csv");
        Path path = Paths.get(resource.toURI());

        Files.lines(path, StandardCharsets.UTF_8).forEach(record -> {
            String[] romanNumbersToTest = record.split(",");
            assertRomanCalculatorAddition(romanNumbersToTest[0], romanNumbersToTest[1], romanNumbersToTest[2]);
        });
    }

    private void assertRomanCalculatorAddition(String firstRomanNumber, String secondRomanNumber, String expectedRomanNumberResult) {
        subject.enter(firstRomanNumber);
        subject.enter(secondRomanNumber);
        String message = String.format("Test of %1$s + %2$s", firstRomanNumber, secondRomanNumber);
        assertEquals(message, expectedRomanNumberResult, subject.add());
    }
}