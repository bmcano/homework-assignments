# CS1210: QotD9
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# Specification: index(S) takes a string, S, and returns a dictionary,
# indexed by the lower case letters a...z, where the value represents
# the number of occurrances of that letter in S.
#
# Example:
#   >>> index('The rain in Spain')
#   {'t': 1, 'h': 1, 'e': 1, 'r': 1, 'a': 2, 'i': 3, 'n': 3, 's': 1, 'p': 1}
#
# There are several approaches here; use whichever you like.
#
# You may use comprehensions, for/while, if/elif/else, and/or
# assignment as needed.
#
def index(S):
    D = {}
    for i in S.lower():
        if i in D:
            D[i] += 1
        elif i not in D and i != " ":
            D[i] = 1
    return(D)
    

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
    if index('Dare mighty things')!={'d': 1, 'a': 1, 'r': 1, 'e': 1, 'm': 1, 'i': 2, 'g': 2, 'h': 2, 't': 2, 'y': 1, 'n': 1, 's': 1}:
        print('### Error: {} does not return {}'.format("index('Dare mighty things')",{'d': 1, 'a': 1, 'r': 1, 'e': 1, 'm': 1, 'i': 2, 'g': 2, 'h': 2, 't': 2, 'y': 1, 'n': 1, 's': 1}))
    if index('CoFfEE gRiNDer')!={'c': 1, 'o': 1, 'f': 2, 'e': 3, 'g': 1, 'r': 2, 'i': 1, 'n': 1, 'd': 1}:

        print('### Error: {} does not return {}'.format("index('CoFfEE gRiNDer')",{'c': 1, 'o': 1, 'f': 2, 'e': 3, 'g': 1, 'r': 2, 'i': 1, 'n': 1, 'd': 1}))
