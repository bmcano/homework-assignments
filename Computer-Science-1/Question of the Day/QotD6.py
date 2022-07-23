# CS1210: QotD6
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# Specification: collectConsonants(S) takes a string, S, and returns a
# new string containing all of the non-vowel characters in S.
#
# Example:
#   >>> collectConsonants('Seize the day!')
#   'Sz th dy!'
#   >>> collectConsonants("Midway on our life’s journey, I found myself in dark woods")
#   'Mdwy n r lf’s jrny,  fnd myslf n drk wds'
#
# For full credit, your solution should use a list comprehension; it
# should not use for/while, if/elif/else or assignment.
#
# Hint: work on filtering out the vowels first, then worry about
# making a string out of it.
def collectConsonants(S):
    return(''.join([x for x in S if x.lower() not in 'aeiou']))

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
    if collectConsonants('Welcome to CS1210!')!='Wlcm t CS1210!':
        print('### Error: collectConsonants{} does not return {}'.format('Welcome to CS1210!','Wlcm t CS1210!'))
    if collectConsonants('The course will be taught in Python')!='Th crs wll b tght n Pythn':
        print('### Error: collectConsonants{} does not return {}'.format('The course will be taught in Python', 'Th crs wll b tght n Pythn'))
