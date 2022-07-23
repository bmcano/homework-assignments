# CS1210: Lab6 [3 functions that code and decode]
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely the work of you and your partners, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano","bhoefle","ikazembe","klayala"])

######################################################################
# Today's lab revisits our class obsession with English words. 
# The objective of today's lab is to create a system that takes
# English sentences and converts them to Ubbi Dubbi, a "play" language
# used by children and popularized many years ago by Zoom, a PBS
# program for kids.
#
# In this language, an extra "ub" is inserted between each syllable of
# a word, so that "together" becomes, for example,
# "tubogethuber". Here, we build a generic Ubbi Dubbi encoding system
# that uses specific definitions of vowels, consonants, and syllables
# and allows the user to specify the "ub".
#
# To simplify things, we'll bring back our isVowel() solution from
# lab4, which is included here and defines what a vowel is. You can
# assume a consonant is defined as a non-vowel.
#
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
def isVowel(word, i):
    if word[i].lower() in 'aeio':
        return(True)
    elif word[i].lower() in 'y' and (i!=0 and not isVowel(word, i-1)):
        return(True)
    elif word[i].lower() in 'u' and (i==0 or word[i-1].lower() not in 'gq'):
        return(True)
    elif word[i].lower() in 'w' and (i!=0 and isVowel(word, i-1)):
        return(True)        
    return(False)

######################################################################
# Next, a "cluster" is a sequence of contiguous vowels or
# consonants. So, for example, in word 'tomorrow' breaks into 6
# clusters, ['t', 'o', 'm', 'o', 'rr', 'ow']. A "syllable" is defined
# as a vowel cluster followed a consonant cluster; note that words
# that start with consonants will have a leading half syllable, while
# words that end with vowels will have a trailing half syllable. The
# word 'tomorrow' has 6 clusters, but 4 syllables ['t', 'om', 'orr',
# 'ow'], with a leading half syllable 't' (no vowels to start it) and
# a trailing half syllable 'ow' (no consonants to end it).
# 
# Specification: syllables(w) takes a word, w, and returns the list of
# syllables that make up that word. A syllable is a maximally long
# sequence of vowels followed by a maximally long sequence of
# consonants (i.e., non-vowels). Your solution should maintain the
# case of the letters in the original word.
#
# Use the most appropriate form of iteration/comprehension/recursion.
#
def syllables(w):

    i = 0
    s = ""
    while i < len(w):
        if isVowel(w, i) == False:
            s += str(w[i])
        elif isVowel(w, i-1) == True:
            s += str(w[i])
        else:
            s += " " + str(w[i])
        i = i + 1
    return(list(s.split(" ")))


######################################################################
# Now we're ready to encode a sentence, S, into our new language using
# a particular "ub", or sequence of repeated letters inserted between
# syllables.
#
# Specification; encode(S, uh) takes a sentence, S, and returns the
# translated version of the sentence where the ub value is inserted
# between each syllable in the constituent words of S.
#
# Example:
#   >>> encode('Four score and seven years ago', 'ub')
#   'Fubour scuborube and subevuben yubears agubo'
#   >>> encode('Four score and seven years ago', 'ay')
#   'Fayour scayoraye and sayevayen yayears agayo'
#
def encode(S, ub):
        
    enc = ''
    l = S.split()
    for j in l:
        e = syllables(j)
        for i in e:
            if i == e[-1]:
                enc += i
            else:
                enc += i + ub
        if j != l[-1]:
            enc += " "

    return enc
######################################################################
# Finally, let's decode an ubbified sentence, S: this is actually much
# harder than it looks, but I think its worth trying. The grading here
# will be very lax. The problem occurs when there is an interplay
# between the "ub" and the structure of the ubbified words: what has
# been ubbified may not always be decodable! Note: it is easier, I
# believe, if the "ub" you have chosen is itself a legal syllable.
#
# Specification: decode(S, ub) takes an ubbified sentence, S, and
# restores it by removing the added ubs and restoring the sentence.
#
# Example:
#   >>> encode('tomorrow', 'ub')
#   'tubomuborrubow'
#   >>> decode('tubomuborrubow', 'ub')
#   'tomorrow'
#
def decode(S, ub):
    return(''.join(S.replace(ub, '')))


######################################################################
# MAKE NO CHANGES BEYOND THIS POINT.
######################################################################
if __name__ == '__main__':
    if all([ "hawkid" in x for x in signed() ]):
        print('### Warning: Complete definition of signed() or no points will be awarded!')
    print('### Provide your own test cases in the space provided; these will not be graded.\n')
