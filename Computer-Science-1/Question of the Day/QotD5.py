# CS1210: QotD5
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# Specification: nearAnagram(S1, S2) takes two sequences, S1 and S2,
# and returns True if the two sequences are possible anagrams of each
# other.
#
# Recall an anagram is simply a permutation of the characters of one
# string to make another word, such as "servant" and "taverns" (here,
# we'll extend the notion from strings to sequences, including lists
# and tuples).
#
# A "near anagram" are two sequences that share the same length and
# are made of identical elements, but my differ in how many times each
# element occurs.
#
# Example:
#    >>> nearAnagram('taverns', 'servant')
#    True
#    >>> nearAnagram([1, 2, 3, 1], (2, 2, 1, 3))
#    True
#    >>> nearAnagram([1, 2, 3, 1], [3, 2, 1])
#    False
#
# Hint: use set operations. For credit, do not use assignment or
# conditionals of any form.
#
def nearAnagram(S1, S2):
    return(len(S1) == len(S2) and set(S1) == set(S2))

######################################################################
# MAKE NO CHANGES BEYOND THIS POINT.
######################################################################
if __name__ == '__main__':
    if type(signed()) is not list:
        print('### Error: signed() must return list of hawkid(s).\n')
        exit()
    if signed()[0] == "hawkid":
        print('### Error: signed() must return your hawkid.\n')
        exit()
    if nearAnagram("recuse", "rescue")!=True:
        print('### Error: nearAnagram("recuse", "rescue") does not return True')
    if nearAnagram("racecar", "carrace")!=True:
        print('### Error: nearAnagram("racecar", "carrace") does not return True')
    if nearAnagram((1, 3, 5, 7), (1, 1, 3, 7, 5))!=False:
        print('### Error: nearAnagram((1, 3, 5, 7), (1, 1, 3, 7, 5)) does not return False')
