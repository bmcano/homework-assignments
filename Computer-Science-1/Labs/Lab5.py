# CS1210: Lab5 [3 functions that tell a story]
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely the work of you and your partners, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano","ikazembe","esedwards"])

######################################################################
# Today in this lab we're going to play a little with non-determinism,
# that is, writing code that incorporates some form of random
# behavior. Randomness, as we'll see shortly in lecture, can actually
# help you write better, more efficient, code -- but today we'll only
# start with some simple notions.
#
# We'll be looking at the problem of shuffling a list. Imagine that
# the list represents a deck of cards, and that the list has some
# cannonical ordering (i.e., a fresh deck's order). Here, we'll stick
# to lists of unique integers, so we'll assume the cannonical ordering
# sorts the integers in ascending order.
#
# We'll start by importing functions shuffle() and randint() from the
# random module.
#
# randint(lo, hi) returns an integer i where lo <= i <= hi.
# CAREFUL! unlike range() and indexing, randint(lo, hi) is
# inclusive of hi, not exclusive of hi. 
from random import shuffle, randint

# Shuffle() is used to randomly permute (reorder) a list, L.  So,
# starting with a sorted list of unique elements, we want to
# manipulate the list in order to mix up those elements, but without
# changing the elements themselves. 
#
# Example:
#   >>> L=list(range(10))
#   >>> shuffle(L)
#   >>> L
#   [1, 2, 4, 9, 7, 5, 0, 8, 3, 6]
#
# Of course, this leaves two questions: first, "how shuffled is it?"
# meaning how close is it to the original list, L, and "how fair is
# it?" meaning is every possible permutation of L equally likely to
# occur.
#
# Today we'll explore only the first question. To do so, we need a
# measure of "sortedness" for permutations (sequences of elements that
# are all unique, like a deck of cards) by counting "inversions." An
# "inversion" is a pair of elements in the list where L[i]<L[j] but
# i>j. The inversion index is the ratio of inversions over the number
# possible comparisons between two elements in the list (note that for
# a list L of length len(L), there are (len(L)*(len(L)-1))/2 unique
# combinations of i and j).
#
# Write a function that returns the inversion index of L. You'll need
# to methodically compare every element i with every other element j
# in L, and count the number of inversions you observe. This usual
# strategy requires two loops, one nested inside the other, where you
# allow i to range from 0 to len(L)-1 and j to range from i to len(L).
def iindex(L):
    count = 0
    for i in range(len(L)-1):
        for j in range(i, len(L)):
            if L[j] < L[i]:
                count += 1
    return(count/((len(L)*(len(L)-1))/2))


# Next, assume you don't want to use the system's shuffle() function
# but want to implement your own. Inspired by that game of poker you
# played the night before Exam1, you decide to implement an "overhand
# shuffle."
#
# An overhand shuffle is performed by repeatedly taking a random
# center section of the deck and moving it to the front of the
# deck. In other words, each round takes a list:
#   [ 0, .... c1, c1+1, .... c2, c2+1 ... s ]
# where s is the last element of the list, and changes it to:
#   [ 0, .... c1, c2+1 ... s, c1+1, .... c2 ]
# and you repeat this many times, using randomly chosen cutpoints c1
# and c2 each time. Your function, like shuffle(), should manipulate L
# to produce the shuffled list; unlike shuffle() however, it should
# also return the list L for convenience.
#
# We'll start with a handy helper function cutPoints(L) which returns
# two integers corresponding to two "cutpoints", where the first
# integer is the smaller of the two. This function will determine what
# center section you will manipulate.
def cutPoints(l):
    c1 = randint(1, len(l)-3)
    c2 = randint(c1+1, len(l)-2)
    return(c1, c2)
    # c1, c2 = randint(0, len(L)-1), randint(0, len(L)-1)
    # return(min(c1, c2), max(c1, c2))
    
# Now we're ready to implement overhandShuffle(L, r=1). We'll use r as
# the number of rounds to shuffle. Each round, you will use
# cutPoints() to select two indeces, then perform the reordering by
# modifying L. At the end of the day, you return L, mostly so we can
# conveniently call iindex() on the output of overhandShuffle().
def overhandShuffle(L, r=1):
    for i in range(r):
        c = cutPoints(L)
        L = L[:c[0]] + L[c[1]:] + L[c[0]:c[1]]
    return(L)    
      
# Finally, if you've done things right, you can actually compare how
# well your overhandShuffle() function randomizes with respect to the
# system shuffle() function.
#
# Here's a silly test function that takes a list of N consecutive
# elements and performs k overhandShuffles() with r=1...k, measuring
# inversions at each step.
#
# Play with this a bit; how many rounds do you need to go to
# approximate a "good" shuffle like shuffle()? Does it matter how long
# the list is?
def testit(N, k):
    L = list(range(N))
    shuffle(L)
    print("Single system shuffle:\n  {}".format(iindex(L)))
    L = list(range(N))
    print("{} overhand shuffles:\n  {}".format(k, [ iindex(overhandShuffle(L, 1)) for i in range(k) ]))

######################################################################
# MAKE NO CHANGES BEYOND THIS POINT.
######################################################################
if __name__ == '__main__':
    if all([ "hawkid" in x for x in signed() ]):
        print('### Warning: Complete definition of signed() or no points will be awarded!')
    print('### Provide your own test cases in the space provided; these will not be graded.\n')
