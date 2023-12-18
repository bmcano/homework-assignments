# When done, submit this entire file to the autograder.

# Part 1

def sum(arr)
  array_sum = 0
  arr.each { |x| array_sum += x }
  array_sum
end

def max_2_sum(arr)
  return 0 if arr.length == 0
  return arr.at(0) if arr.length == 1
  max_values = arr.max(2)
  max_values.at(0) + max_values.at(1)
end

def sum_to_n?(arr, n)
  if arr.length == 0 or arr.length == 1
    false
  else
    arr.each_index { |i|
      val_1 = arr.at(i)
      arr.each_index { |j|
        val_2 = arr.at(j)
        if i != j and n == val_1 + val_2
          return true
        end
      }
    }
    false
  end
end

# Part 2

def hello(name)
  "Hello, #{name}"
end

def starts_with_consonant?(s)
  !s.empty? and s.downcase.start_with?(/[b-df-hj-np-tv-z]/)
end

def binary_multiple_of_4?(s)
  return true if s == "0"
  /^[10]*00$/.match(s) != nil
end

# Part 3

class BookInStock
  attr_accessor :isbn
  attr_accessor :price

  def initialize(isbn, price)
    if isbn.empty? or price <= 0
      raise ArgumentError, "Illegal argument"
    end
    @isbn = isbn
    @price = price
  end

  def price_as_string
    sprintf("$%2.2f", @price)
  end
end
