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
