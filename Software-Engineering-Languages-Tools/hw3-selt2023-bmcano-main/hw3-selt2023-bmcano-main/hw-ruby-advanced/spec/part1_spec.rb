require 'part1.rb'
require 'rspec.rb'

describe '#palindrome?' do
  # HINT https://www.toptenz.net/top-10-famous-palindromes.php
  # HINT http://www.rubyguides.com/2018/07/rspec-tutorial/
  it 'must be defined' do
    expect { palindrome?('Testing') }.not_to raise_error
  end
  it 'is not a palindrome' do
    expect { palindrome?('Testing') }.equal? false
    expect { palindrome?('word') }.equal? false
    expect { palindrome?('!#$#!') }.equal? false
    expect { palindrome?(4) }.equal? false
    expect { palindrome?("A man, a plan, a canal -- Panama") }.equal? false
  end
  it 'is a palindrome' do
    expect { palindrome?('racecar') }.equal? true
    expect { palindrome?('Tat') }.equal? true
  end
  it 'invalid inputs' do
    expect { palindrome?([1,2,2,1]) }.equal? false
    expect { palindrome?(346354643) }.equal? false
    expect { palindrome?(123454321) }.equal? false
    expect { palindrome?(:a) }.equal? false
  end
end

describe '#count_words' do
  it 'must be defined' do
    expect { count_words('Testing') }.not_to raise_error
  end

  it 'must return a Hash' do
    expect(count_words('Testing').class).to eq(Hash)
  end

  it 'counts characters' do
    hash_ex = {'a' => 3, 'man' => 1, 'canal' => 1, 'panama' => 1, 'plan' => 1}
    expect(count_words("A man, a plan, a canal -- Panama")).to eq(hash_ex)
    hash_ex = {'doo' => 3, 'bee' => 2}
    expect(count_words "Doo bee doo bee doo").to eq(hash_ex)
    hash_ex = {'a' => 1, 'b' => 1}
    expect(count_words "A B").to eq(hash_ex)
    hash_ex = {'a' => 6, 'b' => 3}
    expect(count_words "A,B|A$A^a^B*A?b(A)  ").to eq(hash_ex)
  end

  it 'invalid inputs' do
    hash_ex = {}
    expect(count_words 1234).to eq(hash_ex)
    expect(count_words false).to eq(hash_ex)
    expect(count_words :a).to eq(hash_ex)
    expect(count_words [1,2,3]).to eq(hash_ex)
    expect(count_words NIL).to eq(hash_ex)
  end

end
