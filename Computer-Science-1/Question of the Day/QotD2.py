# CS1210: QotD2
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# Specification: extendSequence(S, i, j) takes a sequence, S, and two
# integers, i < len(S) and j >= 0, and returns a (new) sequence
# consisting of S with the last i elements repeated exactly j times.
# Note: your solution should not use if/elif/else; rather it should
# return the value of a single expression.
#
# Examples:
#   >>> extendSequence((1, 3, 5, 6), 2, 3)
#   (1, 3, 5, 6, 5, 6, 5, 6)
#   >>> extendSequence("yes", 1, 10)
#   'yessssssssss'
#
def extendSequence(S, i, j):
    return(S[:len(S)-i]+S[len(S)-i:]*(j))

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
    if extendSequence("cs1210", 2, 2)!='cs121010':
        print('### Error: extendSequence("cs1210", 2, 2) returns {}, expected \'cs121010\''.format(extendSequence("cs1210", 2, 2)))
    if extendSequence(tuple(range(6)), 3, 0)!=(0, 1, 2):
        print('### Error: extendSequence({}, 3, 0) returns {}, expected (0, 1, 2)\n'.format(tuple(range(6)), extendSequence(tuple(range(6)), 3, 0)))
