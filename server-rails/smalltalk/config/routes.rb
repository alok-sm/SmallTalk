Smalltalk::Application.routes.draw do
  resources :events
  match '/match' => 'match#index', :via => [:GET,:POST]
end
