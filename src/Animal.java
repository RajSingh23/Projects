/*
	Provided code by Dan Shervheim
	UMN 1103 TA Fall 2018

	This file does not require any modification by the students.
*/

import java.io.File;
import java.util.*;

public class Animal
{
	// attributes
	private String name;
	private String [] has;  // arrays to store all the things this
	private String [] can;	// animal has, can, is, and eats
	private String [] is;
	private String [] diet;
	private double weight;	// in lbs
	private double length;	// in feet
	private double height;	// in feet
	private double speed;	// in mph

	// constructor
	Animal(File file) {

		String filename = file.getName();

		// set this animals name (sans ".txt");
		this.name = filename.substring(0, filename.length() - 4);

		// if we succesfully parsed the file and setup the arrays,
		// then fill the arrays.
		if (setupAttributeArrays(file))
		{
			int hasCount = 0;
			int canCount = 0;
			int isCount = 0;
			int dietCount = 0;
			Scanner sc;

			// scan through again to actually set the attributes
			try
			{
				sc = new Scanner(file);
				while (sc.hasNextLine())
				{
					String line = sc.nextLine();

					if (line.startsWith("has:"))
					{
						this.has[hasCount] = line.substring(4);
						hasCount += 1;
					}
					else if (line.startsWith("can:"))
					{
						this.can[canCount] = line.substring(4);
						canCount += 1;
					}
					else if (line.startsWith("is:"))
					{
						this.is[isCount] = line.substring(3);
						isCount += 1;
					}
					else if (line.startsWith("eats:"))
					{
						this.diet[dietCount] = line.substring(5);
						dietCount += 1;
					}
					else if (line.startsWith("weight:"))
					{
						this.weight = Double.valueOf(line.substring(7));
					}
					else if (line.startsWith("height:"))
					{
						this.height = Double.valueOf(line.substring(7));
					}
					else if (line.startsWith("length:"))
					{
						this.length = Double.valueOf(line.substring(7));
					}
					else if (line.startsWith("speed:"))
					{
						this.speed = Double.valueOf(line.substring(6));
					}
					else
					{
						System.out.println("\nPossible error in file: " + filename + " in line: \"" + line + "\".\n");
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Error. Could not open file.");
			}
		}
	}

	// parses the array to find the required lengths for the arrays
	// and returns whether it was successful or not
	private boolean setupAttributeArrays(File file)
	{
		int hasMax = 0;
		int canMax = 0;
		int isMax = 0;
		int dietMax = 0;

		Scanner sc;

		// scan through to find number of attributes
		try
		{
			sc = new Scanner(file);
			while (sc.hasNextLine())
			{
				String line = sc.nextLine();

				if (line.startsWith("has:"))
				{
					hasMax += 1;
				}
				else if (line.startsWith("can:"))
				{
					canMax += 1;
				}
				else if (line.startsWith("is:"))
				{
					isMax += 1;
				}
				else if (line.startsWith("eats:"))
				{
					dietMax += 1;
				}
			}

			// declare the arrays
			has = new String[hasMax];
			can = new String[canMax];
			is = new String[isMax];
			diet = new String[dietMax];
		}
		catch (Exception e)
		{
			return false;
		}

		return true;
	}

	// returns a string representation of this animal
	public String toString()
	{
		String str = "";

		// append name
		str = str + Ansi.BLACK + Ansi.BACKGROUND_WHITE + name + Ansi.RESET;

		// append "has"
		str = str + "\n" + Ansi.ITALIC + "has: " + Ansi.RESET + has[0];
		for (int i = 1; i < has.length; i++)
		{
			str += (", " + has[i]);
		}

		// append "can"
		str = str + "\n" + Ansi.ITALIC + "can: " + Ansi.RESET + can[0];
		for (int i = 1; i < can.length; i++)
		{
			str += (", " + can[i]);
		}

		// append "is"
		str = str + "\n" + Ansi.ITALIC + "is: " + Ansi.RESET + is[0];
		for (int i = 1; i < is.length; i++)
		{
			str += (", " + is[i]);
		}

		// append "diet"
		str = str + "\n" + Ansi.ITALIC + "eats: " + Ansi.RESET + diet[0];
		for (int i = 1; i < diet.length; i++)
		{
			str += (", " + diet[i]);
		}

		// append stats
		str = str + "\n" + Ansi.ITALIC + "weight: " + Ansi.RESET + weight + " lbs";
		str = str + "\n" + Ansi.ITALIC + "height: " + Ansi.RESET + height + " ft";
		str = str + "\n" + Ansi.ITALIC + "length: " + Ansi.RESET + length + " ft";
		str = str + "\n" + Ansi.ITALIC + "speed: " + Ansi.RESET + speed + " mph";

		// return str
		return str;
	}

	// returns the animals name
	public String getName()
	{
		return this.name;
	}

	// returns whether this animal has "x"
	public boolean hasX(String x)
	{
		return Arrays.stream(has).anyMatch(x::equals);
	}

	// returns whether this animal can "x"
	public boolean canX(String x)
	{
		return Arrays.stream(can).anyMatch(x::equals);
	}

	// returns wether this animal is "x"
	public boolean isX(String x)
	{
		return Arrays.stream(is).anyMatch(x::equals);
	}

	// returns whether this animal eats "x"
	public boolean eatsX(String x)
	{
		return Arrays.stream(diet).anyMatch(x::equals);
	}

	// returns this animals weight
	public double getWeight()
	{
		return this.weight;
	}

	// returns this animals height
	public double getHeight()
	{
		return this.height;
	}

	// returns this animals length
	public double getLength()
	{
		return this.length;
	}

	// returns the animals speed
	public double getSpeed()
	{
		return this.speed;
	}

}  // end of Animal class
