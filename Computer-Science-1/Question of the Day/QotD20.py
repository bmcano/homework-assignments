# CS1210: QotD20 The end of an era!
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
# Specification: showMatrix(M) takes a matrix, M, and returns a string
# consisting of the printed representation of M.
#
# Hint: if M were an object, the definition of showMatrix(M) could be
# used as the definition of that object's __repr__().
#
# Hint: for simplicity, you may assume M contains values of uniform
# length, e.g., all single digit integers, or all 3 digit integers.
#
def showMatrix(M):
    return('\n'.join(' '.join(str(c) for c in r) for r in M))
    
    m = ''
    for i in range(len(M)):
        for j in range(len(M[0])):
            m += str(M[i][j])
            if j != len(M[0])-1:
                m += ' '
        if i != len(M)-1:
            m += '\n'
    return(m)
    
######################################################################
if __name__ == '__main__':
    if type(signed()) is not list:
        print('### Error: signed() must return list of hawkid(s).\n')
        exit()
    if signed()[0] == "hawkid":
        print('### Error: signed() must return your hawkid.\n')
        exit()
    if showMatrix([[1, 2],[3, 4],[5, 6],[7, 8]]) != '1 2\n3 4\n5 6\n7 8':
        print('### Error: {} does not produce {}'.format("showMatrix([[1, 2],[3, 4],[5, 6],[7, 8]])", '1 2\n3 4\n5 6\n7 8'))
    if showMatrix([[1, 2, 3],[3, 5, 6]]) != '1 2 3\n3 5 6':
        print('### Error: {} does not produce {}'.format("showMatrix([[1, 2, 3],[3, 5, 6]])", '1 2 3\n3 5 6'))
