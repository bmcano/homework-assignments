# CS1210: Lab3 [4 functions, in increasing order of difficulty]
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely the work of you and your partners, and
#  2) it has not been shared with anyone outside the intructional team.
# N.B., add a third string to the list if there are three of you.
#
def signed():
    return(["bmacno","bmcano"])

######################################################################
# Specification: minTimesMax(S): takes a non-empty sequence of
# non-negative integer values S and returns a tuple containing max
# copies of the smallest element of S followed by min copies of the
# largest element of S, where max and min are computed relative to the
# entirety of S.
#
# Your solution should not require the use of assignment or any sort
# of conditional.
#
# Examples:
#   >>> minTimesMax((1, 2, 3, 4, 5))       # N.b., min=1, max=5
#   (1, 1, 1, 1, 1, 5)
#   >>> minTimesMax(range(10))             # N.b., min=0, max=9
#   (0, 0, 0, 0, 0, 0, 0, 0, 0)
#
def minTimesMax(S):
    return(tuple(max(S)*(min(S),) + min(S)*(max(S),)))

######################################################################
# Specification: inflateCenter(S, i) takes a sequence, S, and a
# non-negative integer, i, and returns S with its centermost element
# replaced by i copies of that element.
#
# Your function should work for both lists and tuples, however, when S
# is a tuple your function must necessarily return a new tuple (why?),
# while when S is a list your function should modify (how?) and then
# return the now modified value passed as S.
#
# Example: (assume L was initially set to list(range(10))):
#   >>> L=list(range(8))
#   >>> T=tuple(range(7))
#   >>> inflateCenter(T, 3)
#   (0, 1, 2, 3, 3, 3, 4, 5, 6)
#   >>> T
#   (0, 1, 2, 3, 4, 5, 6)
#   >>> inflateCenter(L, 4)
#   [0, 1, 2, 3, 4, 5, 5, 5, 5, 7, 8, 9]
#   >>> L
#   [0, 1, 2, 3, 4, 5, 5, 5, 5, 7, 8, 9]
#
# Note that the conditional structure dispatching on type of S is
# provided for you. Also, please note that that "centermost" is
# defined in the integer division sense when sequences are of even
# length.
#
def inflateCenter(S, i):
    j = len(S)//2
    if type(S) is list:
        return(list(tuple(S[:j]) + (S[j]-1,)*(i-1) + tuple(S[j+1:])))
    else:
        return(tuple(S[:j]) + (S[j],)*(i) + tuple(S[j+1:]))
    
######################################################################
# Specification: reverseSubseq(S, ss) takes a string, S, and a
# "subsequence", ss (also a string), and returns either False (if ss
# is not in S) or a copy of S with the first occurrence of the
# subsequence reversed.
#
# Hint: Your solution should make use of the string method
# string.find(target) which returns the index of target in the
# original string (see [TP] C8 or [PE] C6 for more details).
#
# Example:
#   >>> reverseSubseq('It was a dark and stormy night', 'dark')
#   'It was a krad and stormy night'
#   >>> reverseSubseq('It was a dark and stormy night', 'light')
#   False
#   >>> reverseSubseq('ABcdefGH', 'cdef')
#   'ABfedcGH'
#
def reverseSubseq(S, ss):
    i = S.find(ss)
    return(ss in S and S[:i] + S[(i + len(ss))-1:i-1:-1] + S[(i + len(ss)):])

    
    
######################################################################
# Specification: reverseWord(S, w) takes a string, S, and a "word", w
# (also a string), and returns a copy of S with the first occurrence
# of the word reversed. You may assume w contains no space characters
# and is a subsequence of S.
#
# Hint: This function is very much like reverseSubseq(S, ss), except
# that internally, it operates on a list of words extracted from the
# initial string S. So your first step should be to "split" S into a
# list of words using the string.split() method (see [TP] C8 or [PE]
# C6 for more details). Once you have your list of words, you should
# make use of the list.index(target) method which returns the index of
# target in the list (see [TP] C10 or [PE] C8 for more details) and
# then reconstruct the string using the string.join() method.
#
# Example:
#   >>> reverseWord('It was a dark and stormy night', 'dark')
#   'It was a krad and stormy night'
#   >>> reverseWord('It was a dark and stormy night', 'stormy')
#   'It was a dark and ymrots night'
#   >>> reverseWord('It was a dark and stormy night', 'a')
#   'It was a dark and stormy night'
#   
def reverseWord(S, w):
    L = S.split()
    L[L.index(w)] = w[::-1]
    return(' '.join(L))
    
    
######################################################################
# MAKE NO CHANGES BEYOND THIS POINT.
######################################################################
if __name__ == '__main__':
    L = list(range(8))
    print('### Notice: L\'s initial value is {}'.format(L))
    print('### Absence of Warnings or Errors indicates a clean pass through the test cases provided.\n')
    if all([ "hawkid" in x for x in signed() ]):
        print('### Warning: Complete definition of signed() or no points will be awarded!')
    if minTimesMax([17, 2, 3, 5, 1])!=(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 17):
        print('### Error: minTimesMax([17, 2, 3, 5, 1]) returns {}, expected {}.'.format(minTimesMax([17, 2, 3, 5, 1]),(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 17)))
    if minTimesMax((3, 2, 0, 5, 1))!=(0, 0, 0, 0, 0):
        print('### Error: minTimesMax((3, 2, 0, 5, 1)) returns {}, expected {}.'.format(minTimesMax((3, 2, 0, 5, 1)),(0, 0, 0, 0, 0)))
    if minTimesMax(range(1, 6, 2))!=(1, 1, 1, 1, 1, 5):
        print('### Error: minTimesMax(range(1, 6, 2)) returns {}, expected {}.'.format(minTimesMax(range(1, 6, 2)),(1, 1, 1, 1, 1, 5)))
    if inflateCenter(L, 0)!=[0, 1, 2, 3, 5, 6, 7]:
        print('### Error: inflateCenter(L, 0) returns {}, expected {}.'.format(inflateCenter(L, 0),[0, 1, 2, 3, 5, 6, 7]))
    if inflateCenter(L, 3)!=[0, 1, 2, 3, 3, 3, 5, 6, 7]:
        print('### Error: inflateCenter(L, 3) returns {}, expected {}.'.format(inflateCenter(L, 3),[0, 1, 2, 3, 3, 3, 5, 6, 7]))
    if inflateCenter(tuple(range(3,8)), 3)!=(3, 4, 5, 5, 5, 6, 7):
        print('### Error: inflateCenter(tuple(range(3,8)), 3) returns {}, expected {}.'.format(inflateCenter(tuple(range(3,8)), 3),(3, 4, 5, 5, 5, 6, 7)))
    if inflateCenter(inflateCenter(tuple(range(4)), 3), 0)!=(0, 1, 2, 2, 3):
        print('### Error: inflateCenter(inflateCenter(tuple(range(4)), 3), 0) returns {}, expected {}.'.format(inflateCenter(inflateCenter(tuple(range(4)), 3), 0),(0, 1, 2, 2, 3)))
    if reverseSubseq(reverseSubseq("abcdefghijklm", "hij"), "jih")!='abcdefghijklm':
        print('### Error: reverseSubseq(reverseSubseq("abcdefghijklm", "hij"), "jih") returns {}, expected {}.'.format(reverseSubseq(reverseSubseq("abcdefghijklm", "hij"), "jih"),'abcdefghijklm'))
    if reverseSubseq(reverseSubseq("abcdefghijklm", "hijklm"), "efgmlk")!='abcdklmgfejih':
        print('### Error: reverseSubseq(reverseSubseq("abcdefghijklm", "hijklm"), "efgmlk") returns {}, expected {}.'.format(reverseSubseq(reverseSubseq("abcdefghijklm", "hijklm"), "efgmlk"),'abcdklmgfejih'))
    if reverseWord("New announcements appear just after the line below", "announcements")!='New stnemecnuonna appear just after the line below':
        print('### Error: reverseWord("New announcements appear just after the line below", "announcements") returns {}, expected {}.'.format(reverseWord("New announcements appear just after the line below", "announcements"),'New stnemecnuonna appear just after the line below'))
