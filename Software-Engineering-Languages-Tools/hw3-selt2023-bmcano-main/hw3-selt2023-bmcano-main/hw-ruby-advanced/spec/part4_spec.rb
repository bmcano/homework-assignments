require 'part4.rb'
require 'rspec.rb'

# 30% of total
describe 'single attr_accessor_with_history' do
  # HINT: a before(:each) clause might make the testign code more DRY
  it 'must have an attr_accessor_with_history method [0 point]' do
    expect(-> { Class.new.attr_accessor_with_history :x }).not_to raise_error
  end

  it 'test' do
    f = Foo.new     # => #<Foo:0x127e678>
    f.bar = 1      # => 3
    f.bar = 2  # => :wowzo
    expect(f.bar_history).to eq([nil, 1, 2])
  end

  it 'test with new mid way' do
    f = Foo.new
    f.bar = 1
    f.bar = 2
    f = Foo.new
    f.bar = 345
    f.bar = "String"
    expect(f.bar_history).to eq([nil, 345, "String"])
  end

  it 'test with new at end' do
    f = Foo.new
    f.bar = 1
    f.bar = 2
    f = Foo.new
    expect(f.bar_history).to eq(nil)
  end
end
