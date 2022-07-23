# CS1210: QotD19
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
# Specification: swapRows(M, i, j) takes a matrix, M, and two column
# indeces, and, after checking that i and j are legal 0-indexed row
# indexes, modifies the matrix by swapping row i with row j.
# Note: M need not be square as it was for flip(M) in QotD16, but it
# should be well formed (uniform number of columns per row). Your
# solution should not return a value but rather modify M.
#
def swapRows(M, i, j):
    if i < len(M)-1 or j < len(M)-1:
        M[i], M[j] = M[j], M[i]

######################################################################
if __name__ == '__main__':
    M0=[[1, 2],[3, 4],[5, 6],[7, 8]]
    if type(signed()) is not list:
        print('### Error: signed() must return list of hawkid(s).\n')
        exit()
    if signed()[0] == "hawkid":
        print('### Error: signed() must return your hawkid.\n')
        exit()
    if swapRows(M0, 1, 2) is not None or M0 != [[1, 2], [5, 6], [3, 4], [7, 8]]:
        print('### Error: {} does not produce {} where M0={}'.format("swapRows(M0, 1, 2)", "M0==[[1, 2], [5, 6], [3, 4], [7, 8]]", M0))
