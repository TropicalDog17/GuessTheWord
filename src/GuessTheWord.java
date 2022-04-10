import java.util.Scanner;
import java.util.HashSet;
import java.util.Random;
import java.lang.StringBuilder;
public class GuessTheWord{
    public static class Game{
        private String word;
        private StringBuilder hiddenWord;
        private int maximumAttempt;
        private HashSet<String> wrongLetterList = new HashSet<String>();
        Game(int maximumAttempt){
            this.hiddenWord = new StringBuilder();
            this.maximumAttempt = maximumAttempt;
        }
        public void setRandomWord(String[] wordBank){
            Random rand = new Random();
            int wordIndex = rand.nextInt(wordBank.length);
            word = wordBank[wordIndex];
        }
        public String getRandomWord(){
            return this.word;
        }
        public void initializingHiddenWord(String word){
            for(int i = 0; i < word.length(); i++){
                hiddenWord.append("_");
            }
        }
        public String getHiddenWord(){
            return hiddenWord.toString();
        }
        public void handleUserInput(String word, char userInput){
            boolean letterExist = false;
            for(int i = 0; i < word.length(); i++){
                if(Character.compare(word.charAt(i), userInput) == 0){
                    hiddenWord.setCharAt(i, userInput);
                    letterExist = true;
                }
            }
            if(!letterExist){
                wrongLetterList.add(Character.toString(userInput));
            }
            System.out.println("You have guessed ("+ wrongLetterList.size() +") wrong letters: " + wrongLetterList.toString());
        }
        public void handleSuccess(){
            System.out.println("The correct word is " + this.word + ". You win!");
        }
        public void handleLose(){
            System.out.println("The correct word is " + this.word + ". You lose! Better luck next" +
                    " " +
                    "time");
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Game Game = new Game(10);
        String[] WORD_BANK = {"hello", "lol", "bitch"};

        //Initializing word
        Game.setRandomWord(WORD_BANK);
        Game.initializingHiddenWord(Game.getRandomWord());

        System.out.println("You are playing Guess The Word Game!");

        int numOfAttempt = 0;
        while(numOfAttempt < Game.maximumAttempt && !(Game.getHiddenWord().equals(Game.getRandomWord()))){
            System.out.println("You are guessing: " + Game.getHiddenWord().toString());
            System.out.print("Guess a letter: ");
            Game.handleUserInput(Game.getRandomWord(), sc.nextLine().charAt(0));
            numOfAttempt++;
        }
        if(Game.getHiddenWord().equals(Game.getRandomWord())){
            Game.handleSuccess();
        }
        else{
            if(numOfAttempt == Game.maximumAttempt){
                Game.handleLose();
            }
        }
    }
}