class MatchController < ApplicationController
	def index
		emails = Event.find(params[:eventid])["emails"].split("\r\n")
		emails << params[:email]
		data = getAllData(emails)
		user_data = data.delete(params[:email])
		user_topics = user_data[:topics]
		raw_rating = {}
		data.each do |k,v|
			raw_rating[k] = (v[:topics] & user_data[:topics]).count
		end
		rating = []
		raw_rating = raw_rating.sort_by {|k, v| v }
		raw_rating.each do |a,b|
			rating << a
		end
		rating.reverse!
		names  = []
		images = []
		topics = []
		rating.each do |r|
			names << data[r][:name]
			images << data[r][:picture]
			topics << (data[r][:topics] & user_topics) 
		end
		str_topics = ""
		str_images = images.join(',')
		str_names = names.join(',')
		str_emails = rating.join(',')

		topics.each do |t|
			str_topics += (t + getTrending).first(5).join(',')+"#"
		end

		str_topics = str_topics[0..-3]
		res = "#{str_emails};#{str_names};#{str_images};#{str_topics}"
		puts "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
		puts res
		puts "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"

		render :json => res
	end

	def getData(email)
		url = "https://vibeapp.co/api/v1/initial_data?api_key=db336b270df37fe9c128e35787f4b026&force=1&email=" + email
		data = JSON.parse(RestClient.get url)
		ret = {
			:name => data ["name"],
			:topics => data["topics"],
			:picture => data["profile_picture"]
		}
	end

	def getAllData(emails)
		threads = []
		data = {}
      	emails.each do |t|
      		threads << Thread.new{data[t] = getData(t)}
      	end
      	threads.each do |t|
      		t.join
      	end
      	return data
	end

	def getTrending()
		raw = JSON.parse(RestClient.get "http://api.frrole.com/v1/topics&apikey=mvhackday4-b1ySamko3r2mx2dnCvTv53b6a92cb8272&location=mysore")["results"].first(30)
		res = []
		raw.each do |r|
			ent = r["entity"].gsub('#','').gsub('_',' ')
			if (!res.include? ent)
				# if(!ent.include? res)
					res << ent.gsub('#','').gsub('_',' ')
				# end
			end
		end
		return res
	end
end
