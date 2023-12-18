# A Dessert top-level class
class Dessert
  attr_accessor :name
  attr_accessor :calories

  def initialize(name, calories)
    @name = name
    @calories = calories
  end

  def healthy?
    @calories < 200
  end

  def delicious?
    true
  end
end

# A JellyBean class derived from Dessert
class JellyBean < Dessert
  attr_accessor :flavor

  def initialize(name, calories, flavor)
    @name = name
    @calories = calories
    @flavor = flavor
  end

  def delicious?
    if @flavor.downcase == 'black licorice'; false else true end
  end
end
