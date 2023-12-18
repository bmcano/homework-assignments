# Top level CartesianProduct class for testing Enumerables
class CartesianProduct
  include Enumerable

  attr_accessor :array

  def initialize(pnt_a, pnt_b)
    @array = []
    return @array if pnt_a.class != Array || pnt_b.class != Array
    pnt_a.each do |a|
      pnt_b.each do |b|
        @array.push([a, b])
      end
    end
    @array
  end

  def each(&block)
		@array.each do |pair|
      block.call(pair)
    end
	end
end
