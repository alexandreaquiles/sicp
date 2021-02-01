(ns exercises.chapter2.exercise2_61)

; Exercise 2.61: Give an implementation of adjoin-set using the ordered representation.
; By analogy with element-of-set? show how to take advantage of the ordering to
;   produce a procedure that requires on the average about half as many steps as with the
;   unordered representation.

(defn adjoin-set [x set]
  (cond (empty? set) (list x)
        (< x (first set)) (cons x set)
        (> x (first set)) (cons (first set) (adjoin-set x (rest set)))
        (= x (first set)) set))

(adjoin-set 1 '())
; => (1)

(adjoin-set 1 '(1))
; => (1)

(adjoin-set 1 '(7 8 9))
; => (1 7 8 9)

(adjoin-set 6 '(1 2 9))
; => (1 2 6 9)

(adjoin-set 6 '(1 2 3))
; => (1 2 3 6)

(adjoin-set 6 '(1 6 3))
; => (1 6 3)