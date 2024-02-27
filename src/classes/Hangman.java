package classes;

import processing.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Hangman {
    private final String secretWord;
    private char[] guessedLetters;

    private ArrayList<Character> allGuesses = new ArrayList<Character>() {
        
    };

    int lives = 6;

    public Hangman(String word){
        secretWord = word.toLowerCase();
        guessedLetters = new char[secretWord.length()];

        //init partial word, and set all
        //letters to '_'
        guessedLetters = new char[word.length()];
        for (int i = 0; i < guessedLetters.length; i++){
            guessedLetters[i] = '_';
        }
    }

    public char[] getGuessedLetters(){
        return guessedLetters;
    }

    //handles exceptions within hangman locally
    //useful for tests;
    public boolean tryGuess(char letter){
        boolean letterInWord = false;
        try {
            letterInWord = guess(letter);
        } catch (InvalidCharacterException e){
            //do nothing only used for tests
        }
        return letterInWord;
    }

    public boolean guess(char letter) throws InvalidCharacterException{
        char lowerCase = Character.toLowerCase(letter);

        Pattern regex = Pattern.compile("[a-zæøå]");
        Matcher isLetter = regex.matcher(""+lowerCase);

        if(!isLetter.find()){
            throw new InvalidCharacterException("Must be a letter");
        }

        for (char c : allGuesses){
            if (lowerCase == c){
                throw new InvalidCharacterException("must be a new letter");
            }
        }
        allGuesses.add(lowerCase);
        
        boolean letterInSecretWord = false;
        for (int i = 0; i < secretWord.length(); i++){
            if (secretWord.charAt(i) == lowerCase){
              guessedLetters[i] = lowerCase;
              letterInSecretWord = true;
            }
        }
        
        if (!letterInSecretWord) lives--;
        return letterInSecretWord;
    }

    public void drawGuessedLetters(PApplet p){
        //draw word on screen
        p.fill(255);
        p.textSize(32);
        for (int i = 0; i < guessedLetters.length; i++){
            //draw guessed letter in center of the screen
            p.text(guessedLetters[i], p.width/2+20*i-
                guessedLetters.length*20/2, p.height/2);
        }
    }

    public void drawGallow(PApplet p, PVector location){
        p.fill(0);
        p.stroke(250);
        if (lives < 6) p.circle(location.x,location.y, 20);
        if (lives < 5) p.line(location.x, location.y+10, location.x, location.y+50);
        if (lives < 4) p.line(location.x, location.y+20, location.x-20, location.y);
        if (lives < 3) p.line(location.x, location.y+20, location.x+20, location.y);
        if (lives < 2) p.line(location.x, location.y+50, location.x-20, location.y+70);
        if (lives < 1) p.line(location.x, location.y+50, location.x+20, location.y+70);
        //dead
        if (!isAlive()) {
            //code for dead eyes
            p.line(location.x-2+3,location.y-2, location.x+2+3,location.y+2);
            p.line(location.x+2+3,location.y-2, location.x-2+3,location.y+2);

            p.line(location.x-2-3,location.y-2, location.x+2-3,location.y+2);
            p.line(location.x+2-3,location.y-2, location.x-2-3,location.y+2);
            p.line(location.x, location.y+50, location.x, location.y+70);
            p.circle(location.x-3, location.y+58, 5);
            p.circle(location.x+3, location.y+58, 5);
        }
    }

    public boolean isAlive(){
        if (lives > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean isWon(){
        for (int i = 0; i < secretWord.length(); i++){
            if (secretWord.charAt(i) != getGuessedLetters()[i]){
                return false;
            }
        }

        return true;
    }
}
