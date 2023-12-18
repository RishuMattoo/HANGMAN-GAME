import java.util.Random;
import java.util.Scanner;

public class HangmanGame {

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel", "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer", "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat", "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose", "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", "python", "rabbit", "ram", "rat", "raven", "rhino", "salmon", "seal", "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan", "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf", "wombat", "zebra"};
    public static String[] gallows = {
            "+---+\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|   |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/    |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/ \\  |\n" +
                    "     |\n" +
                    " =========\n"};

    public static void main(String[] args) {
        String selectedWord = randomWord();
        char[] placeholders = new char[selectedWord.length()];
        boolean[] guessedLetters = new boolean[26];
        int incorrectGuesses = 0;

        Scanner scanner = new Scanner(System.in);

        while (incorrectGuesses < 6) {
            printGallows(incorrectGuesses);
            printPlaceholders(placeholders);
            printMissedGuesses(guessedLetters);

            System.out.print("Enter a letter: ");
            char guess = scanner.next().charAt(0);

            if (!Character.isLetter(guess)) {
                System.out.println("Please enter a valid letter.");
                continue;
            }

            guess = Character.toLowerCase(guess);

            if (guessedLetters[guess - 'a']) {
                System.out.println("You already guessed that letter. Try again.");
                continue;
            }

            guessedLetters[guess - 'a'] = true;

            if (checkGuess(selectedWord, guess, placeholders)) {
                updatePlaceholders(selectedWord, guess, placeholders);

                if (checkWin(placeholders)) {
                    printPlaceholders(placeholders);
                    System.out.println("Congratulations! You guessed the word: " + selectedWord);
                    break;
                }
            } else {
                incorrectGuesses++;
            }
        }

        if (incorrectGuesses == 6) {
            printGallows(6);
            System.out.println("Sorry, you ran out of guesses. The word was: " + selectedWord);
        }

        scanner.close();
    }

    public static String randomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    public static boolean checkGuess(String word, char guess, char[] placeholders) {
        boolean correctGuess = false;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                correctGuess = true;
            }
        }

        return correctGuess;
    }

    public static void updatePlaceholders(String word, char guess, char[] placeholders) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                placeholders[i] = guess;
            }
        }
    }

    public static void printPlaceholders(char[] placeholders) {
        for (char placeholder : placeholders) {
            if (placeholder == 0) {
                System.out.print("_ ");
            } else {
                System.out.print(placeholder + " ");
            }
        }
        System.out.println("\n");
    }

    public static void printGallows(int incorrectGuesses) {
        System.out.println(gallows[incorrectGuesses]);
    }

    public static void printMissedGuesses(boolean[] guessedLetters) {
        System.out.print("Missed guesses: ");
        for (int i = 0; i < guessedLetters.length; i++) {
            if (guessedLetters[i]) {
                System.out.print((char) ('a' + i) + " ");
            }
        }
        System.out.println("\n");
    }

    public static boolean checkWin(char[] placeholders) {
        for (char placeholder : placeholders) {
            if (placeholder == 0) {
                return false;
            }
        }
        return true;
    }
}
