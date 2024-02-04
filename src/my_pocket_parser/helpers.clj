(ns my-pocket-parser.helpers
  (:require [java-time :as jt]
            [clojure.edn :as edn]
            [clojure.string :as str]))

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
