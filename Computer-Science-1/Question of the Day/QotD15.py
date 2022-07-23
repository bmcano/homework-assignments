# CS1210: QotD15
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# Specification: euclid(x, y) takes two positive integers and returns
# their greatest common divisor, or gcd. The gcd is the largest
# integer 1<=i<=min(x, y) that evenly divides both x and y.
#
# In this variant of QotD14, produce an iterative version of Euclid's
# algorithm (no recursion allowed!).
#
def euclid(x, y):
    while y != 0:
       y, x = x%y, y 
    return(x)
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
    if euclid(186, 15)!=3:
        print('### Error: {} does not return {}'.format("euclid(186, 15)", 3))
    if euclid(32375, 4510)!=5:
        print('### Error: {} does not return {}'.format("euclid(32375, 4510)", 5))
