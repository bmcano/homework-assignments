# require 'byebug'              # optional, may be helpful
require 'open-uri'              # allows open('http://...') to return body
require 'cgi'                   # for escaping URIs
require 'nokogiri'              # XML parser
require 'active_model'          # for validations

# The  main class for the OracleOfBacon behaviors
class OracleOfBacon

  class InvalidError < RuntimeError ; end
  class NetworkError < RuntimeError ; end
  class InvalidKeyError < RuntimeError ; end

  attr_accessor :from, :to
  attr_reader :api_key, :response, :uri

  include ActiveModel::Validations
  validates_presence_of :from
  validates_presence_of :to
  validates_presence_of :api_key
  validate :from_does_not_equal_to

  def from_does_not_equal_to
    errors.add(@from,"From cannot be the same as To") if @from == @to
  end

  def initialize(api_key = '38b99ce9ec87')
    @api_key = api_key
    @from = "Kevin Bacon"
    @to = "Kevin Bacon"
  end

  def find_connections
    make_uri_from_arguments
    begin
      xml = URI.parse(uri).read
    rescue OpenURI::HTTPError
      xml = %q{<?xml version="1.0" standalone="no"?>
<error type="unauthorized">unauthorized use of xml interface</error>}
    rescue Timeout::Error, Errno::EINVAL, Errno::ECONNRESET, EOFError,
      Net::HTTPBadResponse, Net::HTTPHeaderSyntaxError,
      Net::ProtocolError => e
      raise OracleOfBacon::NetworkError, e.message
    end
    @response = Response.new(xml)
  end

  def make_uri_from_arguments
    escaped_to = CGI.escape(@to)
    escaped_from = CGI.escape(@from)
    @uri = "http://oracleofbacon.org/cgi-bin/xml?p=#{@api_key}&a=#{escaped_to}&b=#{escaped_from}"
  end

  class Response
    attr_reader :type, :data
    # create a Response object from a string of XML markup.
    def initialize(xml)
      @doc = Nokogiri::XML(xml)
      parse_response
    end

    private

    def parse_response
      if ! @doc.xpath('/error').empty?
        parse_error_response
      elsif ! @doc.xpath('/link').empty?
        parse_graph_response
      elsif ! @doc.xpath('/spellcheck').empty?
        parse_spellcheck_response
      else
        parse_unknown_response
      end
    end

    def parse_error_response
      error_type = @doc.xpath('/error').attr("type").value
      error_message = @doc.xpath('/error').text
      if error_type == "unauthorized"
        @type = :unauthorized
        @data = error_message
      elsif error_type == "badinput"
        @type = :badinput
        @data = error_message
      elsif error_type == "unlinkable"
        @type = :unlinkable
        @data = error_message
      else
        @type = :unknown
        @data = "unknown"
      end
    end

    def parse_spellcheck_response
      @type = :spellcheck
      @data = @doc.xpath('//match').map(&:text)
    end

    def parse_graph_response
      @type = :graph
      actor = @doc.xpath('//actor')
      movie = @doc.xpath('//movie')
      @data = actor.zip(movie).flatten.compact.map(&:text)
    end

    def parse_unknown_response
      @type = :unknown
      @data = "unknown"
    end
  end
end

