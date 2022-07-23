# CS1210: QotD13
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# Specification: smartSum(S) returns the sum of any int or float
# elements in S, where S is an int, a float, a range, or a (possibly)
# nested list, tuple, or set. If no int or float elements are found,
# smartSum() should return 0.
#
# Example:
#   >>> smartSum([([(4, 5, [6])])])
#   15
#   >>> smartSum([range(12, 5), {'aha', -7}, [(1,), (2,)]])
#   -4
#
# Your solution must be recursive.
#
# Use a comment to indicate the base case(s) and recursive step(s).
#
def smartSum(S):
    ssum = 0
    for i in S:
        if isinstance(i, (list, tuple, set)):
            ssum += smartSum(i)
        elif isinstance(i, (int, float)):
            ssum += i
        elif isinstance(i, range):
            ssum += sum(i)
    return(ssum)

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
    if smartSum((-10, 11, [4, 'foo'], 'bar', (8,)))!=13:
        print('### Error: {} does not return {}'.format("smartSum((-10, 11, [4, 'foo'], 'bar', (8,)))",13))
    if smartSum([1, (2, 3, 'abc'), [{4, 5}, {'xyz', '747', 0}, range(5, 100, 15)]])!=365:
        print('### Error: {} does not return {}'.format("smartSum([1, (2, 3, 'abc'), [{4, 5}, {'xyz', '747', 0}, range(5, 100, 15)]])", 365))
