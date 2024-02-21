package classes;

import processing.core.PApplet;
import processing.core.PVector;

public class Hangman {
    private String secretWord;
    private char[] guessedLetters;

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

    public boolean guess(char letter){
        char lowerCase = Character.toLowerCase(letter);
        
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
        }
    }

    public boolean isAlive(){
        if (lives > 0){
            return true;
        }else {
            return false;
        }
    }
}
