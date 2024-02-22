package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import classes.*;

public class HangmanTest {

    final String[] testWords = {"Kitten", "space", "fisk", "mål"};

    @Test
    public void charArraySet(){
        for (String word : testWords){
            final Hangman game = new Hangman(word);

            char[] charArray = game.getGuessedLetters();

            assertEquals(word.length(), charArray.length);
            
            for (char letter : charArray){
                assertSame(null, letter, '_');
            }
        }
    }

    @Test
    public void guessTrue(){
        final Hangman game = new Hangman("kitten");

        assertTrue(game.guess('k'));
    }

    @Test
    public void guessFalse(){
        final Hangman game = new Hangman("kitten");

        assertFalse(game.guess('z'));
    }

    @Test
    public void guessMultipel(){
        final Hangman game = new Hangman("kitten");

        game.guess('t');

        assertTrue(game.getGuessedLetters()[2] == 't');
        assertTrue(game.getGuessedLetters()[3] == 't');
    }

    @Test
    public void guessCaseInsensitiv(){
        final Hangman game = new Hangman("KITTEN");

        game.guess('n');
        try{
            assertTrue(game.getGuessedLetters()[5] == 'N');
        } catch (java.lang.AssertionError e){
            fail("should be case insensitive");
        }
    }

    @Test
    public void guessCaseInsensitiv2(){
        final Hangman game = new Hangman("kitten");

        game.guess('N');
        try{
            assertTrue(game.getGuessedLetters()[5] == 'n');
        } catch (java.lang.AssertionError e){
            fail("should be case insensitive");
        }
    }
}
