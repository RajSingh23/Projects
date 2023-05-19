#CSCI 1913, Fall 2021, Lab 2
#Name: Raj Singh
import math

def count(words):
    """
    Establishes the dictionary by passing the strings in as keys and the word count as values
    :param words: list of strings
    :return: dictionary
    """
    word_dict = {}

    for word in words:
        if word_dict.get(word) == None:
            word_dict[word] = 1
        else:
            word_dict[word] += 1

    return word_dict

def word_count_distance(count1, count2):
    """
    Passes the two dictionaries into the normalization function then calculates the sum and finally the distance score
    :param count1: dictionary
    :param count2: dictionary
    :return: float
    """
    count_sum = 0
    dict1 = dict.copy(count1)
    dict2 = dict.copy(count2)

    norm_dict1 = normalization(dict1)
    norm_dict2 = normalization(dict2)

    for word in norm_dict1:
        count_sum = norm_dict1.get(word, 0) * norm_dict2.get(word, 0) + count_sum

    distance = 2 - 2 * count_sum

    return distance

def normalization(dict):
    """
    Uses the normalization equation to find size and then changes the original dictionary values to the normalized ones
    :param dict: dictionary
    :return: dictionary
    """
    determinant = 0

    for i in dict:
        determinant = math.pow((dict[i]), 2) + determinant

    size = math.sqrt(determinant)

    for j in dict:
        dict[j] = dict[j] / size

    return dict

def most_common_word(counts):
    common_dict = dict.copy(counts)
    max_count = 0
    max_word = ''
    for word in common_dict:
        if common_dict[word] > max_count:
            max_count = common_dict[word]
            max_word = word
        elif common_dict[word] == max_count:
            max_word = max_word + word

    return max_word, max_count

if __name__ == "__main__":
    list1 = ['a', 'a', 'a', 'a', 'a', 'a', 'a', 'b', 'b', 'b', 'b', 'b', 'c', 'c', 'c', 'c', 'c', 'e']
    list2 = ['a', 'a', 'a', 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'd', 'd', 'd', 'q', 'q', 'q', 'w', 's']
    print(count(list1))
    print(count(list2))
    print(normalization(count(list1)))
    print(normalization(count(list2)))
    print(word_count_distance(count(list1), count(list2)))
    print(most_common_word(count(list1)))
