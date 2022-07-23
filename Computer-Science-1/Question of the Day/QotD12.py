# CS1210: QotD12
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# Specification: howMany(L, t) takes a possibly nested list L of
# elements and a target element t and returns the number of times that
# t appears somewhere in L.
#
# Example:
#   >>> 
#   3
#   >>> howMany([1, 4, [3], [[[[4]]]]], 4)
#   2
#   >>> howMany([1, 4, [3], [[[[4]]]]], [])
#   6
#
# Your solution must be recursive.
#
# Use a comment to indicate the base case(s) and recursive step(s).
#
def howMany(L, t):
    if L == t:
        return 1
    elif not isinstance(L, list) or L == []:
        return 0
    return(howMany(L[0], t) + howMany(L[1:], t))

    """ counter = 0
    if t == []:
        return(str(L).count('['))
    for i in L:
        if i == t:              # for base cases
            counter += 1 
        elif type(i) == list:   # for recursive cases
            counter += howMany(i, t)
        
    return(counter)"""


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
    if howMany([[[2, 3], 1, [2, [2, 5, [3]]]], 8, 3], 3)!=3:
        print('### Error: {} does not return {}'.format("howMany([[[2, 3], 1, [2, [2, 5, [3]]]], 8, 3], 3)",3))
    if howMany([[0, 1], 1, [0, 0, [1, 0, 1]]], 0)!=4:
        print('### Error: {} does not return {}'.format("howMany([[0, 1], 1, [0, 0, [1, 0, 1]]], 0)",4))
