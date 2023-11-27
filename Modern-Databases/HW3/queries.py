import psycopg2
import re

COUNTRIES_TABLE = '"homework"."countries"'
CITIES_TABLE = '"homework"."cities"'
VENUES_TABLE = '"homework"."venues"'
EVENTS_TABLE = '"homework"."events"'

# file to hold all functions that perform a query
def list_countries(cursor):
    # List all the countries
    cursor.execute(f'SELECT * FROM {COUNTRIES_TABLE};')
    countries = cursor.fetchall()
    for row in countries:
        print(f"Country Code: {row[0]} | Country: {row[1]}")
    
def search_city(cursor):
    # Search for/select a city/cities based on postal code, country code, and/or name
    user_input = -1
    while (user_input != "4"):
        user_input = input(
        """
        Search a city by: 
        1. Name
        2. Postal code
        3. Country code
        4. Exit to main menu
        """
        )
        if (user_input == "1"):
            city_name = input("Enter a city name: ")
            cursor.execute(f'SELECT * FROM {CITIES_TABLE} WHERE name = \'{city_name}\'')
            output = cursor.fetchall()
            search_term_output(output, city_name)
        elif (user_input == "2"):
            postal_code = input("Enter a postal code: ")
            cursor.execute(f'SELECT * FROM {CITIES_TABLE} WHERE postal_code = \'{postal_code}\'')
            output = cursor.fetchall()
            search_term_output(output, postal_code)
        elif (user_input == "3"):
            country_code = input("Enter a country code: ")
            cursor.execute(f'SELECT * FROM {CITIES_TABLE} WHERE country_code = \'{country_code}\'')
            output = cursor.fetchall()
            search_term_output(output, country_code)
        elif (user_input == "4"):
            break
        else:
            print("Invalid menu option")
            output = []

def add_city(cursor):
    # Add a new city to the cities table
    city_name = input("Enter a city to add: ")
    postal_code = input("Enter its postal code: ")
    country_code = input("Enter the country code of the city: ")
    
    if not is_valid_postal_code(postal_code):
        print(f"Invalid postal code, {postal_code}, was entered, needs to be 9 or less characters.")
        return
    if not is_valid_countrty_code(cursor, country_code):
        print(f"Invalid country code, {country_code}, was entered, needs to be 2 characters and in the database.")
        return

    cursor.execute(f'INSERT INTO {CITIES_TABLE} VALUES (\'{city_name}\',\'{postal_code}\',\'{country_code}\');')
    print("City information add to the databse")

def update_city(cursor):
    # Update a city name, country code, and/or postal code
    city_name = input("Enter a city to modify: ")
    postal_code = input("Enter the updated postal code: ")
    country_code = input("Enter the updated country code: ")

    if not is_valid_postal_code(postal_code):
        print(f"Invalid postal code, {postal_code}, was entered, needs to be 9 or less characters.")
        return
    if not is_valid_countrty_code(cursor, country_code):
        print(f"Invalid country code, {country_code}, was entered, needs to be 2 characters and in the database.")
        return

    cursor.execute(f'UPDATE {CITIES_TABLE} SET postal_code = \'{postal_code}\', country_code = \'{country_code}\' WHERE name = \'{city_name}\';')
    print("City information has been updated")
    
def delete_city(cursor):
    # Delete a city
    city_name = input("Enter a city to delete from the database: ")
    cursor.execute(f'DELETE FROM {CITIES_TABLE} WHERE name = \'{city_name}\';')
    print("City has been deleted")

def list_active_venues(cursor):
    # List all the active venues given a country code
    country_code = input("Give a country code (xy): ")
    cursor.execute(f'SELECT * FROM {VENUES_TABLE} WHERE country_code = \'{country_code}\' AND inactive = FALSE')
    venues = cursor.fetchall()
    if len(venues) == 0: print(f"There are no active venues with the country code: {country_code}")
    for venue in venues:
        print(venue)

def list_inactive_venues(cursor):
    # List of the inactive venues  
    cursor.execute(f'SELECT * FROM {VENUES_TABLE} WHERE inactive = TRUE')
    venues = cursor.fetchall()
    if len(venues) == 0: print("There are no inactive venues.")
    for venue in venues:
        print(venue)

def delete_venue(cursor):
    # Delete a venue using the venue name (mark inactive true)
    venue_name = input("Enter a venue name to delete: ")
    cursor.execute(f'SELECT * FROM {VENUES_TABLE} WHERE name=\'{venue_name}\';')
    venues = cursor.fetchall()
    if len(venues) == 0: 
        print("There are no venues with this name.")
        return
    for venue in venues:
        print(venue)
    confirm = input("The venues with the name are above. Are you sure you want to delete these? (y/n) > ")
    if confirm.lower() == 'y':
        cursor.execute(f'DELETE FROM {VENUES_TABLE} WHERE name=\'{venue_name}\';')
        print(f"All venues with the name, {venue_name}, have been deleted.")
    else:
        print("Return back to main menu, no venues will be deleted.")

def add_event(cursor):
    # Add an event
    title = input("Give a name for the event: ")
    starts = input("Give the start time in the format (year-month-day ab:cd:ef): ")
    ends = input("Give the end time in the format (year-month-day ab:cd:ef): ")
    venue = input("Give the venue where the event will occur: ")
    postal = input("Give the postal code of where the event is at: ")
    country = input("Give the country code where the event will occur: ")

    if not is_valid_timestamp(starts):
        print(f"Invalid time stamp, {starts}, was entered, needs to be in the format: year-month-day ab:cd:ef")
        return
    if not is_valid_timestamp(ends):
        print(f"Invalid time stamp, {ends}, was entered, needs to be in the format: year-month-day ab:cd:ef")
        return
    if not does_postal_code_exist(cursor, postal):
        print(f"Postal code, {postal}, does not currently exist in the system, please use one currently in the database.")
        return
    if not is_valid_countrty_code(cursor, country):
        print(f"Invalid country code, {country}, was entered, needs to be 2 characters and in the database.")
        return
    
    # add_event(text, timestamp without time zone, timestamp without time zone, text, character varying, character)
    cursor.execute(f'SELECT add_event(text \'{title}\', timestamp \'{starts}\', timestamp \'{ends}\', text \'{venue}\', VARCHAR \'{postal}\', character \'{country}\')')
    print("Event has been added to the database")

def search_term_output(results, search_term):
    # if someone searches for something this will give a message if there was no results
    if results == []:
        print(f"There were no results found from your search of '{search_term}'.") 
    else:
        print(results)

def is_valid_postal_code(code):
    return len(code) <= 9

def is_valid_countrty_code(cursor, code):
    cursor.execute(f"SELECT * FROM {COUNTRIES_TABLE} WHERE country_code = '{code}'")
    result = cursor.fetchall()
    return result != []

def is_valid_timestamp(time):
    return re.match(r"\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}", time)

def does_postal_code_exist(cursor, code):
    cursor.execute(f'SELECT * FROM {CITIES_TABLE} WHERE postal_code = \'{code}\'')
    result = cursor.fetchall()
    return result != []

# Task 3 queries:
# ALTER TABLE venues ADD COLUMN inactive BOOLEAN DEFAULT FALSE;
#
# CREATE OR REPLACE FUNCTION mark_venue_inactive()
# RETURNS TRIGGER AS $$
# BEGIN
#     UPDATE homework.venues
#     SET inactive = TRUE
#     WHERE venue_id = OLD.venue_id;
#     RETURN NULL;
# END;
# $$ LANGUAGE plpgsql;
#
# CREATE TRIGGER trigger_mark_inactive
# BEFORE DELETE ON venues
# FOR EACH ROW
# EXECUTE FUNCTION mark_venue_inactive();