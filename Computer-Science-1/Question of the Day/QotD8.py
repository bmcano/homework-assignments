# CS1210: QotD8
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# Specification: smooth(L, k) takes a list of numeric values (int or
# float) and a smoothing parameter, k, where 1 <= k < len(L), and
# returns a new list that contains a "smoothed" version of the values
# in L.
#
# Each "smoothed" value in the result consists of the mean of k
# elements in the original list, L, starting with the corresponding
# index as the result. So:
#    >>> smooth([3, 5, 4, 7, 2, 8], 2)
#    [4.0, 4.5, 5.5, 4.5, 5.0]
# computed as follows:
#    [((3+5)/2), ((5+4)/2), ((4+7)/2), ((7+2)/2), ((2+8)/2)]
# Note that the length of the result will always be k-1 elements
# shorter than L.
#
# For full credit, your solution should use a comprehension; it
# should not use for/while, if/elif/else, or assignment.
#
def smooth(L, k):
    return( [ sum(L[x:x+k])/k for x in range(len(L)-(k-1)) ] )
         
    
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
    if smooth([1, 1, 1, 4, 1, 1, 1], 3)!=[1.0, 2.0, 2.0, 2.0, 1.0]:
        print('### Error: {} does not return {}'.format('smooth([1, 1, 1, 4, 1, 1, 1], 3)',[1.0, 2.0, 2.0, 2.0, 1.0]))
    if smooth([1, 1, 1, 4, 1, 1, 1], 2)!=[1.0, 1.0, 2.5, 2.5, 1.0, 1.0]:
        print('### Error: {} does not return {}'.format('smooth([1, 1, 1, 4, 1, 1, 1], 2)',[1.0, 1.0, 2.5, 2.5, 1.0, 1.0]))
