(ns com.danielmartincraig.waterbottle
  (:gen-class)
  (:require [emmy.env :as e :refer :all]))



(comment
  (e/asin -10)
  (e/cos e/pi)
  )

(defn greet
  "Callable entry point to the application."
  [data]
  (println (str "Hello, " (or (:name data) "World") "!")))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (greet {:name (first args)}))
