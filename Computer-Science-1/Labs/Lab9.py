# CS1210: Lab9 Fun with (Roman) numbers...
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely the work of you and your partners, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano","icheville","bsuter"])

######################################################################
# Roman numerals are string of characters that are read from left to
# right. Each character represents a specific value:
#    M=1000, D=500, C=100, L=50, X=10, V=5 and I=1
# If sorted from larger to smaller, a string of these would then
# be interpreted as the sum of those elements. So:
#    MDCLXXXV = 1000+500+100+50+10+10+10+5 = 1685
# In addition, two character combinations arranged from smaller to
# larger are also allowed. Thus we can use IV rather than IIII to
# represent the number 4.
#
# Specification: rom2int(R) takes a string, R, representing a legal
# Roman numeral, and returns the equivalent integer.
#
# A Roman numeral is legal if it is generally arranged from largest
# value to smallest, with the exception of allowing a
# single-small-large-letter combination at the end of the string of
# "large" letters. Thus "XXIXV" is legal, but "XIXXV" is not.
#
# Hint: an iterative approach seems warranted, traversing R
# left-to-right while accumulating is integer counterpart.
#
# Hint: please, no giant if/elif/else statements checking each Roman
# numeral character; you know better than that by now!
#
# Here, you can assume that the input R is a legal Roman number; if R
# is illegal, your answer cannot be incorrect.
#
# Example:
#   >>> rom2int("MCCX")
#   1210
#   >>> rom2int("MCMXCIV")
#   1994
#
def rom2int(R):
    rn = {'M':1000, 'D':500, 'C':100, 'L':50, 'X':10, 'V':5, 'I':1, 'IV':4, 'IX':9, "XL":40, 'XC':90, 'CD':400, 'CM':900}
    val = 0
    i = 0
    
    while i < len(R):
        if len(R[i:i+2]) == 2 and R[i:i+2] in rn:
            val += rn[R[i:i+2]]
            i += 2
        else:
            val += rn[R[i]]
            i += 1
            
    return(val)

######################################################################
# Specification: rom2intSafe(R) takes a string, R, representing a legal
# Roman numeral, and returns the equivalent integer. This time, you
# should raise a ValueError if R is illegal. Recall that R is illegal
# if a smaller value preceeds a larger value that is not the rightmost
# character of its kind.
#
# Example:
#   >>> rom2intSafe("MCMLVII")
#   1957
#   >>> rom2intSafe("MCMXCIV")
#   1994
#   >>> rom2intSafe("MCMMXXII")
#   Traceback (most recent call last):
#     File "<stdin>", line 1, in <module>
#     File "<stdin>", line 10, in rom2intSafe
#   ValueError: Illegal subsequence CMM in Roman number MCMMXXII
#
def rom2intSafe(R):
    rn = {'M':1000, 'D':500, 'C':100, 'L':50, 'X':10, 'V':5, 'I':1, 'IV':4, 'IX':9, "XL":40, 'XC':90, 'CD':400, 'CM':900}
    val = 0
    i = 0

    while i < len(R):
        if i == len(R) - 1 or rn[R[i]] >= rn[R[i+1]]:
            val += rn[R[i]]
            i += 1
            
        elif i+2 < len(R) and rn[R[i]] <= rn[R[i+2]]:
            raise ValueError("Illegal subsequence {} in Roman number {}".format(R[i:i+3],R)) 
            
        else:
            val += rn[R[i+1]] - rn[R[i]]
            i += 2
            
    return(val)


######################################################################
# MAKE NO CHANGES BEYOND THIS POINT.
######################################################################
if __name__ == '__main__':
    if all([ "hawkid" in x for x in signed() ]):
        print('### Warning: Complete definition of signed() or no points will be awarded!')
    print("### Create your own test cases; they will not be graded.")
