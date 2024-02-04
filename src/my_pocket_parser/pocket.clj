(ns my-pocket-parser.pocket
  (:require [my-pocket-parser.helpers :as helpers]))

(def base-url "https://getpocket.com")
(def v "v3")

;; https://getpocket.com/developer/docs/v3/retrieve
(def retrieve (helpers/build-url [base-url v "get"]))

;; https://getpocket.com/developer/docs/v3/add
(def add (helpers/build-url [base-url v "add"]))

;; https://getpocket.com/developer/docs/v3/modify
(def modify (helpers/build-url [base-url v "send"]))
