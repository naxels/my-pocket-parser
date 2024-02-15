(ns my-pocket-parser.pocket
  (:require [my-pocket-parser.utils :as u]))

(def base-url "https://getpocket.com")
(def v "v3")

;; API Endpoints

;; https://getpocket.com/developer/docs/v3/retrieve
(def retrieve (u/build-url [base-url v "get"]))

;; https://getpocket.com/developer/docs/v3/add
(def add (u/build-url [base-url v "add"]))

;; https://getpocket.com/developer/docs/v3/modify
(def modify (u/build-url [base-url v "send"]))

;; Parameters

(defn common-required-parameters
  "Required parameters
   - consumer_key
   - access_token"
  [[consumer-key access-token]]
  {:consumer_key consumer-key
   :access_token access-token})

;; Some default optional parameters
(def retrieve-optional {:state "unread"
                        :count "10"
                        :detailType "complete"})

;; Payload builder
(defn payload
  "Build the JSON payload
   containing the required parameters and additional (if any) parameter(s)
   both need to be a map"
  ([required-parameters] (u/map->json required-parameters))
  ([required-parameters parameter-map] (u/map->json (merge required-parameters
                                                                 parameter-map))))
