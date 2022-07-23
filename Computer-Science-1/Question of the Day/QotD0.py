# CS1210: QotD0
######################################################################
# Specification: the signed() function returns a list containing your
# student hawkid, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
# ToDo: Change the word "hawkid" between the two double quote marks to
# match your own hawkid. Your hawkid is the "login identifier" you use
# to login to all University services, like `https://myUI.uiowa.edu/'
#
# Note: we are not asking for your password, just the login
# identifier: for example, mine is "segre".
#
def signed():
    return(["bmcano"])

######################################################################
# MAKE NO CHANGES BEYOND THIS POINT.
######################################################################
if __name__ == '__main__':
    if not isinstance(signed(), list) or len(signed()) != 1:
        print('### Careful: only change what\'s between the two quote marks.\n')
        exit()
    elif signed()[0] == "hawkid":
        print('### Error: Edit the signed() function to return your hawkid.\n')
        exit()
    print('### If your University login identifier is \'{}\', then you\'re good to go.\n'.format(signed()[0]))
    print('### Download your solution to your machine and then submit that file to ICON.\n')
