/*
	Provided code by Dan Shervheim
	UMN 1103 TA Fall 2018

	Additions made by student: <sing0780> Rajan Singh
	AskQuestion()
	<ADD ANY OTHER METHODS YOU WRITE HERE>
*/

import java.util.*;
import java.util.Scanner;

public class ExistentialQuestion extends Question
{
	ExistentialQuestion(Animal[] animals, Animal answer)
	{
		super(animals, answer);
	}

	public boolean AskQuestion()
	{
		// offer choices to player
		System.out.println("What do you want to know?");
		System.out.println("\t1. Does it have <x>?");
		System.out.println("\t2. Can it <x>?");
		System.out.println("\t3. Is it <x>?");
		System.out.println("\t4. Does it eat <x>?");
		System.out.println("\t5. Go back");

		Scanner in = new Scanner(System.in);

		while (!in.hasNextInt()) {
			System.out.println("Please enter an integer between 1 and 5");
			in.next();
		}
		int choice = in.nextInt();

		while (choice < 1 || choice > 5) {
			System.out.println("Please enter a value between 1 and 5");
			choice = in.nextInt();
		}

		if (choice == 5) {
			return false;
		}

		System.out.println("Enter a string for x: ");
		Scanner eqxvalue = new Scanner(System.in);

		String xchoice = eqxvalue.nextLine();


		if (choice == 1) {
			if (answer.hasX(xchoice)) {
				System.out.println("Yes! This animal does have " + xchoice);
			}
			else {
				System.out.println("No! This animal does not have " + xchoice);
			}
			return true;
		}

		if (choice == 2) {
			if (answer.canX(xchoice)) {
				System.out.println("Yes! This animal can " + xchoice);
			}
			else {
				System.out.println("No! This animal can not " + xchoice);
			}
			return true;
		}

		if (choice == 3) {
			if (answer.isX(xchoice)) {
				System.out.println("Yes! This animal is " + xchoice);
			}
			else {
				System.out.println("No! This animal is not " + xchoice);
			}
			return true;
		}

		if (choice == 4) {
			if (answer.eatsX(xchoice)) {
				System.out.println("Yes! This animal eats " + xchoice);
			}
			else {
				System.out.println("No! This animal does not eat " + xchoice);
			}
			return true;
		}
		return true;
	}
}
