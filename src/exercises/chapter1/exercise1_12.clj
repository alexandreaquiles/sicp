(ns exercises.chapter1.exercise1_12)

; Exercise 1.12.
;   The following pattern of numbers is called Pascal's triangle.
;
; 1
; 1   1
; 1   2    1
; 1   3    3     1
; 1   4    6     4     1
; 1   5   10    10     5     1
; 1   6   15    20    15     6   1
; 1   7   21    35    35    21   7    1
;
;   The numbers at the edge of the triangle are all 1, and each number inside the triangle is the sum of the two numbers above it.
;   Write a procedure that computes elements of Pascal's triangle by means of a recursive process.
;
; Footnote:
;   The elements of Pascal's triangle are called the binomial coefficients,
; because the nth row consists of the coefficients of the terms in the
; expansion of (x + y)n. This pattern for computing the coefficients appeared
; in Blaise Pascal's 1653 seminal work on probability theory, Traité du
; triangle arithmétique. According to Knuth (1973), the same pattern appears in
; the Szu-yuen Yü-chien (``The Precious Mirror of the Four Elements''),
; published by the Chinese mathematician Chu Shih-chieh in 1303, in the works of
; the twelfth-century Persian poet and mathematician Omar Khayyam, and in the
; works of the twelfth-century Hindu mathematician Bháscara Áchárya.

(defn pascal-rule [n, k]
  (assert (and (>= n k) (>= k 0))) ;n ≥ k ≥ 0
  (if (= k 0)
    1
    (* (pascal-rule n (- k 1)) (/ (- (+ n 1) k) k))))

(defn pascal-triangle [last-row]
  (defn pascal-triangle-for-row [row]
    (map (partial pascal-rule row) (range (inc row)) ))
  (map pascal-triangle-for-row (range (inc last-row))))

; Reference: https://en.wikipedia.org/wiki/Pascal%27s_triangle#Calculating_a_row_or_diagonal_by_itself

(pascal-rule 0 0)
;=> 1

(pascal-rule 1 0)
;=> 1
(pascal-rule 1 1)
;=> 1

(pascal-rule 2 0)
;=> 1
(pascal-rule 2 1)
;=> 2
(pascal-rule 2 2)
;=> 1N

(pascal-rule 3 0)
;=> 1
(pascal-rule 3 1)
;=> 3
(pascal-rule 3 2)
;=> 3
(pascal-rule 3 3)
;=> 1N

(pascal-triangle 3)
; => ((1) (1 1) (1 2 1N) (1 3 3 1N))

(pascal-triangle 7)
; => ((1) (1 1) (1 2 1N) (1 3 3 1N) (1 4 6N 4N 1N) (1 5 10 10 5N 1N) (1 6 15N 20N 15N 6N 1N) (1 7 21 35N 35N 21N 7N 1N))
