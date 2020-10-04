(ns exercises.chapter2.exercise2_28
  (:use exercises.chapter2.text))

; Exercise 2.28:
; Write a procedure fringe that takes as argument a tree (represented as a list)
;   and returns a list whose elements are all the leaves of the tree arranged
;   in left-to-right order. For example,
;
;   (define x (list (list 1 2) (list 3 4)))
;
;   (fringe x)
;   (1 2 3 4)
;
;   (fringe (list x x))
;   (1 2 3 4 1 2 3 4)

(def x1 (list (list 1 2) (list 3 4)))

(defn fringe [items]
  (cond (not (seq? items)) (list items)
        (empty? items) nil
        :else (append (fringe (first items)) (fringe (rest items)))))

(fringe x1)
; => (1 2 3 4)

(fringe (list x1 x1))
; => (1 2 3 4 1 2 3 4)
