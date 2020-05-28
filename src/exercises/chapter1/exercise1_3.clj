(ns exercises.chapter1.exercise1_3)

; Exercise 1.3.
;   Define a procedure that takes three numbers as arguments and
;     returns the sum of the squares of the two larger numbers.

(defn square [x]
  (* x x))

(defn two-larger-numbers [a b c]
  (cond (and (>= a c) (>= b c)) [a b])
  (cond (and (>= a b) (>= c b)) [a c])
  (cond (and (>= b a) (>= c a)) [b c]))

(defn sum-of-squares
  ([a b c] (sum-of-squares (two-larger-numbers a b c)))
  ([[x y]] (+ (square x) (square y))))

(sum-of-squares 1 2 3)
; => 13
(sum-of-squares 1 1 1)
; => 2
(sum-of-squares 1 2 2)
; => 8
(sum-of-squares 1 1 2)
; => 5
