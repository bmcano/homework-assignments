# CS1210: QotD3
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# Specification: spliceEnds(S, i) takes a sequence, S, and an integer,
# i, and returns a (new) sequence consisting of the last i elements of
# S spliced with S spliced with the first i elements of S.
#
# Note: no assignment is needed: your solution should not use
# if/elif/else, and should function appropriately even if i >
# len(S). As with Qotd2, a single expression in the return suffices.
#
# Examples:
#   >>> spliceEnds((1, 3, 5, 6), 2)
#   (5, 6, 1, 3, 5, 6, 1, 3)
#   >>> spliceEnds("0123456", 3)
#   '4560123456012'
#
def spliceEnds(S, i):
    return(S[len(S)-i:] + S + S[:i])

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
    if spliceEnds("012345", 0)!='012345':
        print('### Error: spliceEnds("012345", 8) returns {}, expected \'012345012345012345\''.format(spliceEnds("012345", 8)))
    if spliceEnds(list(range(3, 14, 3)), 2)!=[9, 12, 3, 6, 9, 12, 3, 6]:
        print('### Error: spliceEnds({}, 2) returns {}, expected [9, 12, 3, 6, 9, 12, 3, 6]\n'.format(list(range(3, 14, 3)), spliceEnds(list(range(3, 14, 3)), 2)))
