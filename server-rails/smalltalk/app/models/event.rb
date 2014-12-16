class Event < ActiveRecord::Base
  attr_accessible :description, :emails, :eventdatetime, :imageurl, :title
end
