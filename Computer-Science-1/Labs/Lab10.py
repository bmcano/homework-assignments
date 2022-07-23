# CS1210: Lab10 More fun with (Roman) numbers...
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely the work of you and your partners, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano","icheville","ikazembe","sahubbard"])

######################################################################
# Roman numerals are string of characters that are read from left to
# right. Each character represents a specific value:
#    M=1000, D=500, C=100, L=50, X=10, V=5 and I=1
# If sorted from larger to smaller, a string of these would then
# be interpreted as the sum of those elements. So:
#    MDCLXXXV = 1000+500+100+50+10+10+10+5 = 1685
# In addition, certain two-character combinations arranged from
# smaller to larger are also allowed. So, for example, we can use IV
# rather than IIII to represent the number 4.
#
# In Lab9, you wrote a rom2int(R) function that takes a string, R,
# representing a legal Roman numeral, and returns the equivalent
# integer. Here is a reference solution for Lab9 that assumes R is a
# legal Roman number.
#
def rom2int(R):
    values = {'M':1000, 'D':500, 'C':100, 'L':50, 'X':10, 'V':5, 'I':1}
    N = 0
    i = 0
    while i < len(R):
        if i == len(R)-1 or values[R[i+1]] <= values[R[i]]:
            # Next character is less than or equal to this: add
            N = N+values[R[i]]
            i = i+1
        else:
            # Next character is greater than present character.
            # You should really check to make sure its a singleton.
            N = N-values[R[i]]+values[R[i+1]]
            i = i+2
    return(N)

######################################################################
# Specification: int2rom(N) takes an integer, N, and returns a string
# of characters corresponding to N's legal Roman numeral
# representation.
#
# Note that there are restrictions on what small-large character
# combinations are actually legal. You would never see, for example,
# LC used to represent 50, when simply L would do. Nor would you see
# IM to represent 999: the proper representation would be CMXCIX. In
# short, the only legal smaller-larger combinations your code should
# generate are IV, IX, XL, XC, CD, and CM.
#
# Your first solution should take an iterative approach, repeatedly
# decrementing N towards 0 using the largest possible remaining Roman
# numeral or Roman numeral combination.
#
# Hint: please, no giant if/elif/else statements checking each Roman
# numeral character; you know better than that by now!
#
def int2rom(N):
    values = {1000:'M', 900:'CM', 500:'D', 400:'CD', 100:'C', 90:'XC', 50:'L', 40:'XL', 10:'X', 9:'IX', 5:'V', 4:'IV', 1:'I'}
    num = list(values.keys()) # list of keys from dictionary
    
    roNum = '' # emtpy string
    i = 0      # counter 
    while N > 0:
        for x in range(N // num[i]):
            roNum += values[num[i]] # adds letter(s) to string
            N = N - num[i]          # decrement towards 0
        i += 1
    return(roNum)

######################################################################
# Specification; int2romRec(N, ...) is a recursive formulation of
# int2rom(N), where you should feel free to incorporate additional --
# but strictly optional -- arguments if need be. Otherwise, the
# functionality of int2rom() and int2romRec() should be identical.
#
# Important: feel free to add additional optional arguments to the
# signature of int2romRec if needed.
#
def int2romRec(N):   # additional optional arguments are ok
    values = {1000:'M', 900:'CM', 500:'D', 400:'CD', 100:'C', 90:'XC', 50:'L', 40:'XL', 10:'X', 9:'IX', 5:'V', 4:'IV', 1:'I'}
    num = list(values.keys())
    
    roNum = ''
    i = 0
    while N > 0:
        if N // num[i]:
            return(roNum + values[num[i]] + int2romRec(N - num[i])) # recursive step
        i += 1
    return(roNum)   # base case

######################################################################
# MAKE NO CHANGES BEYOND THIS POINT.
######################################################################
if __name__ == '__main__':
    if all([ "hawkid" in x for x in signed() ]):
        print('### Warning: Complete definition of signed() or no points will be awarded!')
    print("### Create your own test cases; they will not be graded.")
    print("### Feel free to test, e.g., rom2int(int2rom(x)) for any integer x.")
