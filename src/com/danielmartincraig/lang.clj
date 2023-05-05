(ns com.danielmartincraig.lang
  (:require [emmy.env :as e :refer :all]
            [emmy.matrix :as matrix]
            [emmy.quaternion :as quaternion]
            [emmy.mechanics.rotation :as rotation]))

(defn translate
  ([v]
   (translate (matrix/I 4) v))
  ([M v]
   (matrix/with-substituted-row M 3 (conj v 1))))

(defn pqp' 
  [p q]
  (quaternion/mul p q (quaternion/conjugate p)))

(defn ldraw-command [part-number color position rotation]
  (let [[x y z] position
        r (quaternion/normalize (apply quaternion/->Quaternion rotation))
        [[a b c] [d e f] [g h i]] (quaternion/->rotation-matrix r)]
    [1 color x y z a b c d e f g h i (str part-number ".dat")]))



