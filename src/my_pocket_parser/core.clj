(ns my-pocket-parser.core
  (:gen-class)
  (:require [my-pocket-parser.utils :as u]
            [naxels.getpocket.api :as p]))
            ;[my-pocket-parser.stats :as stats]))

(defn -main
  "Grab dataset from Pocket and display stats"
  [& _args]
  (let [p-opts (p/common-required-parameters [(:consumer-key u/read-config)
                                              (:access-token u/read-config)])
        retrieve-payload (p/payload p-opts p/retrieve-optional)
        items (-> (p/retrieve retrieve-payload)
                  (get :list))]
    (println (str "Found " (count items) " items"))
    (doseq [[_id item] items]
      #_(println (keys item))
      (println (str "- " (:resolved_title item)))))
  ;; (prn (count (sequence-of-events)))) ; temp: print the count of the map
  #_(let [i (partial helpers/items->seq-for-keyword items)]
     (prn (frequencies (i :status)))
     (prn (frequencies (i :favorite)))
     (prn (->>
           (i :time_added)
           (map helpers/str->int) ; convert to number
           (map helpers/epoch->time))))) ;convert to timestamp

(comment
  ;; Testers

  ; call retrieve
  (def p-opts (p/common-required-parameters [(:consumer-key u/read-config)
                                             (:access-token u/read-config)]))
  (def retrieve-payload (p/payload p-opts p/retrieve-optional))

  ;; item {id map}
  (def items (-> (p/retrieve retrieve-payload)
                 (get :list)))

  (def add-payload (p/payload p-opts {:url "https://clojure.org"}))
  (p/add add-payload)

  items

  (count items)

  ;; {id map}
  (def item (second (first items)))
  ;; (clojure.pprint/pprint (second (second items)))

  (keys item)

  item

  ;; convert :time_added into time (is unix timestamp)
  ;;(defn get-specific-keys-for-item
  ;;  "Return the specific keys for an item"
  ;;  [item]
  ;;  (select-keys (second item) [:given_url :favorite :time_added]))
    ;;(get-in (second item) [:given_url]))

  ;; each key in items
  ;; get [:given_url :favorite :time_added]
  ;; (def working-map (map get-keys-for-item items))

  ;; (defn convert-time
    ;; "Return a new map with :time_added updated to actual timestamp"
    ;; [datamap]
    ;; (map :time_added datamap))
  ;; probably create a function that extracts the :time_added for each item, then converts the time and puts it back in each item + return

  ;; (defn sequence-of-events
    ;; []
    ;; (-> working-map ; get data, then transform
        ;; convert-time));TODO-> ))
  ;; (transform convert_time working_map)

  )
