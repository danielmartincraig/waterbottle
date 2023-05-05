(ns com.danielmartincraig.waterbottle
  (:gen-class)
  (:require [com.danielmartincraig.lang :as wb]
            [clojure.string :as str :refer [join]]))

(def parts
  [(wb/ldraw-command 3005 1 [0 0 0] [1 0 0 0])
   (wb/ldraw-command 3065 1 [50 0 0] [1 1 0 0])
   (wb/ldraw-command 3065 1 [50 0 0] [1 1 -1 0])
   (wb/ldraw-command 3065 1 [50 0 0] [1 1 1 0])
   ])

(defn write-file
  [filename parts & _]
  (spit filename (str/join " " ["0" "FILE" filename "\n"]))
  (doseq [part parts]
    (spit filename (str (join " " part) "\n") :append true)))

(defn greet
  "Callable entry point to the application."
  [data]
  (write-file "model.ldr" parts)
  (println (str "Hello, " (or (:name data) "World") "!")))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (greet {:name (first args)}))

(comment
  (greet {:name "Daniel"})

  (wb/ldraw-command 3005 1 [0 0 0] [-2 -1 30 1])

  )

