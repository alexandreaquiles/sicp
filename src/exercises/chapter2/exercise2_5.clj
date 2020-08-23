(ns exercises.chapter2.exercise2_5
  (:use exercises.math))

; Exercise 2.5:
; Show that we can represent pairs of nonnegative integers using only numbers
;   and arithmetic operations if we represent the pair a and b as the integer
;   that is the product 2^a3^b .
; Give the corresponding definitions of the procedures cons, car, and cdr.

(defn cons [a b]
  (* (expt 2 a) (expt 3 b)))

(defn extract-pair [pair divisor]
  (loop [n pair count 0]
    (if (not= (rem n divisor) 0)
      count
      (recur (/ n divisor) (inc count)))))

(defn car [pair]
  (extract-pair pair 2))

(defn cdr [pair]
  (extract-pair pair 3))

(cons 2 3)
; => 108

(car (cons 2 3))
; => 2

(cdr (cons 2 3))
; => 3
