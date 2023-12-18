require 'part3.rb'
require 'rspec.rb'

describe 'dessert' do
  it 'must define a constructor [0 points]' do
    expect { Dessert.new('a', 1) }.not_to raise_error
  end
  %i[healthy? delicious?].each do |method|
    it "must define #{method} [0 points]" do
      #      Dessert.new('a',1).should respond_to method
      expect(Dessert.new('a', 1)).to respond_to(method)
    end
  end
  it 'healthy method testing' do
    d = Dessert.new('a', 1)
    expect(d.healthy?).to eq(true)
    d = Dessert.new('a', 234)
    expect(d.healthy?).to eq(false)
    d = Dessert.new('a', 58.46)
    expect(d.healthy?).to eq(true)
    d = Dessert.new('a', 134453456)
    expect(d.healthy?).to eq(false)
  end
  it 'delicious? method testing' do
    d = Dessert.new('a', 134453456)
    expect(d.delicious?).to eq(true)
    d = Dessert.new(543, "134453456")
    expect(d.delicious?).to eq(true)
    d = Dessert.new('a', false)
    expect(d.delicious?).to eq(true)
    d = Dessert.new(:f, 134453456)
    expect(d.delicious?).to eq(true)
  end
end

describe 'jellybean' do
  it 'must define a constructor [0 points]' do
    expect { JellyBean.new('a', 1, 2) }.not_to raise_error
  end
  %i[healthy? delicious?].each do |method|
    it "must define #{method} [0 points]" do
      #      JellyBean.new('a',1, 2).should respond_to method
      expect(JellyBean.new('a', 1, 2)).to respond_to(method)
    end
  end
  it 'test jelly bean class' do
    j = JellyBean.new("Candy", 56, "Cherry")
    expect(j.delicious?).to eq(true)
    expect(j.healthy?).to eq(true)

    j = JellyBean.new("Candy", 200, "bLacK lIcoRice")
    expect(j.delicious?).to eq(false)
    expect(j.healthy?).to eq(false)
  end
end
