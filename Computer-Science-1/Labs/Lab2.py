# CS1210: Lab2 [5 functions to complete]
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely the work of you and your partners, and
#  2) it has not been shared with anyone outside the intructional team.
# N.B., add a third string to the list if there are three of you.
#
def signed():
    return(["bmcano","bmcano"])

######################################################################
# Specification: cylvol(r, h) takes two integers, r and h, and returns
# an integer approximation of the volume of a cylinder with radius r
# and height h. Note that your function should use only integer
# operations and does not require the use of assignment or any sort of
# conditional.
#
# Background: Recall from high school math that the volume of a
# cylinder is given by the expression V=Pi*h*r^2, where r^2 denotes
# radius squared and Pi is Archimedes' constant, the irrational
# transcendental number that begins 3.1415.
#
# Because Pi is irrational, its decimal representation is infinitely
# long.  Fortunately for us, there are simple integer approximations
# of Pi that have been used for over a thousand years. For example, Pi
# can be approximated with the following ratios: 22:7, 333:106,
# 355:113, 3927:1250, and 52163:16604, among others. Here, we'll use
# the last one, 52163:16604, as the larger values provide greater
# precision in this situation.
#
# Note that because we're interested in the integer approximation of
# the volume and using only integer operations, as a general strategy
# we will "delay" the implicit integer division contained in the
# approximation of Pi for as long as possible, so that the numerator
# of the final division is as large as possible, a strategy that makes
# great use of Python's unbounded integer representation.
#
# For example:
#   >>> cylvol(1, 10)
#   31			# Compare with 31.42
#   >>> cylvol(25, 15)
#   29452		# Compare with 29451.56
#
def cylvol(r, h):
    return(int((52163/16604)*h*(r**2)))	# Start here: replace this line with your code.

######################################################################
# Specification: endSwap(S, i) takes a sequence, S, and an integer i
# where 0 <= i < len(S) and returns a new sequence constructed by
# exchanging the ends of S at index location i.  Your solution should
# not require the use of assignment or any sort of conditional.
#
# Hint: note that the sequence returned will always begin with S[i].
#
# Example:
#   >>> endSwap("abcdefgh", 3)
#   'defghabc'			# S[3] is 'd'
#
def endSwap(S, i):
    return(S[i:]+S[:i])	# Start here: replace this line with your code.

######################################################################
# Specification: meiosis(S1, S2, i) takes two equal-length sequences,
# S1 and S2, and an integer i where 0 <= i < len(S1) and returns a
# tuple of two new sequences constructed by exchanging subsequences of
# S1 and S2 starting at index location i. Your solution should not
# require the use of assignment or any sort of conditional.
#
# Example:
#   >>> meiosis("abcdefghij", "0123456789", 4)
#   ('0123efghij', 'abcd456789')
#
def meiosis(S1, S2, i):
    return(S2[:i]+S1[i:], S1[:i]+S2[i:])		# Start here: replace this line with your code.

######################################################################
# Specification: squiggle(S, k) takes a string, S, having more than
# one element, and returns a new string that breaks S apart at its
# centermost location, inserts k underscores (i.e., '_') and reverses
# the trailing half of S.  Note: you don't have to enforce the
# len(S)>=2 constraint, nor should your solution require the use of
# assignment or any sort of conditional.
#
# Hint: In the event S has odd length, the "centermost location" is
# defined as the location that produces a "shorter" half followed by a
# "longer" half.
#
# Example:
#   >>> squiggle("1234567890", 4)
#   '12345____09876'
#   >>> squiggle("1234", 0)
#   '1243'
#
def squiggle(S, k):
    return(S[:(len(S)//2)]+(k*"_")+S[:len(S)//2-1:-1])		# Start here: replace this line with your code.

######################################################################
# Specification: countVowels(S) takes a lowercase string, S, and
# returns the number of vowels that are present in the string.  Your
# solution does not require use of assignment or any sort of
# conditional, but should rather consist of a return statement
# containing a single expression.
#
# Hint: The vowels consist of the five characters 'a', 'e', 'i', 'o',
# and 'u'.
#
# Example:
#   >>> countVowels("the rain in spain falls mainly in the plains")
#   3
#   >>> countVowels("sock it to me")
#   3
#
def countVowels(S):
    return(len(set(S) & {'a','e','i','o','u'}))	# Start here: replace this line with your code.

######################################################################
# MAKE NO CHANGES BEYOND THIS POINT.
######################################################################
if __name__ == '__main__':
    print('### Absence of Warnings or Errors indicates a clean pass through the test cases provided.\n')
    if all([ "hawkid" in x for x in signed() ]):
        print('### Warning: Complete definition of signed() or no points will be awarded!')
    if cylvol(27, 111)!=254214:
        print('### Error: cylvol(27, 111) returns {}, expected 245214.'.format(cylvol(13, 13)))
    if endSwap((4, 5, 6, 1, 2, 3), 3)!=(1, 2, 3, 4, 5, 6):
        print('### Error: endSwap((4, 5, 6, 1, 2, 3), 3) returns {}, expected (1, 2, 3, 4, 5, 6).'.format(endSwap((4, 5, 6, 1, 2, 3), 3)))
    if endSwap('to be or not to be', 9)!='not to beto be or ':
        print('### Error: endSwap(\'to be or not to be\', 9) returns {}, expected \'not to beto be or \'.'.format(endSwap('to be or not to be', 9)))
    if meiosis("computer", "SCIENCE", 3)!=('SCIputer', 'comENCE'):
        print('### Error: meiosis("computer", "SCIENCE", 3) returns {}, expected (\'SCIputer\', \'comENCE\').'.format(meiosis("computer", "SCIENCE", 3)))
    if meiosis("computer", "SCIENCE", 0)!=('computer', 'SCIENCE'):
        print('### Error: meiosis("computer", "SCIENCE", 0) returns {}, expected (\'computer\', \'SCIENCE\')).'.format(meiosis("computer", "SCIENCE", 3)))
    if meiosis((1, 2, 3, 4), (5, 6, 7, 8), -2)!=((5, 6, 3, 4), (1, 2, 7, 8)):
        print('### Error: meiosis((1, 2, 3, 4), (5, 6, 7, 8), -2) returns {}, expected ((5, 6, 3, 4), (1, 2, 7, 8)).'.format(meiosis((1, 2, 3, 4), (5, 6, 7, 8), 2)))
    if squiggle("ab", 0)!='ab':
        print('### Error: squiggle("ab", 0) returns {}, expected \'ab\'.'.format(squiggle("ab", 0)))
    if squiggle("computersecneics", 4)!='computer____sciences':
        print('### Error: squiggle("computersecneics", 4) returns {}, expected \'computer____sciences\'.'.format(squiggle("computersecneics", 4)))
    if countVowels("she had a dream about the king of sweden")!=5:
        print('### Error: countVowels("she had a dream about the king of sweden") returns {}, expected 5.'.format(countVowels("she had a dream about the king of sweden")))
    if countVowels("six and three is nine, nine and nine is eighteen")!=3:
        print('### Error: countVowels("six and three is nine, nine and nine is eighteen") returns {}, expected 3.'.format(countVowels("six and three is nine, nine and nine is eighteen")))
