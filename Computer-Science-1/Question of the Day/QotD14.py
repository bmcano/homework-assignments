# CS1210: QotD14
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
# Your solution should be based on Euclid's algorithm, due Euclid of
# Alexandria, the famous ancient Greek mathematician credited with the
# development of Euclidean geometry (est. 330BC-270BC).  Euclid's
# algorithm computes the remainder r obtained by dividing the larger
# of x,y by the smaller of x,y. If r is 0, then the divisor is the
# gcd; otherwise, repeat the process using r and the smaller of x,y.
#
# Your solution should be recursive.
#
def euclid(x, y):
    if x == 0:             # base case
        return(y) 
    return(euclid(y%x, x)) # recursion
        
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
