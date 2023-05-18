/*
	Provided code by Dan Shervheim
	UMN 1103 TA Fall 2018

	Additions made by student: <sing0780> Rajan Singh
	AskQuestion()
	<ADD ANY OTHER METHODS YOU WRITE HERE>
*/

import java.util.*;
import java.util.Scanner;

public class RelationalQuestion extends Question
{
	RelationalQuestion(Animal[] animals, Animal answer)
	{
		super(animals, answer);
	}

	public boolean AskQuestion()
	{
		// offer the player choices
		System.out.println("What do you want to know?");
		System.out.println("\t1. Is it heavier than another animal?");
		System.out.println("\t2. Is it taller than another animal?");
		System.out.println("\t3. Is it longer than another animal?");
		System.out.println("\t4. Is it faster than another animal?");
		System.out.println("\t5. Is it heavier than <x> lbs");
		System.out.println("\t6. Is it taller than <x> feet");
		System.out.println("\t7. Is it longer than <x> feet");
		System.out.println("\t8. Is it faster than <x> mph?");
		System.out.println("\t9. Go back");

		Scanner in = new Scanner(System.in);

		while (!in.hasNextInt()) {
			System.out.println("Please enter an integer between 1 and 9");
			in.next();
		}
		int input = in.nextInt();

		while(input < 1 || input > 9) {
			System.out.println("Please enter a value between 1 and 9");
			input = in.nextInt();
		}

		if (input == 9) {
			return false;
		}

		if (input > 4 && input < 9) {
			Scanner xvalue = new Scanner(System.in);
			System.out.println("Enter a number for x: ");

			while (!xvalue.hasNextInt()) {
				System.out.println("Please enter an integer value for x: ");
				xvalue.next();
			}
			int x = xvalue.nextInt();

			while (x < 0) {
				System.out.println("Please enter a positive value: ");
				x = xvalue.nextInt();
			}


			if (input == 5) {
				if (answer.getWeight() > x) {
					System.out.println("Yes, it is heavier than " + x + " lbs");
				}
				else {
					System.out.println("No, it is not heavier than " + x + " lbs");
				}
				return true;
			}

			if (input == 6) {
				if (answer.getHeight() > x) {
					System.out.println("Yes, it is taller than " + x + " feet");
				}
				else {
					System.out.println("No, it is not taller than " + x + " feet");
				}
				return true;
			}

			if (input == 7) {
				if (answer.getLength() > x) {
					System.out.println("Yes, it is longer than " + x + " feet");
				}
				else {
					System.out.println("No, it is not longer than " + x + " feet");
				}
				return true;
			}

			if (input == 8) {
				if (answer.getSpeed() > x) {
					System.out.println("Yes, it is faster than " + x + " mph");
				}
				else {
					System.out.println("No, it is not faster than " + x + " mph");
				}
				return true;
			}
		}



		if (input > 0 && input < 5) {
			Scanner nameGuess = new Scanner(System.in);
			System.out.println("Enter the name of the animal: ");
			String animalDatabase = nameGuess.nextLine();

			int animalIndex = getIndexByName(animalDatabase);

			if (animalIndex < 0) {
				System.out.println("Sorry! This animal is not in our database.");
				return false;
			}

			if (input == 1) {
				if (answer.getWeight() > animals[animalIndex].getWeight()) {
					System.out.println("Yes! This animal weighs more than a " + animalDatabase);
				}
				else {
					System.out.println("No! This animal does not weigh more than a " + animalDatabase);
				}
				return true;
			}

			if (input == 2) {
				if (answer.getHeight() > animals[animalIndex].getHeight()) {
					System.out.println("Yes! This animal is taller than a " + animalDatabase);
				}
				else {
					System.out.println("No! This animal is not taller than a " + animalDatabase);
				}
				return true;
			}

			if (input == 3) {
				if (answer.getLength() > animals[animalIndex].getLength()) {
					System.out.println("Yes! This animal is longer than a " + animalDatabase);
				}
				else {
					System.out.println("No! This animal is not longer than a " + animalDatabase);
				}
				return true;
			}

			if (input == 4) {
				if (answer.getSpeed() > animals[animalIndex].getSpeed()) {
					System.out.println("Yes! This animal is faster than a " + animalDatabase);
				}
				else {
					System.out.println("No! This animal is not faster than a " + animalDatabase);
				}
				return true;
			}

		}
	return true;
	}
}
