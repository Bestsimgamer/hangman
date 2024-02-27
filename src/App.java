import processing.core.*;

import classes.*;

public class App  extends PApplet{

    Hangman game;
    public static void main(String[] args) {
        String[] processingArgs = {"MySketch"};
		App mySketch = new App();
		PApplet.runSketch(processingArgs, mySketch);
    }

    public void settings(){
        size(600, 400);
    }

    public void setup(){
        game = new Hangman("yayyyy");
    }

    public void draw(){
        background(0);
        game.drawGuessedLetters(this);
        game.drawGallow(this, new PVector(500,100));
    }

    public void keyPressed(){
        if (key != CODED){
            try {
                game.guess(key);
            } catch (InvalidCharacterException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
