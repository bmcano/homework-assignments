# One way to work on your code
# is to have a terminal  window open and an editor window with this
# file loaded.  Make changes to this file, save them, and then
# run 'ruby part1.rb' at the command line, this will run your program with
# the specified test cases.  You may want to add additional test cases to
# make sure that you have thoroughly tested your code.

# Alternatively, if you are confident with rspec syntax, you can add additional
# tests to the spec/part1_spec.rb file to thoroughly test your
# program and test it by typing:
# 'rspec spec/part1_spec.rb'

# You will need to be in the parent directory of the spec directory when
# you type this command.
# NOTE:  THE PROVIDED RSPEC TESTS ARE ONLY MINIMAL SANITY CHECKS TO INSURE
# THAT OUR AUTOMATED GRADER WILL BE ABLE TO GRADE YOUR SUBMISSION.  THEY DO
# NOT COMPREHENSIVELY TEST THE CORRECTNESS OF YOUR SOLUTION.
# Be absolutely certain that your code passes the rspec sanity checks before
# submitting it.  If it doesn't pass these sanity checks, our auto-grader will
# not be able to grade it.
#
# It is up to you to add sufficient test cases to this file (or additional rspec tests to
# the spec/part1_spec.rb file) to thoroughly test your program.
#
# When you are satisfied with your work, REMOVE THE TEST CASES and save your file.
# When you have completed all five parts of the assignment, follow the submission
# instructions specified in the assignment description to hand-in your work.

def palindrome?(str)
  str = str.downcase.gsub(/[^a-z]/, "")
  str == str.reverse
end

def count_words(str)
  hash = {}
  return hash if str.class != String
  chars = str.downcase.gsub(/[^a-z]/, " ").split
  chars.each do |char|
    if hash[char]
      hash[char] += 1
    else
      hash[char] = 1
    end
  end
  hash
end
