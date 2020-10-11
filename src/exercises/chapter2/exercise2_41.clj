(ns exercises.chapter2.exercise2_41)

; Exercise 2.41:
; Write a procedure to find all ordered triples of distinct positive
;   integers i, j, and k less than or equal to a given integer n that
;   sum to a given integer s.

(def append concat)

(def accumulate reduce)

(defn enumerate-interval [low high]
  (if (> low high)
    nil
    (cons low (enumerate-interval (inc low) high))))

(defn flatmap [proc seq]
  (accumulate append nil (map proc seq)))

(defn unique-triples [n]
  (flatmap
    (fn [i]
      (flatmap
        (fn [j]
          (map (fn [k] (list i j k))
               (enumerate-interval 1 (- j 1))))
           (enumerate-interval 1 (- i 1))))
    (enumerate-interval 1 n)))

(unique-triples 6)
; ((3 2 1)
; (4 2 1)
; (4 3 1)
; (4 3 2)
; (5 2 1)
; (5 3 1)
; (5 3 2)
; (5 4 1)
; (5 4 2)
; (5 4 3)
; (6 2 1)
; (6 3 1)
; (6 3 2)
; (6 4 1)
; (6 4 2)
; (6 4 3)
; (6 5 1)
; (6 5 2)
; (6 5 3)
; (6 5 4))

(defn triples-with-sum-equal-to-s [n s]
  (defn first-in-triple [triple]
    (first triple))
  (defn second-in-triple [triple]
    (first (rest triple)))
  (defn third-in-triple [triple]
    (first (rest (rest triple))))
  (defn triple-sum [triple]
    (+ (first-in-triple triple) (second-in-triple triple) (third-in-triple triple)))
  (defn make-triple-sum [triple]
    (list (first-in-triple triple) (second-in-triple triple) (third-in-triple triple) (triple-sum triple)))
  (defn sum-equal-to-s? [triple]
    (= s (triple-sum triple)))
  (map make-triple-sum
       (filter sum-equal-to-s? (unique-triples n))))

(triples-with-sum-equal-to-s 6 5)
; => ()

(triples-with-sum-equal-to-s 6 6)
; => ((3 2 1 6))

(triples-with-sum-equal-to-s 6 7)
; => ((4 2 1 7))

(triples-with-sum-equal-to-s 6 8)
; => ((4 3 1 8) (5 2 1 8))

(triples-with-sum-equal-to-s 6 9)
; => ((4 3 2 9) (5 3 1 9) (6 2 1 9))

(triples-with-sum-equal-to-s 6 10)
; => ((5 3 2 10) (5 4 1 10) (6 3 1 10))

(triples-with-sum-equal-to-s 6 11)
; => ((5 4 2 11) (6 3 2 11) (6 4 1 11))

(triples-with-sum-equal-to-s 6 12)
; => ((5 4 3 12) (6 4 2 12) (6 5 1 12))

(triples-with-sum-equal-to-s 6 13)
; => ((6 4 3 13) (6 5 2 13))

(triples-with-sum-equal-to-s 6 14)
; => ((6 5 3 14))

(triples-with-sum-equal-to-s 6 15)
; => ((6 5 4 15))

(triples-with-sum-equal-to-s 6 16)
; => ()