class Movie < ActiveRecord::Base
  def self.all_ratings
    %w(G PG PG-13 NC-17 R)
  end

  class Movie::InvalidKeyError < StandardError ; end

  def self.find_in_tmdb(string)
    begin
      Tmdb::Api.key("f4702b08c0ac6ea5b51425788bb26562")
      tmdb_array = []
      movie_array = Tmdb::Movie.find(string)
      movie_array.each do |movie|
        tmdb_array.append({ :id => movie.id, :rating => rating_of_movie(movie.id), :release_date => movie.release_date, :title => movie.title })
      end
    rescue Tmdb::InvalidApiKeyError
        raise Movie::InvalidKeyError, 'Invalid API key'
    end
    tmdb_array
  end

  def self.rating_of_movie(id)
    release_dates = Tmdb::Movie.releases(id)
    us_release = release_dates['countries'].find { |result| result['iso_3166_1'] == 'US' && result['certification'] != "" }
    us_release["certification"] if us_release
  end

  def self.add_tmdb_movie(id)
    print("check")
    detail_of_movies = Tmdb::Movie.detail(id)
    movie_check = {:title => detail_of_movies["title"], :rating => rating_of_movie(detail_of_movies["id"]), :release_date => detail_of_movies["release_date"]}
    Movie.create!(movie_check)
  end
end
