(ns my-pocket-parser.utils
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [clj-http.client :as http]
            [java-time.api :as jt]
            [jsonista.core :as json]))

(def read-config
  (-> "resources/config.edn"
      (slurp)
      (edn/read-string)))

(defn build-url
  "Combine the URL parts"
  [url-parts]
  (str/join "/" url-parts))

(defn str->int
  "Simply convert string to int"
  [s]
  (read-string s))

(defn epoch->time
  "Multiply the epochtime * 1000 and convert"
  [epochtime]
  (jt/instant (* 1000 epochtime)))

(defn items->seq-for-keyword
  "Turn the items into a seq for the given keyword"
  [items keyword-to-get]
  (map keyword-to-get (vals items)))

(defn map->json
  [m]
  (json/write-value-as-string m))

(defn http-retrieve
  [url request_body]
  (let [resp (http/post url {:body request_body
                             :content-type :json})]
    (when (= 200 (:status resp))
      (json/read-value (:body resp)
                       (json/object-mapper {:decode-key-fn true})))))
