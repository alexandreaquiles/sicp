(ns exercises.chapter1.exercise1_33)

;Exercise 1.33.
; You can obtain an even more general version of 'accumulate' (exercise 1.32) by introducing the notion of a filter on the
;   terms to be combined.
; That is, combine only those terms derived from values in the range that satisfy a specified condition.
; The resulting 'filtered-accumulate' abstraction takes the same arguments as accumulate, together with an additional
;   predicate of one argument that specifies the filter.
; Write 'filtered-accumulate' as a procedure.
; Show how to express the following using filtered-accumulate:
;   a. the sum of the squares of the prime numbers in the interval a to b
;     (assuming that you have a prime? predicate already written)
;   b. the product of all the positive integers less than n that are relatively prime to n
;     (i.e., all positive integers i < n such that GCD(i,n) = 1).

(defn filtered-accumulate [combiner null-value filter term a next b]
  (loop [a a result null-value]
    (if (> a b)
      result
      (if (filter a)
        (recur (next a) (combiner (term a) result))
        (recur (next a) result)))))

(defn sum [a b]
  (filtered-accumulate + 0 (constantly true) identity a inc b))

(assert (= 55 (sum 1 10)))

(defn product [a b]
  (filtered-accumulate * 1 (constantly true) identity a inc b))

(assert (= 120 (product 1 5)))

(defn square [x]
  (* x x))

(defn divides? [a b] (= (rem b a) 0))

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (+ test-divisor 1))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (if (= n 1) false
              (= n (smallest-divisor n))))

(defn sum-of-squares-of-primes-in-the-interval [a b]
  (filtered-accumulate + 0 prime? square a inc b))

(sum-of-squares-of-primes-in-the-interval 1 10)
; => 87
; (+ 2² 3² 5² 7²) = (+ 4 9 25 49) => 87

(defn gcd [a b]
  (if (= b 0)
    a
    (recur b (rem a b))))

(defn relatively-prime-to-n? [n i]
  (= (gcd i n) 1))

(defn products-of-all-relatively-primes-to-n [a n]
  (filtered-accumulate * 1 (partial relatively-prime-to-n? n) identity a inc n))

(products-of-all-relatively-primes-to-n 1 10)
;=> 189
;(filter #(= (gcd % 10) 1) (range 1 10)) => (1 3 7 9)
;(* 1 3 7 9) => 189