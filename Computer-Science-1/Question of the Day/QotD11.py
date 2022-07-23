# CS1210: QotD11
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# Specification: supersum takes a possibly nested list of elements and
# returns a "flat" list of the same elements.
#
# Example:
#   >>> superlist([[[2, 3], 1, [2, [4, 5, [9]]]], 8, 0])
#   [2, 3, 1, 2, 4, 5, 9, 8, 0]
#   >>> superlist([1, 2, [3], [[[[4]]]]])
#   [1, 2, 3, 4]
#
# Your solution must be recursive.
#
# Use a comment to indicate the base case(s) and recursive step(s).
#

def superlist(L):
    flat = []
    for i in L:
        if type(i) == list:     
            flat += superlist(i)    # for recusive steps
        else:
            flat.append(i)          # base cases  
    return(flat)

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
    if superlist([ list(x) for x in "this is a test".split() ])!=['t', 'h', 'i', 's', 'i', 's', 'a', 't', 'e', 's', 't']:
        print('### Error: {} does not return {}'.format('superlist([ list(x) for x in "this is a test".split() ])', ['t', 'h', 'i', 's', 'i', 's', 'a', 't', 'e', 's', 't']))
    if superlist([[0, 1, 2], [[3], [[4, 5], [[[[6]]]], 7]]])!=[0, 1, 2, 3, 4, 5, 6, 7]:
        print('### Error: {} does not return {}'.format("superlist([[0, 1, 2], [[3], [[4, 5], [[[[6]]]], 7]]])", [0, 1, 2, 3, 4, 5, 6, 7]))
