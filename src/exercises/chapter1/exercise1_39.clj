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

(Math/tan (/ Math/PI 4))
; => 0.9999999999999999

(tan-cf (/ Math/PI 4) 10)
; => 0.9999999999999958

(Math/tan (/ Math/PI 3))
; => 1.7320508075688767

(tan-cf (/ Math/PI 3) 10)
; => 1.7320508075678038
