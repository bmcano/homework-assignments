# CS1210: QotD17
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work; and
#  2) it has not been shared with anyone.
def signed():
    return(["bmcano"])

######################################################################
# Specification: charIdx(S, C) takes a string representing a
# collection of words, S, and a second string of characters, C, and
# returns a dictionary where the keys are the characters in C and the
# values are lists of all the words (in lower case) in S that contain
# those characters.
#
# Note: no entry is expected for a character in C that does not occur
# in S.
#
# Hint: Use comprehensions.
# Hint: Add comments as appropriate.
#
# Example:
#   >>> charIdx('The rain in Spain', 'aeiou')
#   {'a': ['rain', 'spain'], 'e': ['the'], 'i': ['rain', 'in', 'spain']}
#
def charIdx(S, C):
    return( { index:[i for i in S.lower().split() if index in i] for index in C if index in S.lower() } )
    # I used list comprehensions inside of a dictionary comprehension to create the dictionary it runs through each letter of C then will
    # check each word in S, and if the list of words with the part of C is empty it will not include it
    
######################################################################
if __name__ == '__main__':
    if type(signed()) is not list:
        print('### Error: signed() must return list of hawkid(s).\n')
        exit()
    if signed()[0] == "hawkid":
        print('### Error: signed() must return your hawkid.\n')
        exit()
    if charIdx("The changing of the guard", "aeiou")!={'a': ['changing', 'guard'], 'e': ['the', 'the'], 'i': ['changing'], 'o': ['of'], 'u': ['guard']}:
        print('### Error: {} does not produce {}'.format('charIdx("The changing of the guard", "aeiou")',{'a': ['changing', 'guard'], 'e': ['the', 'the'], 'i': ['changing'], 'o': ['of'], 'u': ['guard']}))
    if charIdx("The sun never sets on the Empire", "se")!={'s': ['sun', 'sets'], 'e': ['the', 'never', 'sets', 'the', 'empire']}:
        print('### Error: {} does not produce to {}'.format('charIdx("The sun never sets on the Empire", "se")',{'s': ['sun', 'sets'], 'e': ['the', 'never', 'sets', 'the', 'empire']}))
