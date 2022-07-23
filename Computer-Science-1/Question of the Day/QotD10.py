# CS1210: QotD10
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# Specification: superdigit(N) takes a single positive integer, N, and
# returns a single digit that results by repeated addition of
# individual digits of N.
#
# Example:
#   >>> superdigit(12)
#   3			# 1+2=3
#   >>> superdigit(4235)
#   5			# 4+2+3+5=14; 1+4=5
#
# There are several approaches here; use whichever you like. However,
# in order to earn credit, your solution must be recursive.
#
# Use a comment to indicate the base case and recursive step.
#
def superdigit(N):
    S = str(N)
    t = 0
    for i in range(len(S)):
        t += int(S[i])
    if len(str(t)) > 1:         #recursive step starts here by checking if the number is more than one digit 
        return(superdigit(t))
    else:
        return(t)


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
    if superdigit(14297)!=5:
        print('### Error: {} does not return {}'.format("superdigit(14297)",5))
    if superdigit(3141578250234)!=9:
        print('### Error: {} does not return {}'.format("superdigit(3141578250234)",9))
