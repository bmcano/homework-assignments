# CS1210: QotD18
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work; and
#  2) it has not been shared with anyone.
def signed():
    return(["bmcano"])

######################################################################
# Recall the matrix representation of QotD16, where a matrix is
# represented as a list of lists, with each sublist representing a
# row.
#
# Specification: swapCols(M, i, j) takes a matrix, M, and two column
# indeces, and, after checking that i and j are legal 0-indexed column
# indexes, modifies the matrix by swapping column i with column j.
# Note: M need not be square as it was for flip(M) in QotD16, but it
# should be well formed (uniform number of columns per row). Your
# solution should not return a value but rather modify M.
#
def swapCols(M, i, j):
    if i < len(M[0])-1 or j < len(M[0])-1:
        for r in range(len(M)):
            M[r][i], M[r][j] = M[r][j], M[r][i]
 
######################################################################
if __name__ == '__main__':
    M0=[[1, 2],[3, 4],[5, 6],[7, 8]]
    if type(signed()) is not list:
        print('### Error: signed() must return list of hawkid(s).\n')
        exit()
    if signed()[0] == "hawkid":
        print('### Error: signed() must return your hawkid.\n')
        exit()
    if swapCols(M0, 0, 1) is not None or M0 != [[2, 1], [4, 3], [6, 5], [8, 7]]:
        print('### Error: {} does not produce {} where M0={}'.format("swapCols(M0, 0, 1)", "M0==[[2, 1], [4, 3], [6, 5], [8, 7]]", M0))
        