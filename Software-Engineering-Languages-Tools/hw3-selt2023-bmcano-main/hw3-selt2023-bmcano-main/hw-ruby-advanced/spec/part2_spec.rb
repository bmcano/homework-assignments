require 'part2.rb'
require 'rspec.rb'

# 30 pts for part A
describe '#rps_game_winner' do
  it 'must be defined [0 point]' do
    expect { rps_game_winner([%w[X P], %w[Y S]]) }.not_to raise_error
  end

  # this is given for free in the outline
  it 'raises WrongNumberOfPlayersError if there are not exactly two players [1 point]' do
    expect { rps_game_winner([%w[Allen S]]) }.to raise_error(WrongNumberOfPlayersError), 'No error raised for incorrect number of players'
  end
  # => returns the list ["Mary", "S"] wins since S>P
  it 'player 2 wins' do
    expect(rps_game_winner([%w[John P], %w[Mary S]])).to eq(%w[Mary S])
    expect(rps_game_winner([%w[John S], %w[Mary R]])).to eq(%w[Mary R])
    expect(rps_game_winner([%w[John R], %w[Mary P]])).to eq(%w[Mary P])
  end

  it 'player 1 wins' do
    expect(rps_game_winner([%w[John P], %w[Mary R]])).to eq(%w[John P])
    expect(rps_game_winner([%w[John S], %w[Mary P]])).to eq(%w[John S])
    expect(rps_game_winner([%w[John R], %w[Mary S]])).to eq(%w[John R])
  end

  it 'players draw' do
    expect(rps_game_winner([%w[John P], %w[Mary P]])).to eq(%w[John P])
    expect(rps_game_winner([%w[John S], %w[Mary S]])).to eq(%w[John S])
    expect(rps_game_winner([%w[John R], %w[Mary R]])).to eq(%w[John R])
  end

  it 'invalid guesses' do
    expect { rps_game_winner([%w[John D], %w[Mary P]]) }.to raise_error(NoSuchStrategyError)
    expect { rps_game_winner([%w[John D], %w[Mary D]]) }.to raise_error(NoSuchStrategyError)
    expect { rps_game_winner([%w[John R], %w[Mary T]]) }.to raise_error(NoSuchStrategyError)
  end

  it 'invalid objects' do
    expect(rps_game_winner("String")).to eq([])
    expect(rps_game_winner(:h)).to eq([])
    expect(rps_game_winner(true)).to eq([])
    expect(rps_game_winner(76345967834)).to eq([])
    expect(rps_game_winner(NIL)).to eq([])
  end
end

# 70 pts for part B
describe '#rps_tournament_winner' do
  it 'must be defined' do
    expect(-> { rps_tournament_winner([%w[X P], %w[Y S]]) }).not_to raise_error
  end

  it 'even bracket winner' do
    expect(rps_tournament_winner([
                                   [
                                     [%w[Joe P], %w[Mary S]],
                                     [%w[Bob R], %w[Alice S]]
                                   ],
                                   [
                                     [%w[Steve S], %w[Jane P]],
                                     [%w[Ted R], %w[Carol P]]
                                   ]
                                 ])).to eq(%w[Bob R])
  end

  it 'uneven bracket setup' do
    expect(rps_tournament_winner([
                                   [
                                     [%w[Joe P], %w[Mary S]],
                                     [%w[Bob R], %w[Alice S]],
                                     [%w[Joe P], %w[Mary S]],
                                     [%w[Bob R], %w[Alice S]]
                                   ],
                                   [
                                     [%w[Steve S], %w[Jane P]],
                                     [%w[Ted R], %w[Carol P]]
                                   ]
                                 ])).to eq(%w[Bob R])
  end

  it 'invalid number of players in tournament' do
    expect { rps_tournament_winner([[[%w[Joe P]]], [[%w[Steve S]]]]) }.to raise_error(WrongNumberOfPlayersError)
  end
end
