
(ns exercises.chapter1.exercise1_32)

;Exercise 1.32.
; a. Show that sum and product (exercise 1.31) are both special cases of a still more general notion called accumulate
;   that combines a collection of terms, using some general accumulation function:
;
;   (accumulate combiner null-value term a next b)
;
; Accumulate takes as arguments the same term and range specifications as sum and product, together with a combiner
;   procedure (of two arguments) that specifies how the current term is to be combined with the accumulation of the
;   preceding terms and a null-value that specifies what base value to use when the terms run out.
; Write accumulate and show how sum and product can both be defined as simple calls to accumulate.
;
;b. If your accumulate procedure generates a recursive process, write one that generates an iterative process.
;   If it generates an iterative process, write one that generates a recursive process.

;(defn sum [term a next b]
;  (loop [a a result 0]
;    (if (> a b)
;      result
;      (recur (next a) (+ (term a) result)))))
;
;(defn product [term a next b]
;  (loop [a a result 1]
;    (if (> a b)
;      result
;      (recur (next a) (* (term a) result)))))

(defn accumulate [combiner null-value term a next b]
  (loop [a a result null-value]
    (if (> a b)
      result
      (recur (next a) (combiner (term a) result)))))

(defn sum [term a next b]
  (accumulate + 0 term a next b))

(defn sum-integers [a b]
  (sum identity a inc b))

(assert (= 55 (sum-integers 1 10)))

(defn product [term a next b]
  (accumulate * 1 term a next b))

(defn factorial [a b]
  (product identity a inc b))

(assert (= 120 (factorial 1 5)))

(defn accumulate-recursive [combiner null-value term a next b]
  (if (> a b)
    null-value
    (combiner (term a) (accumulate-recursive combiner null-value term (next a) next b))))

(defn sum-recursive [term a next b]
  (accumulate-recursive + 0 term a next b))

(defn sum-integers-recursive [a b]
  (sum-recursive identity a inc b))

(assert (= 55 (sum-integers-recursive 1 10)))

(defn product-recursive [term a next b]
  (accumulate-recursive * 1 term a next b))

(defn factorial-recursive [a b]
  (product-recursive identity a inc b))

(assert (= 120 (factorial-recursive 1 5)))