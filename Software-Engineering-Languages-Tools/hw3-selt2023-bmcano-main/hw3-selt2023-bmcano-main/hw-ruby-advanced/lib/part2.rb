class WrongNumberOfPlayersError < StandardError; end
class NoSuchStrategyError < StandardError; end

def rps_game_winner(game)
  return [] if game.class != Array
  raise WrongNumberOfPlayersError unless game.length == 2
  # R > S, S > P, P > R
  moves = %w[R P S]
  plays = [ game[0][1].upcase, game[1][1].upcase ]
  if !moves.include?(plays[0]) || !moves.include?(plays[1])
    raise NoSuchStrategyError
  end
  if plays.include?("R") && plays.include?("S")
    winner = plays.index("R")
  elsif plays.include?("S") && plays.include?("P")
    winner = plays.index("S")
  elsif plays.include?("P") && plays.include?("R")
    winner = plays.index("P")
  else
    winner = 0
  end
  game[winner]
end

def rps_tournament_winner(tournament)
  if tournament[0][0].is_a?(String)
    tournament_winner = rps_game_winner(tournament)
  else
    player_one = rps_tournament_winner(tournament[0])
    player_two = rps_tournament_winner(tournament[1])
    tournament_winner = rps_game_winner([player_one, player_two])
  end
  tournament_winner
end
