require 'part5.rb'
require 'rspec.rb'

describe 'CartesianProduct' do
  it 'must exist [0 points]' do
    expect { CartesianProduct.new(1..2, 3..4) }.not_to raise_error
  end

  it 'test valid products' do
    c = CartesianProduct.new([:a,:b,:c], [4,5])
    expect(c.array).to eq([[:a,4],[:a,5],[:b,4],[:b,5],[:c,4],[:c,5]])
    c = CartesianProduct.new([:a], [4])
    expect(c.array).to eq([[:a,4]])
    c = CartesianProduct.new([:a,:b,:c], [])
    expect(c.array).to eq([])
  end

  it 'test invalid inputs' do
    c = CartesianProduct.new([:a,:b,:c], 4)
    expect(c.array).to eq([])
    c = CartesianProduct.new("test", 4)
    expect(c.array).to eq([])
    c = CartesianProduct.new(:a, 4)
    expect(c.array).to eq([])
    c = CartesianProduct.new(1324523452345, true)
    expect(c.array).to eq([])
    c = CartesianProduct.new([:a,:b,:c], false)
    expect(c.array).to eq([])
    c = CartesianProduct.new([:a,:b,:c], NIL)
    expect(c.array).to eq([])
  end

  it 'test the each method' do
    c = CartesianProduct.new([:a,:b,:c], [4,5])
    expect {
      c.each { |x| puts x.inspect }
    }.not_to raise_error

    count = 0
    c.each { |x|
      if x.inspect == [:b,4]
        count += 1
      end
    }
    expect (count).equal? 1

  end
end
