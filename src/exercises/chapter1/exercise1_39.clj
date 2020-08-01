(ns exercises.chapter1.exercise1_39)

; Exercise 1.39:
; A continued fraction representation of the tangent function was published in 1770
;   by the German mathematician J.H. Lambert:
;
;   tan x = x / (1 - ( x² / 3 - (x² / 5 - ...)))
;
;   where x is in radians.
;
; Define a procedure (tan-cf x k) that computes an approximation to the tangent function based
;   on Lambert’s formula.
; k specifies the number of terms to compute, as in Exercise 1.37.

(defn square [x]
  (* x x))

(defn di [i]
  (+ i (- i 1)))

(map di (range 1 10))
; => (1 3 5 7 9 11 13 15 17)

(defn tan-cf [x k]
  (assert (> k 1))
  (defn tan-cf-i [i]
    (if (= i k)
      (do
        (println i)
        (println (di i))
        (println (square x))
        (println (/ (square x) (di i) ))
        (/ (square x) (di i) ))
      (- (di i) (/ (square x) (tan-cf-i (+ i 1))))))
  (if (= x 0)
    0
    (/ x (tan-cf-i 1))))

;(Math/tan 0)
; => 0.0
(tan-cf 0 10)
; => 0

;(Math/tan 1)
; => 1.5574077246549023
(tan-cf 1 10)
; => 3645485/2340739 (~ 1.557407724654479)

;(Math/tan (/ Math/PI 2))
; => 1.633123935319537E16
(tan-cf (/ Math/PI 2) 10)
; => 4.309201793203909E9
;(tan-cf (/ Math/PI 2) 20)
; Syntax error (ArithmeticException) Divide by zero

;(Math/tan Math/PI)
; => -1.2246467991473532E-16
(tan-cf Math/PI 10)
; => -1.5203918510336283E-5

