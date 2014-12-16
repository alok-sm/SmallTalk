class CreateEvents < ActiveRecord::Migration
  def change
    create_table :events do |t|
      t.string :title
      t.datetime :eventdatetime
      t.text :description
      t.string :imageurl
      t.text :emails

      t.timestamps
    end
  end
end
