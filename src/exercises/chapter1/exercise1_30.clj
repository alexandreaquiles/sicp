(ns exercises.chapter1.exercise1_30)

(defn sum [term a next b]
  (defn iter [a result]
    (if (> a b)
      result
      (iter (next a) (+ (term a) result))))
  (iter a 0))

(defn sum-integers [a b]
  (sum identity a inc b))

(sum-integers 1 10)
; => 55

(defn cube [x]
  (* x x x))

(defn sum-cubes [a b]
  (sum cube a inc b))

(sum-cubes 1 10)
; => 3025

(defn pi-sum [a b]
  (defn pi-term [x]
    (/ 1.0 (* x (+ x 2))))
  (defn pi-next [x]
    (+ x 4))
  (sum pi-term a pi-next b))

(* 8 (pi-sum 1 1000))
; => 3.139592655589782

