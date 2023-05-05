(ns com.danielmartincraig.waterbottle
  (:gen-class)
  (:require [com.danielmartincraig.lang :as wb]
            [clojure.string :as str :refer [join]]))

(def fib-seq
  ((fn rfib [a b]
     (lazy-seq (cons a (rfib b (+ a b)))))
   0 1))


(def part-attrs (partition 7 1 fib-seq))

(def part-seq (map (fn [[x y z r i j k]] (wb/ldraw-command 3005 1 [x y z] [r i j k])) part-attrs)) 

(take 2 part-seq)

(def parts (take 30 part-seq))

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

