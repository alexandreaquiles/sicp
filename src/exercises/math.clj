(ns exercises.math)

(defn square [x] (* x x))

(defn abs [x]
  (if (< x 0)
    (- x)
    x))

(defn gcd [a b]
  (if (= b 0)
    a
    (recur b (rem a b))))

(defn average [x y]
  (/ (+ x y) 2))

(defn sqrt [x]
  (defn good-enough? [guess]
    (< (abs (- (square guess) x)) 0.001))
  (defn improve [guess]
    (average guess (/ x guess)))
  (defn sqrt-iter [guess]
    (if (good-enough? guess)
      guess
      (recur (improve guess))))
  (sqrt-iter 1.0))

(defn expt [b n]
  (loop [b b counter n product 1]
    (if (= counter 0)
      product
      (recur b (- counter 1) (* b product)))))

(assert (= '(2 4 8 16 32) (map #(expt 2 %) (range 1 6))))

(defn divides? [a b] (= (rem b a) 0))

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (+ test-divisor 1))))
(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= n (smallest-divisor n)))

(assert (= '(1 2 3 5 7 11 13 17 19) (filter prime? (range 1 20))))
