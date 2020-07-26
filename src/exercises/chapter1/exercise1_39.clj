(ns exercises.chapter1.exercise1_39)

(defn square [x]
  (* x x))

(defn di [i]
  (+ i (- i 1)))

(map di (range 1 10))
; => (1 3 5 7 9 11 13 15 17)

(defn tan-cf [x k]
  (assert (> k 1))
  (defn tan-cf-i [i]
    (if (>= i k)
      (di i)
      (- (di i) (/ (square x) (tan-cf-i (+ i 1))))))
  (/ x (tan-cf-i 1)))

;(Math/tan 0)
; => 0.0
(tan-cf 0 10)
; Execution error (ArithmeticException) Divide by zero

;(Math/tan 1)
; => 1.5574077246549023
(tan-cf 1 10)
; => 565649425/363199319 (~ 1.557407724654902)

(Math/tan (/ Math/PI 2))
; => 1.633123935319537E16
(tan-cf (/ Math/PI 2) 10)
; => 7.446566054766779E14
(tan-cf (/ Math/PI 2) 20)
; Syntax error (ArithmeticException) Divide by zero

;(Math/tan (/ Math/PI))
; => 0.32951473309607837
(tan-cf (/ Math/PI) 10)
; => 0.32951473309607837
