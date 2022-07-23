# CS1210: QotD16
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work; and
#  2) it has not been shared with anyone.
def signed():
    return(["bmcano"])

######################################################################
# The transpose of a matrix is what you get when you flip the matrix
# along the diagonal (from upper left to lower right). Here, we'll
# deal exclusivly with square matrices, or matrices with an equal
# number of rows and columns.
#
# So assume you have a 3x3 square matrix, M, that looks like:
#   1 2 3
#   4 5 6
#   7 8 9
# The "flipped" version of this matrix is its transpose:
#   1 4 7   => same as column 1 above
#   2 5 8   => same as column 2 above
#   3 6 9   => same as column 3 above
# Note how the diagonal elements 1, 5, 9 are unchanged.
#
# Here, we'll represent a matrix as a list of rows, with each row also
# being a list of element. So the matrix M shown above would be
# represented as:
#   [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
# and its transpose would be:
#   [[1, 4, 7], [2, 5, 8], [3, 6, 9]]
# this representation allows you to reference individual elements of
# the matrix as M[r][c], where r and c are integer indexes. So the
# upper left corner would be M[0][0], and the diagonal elements all
# have the form M[i][i].
#
# Specification: write a function flip(M) that takes a nested list
# structure representing a square matrix and "flips" that structure
# diagonally "in place" by modifying the nested list structure (i.e.,
# not creating a new list structure).
#
# Hint: Remember, this function changes M, so think assignment rather
# than constructing new structure. As a corollarly, because you're not
# building new structure, you probably shouldn't be using a
# comprehension.
#
def flip(M):
    #return([[M[index][i] for index in range(len(M))] for i in range(len(M[0]))])
    for r in range(1, len(M)):
        for c in range(r):
            M[r][c], M[c][r] = M[c][r], M[r][c]
    
######################################################################
# MAKE NO CHANGES BEYOND THIS POINT.
######################################################################
M0 = [list(range(11, 14)), list(range(21, 24)), list(range(31, 34))]
M1 = [list(range(11, 15)), list(range(21, 25)), list(range(31, 35)), list(range(41, 45))]
if __name__ == '__main__':
    if type(signed()) is not list:
        print('### Error: signed() must return list of hawkid(s).\n')
        exit()
    if signed()[0] == "hawkid":
        print('### Error: signed() must return your hawkid.\n')
        exit()
    if flip(M0) is None and M0!=[[11, 21, 31], [12, 22, 32], [13, 23, 33]]:
        print('### Error: {} does not change {} to {}'.format("flip(M)", [list(range(11, 14)), list(range(21, 24)), list(range(31, 34))], [[11, 21, 31], [12, 22, 32], [13, 23, 33]]))
    if flip(M1) is None and M1!=[[11, 21, 31, 41], [12, 22, 32, 42], [13, 23, 33, 43], [14, 24, 34, 44]]:
        print('### Error: {} does not change {} to {}'.format("flip(M)", [list(range(11, 15)), list(range(21, 25)), list(range(31, 35)), list(range(41, 45))], [[11, 21, 31, 41], [12, 22, 32, 42], [13, 23, 33, 43], [14, 24, 34, 44]]))
