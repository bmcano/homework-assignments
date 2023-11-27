from pymongo import MongoClient

# Brandon Cano
# ECE:5845
# Homework 6; Part 3

# Connect to MongoDB
client = MongoClient('mongodb://localhost:27017')
db = client['moderndb']
business_collection = db['business']
reviews_collection = db['reviews']

# option 1
def find_closest_businesses(longitude, latitude):
    query = {
        'location': {
            '$nearSphere': {
                '$geometry': {
                    'type': 'Point',
                    'coordinates': [longitude, latitude]
                }
            }
        }
    }

    projection = { 'name': 1, 'address': 1, 'city': 1, 'state': 1, 'categories': 1, 'stars': 1, 'review_count': 1, '_id': 0 }
    closest_businesses = business_collection.find(query, projection).limit(3)
    return list(closest_businesses)

# option 2
def submit_review(business_id, review_score):
    # find the business by its ID
    business = business_collection.find_one({'business_id': business_id})
    # check to see if a business was found
    if business:
        stars = business['stars']
        review_count = business['review_count']
        new_stars = (stars * review_count + review_score) / (review_count + 1)
        
        # update the business with new rating and review count
        business_collection.update_one({'business_id': business_id}, {
            '$set': {
                'stars': new_stars,
                'review_count': review_count + 1
            }
        })
        
        # store review information in the 'reviews' collection
        review_info = { 'business_id': business_id, 'review_score': review_score }
        reviews_collection.insert_one(review_info)
        print("Review submitted.")
    else:
        print("Business not found.")

def main():
    # main loop
    while True:
        print("\nChoose an option:")
        print("1. Find closest businesses to a location\n2. Submit a review for a business\n3. Exit")
        choice = input("Enter your choice: ")
        if choice == '1':
            try:
                longitude = float(input("Enter longitude: "))
                latitude = float(input("Enter latitude: "))
                closest_businesses = find_closest_businesses(longitude, latitude)
                for business in closest_businesses:
                    print(business)
            except:
                print("\nInvalid longitude or latitude value, it must be a number.")
        elif choice == '2':
            try:
                business_id = input("Enter business ID: ")
                review_score = int(input("Enter a review score (1-5): "))
                if review_score < 1 or review_score > 5:
                    print("Score must be between 1 and 5.")
                else:
                    submit_review(business_id, review_score)
            except:
                print("Score must be a whole number between 1 and 5.")
        elif choice == '3':
            break # exit
        else:
            print("Invalid choice. Please select a valid option.") 

if __name__ == '__main__':
    main()
