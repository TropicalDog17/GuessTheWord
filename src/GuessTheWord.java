/*
    The rules are simple, the computer randomly picks a movie title, and shows you how many
    letters  it's made up of. Your goal is to try to figure out the movie by guessing one letter
    at a time.

    If a letter is indeed in the title the computer will reveal its correct position in the word, if
not, you lose a point. If you lose 10 points, game over!

    BUT the more correct letters you guess the more obvious the movie becomes and at a certain point
you should be able to figure it out.

The program will randomly pick a movie title from a text file that contains a large list of movies.
Once the computer picks a random title, it will display underscores "_" in place of the real
letters, thereby only giving away the number of letters in the movie title.

Steps:
    1. Initialize a list of word to guess
    2. Choose a random word from the hidden list, show the hidden word with each character is
    replaced with underscore.
    3. Receive user input, user input one character at a time
            + If correct, show the word with the correct character in the corresponding position
            + If incorrect, show the hidden word
    4. After each input, update the state of user(number of wrong , a list of all the
    wrong letters)
    5. When user have guessed all the character before 10 attempts, congrat and end the game.
    Otherwise, display lost message.
 */
import java.util.Scanner;
import java.util.Random;
import java.util.HashSet;
public class GuessTheWord {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] wordBank = {"handle", "meal", "fly", "chicken", "cat", "band", "intentional",
                "butter"};

        //Choose a random word from the word bank.
        Random rand = new Random();
        int wordIndex = rand.nextInt(wordBank.length);
        String word = wordBank[wordIndex];
        StringBuilder hiddenWord = new StringBuilder("");
        int wordLength = word.length();
        //Initialize the hidden word
        for(int i = 0; i < word.length(); i++){
            hiddenWord.append("_");
        }
        HashSet wrongCharacterList = new HashSet<String>();
        int attemptCount = 0;
        int MAXIMUM_ATTEMPT = 10;
        while(attemptCount <  MAXIMUM_ATTEMPT && !hiddenWord.toString().equals(word)){
            System.out.println("You are guessing: " + hiddenWord + "(" + wordLength + " " +
                    "characters)");
            System.out.println("You have guess" + "(" + wrongCharacterList.size() + ") wrong " +
                    "letters " + wrongCharacterList.toString());
            System.out.print("Guess a letter: ");

            //Read user input
            String guessLetter = sc.next();
            if(guessLetter.length() != 1){
                System.out.println("Invalid input!");
                continue;
            }

            //Check if letter input by user match any character of the word
            boolean characterExist = false;
            for(int i = 0; i < wordLength; i++){
                if(Character.compare(guessLetter.charAt(0), word.charAt(i)) == 0){
                    hiddenWord.setCharAt(i, guessLetter.charAt(0));
                    characterExist = true;
                }
            }
            if(characterExist == true){
                characterExist = false;
            }
            else{
                wrongCharacterList.add(guessLetter);
            }
            attemptCount++;
        }
        if(attemptCount == 10 && hiddenWord.equals(word) != true){
            System.out.println("You lose! Better luck next time.");
        }
        else{
            System.out.println("You win. Congrats!");
        }
        System.out.println("The correct word is: "+ word);
    }

}
