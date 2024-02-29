(ns my-pocket-parser.utils
  (:require [clojure.edn :as edn]
            [java-time.api :as jt]))

(defn read-config
  []
  (-> "resources/config.edn"
      (slurp)
      (edn/read-string)))

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
