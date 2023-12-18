# Demonstrate monkey-patching of the Class object to change base Ruby behavior
class Class
  def attr_accessor_with_history(attr_name)
    attr_name = attr_name.to_s   # make sure it's a string
    attr_reader attr_name        # create the attribute's getter
    
    # HINT See use of string literals https://en.wikibooks.org/wiki/Ruby_Programming/Syntax/Literals#The_%_Notation
    attr_reader attr_name + '_history' # create bar_history getter
    class_eval %(
      def #{attr_name}=(attr_name)
        @#{attr_name} = attr_name
        unless @#{attr_name + "_history"}
          @#{attr_name + "_history"} = []
          @#{attr_name + "_history"} << nil
        end
        @#{attr_name + "_history"} << attr_name
      end

      def #{attr_name + "_history"}
        @#{attr_name + "_history"}
        @#{attr_name + "_history"}
      end
    )
  end
end

class Foo
  attr_accessor_with_history :bar
end