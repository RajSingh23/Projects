/*
	Provided code by Dan Shervheim
	UMN 1103 TA Fall 2018

	Additions made by student: <sing0780> Rajan Singh
	main(String[] args)
	playLoop(Animal[] animals, Animal answer)
	<ADD ANY OTHER METHODS YOU WRITE HERE>
*/

import java.util.Random;
import java.io.File;
import java.util.*;

public class TwentyQuestions
{
	public static void main(String[] args)
	{

		File animalFolder = new File("../animals");
		File[] animalTxtFiles = animalFolder.listFiles();

		Animal[] animalList = new Animal[animalTxtFiles.length];

		for (int i = 0; i < animalList.length; i++) {
			animalList[i] = new Animal(animalTxtFiles[i]);
		}

		//The text files are now able to be accessed

		System.out.println();
		System.out.println("Hello and welcome to Twenty Questions!");
		System.out.println("We're thinking of an animal, and you have 20 questions to guess which animal it is!");
		System.out.println("Remember, only yes/no questions!");
		System.out.println();

		Random r = new Random();
		int randomIndex = r.nextInt(animalList.length);

		Animal answer;
		answer = animalList[randomIndex]; //There is now an answer

		playLoop(animalList, answer); // calling the method to start playing the game
}

		private static void playLoop(Animal[] animals, Animal answer) {

			int questionsLeft = 20;
			Scanner questionInput = new Scanner(System.in);

			while (questionsLeft > 0) {
				System.out.println("You have " + questionsLeft + " questions left");
				System.out.println();
				System.out.println("1. ask a relational question");
				System.out.println("2. ask an existential question");
				System.out.println("3. make a guess");
				System.out.println("4. lookup an animal in the database");
				System.out.println("5. quit");

				while (!questionInput.hasNextInt()) {
					System.out.println("Please enter an integer between 1 and 5");
					questionInput.next();
				} //verifies that the user enters an integer

				int questionChoice = questionInput.nextInt();

				while (questionChoice < 1 || questionChoice > 5) {
					System.out.println("Please enter a value between 1 and 5");
					questionChoice = questionInput.nextInt();
				} //verfiies that the user enters a value within bounds

				if (questionChoice == 1) {
					RelationalQuestion rq1 = new RelationalQuestion(animals, answer);
					if (rq1.AskQuestion() == true) {
						questionsLeft--; //decrementing the questions left if they got information
					}
				}

				if (questionChoice == 2) {
					ExistentialQuestion eq1 = new ExistentialQuestion(animals, answer);
					if (eq1.AskQuestion() == true) {
						questionsLeft--; //decrementing the questions left if they got information
					}
				}

				if (questionChoice == 3) {
					Scanner guess = new Scanner(System.in);
					System.out.println("Enter your guess: ");
					String usersGuess = guess.nextLine();
					if (answer.getName().equals(usersGuess)) {
						System.out.println("Congratulations! You got it right!");
						return;
					}
					else {
						System.out.println("Sorry, that's incorrect. Keep on going!");
					}
					questionsLeft--; //decrementing the questions left because they spent a question on a guess
				}

				if (questionChoice == 4) {
					Scanner info = new Scanner(System.in);
					System.out.println("Enter the name of an animal you want information on: ");
					String search = info.nextLine();
					int count = -1;
					for (int j = 0; j < animals.length; j++) {
						if (animals[j].getName().equals(search)) {
							count = j;
						}
					}
					if (count == -1) {
						System.out.println("This animal is not in our database"); //if count == -1, the user's string does not match the name of any file in the animals directory
					}
					else {
						System.out.println(animals[count]);
					}
				}

				if (questionChoice == 5) {
					System.out.println("Goodbye.");
					return; //quit command, so we instantly return
				}
			}
			System.out.println("You're out of questions! Make one last guess."); //the loop has ended so questionsLeft == 0, meaning  they get one last guess
			Scanner finalGuess = new Scanner(System.in);
			String lastGuess = finalGuess.nextLine();
			if (answer.getName().equals(lastGuess)) {
				System.out.println("Congratulations! You got it right!");
			}
			else {
				System.out.println("Sorry, that's incorrect. The right animal was a " + answer.getName()); //showing the user the right answer
			}
			return;
    }

}
