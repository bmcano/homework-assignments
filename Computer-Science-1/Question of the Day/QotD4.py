# CS1210: QotD4
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# Specification: eviscerateList(L, i, j) takes a list, L, and two
# integers, i and j, and modifies the list, L, such that j elements
# starting at index location i are surgically removed from L. 
# The function should then return the new value of L.
#
# You may assume 0<=i<len(L) and 0<j.
#
# Note: your solution should not use if/elif/else.
#
# Examples:
#   >>> L=list(range(10))
#   >>> L
#   [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
#   >>> eviscerateList(L, 6, 2)
#   [0, 1, 2, 3, 4, 5, 8, 9]
#   >>> L
#   [0, 1, 2, 3, 4, 5, 8, 9]
#   >>> eviscerateList(eviscerateList(L, 7, 1), 1, 3)
#   [0, 4, 5, 8]
#
def eviscerateList(L, i, j):
    del(L[i:i+j])
    return(L)

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
    L = list(range(10))
    print('### Note: L\'s initial value is {}'.format(L))
    r = eviscerateList(L, 3, 4)
    if r != [0, 1, 2, 7, 8, 9]:
        print('### Error: eviscerateList{} does not return {}'.format(r, [0, 1, 2, 7, 8, 9]))
    if L != [0, 1, 2, 7, 8, 9]:
        print('### Error: L was not modified; should be {}'.format([0, 1, 2, 7, 8, 9]))
    r = eviscerateList(L, 3, 10)
    if r != [0, 1, 2]:
        print('### Error: eviscerateList{} does not return {}'.format(r, [0, 1, 2]))
    if L != [0, 1, 2]:
        print('### Error: L was not modified; should be {}'.format([0, 1, 2]))
