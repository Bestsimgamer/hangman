package classes;

import processing.core.PApplet;

public class Hangman {
    private String secretWord;
    private char[] guessedLetters;

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
          char tempChar = Character.toLowerCase(secretWord.charAt(i));
            if (tempChar == Character.toLowerCase(letter)){
              guessedLetters[i] = secretWord.charAt(i);
              letterInSecretWord = true;
            }
          }
        return letterInSecretWord;
    }

    public void drawGuessedLetters(PApplet p){
        //draw word on screen
        p.textSize(32);
        for (int i = 0; i < guessedLetters.length; i++){
            //draw guessed letter in center of the screen
            p.text(guessedLetters[i], p.width/2+20*i-
                guessedLetters.length*20/2, p.height/2);
        }
    }

    public boolean isAlive(){
        return true;
    }
}
