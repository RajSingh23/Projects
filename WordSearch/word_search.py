#CSCI 1913 Project 1
#Name: Raj Singh

import random

def get_size(grid):
    """
    Returns the horizontal width of the grid and the vertical length
    :param grid: List of lists of strings (crossword)
    :return: integer values of width and length of the grid
    """
    width = len(grid[0])
    length = len(grid)
    for i in range(len(grid)):
        if len(grid[i]) > width:
            width = len(grid[i])

    return width, length

def print_word_grid(grid):
    """
    Prints each list that makes up grid as a string rather than a list
    :param grid: List of lists of strings (crossword)
    :return: doesn't return but prints the lists as strings one by one
    """
    str1 = ''
    length = 0

    for i in range(len(grid)):
        for j in range(len(grid[i])):
            str1 += grid[i][j]
            length = length + 1
            if length == len(grid[i]) and i != len(grid) - 1:
                str1 += "\n"
                length = 0

    print(str1)

def copy_word_grid(grid):
    """
    Uses slicing to create a copy of the grid
    :param grid: List of lists of strings (crossword)
    :return: An exact copy of the grid passed in
    """
    copy_grid = []

    copy_grid = [x[:] for x in grid]

    return copy_grid

def extract(grid, position, direction, max_len):
    """
    Extracts a string from a starting position on the grid and going a certain distance in a direction
    :param grid: List of lists of strings (crossword)
    :param position: Index of the starting point on the grid
    :param direction: Tuple that represents the direction on the grid
    :param max_len: integer value that is the maximum movement
    :return: String that follows the three parameters
    """
    i = 0
    j = 0

    str2 = ''

    while j < max_len and 0 <= position[1] + (direction[1] * i) < len(grid) and position[0] + (direction[0] * i) < len(grid[i]):
        str2 = str2 + (grid[position[1] + (direction[1] * i)][position[0] + (direction[0] * i)])
        i += 1
        j += 1

    return str2


def show_solution(grid, word, solution):
    """
    Shows the solution on the crossword by capitalizing it
    :param grid: List of lists of strings (crossword)
    :param word: the target string that we look for on the grid
    :param solution: returns False if the solution isn't on the grid otherwise it's two tuples of position and direction
    :return: prints out a copy of the grid with the solution capitalized
    """
    if not solution:
        print(word + " is not found in this word search")
        return
    else:
        new_grid = copy_word_grid(grid)

        tuple1, tuple2 = solution

        y2 = tuple2[1]
        x2 = tuple2[0]
        y = tuple1[1]
        x = tuple1[0]

        i = 0

        while 0 <= y + (y2 * i) < len(grid) and x + (x2 * i) < len(grid[i]) and i < len(word):
            new_grid[y + (y2 * i)][x + (x2 * i)] = new_grid[y + (y2 * i)][x + (x2 * i)].upper()
            i += 1

        print(word.upper() + " can be found as below")
        print_word_grid(new_grid)

def find(grid, word):
    """
    Finds a word in a given crossword and returns the position and direction of the word
    :param grid: List of list of strings (crossword)
    :param word: the target string that we are searching for in the grid
    :return: a tuple of two tuples
    """
    word_list = list(word)
    max_length = len(word_list)
    initial_list = []

    for i in range(len(grid)):
        for j in range(len(grid[i])):
            if grid[i][j] == word_list[0]:
                initial_list.append((j, i))

    for initial in initial_list:
        if extract(grid, initial, (1, 0), max_length) == word:
            return initial, (1, 0)
        elif extract(grid, initial, (0, 1), max_length) == word:
            return initial, (0, 1)
        elif extract(grid, initial, (1, 1), max_length) == word:
            return initial, (1, 1)
        elif extract(grid, initial, (1, -1), max_length) == word:
            return initial, (1, -1)

    return False

def find_all(grid, word_lst):
    """
    Similar to the find function however in this one we have a list of words that we find in grid
    :param grid: List of list of strings (crossword)
    :param word_lst: List of strings that are the words we are searching for
    :return: A dictionary containing tuples of two tuples indicating initial position and direction of words in grid
    """
    word_dict = {}

    for word in word_lst:
        word_list = list(word)
        max_length = len(word_list)
        initial_list = []
        word_dict[word] = False

        for i in range(len(grid)):
            for j in range(len(grid[i])):
                if max_length > len(grid[i]):
                    word_dict[word] = "too large"
                if grid[i][j] == word_list[0]:
                    initial_list.append((j, i))

        if word_dict[word] != "too large":
            for initial in initial_list:
                if extract(grid, initial, (1, 0), max_length) == word:
                    word_dict[word] = initial, (1, 0)
                elif extract(grid, initial, (0, 1), max_length) == word:
                    word_dict[word] = initial, (0, 1)
                elif extract(grid, initial, (1, 1), max_length) == word:
                    word_dict[word] = initial, (1, 1)
                elif extract(grid, initial, (1, -1), max_length) == word:
                    word_dict[word] = initial, (1, -1)
        elif word_dict[word] == "too large":
            word_dict[word] = False

    return word_dict

def generate(width, height, words):
    """
    Generates a crossword given dimensions and a list of words
    :param width: Integer value that indicates the size of each list within the grid we are returning
    :param height: Integer value that indicates the amount of lists in grid
    :param words: List of words that we want to include in the crossword
    :return: A list of lists of strings that includes the elements of list words that we could fit
    """
    RIGHT = (1, 0)
    DOWN = (0, 1)
    RIGHT_DOWN = (1, 1)
    RIGHT_UP = (1, -1)
    DIRECTIONS = [RIGHT, DOWN, RIGHT_DOWN, RIGHT_UP]

    grid = []
    grid = generate_empty(width, height, grid)

    alphabet = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
                "v", "w", "x", "y", "z"]

    for word in words:
        grid = generate_helper2(width, height, grid, word, DIRECTIONS)

    for i in range(len(grid)):
        for j in range(len(grid[i])):
            if grid[i][j] == " ":
                grid[i][j] = random.choice(alphabet)

    return grid, words


def generate_empty(width, height, grid):
    """
    Generates an empty crossword which we will later fill
    :param width: Integer value that indicates the size of each list within the grid we are returning
    :param height: Integer value that indicates the amount of lists in grid
    :param grid: An empty grid that we will fill with strings
    :return: a list of lists of strings filled with " " at each element
    """
    for i in range(width):
        grid.append(" ")

    full_grid = [grid]

    for j in range(height - 1):
        grid_new = [x[:] for x in grid]
        full_grid.append(grid_new)

    return full_grid


def verifier(x, y, x2, y2, word, width, height):
    """
    Verifies that the random solution stays within the bounds of the crossword
    :param x: integer that represents the horizontal index of the initial position
    :param y: integer that represents the vertical index of the initial position
    :param x2: integer that represents the horizontal index of the direction
    :param y2: integer that represents the vertical index of the direction
    :param word: String that represents the word we are trying to put into the grid
    :param width: Integer value that indicates the length of each list in grid
    :param height: Integer value that indicates the amount of lists in grid
    :return: Boolean, True if the values work, False if the values are outside the bounds
    """
    j = 0
    while (0 <= y + (y2 * j) < height and 0 <= x + (x2 * j) < width and j <= len(word)):
        j += 1
        if j == len(word):
            return True

    return False

def verifier2(x, y, x2, y2, word, grid):
    """
    Verifies that each location is available for the word
    :param x: integer that represents the horizontal index of the initial position
    :param y: integer that represents the vertical index of the initial position
    :param x2: integer that represents the horizontal index of the direction
    :param y2: integer that represents the vertical index of the direction
    :param word: word that we are trying to fit into grid
    :param grid: list of list of strings (crossword)
    :return: Boolean, true if the spaces are available, false if any of the spaces are not available
    """
    word_list = list(word)
    cnt = 0
    for i in range(len(word_list)):
        if grid[y + (y2 * i)][x + (x2 * i)] == " " or grid[y + (y2 * i)][x + (x2 * i)] == word_list[i]:
            cnt += 1

    if cnt == len(word):
        return True
    else:
        return False

def generate_helper2(width, height, grid, word, DIRECTIONS):
    """
    This helper function attempts to put each word into the grid
    :param width: integer that represents the size of each list in grid
    :param height: integer that represents the amount of lists in grid
    :param grid: A list of lists of strings
    :param word: String, it is the word we are trying to put into grid
    :param DIRECTIONS: List of tuples that each represent a direction
    :return: A list of list of strings that includes word as long as word could fit
    """
    word_list = list(word)
    x = random.randint(0, width - 1)
    y = random.randint(0, height - 1)
    random_direction = random.choice(DIRECTIONS)

    if len(word) > width:
        random_direction = (0, 1)
    elif len(word) > height:
        random_direction = (1, 0)

    x2 = random_direction[0]
    y2 = random_direction[1]
    cnt = 0

    while verifier(x, y, x2, y2, word, width, height) == False or verifier2(x, y, x2, y2, word, grid) == False:
        cnt += 1

        if cnt == 100:
            return grid

        x = random.randint(0, width - 1)
        y = random.randint(0, height - 1)
        random_direction = random.choice(DIRECTIONS)

        if len(word) > width:
            random_direction = (0, 1)
        elif len(word) > height:
            random_direction = (1, 0)

        x2 = random_direction[0]
        y2 = random_direction[1]

    for i in range(len(word)):
        grid[y + (y2 * i)][x + (x2 * i)] = word_list[i]

    return grid


if __name__ == "__main__":
    user_grid = [['c', 'a', 't', 'o', 's'],
            ['m', 'r', 'd', 'o', 'g'],
            ['q', 't', 'o', 'w', 'n']]

    user_grid2 = [['p', 'c', 'n', 'd', 't', 'h', 'g'],
                  ['w', 'a', 'x', 'o', 'a', 'x', 'f'],
                  ['o', 't', 'w', 'g', 'd', 'r', 'k'],
                  ['l', 'j', 'p', 'i', 'b', 'e', 't'],
                  ['f', 'v', 'l', 't', 'o', 'w', 'n']]

    RIGHT = (1, 0)
    DOWN = (0, 1)
    RIGHT_DOWN = (1, 1)
    RIGHT_UP = (1, -1)
    DIRECTIONS = (RIGHT, DOWN, RIGHT_DOWN, RIGHT_UP)

    print(get_size(user_grid2))
    print_word_grid(user_grid)
    print(copy_word_grid(user_grid))
    print(extract(user_grid2, (1, 3), RIGHT_UP, 3))
    show_solution(user_grid2, "cat", ((1, 0), (0, 1)))
    print(find(user_grid2, "cat"))
    print(find_all(user_grid, ["tow", "cat"]))
    print(generate(5, 10, ['cat', 'dog', 'art', 'town', 'den', 'wolf', 'part', 'mansion']))
