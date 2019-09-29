(ns my-pocket-parser.core
  (:gen-class)
  (:require [clj-http.client :as http]
            [clojure.data.json :as json]
            [my-pocket-parser.helpers :refer :all]))

(def pocket-get-url (build-url "https://getpocket.com" "v3" "get"))

(defn create-json-payload
  [consumer-key access-token]
  (json/write-str {:consumer_key consumer-key
                   :access_token access-token
                   :state "unread"
                   :count "2"
                   :detailType "complete"}))

(defn http-retrieve
  [url request_body]
  (let [resp (http/post url {:body request_body
                             :content-type :json})]
    (when (= 200 (:status resp))
      (json/read-str (:body resp)
                     :key-fn keyword))))

;; Testers
; call retrieve
(def m (http-retrieve pocket-get-url (create-json-payload (:consumer-key read-config)
                                                          (:access-token read-config))))
; (:list m)
; (get m :list) ; list of items
(def items (get m :list))

;; convert :time_added into time (is unix timestamp)
(defn get-keys-for-item
  "Return the specific keys for an item"
  [item]
  (select-keys (second item) [:given_url :favorite :time_added]))
  ;;(get-in (second item) [:given_url]))

;; each key in items
;; get [:given_url :favorite :time_added]
(def working-map (map get-keys-for-item items))

(defn convert-time
  "Return a new map with :time_added updated to actual timestamp"
  [datamap]
  (map :time_added datamap))
;; probably create a function that extracts the :time_added for each item, then converts the time and puts it back in each item + return

(defn sequence-of-events
  []
  (-> working-map ; get data, then transform
      convert-time));TODO-> ))
;; (transform convert_time working_map) 

(defn -main
  "Grab dataset from Pocket and display stats"
  [& args]
  (prn (count (sequence-of-events)))) ; temp: print the count of the map
