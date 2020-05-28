(ns exercises.chapter1.exercise1_2
  (:use clojure.pprint))

; Exercise 1.2.
;   Translate the following expression into prefix form

(pprint (/
          (+ 5
             4
             (- 2
                (- 3
                   (+ 6 4/5))))
          (* 3
             (- 6 2)
             (- 2 7))))
;=> -37/150
