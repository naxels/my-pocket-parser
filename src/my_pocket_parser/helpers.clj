(ns my-pocket-parser.helpers
  (:require [java-time :as jt]
            [clojure.edn :as edn]))

(def read-config
  (-> "resources/config.edn"
      (slurp)
      (edn/read-string)))

;; (def read-config
;;   (when-let [data (slurp (io/reader (io/resource "config.edn")))]
;;     (edn/read-string data)))

(defn build-url
  [base version path]
  (str base "/" version "/" path))

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
