from neo4j import GraphDatabase

# Brandon Cano
# ECE:5845
# Homework 8

PASSWORD="add yours here"

class Neo4jConnection:

    def __init__(self, uri, user, password):
        self.driver = GraphDatabase.driver(uri, auth=(user, password))

    def close(self):
        print("Closing connection...")
        self.driver.close()

    def get_user_from_id(self, user_id):
        with self.driver.session() as session:
            record = session.execute_write(self._parse_and_return_user, user_id)
            if not record or record["name"] is None:
                user_name = input("Enter your name: ")
                session.run("MATCH (u:User {userId: $user_id})"
                            "SET u.name = $user_name", user_id=user_id, user_name=user_name)
                print(f"Name '{user_name}' added for User ID {user_id}.")
            
            return session.execute_write(self._parse_and_return_user, user_id)

    @staticmethod
    def _parse_and_return_user(tx, user_id):
        result = tx.run("MATCH (u:User {userId: $user_id}) "
                        "RETURN u.name IS NOT NULL AS name_exists, u.name AS name", user_id=user_id)
        return result.single()

    def get_movies_from_keyword(self, user_id, keywords):
        with self.driver.session() as session:
            return session.execute_write(self._search_for_movies_from_keywords, user_id, keywords)

    @staticmethod
    def _search_for_movies_from_keywords(tx, user_id, keywords):
        query = (
            "MATCH (u:User {userId: $user_id})-[:RATED]->(m:Movie) "
            "WHERE toLower(m.title) CONTAINS toLower($keyword) "
            "OPTIONAL MATCH (m)-[:IN_GENRE]->(g:Genre) "
            "OPTIONAL MATCH (m)<-[r:RATED]-(u) "
            "WITH m, g, AVG(r.rating) AS avg_rating, COUNT(r) AS user_rating_count, u "
            "RETURN m.title AS title, COLLECT(g.name) AS genres, avg_rating, "
            "(CASE WHEN user_rating_count > 0 THEN true ELSE false END) AS has_rated, "
            "HEAD(labels(u)) = 'User' AS is_user "
            "ORDER BY m.title"
        )
        result = tx.run(query, user_id=user_id, keyword=keywords)
        records = []
        for record in result:
                records.append(record)
                print(f"Title: {record['title']}")
                print(f"Genres: {', '.join(record['genres'])}")
                print(f"Average Rating: {record['avg_rating'] or 'N/A'}")
                print(f"User Has Rated: {record['has_rated']}")
                if record['has_rated']:
                    print(f"User's Rating: {record['is_user'] and 'N/A' or record['rating']}")
                print("")
        return records
        
    def get_top_five_movie_recommendations(self, user_id):
        with self.driver.session() as session:
            return session.execute_write(self._search_top_movie_recommendations, user_id)

    @staticmethod
    def _search_top_movie_recommendations(tx, user_id):
        query = (
            "MATCH (u:User {userId: $user_id})-[:RATED]->(:Movie)-[:IN_GENRE]->(g:Genre) "
            "WITH u, g, COUNT(*) AS genreCount "
            "ORDER BY genreCount DESC LIMIT 1 "
            "MATCH (u)-[:RATED]->(m:Movie)-[:IN_GENRE]->(topGenre:Genre) "
            "WITH u, COLLECT(topGenre.name) AS topGenres "
            "MATCH (m2:Movie)-[:IN_GENRE]->(genre:Genre) "
            "WHERE NOT (u)-[:RATED]->(m2) AND genre.name IN topGenres "
            "RETURN m2.movieId as movie_id, m2.title AS movie_title LIMIT 5"
        )
        result = tx.run(query, user_id=user_id)
        records = []
        for record in result:
                records.append(record)
        return records

    def add_movie_rating_from_id(self, user_id, movie_id, rating):
        with self.driver.session() as session:
            query = (
                "MATCH (u:User {userId: $user_id}) "
                "MATCH (m:Movie {movieId: $movie_id}) "
                "MERGE (u)-[r:RATED]->(m) "
                "SET r.rating = $rating "
                "RETURN r.rating"
            )
            result = session.run(query, user_id=user_id, movie_id=movie_id, rating=rating)
            return result.single()

def get_user_id():
    while True:
        try:
            id = input("Enter your user ID number (1-600):\n")
            idn = int(id)
            if idn < 1 or idn > 600:
                print("The value you entered is not within the range of 1-600.")
            else:
                return idn
        except:
            print("The value you entered was not a valid integer.")
    

if __name__ == '__main__':
    # Step 0: connect to the neo4j database
    connection = Neo4jConnection("bolt://localhost:7687", "neo4j", PASSWORD)
    # Step 1: ask for an user id and get user node
    user_id = get_user_id()
    user = connection.get_user_from_id(user_id)
    print(f"Welcome, {user['name']}!")

    # menu options
    option = ""
    while option != "3":
        option = input("""
        Select an option:
        1. Search for a movie
        2. Get top 5 movie recommendations (and add reviews to these)
        3. Exit
        """)

        if option == "1":
            # Step 2: search for a movie title
            movie_search = input("Search for a movie: ")
            movie_matches = connection.get_movies_from_keyword(user_id, movie_search)
        elif option == "2":
            # Step 3: give top 5 recommendations
            top_movies = connection.get_top_five_movie_recommendations(user_id)
            valid_ids = []
            for movie in top_movies:
                valid_ids.append(movie['movie_id'])
                print(f"{movie['movie_title']}, Movie ID: {movie['movie_id']}")
            # Step 4: provide a rating 
            movie_id_to_rate = -1
            while movie_id_to_rate not in valid_ids:
                try:
                    movie_id_to_rate = int(input("\nOf the listed movies, which one (movie_id) would you like to add a rating to? "))
                    if movie_id_to_rate not in valid_ids:
                        print("Please enter a valid ID from the listed movies.")
                        continue
                    rating_of_movie = float(input("\nWhat rating would you like to add (format=x.x)? "))
                except Exception as e:
                    print("Enter a valid integer/float values.")
            result = connection.add_movie_rating_from_id(user_id, movie_id_to_rate, rating_of_movie)
            print(f"Rating added: {result}")

    # Step 5: close connection
    connection.close()
