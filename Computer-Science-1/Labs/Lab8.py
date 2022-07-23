# CS1210: Lab8 [2 second chances]
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely the work of you and your partners, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano","gmfisher","adalsing"])

######################################################################
# SPECIAL NOTE: Back by popular demand are Exam2 questions 3 and 4!
#
# Folks: these are important concepts. Since many of you had trouble
# with them, it's worth discussing in your groups. At the end of the
# day, I really want all of you to feel confident on the Exam2
# material.
#
######################################################################
# Specification: deepReplace(L, e1, e2) takes a possibly nested list,
# L, and two items, e1 and e2, and alters L by replacing all
# occurrences of e1 with e2. Your function should be recursive, and it
# should mutate or alter L; it should not construct a new list, nor
# should it return a value.
#
# Hint: When a recursive function need not return a value, sometimes
#   the base case might "seem to disappear." Be careful! Because there
#   is no value to return, it might appear that the base case is
#   missing, but it will always be there, just sometimes in the form of
#   an unmet condition from the recursive step.
#
# Example:
#   >>> L=[1, [3, 1, [4, -1], 2], [[[5]]], [1, 3]]
#   >>> deepReplace(L, 1, 'a')
#   >>> L
#   ['a', [3, 'a', [4, -1], 2], [[[5]]], ['a', 3]]
#
def deepReplace(L, old, new):
    l = []
    for i in range(len(L)):
        
        if type(L[i]) == list:
            l = deepReplace(L[i], old, new)     # recursive step
            L[i] = l
    
        if L[i] == old:                         # base case
            L[i] = new
            
    return(L)

######################################################################
# Specification: makeChange(amount, purse) takes an integer amount and
# a list of the coins you currently have in your coin purse, and
# returns True if there is a combination of your coins that equals
# amount, or False if no such combination exists. Your solution should
# be recursive.
#
# Hint: The recursive step basically notes that a solution will either
#   require using a given coin (and concomitantly reducing the remaining
#   amount) or not using a given coin (and leaving the remaining amount
#   unchanged).
#
# Hint: Let me restate that, because its important. You basically
#   unpack your purse, considering one coin at a time, and search for
#   a solution along two different branches.
#
#      In the first branch, you elect to use the coin in question,
#      reducing your problem to finding a combination that sums to
#      (amount-value) from the coins that remain in your purse.
#
#      In the second branch, you elect to skip the coin in question,
#      reducing your problem to finding a combination that sums to
#      amount from the remaining coins.
#
#  If you find a solution in either branch, you've got a solution to
#  the problem.
#
# Example:
#   >>> makeChange(23, [1, 10, 5, 10, 25, 1])
#   False
#   >>> makeChange(26, [1, 10, 5, 10, 25, 1])
#   True
#
def makeChange(amount, purse):
    for i in range(len(purse)-1):                   # selection sort
        j = i + purse[i:].index(min(purse[i:]))
        purse[i], purse[j] = purse[j], purse[i]
    
    total = 0                                       # counter variable
    for index in purse[::-1]:                       # reverses the order to biggest to smallest
        total += index
        if total == amount:                         # if equal return true
            return(True)
        elif total > amount:                        # if over amount then subtract added value and move on
            total = total - index
    return(False)                                   # if the combination doesnt exist

######################################################################
# MAKE NO CHANGES BEYOND THIS POINT.
######################################################################
if __name__ == '__main__':
    if all([ "hawkid" in x for x in signed() ]):
        print('### Warning: Complete definition of signed() or no points will be awarded!')
    print('### Provide your own test cases in the space provided; these will not be graded.\n')
