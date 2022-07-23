# CS1210: Lab1 [7 functions to complete]
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely the work of you and your partners, and
#  2) it has not been shared with anyone outside the intructional team.
# N.B., add a third string to the list if there are three of you.
#
def signed():
    return(["bmcano","bgerhardt"])

######################################################################
# Specification: ac2msq(a) takes a number, representing an acreage,
# and returns how many meters squared are in that acreage.
#
# Background: An acre is a measure of land, equivalent to to what
# could be ploughed in a single day by a yoke of oxen. It is also
# 4,047 m^2 (meters squared).
#
# Example:
#   >>> ac2msq(40)
#   161880
#
def ac2msq(a):
    x = a * 4047
    return x

######################################################################
# Specification: msq2ac(msq) takes a number, representing an area
# measured in meters squared, and returns an equivalent number of
# acres.
#
# Example:
#   >>> msq2ac(1000)
#   0.24709661477637757
#
def msq2ac(msq):
    x = msq/4047
    return x		

######################################################################
# Specification: triplePlay(S1, S2) takes two strings, S1 and S2, and
# returns a new string that consists of 1 copy of S1, followed by 2
# copies of S2, followed by 3 copies of S1.
#
# Example:
#   >>> triplePlay("a", "b")
#   'abbaaa'
#
def triplePlay(S1,S2):
    S3 = S1 + (2*S2) + (3*S1)
    return S3	

######################################################################
# Specification: notThere(S1, S2) takes two strings, S1 and S2, and
# returns True only if S1 does not appear within S2.
#
# Example:
#   >>> notThere('b', 'foo')
#   True
#   >>> notThere('o', 'foo')
#   False
#
def notThere(S1, S2):
    if S1 not in S2:
        return True
    else:
        return False

######################################################################
# Specification: mTimer(h,m) takes two integers, h and m, representing
# a time of day on a 24 hour clock; it returns the corresponding
# number of minutes past midnight.
#
# Example:
#   >>> mTimer(12, 30)
#   750
#
def mTimer(h, m):
    x = (h*60) + m
    return x

######################################################################
# Specification: hTimer(h,m) takes two integers, h and m, representing
# a time of day on a 24 hour clock; it returns the corresponding
# number of (possibly fractional) hours past midnight.
#
# Example:
#   >>> hTimer(12, 30)
#   12.5
#
def hTimer(h, m):
    x = h + (m/60)
    return x

######################################################################
# Specification: euro2us(h,m) takes two integers, h and m,
# representing a time of day on a 24 hour clock; it returns the
# equivalent time as a tuple, but expressed on a 12 hour clock.
#
# Recall a tuple is a sequence data type, expressed as multiple
# comma-separated values within parenthesis, e.g., (1, 3, 3)
#
# Example:
#   >>> mil2us(11, 30)
#   (11, 30)
#   >>> mil2us(18, 30)
#   (6, 30)
# 
def mil2us(h, m):
    if h > 12:
        x = h - 12
    t = (x, m)
    return t
######################################################################
# MAKE NO CHANGES BEYOND THIS POINT.
######################################################################
if __name__ == '__main__':
    print('### Absence of Warnings or Errors indicates a clean pass through the test cases provided.\n')
    if all([ "hawkid" in x for x in signed() ]):
        print('### Warning: Complete definition of signed() or no points will be awarded!')
    if ac2msq(45)!=182115:
        print('### Error: ac2msq(45) returns {}, expected 182115.'.format(ac2msq(45)))
    if not (isinstance(msq2ac(100000), float) and 24.70 <= msq2ac(100000) <= 24.71):
        print('### Error: msq2ac(100000) returns {}, expected (roughly) 24.705.'.format(msq2ac(100000)))
    if triplePlay('bab', 'cd')!='babcdcdbabbabbab':
        print("### Error: triplePlay('bab', 'cd') returns {}, expected 'babcdcdbabbabbab'.".format(msq2ac(100000)))
    if not (notThere('boo', 'booboo')==False and notThere('baa', 'booboo')==True):
        if not notThere('boo', 'booboo'):
            print("### Error: notThere('boo', 'booboo') returns {}, expected False.".format(notThere('boo', 'booboo')))
        elif notThere('baa', 'booboo'):
            print("### Error: notThere('baa', 'booboo') returns {}, expected True.".format(notThere('baa', 'booboo')))
    if mTimer(15, 45)!=945:
        print("### Error: mTimer(15, 45) returns {}, expected 945.".format(mTimer(15, 45)))
    if hTimer(15, 45)!=15.75:
        print("### Error: hTimer(15, 45) returns {}, expected 15.75.".format(hTimer(15, 45)))
    if mil2us(15, 45)!=(3, 45):
        print("### Error: mil2us(15, 45) returns {}, expected (3, 45).".format(mil2us(15, 45)))
