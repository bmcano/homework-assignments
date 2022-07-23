# CS1210: QotD1
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# Specification: sumByproduct(i, j) takes two integers, i and j, and
# returns the sum of i and j times the product of i and j.
#
# Example:
#   >>> sumByProduct(-3, 7)
#   -84
#
def sumByProduct(i, j):
    return((i+j) * (i*j))

######################################################################
# MAKE NO CHANGES BEYOND THIS POINT.
######################################################################
if __name__ == '__main__':
    if signed()[0] == "hawkid":
        print('### Error: Edit the signed() function to return your hawkid.\n')
        exit()
    if sumByProduct(-3, 3)!=0:
        print('### Error: sumByProduct(-3, 3) returns {}, expected 0'.format(sumByProduct(-3, 3)))
    if sumByProduct(97, 41)!=548826:
        print('### Error: sumByProduct(97, 41) returns {}, expected 0'.format(sumByProduct(97, 41)))
