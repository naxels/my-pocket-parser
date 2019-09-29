(ns my-pocket-parser.helpers
  (:require [java-time :as jt]
            [clojure.edn :as edn]
            [clojure.java.io :as io]))

(def read-config
  (when-let [data (slurp (io/reader (io/resource "config.edn")))]
    (edn/read-string data)))

(defn build-url
  [base version path]
  (str base "/" version "/" path))

(defn epoch->time
  "Multiply the epochtime * 1000 and convert"
  [epochtime]
  (jt/instant (* 1000 epochtime)))
