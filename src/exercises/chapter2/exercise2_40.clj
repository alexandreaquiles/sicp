(ns exercises.chapter2.exercise2_40
  (:use exercises.math))

; Exercise 2.40:
; Define a procedure unique-pairs that, given an integer n,
;   generates the sequence of pairs (i, j) with 1 ≤ j < i ≤ n.
; Use unique-pairs to simplify the definition of prime-sum-pairs
;   given above.

(def append concat)

(def accumulate reduce)

(def flatmap mapcat)

(defn enumerate-interval [low high] (range low (inc high)))

(defn unique-pairs [n]
  (flatmap
    (fn [i]
      (map (fn [j] (list i j))
           (enumerate-interval 1 (- i 1))))
    (enumerate-interval 1 n)))

(unique-pairs 6)
; => ((2 1) (3 1) (3 2) (4 1) (4 2) (4 3) (5 1) (5 2) (5 3) (5 4) (6 1) (6 2) (6 3) (6 4) (6 5))

(defn prime-sum? [pair]
  (prime? (+ (first pair) (first (rest pair)))))

(defn make-pair-sum [pair]
  (list (first pair) (first (rest pair)) (+ (first pair) (first (rest pair)))))

(defn prime-sum-pairs [n]
  (map make-pair-sum
       (filter prime-sum? (unique-pairs n))))

(prime-sum-pairs 6)
; => ((2 1 3) (3 2 5) (4 1 5) (4 3 7) (5 2 7) (6 1 7) (6 5 11))
