import psycopg2
from queries import list_countries, search_city, add_city, update_city, delete_city, list_active_venues, list_inactive_venues, delete_venue, add_event

# Brandon Cano
# ECE:5845
# Homework 3

def main():
    connection = None
    username = ""
    password = ""
    try:
        # get credentials
        username, password = get_credentials(username, password)
        # connect to the uiowa phpPgAdmin server
        print(username)
        print(password)
        connection = connect(username, password)
        # create a cursor from the connection
        cursor = connection.cursor()
        menu_loop(cursor)
	    # close the communication with the PostgreSQL
        cursor.close()
    except (Exception, psycopg2.DatabaseError) as error:
        print(error)
    finally:
        if connection is not None:
            connection.close()
            print('Database connection closed.')

def menu_loop(cursor):
    menu_selection = -1
    while (menu_selection != "10"):
        menu_selection = input(
        """
        Select an option from the menu (everything is case sensitive):
         1.  List all the countries
         2.  Search for a city
         3.  Add a new city
         4.  Update a city name, county code, or postal code
         5.  Delete a city
         6.  List all the active venues given a country code
         7.  List of the inactive venues  
         8.  Delete a venue using the venue name
         9.  Add event
         10. Exit
        """
        )
        # navigate to the option selected
        if (menu_selection == "1"):
            list_countries(cursor)
        elif (menu_selection == "2"):
            search_city(cursor)
        elif (menu_selection == "3"):
            add_city(cursor)
        elif (menu_selection == "4"):
            update_city(cursor)
        elif (menu_selection == "5"):
            delete_city(cursor)
        elif (menu_selection == "6"):
           list_active_venues(cursor)
        elif (menu_selection == "7"):
            list_inactive_venues(cursor)
        elif (menu_selection == "8"):
            delete_venue(cursor)
        elif (menu_selection == "9"):
            add_event(cursor)
        elif (menu_selection == "10"):
            print("Exiting...")
        else:
            print("Invalid selection")

def connect(username, password):
    # connect to database server and return the connection
    print("Connecting to phpPgAdmin PostgreSQL database...")
    return psycopg2.connect(
            host="s-l112.engr.uiowa.edu",
            database="mdb_student31",
            user=username,
            password=password,
            port=5432,
            options=f'-c search_path=homework,public'
        )    

def get_credentials(username, password):
    f = open("credentials.txt", "r")
    username = f.readline()
    password = f.readline()
    return (username.strip(), password.strip())

# run the program
if __name__ == "__main__":
    main()