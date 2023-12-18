class WordGuesserGame

  # add the necessary class methods, attributes, etc. here
  # to make the tests in spec/wordguesser_game_spec.rb pass.

  # Get a word from remote "random word" service

  def initialize(word)
    @word = word
    @guesses = ''
    @wrong_guesses = ''
  end

  attr_accessor :word
  attr_accessor :guesses
  attr_accessor :wrong_guesses

  def guess(guess)
    if guess =~ /[[:alpha:]]/
      guess.downcase!
      if @word.include?(guess) and !@guesses.include?(guess)
        @guesses.concat(guess)
        return true
      elsif !@wrong_guesses.include?(guess) and !@word.include?(guess)
        @wrong_guesses.concat(guess)
        return true
      else return false end
    else
      guess = :invalid
      raise ArgumentError
    end
  end

  def word_with_guesses
    guesses_as_string = ""
    @word.each_char do |letter|
      if @guesses.include?(letter)
        guesses_as_string.concat(letter) 
      else
        guesses_as_string.concat("-")
      end
    end
    return guesses_as_string
  end

  def check_win_or_lose
    count = 0
    if @wrong_guesses.length >= 7
      return :lose
    end
    @word.each_char do |letter|
      if @guesses.include?(letter)
        count += 1
      end
    end
    if count == @word.length then :win
    else :play end
  end

  # You can test it by installing irb via $ gem install irb
  # and then running $ irb -I. -r app.rb
  # And then in the irb: irb(main):001:0> WordGuesserGame.get_random_word
  #  => "cooking"   <-- some random word
  def self.get_random_word
    require 'uri'
    require 'net/http'
    uri = URI('http://randomword.saasbook.info/RandomWord')
    Net::HTTP.new('randomword.saasbook.info').start { |http|
      return http.post(uri, "").body
    }
  end

end
