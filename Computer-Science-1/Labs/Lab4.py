# CS1210: Lab4 [3 functions, in increasing order of difficulty]
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely the work of you and your partners, and
#  2) it has not been shared with anyone outside the intructional team.
# N.B., add a third string to the list if there are three of you.
#
def signed():
    return(["bmcano","mrhodes1"])

######################################################################
# Specification: unused(w1, w2, all=printable[10:36]) takes two words,
# w1 and w2, or strings composed of lower-case alphabetic characters,
# and returns a tuple of lower-case alphabetic characters that do not
# occur in either word.
#
# Note that the default value of alphabet is a subsequence of the
# printable characters, imported from the string module here:
from string import printable
#
# Example:
#   >>> unused('background', 'information')
#   ('p', 'j', 'x', 'l', 'q', 'z', 's', 'y', 'w', 'h', 'v', 'e')
#   >>> unused('fitfully', 'superfactorial')
#   ('k', 'n', 'g', 'd', 'j', 'x', 'z', 'q', 'h', 'w', 'v', 'm', 'b')
#
def unused(w1, w2, alphabet=printable[10:36]):
    a = []
    for i in alphabet:
        if i not in w1:
            if i not in w2:
                a += [i]
    return(tuple(a))
    
# Provide your own test cases here: these will not be graded.

######################################################################
# Specification: isVowel(word, i) returns True if the ith character of
# word w is a vowel, otherwise returns False. You can assume
# 0<=i<len(word).
#
# To determine if a character is a vowel:
#     aeio are always vowels;
#     y is a vowel unless it follows a vowel or is the first letter of the word;
#     u is a vowel unless it follows g or q; and
#     w is a vowel when it follows a vowel.
#
# Example:
#   >>> isVowel('fitfully', 4)
#   True
#   >>> isVowel('gurgle', 1)
#   False
#   isVowel('glutton', 2)
#   True
#
def isVowel(word, i):
    a = word[i]
    if a in 'aeio':
        return True
    elif a in 'y' and (1!=0 and not isVowel(word, i-1)):
        return True
    elif a in 'u' and (i==0 or word[i-1] not in 'gq'):
        return True
    elif a in 'w' and (1!=0 and isVowel(word, i-1)):
        return True
    return False    
                

# Provide your own test cases here: these will not be graded.

######################################################################
# Specification: abjadic(S) takes a sentence, S, and converts it into
# an equivalent sentence without any vowels. You can assume the usual
# set of vowels (a, e, i, o, and u), and should conserve all non-vowel
# characters (consonants, punctuation, numerals, etc.) and should
# conserve case as well.
#
# Warning: Note that some words, like "a" or "I" may disappear
# entirely, leaving extra spaces here and there. For full credit,
# flush these extra spaces.
#
# Background: A number of ancient writing scripts are abjadic, meaning
# that only consonants are written, leaving the reader to infer the
# vowels as they go. Modern Arabic, Hebrew, Persian and Urdu are
# abjadic; many ancient languages like Aramaic, Berber, Mandaic are
# also abjadic.  In fact, even some forms of English communication
# (usually over SMS) have become informal abjads! Consider "o rly?",
# "thx", or "r u nts?", and so on (although these tend to retain the
# ``most important'' letters rather than consonants specifically).
#
# Example:
#   abjadify("In a minute!")
#   'n mnt!'
#   >>> abjadify('There but for the grace of Python go I.')
#   'Thr bt fr th grc f Pythn g .'
#
def abjadify(S):
    S = ''.join([c for c in S if c.lower() not in 'aeiouAEIOU'])
    return(' '.join([ w for w in S.split() ]))
    

# Provide your own test cases here: these will not be graded.

######################################################################
# MAKE NO CHANGES BEYOND THIS POINT.
######################################################################
if __name__ == '__main__':
    if all([ "hawkid" in x for x in signed() ]):
        print('### Warning: Complete definition of signed() or no points will be awarded!')
    print('### Provide your own test cases in the space provided; these will not be graded.\n')
