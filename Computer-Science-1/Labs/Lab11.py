# CS1210: Lab11 Regular expressions
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely the work of you and your partners, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano","ayherkelman","bgerhardt"])

######################################################################
# This lab focuses on regulat expressions. We'll start by importing
# the re module.
import re
#
# wordCount(S) takes a string, S, and returns a dictionary where the
# keys are all of the words in S (convert to lower case), and the
# value is a tuple of start,end indexes in S where those words (entire
# words only, please) are found.
#
# So, for example:
#   >>> wordCount('To be or not to be, that is the question')
#   {'to': [(0, 2), (13, 15)], 'be': [(3, 5), (16, 18)], 'or': [(6, 8)], 'not': [(9, 12)], 
#    'that': [(20, 24)], 'is': [(25, 27)], 'the': [(28, 31)], 'question': [(32, 40)]}
#
# Your solution should not involve using S.split() and reconstructing
# indexes. Instead, a single dictionary comprehension should be used.
#
# Hint: use re.findall(r'...', S, re.IGNORECASE) with an appropiate
# regexp in place of r'...' to find all words composed of alphabetic
# characters: these will be your keys. You may find re.finditer() to
# be useful in constructing the values.
#
def wordCount(S):
    '''S = S.lower()
    l = list(set(re.findall(r'\b[a-z]+\b', S, re.IGNORECASE)))
    return({ i:[(m.start(), m.end()) for m in re.finditer(i, S) ] for i in l })'''
    

    '''S = S.lower()
    
    l = re.findall(r'\b\w+\b', S, re.IGNORECASE)
    l = list(set(l))
    
    D = { i:[] for i in l }

    for i in l:
        for m in re.finditer(i, S):
            D[m.group(0)].append((m.start(), m.end()))
    
    return(D)'''


    return({ i:[ (m.start(), m.end()) for m in re.finditer(i, S.lower()) ] for i in list(set(re.findall(r'\b[a-z]+\b', S.lower(), re.IGNORECASE))) })

######################################################################
# MAKE NO CHANGES BEYOND THIS POINT.
######################################################################
if __name__ == '__main__':
    if all([ "hawkid" in x for x in signed() ]):
        print('### Warning: Complete definition of signed() or no points will be awarded!')
    if wordCount('If life were predictable it would cease to be life, and be without flavor')!={'if': [(0, 2), (4, 6), (47, 49)], 'life': [(3, 7), (46, 50)], 'were': [(8, 12)], 'predictable': [(13, 24)], 'it': [(25, 27), (60, 62)], 'would': [(28, 33)], 'cease': [(34, 39)], 'to': [(40, 42)], 'be': [(43, 45), (56, 58)], 'and': [(52, 55)], 'without': [(59, 66)], 'flavor': [(67, 73)]}:
        print('### Error: {} does not produce {}'.format("wordCount('If life were predictable it would cease to be life, and be without flavor')",{'if': [(0, 2), (4, 6), (47, 49)], 'life': [(3, 7), (46, 50)], 'were': [(8, 12)], 'predictable': [(13, 24)], 'it': [(25, 27), (60, 62)], 'would': [(28, 33)], 'cease': [(34, 39)], 'to': [(40, 42)], 'be': [(43, 45), (56, 58)], 'and': [(52, 55)], 'without': [(59, 66)], 'flavor': [(67, 73)]}))
