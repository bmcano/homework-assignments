# CS1210: QotD7
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# Specification: fingerprints(S) takes a string, S, consisting of a
# collection of words separated by spaces, and returns a set of word
# "fingerprints," where a word fingerprint is a string of the word's
# unique lowercase constituent characters in sorted order (e.g., the
# fingerprint of "fingerprint" is "efginprt").
#
# Example:
#   >>> fingerprints("The rain in Spain falls mainly in the plains")
#   {'ainr', 'afls', 'in', 'eht', 'ailmny', 'ailnps', 'ainps'}
# 
# For full credit, your solution should use a comprehension; it
# should not use for/while, if/elif/else, or assignment.
#
# Hint: you may wish to use the sorted() function that returns a new
# list of sorted elements from its argument.
#
def fingerprints(S):
    return(set([''.join(sorted(set(S.lower().split()[x]))) for x in range(len(S.split()))]))

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
    if fingerprints('This is a test of the Emergency Broadcast System')!={'a', 'is', 'cegmnry', 'eht', 'fo', 'abcdorst', 'hist', 'est', 'emsty'}:
        print('### Error: fingerprints(\'{}\') does not return {}'.format('This is a test of the Emergency Broadcast System',{'a', 'is', 'cegmnry', 'eht', 'fo', 'abcdorst', 'hist', 'est', 'emsty'}))
    if fingerprints('Question of the Day')!={'eht', 'einoqstu', 'fo', 'ady'}:
        print('### Error: fingerprints(\'{}\') does not return {}'.format('Question of the Day', {'eht', 'einoqstu', 'fo', 'ady'}))
