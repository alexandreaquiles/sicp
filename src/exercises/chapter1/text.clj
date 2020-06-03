(ns exercises.chapter1.text)

(defn square [x] (* x x))

;(defn square [x]
;  (exp (double (log x))))
;(defn double [x] (+ x x))
;PROBLEM: exp and log are not defined in Clojure Core

(defn sum-of-squares [x y]
  (+ (square x) (square y)))

(defn abs [x]
  (cond ((< x 0) (- x))
        (else x)))

(defn abs [x]
  (if (< x 0)
    (- x)
    x))

(defn >= [x y]
  (or (> x y) (= x y)))

(defn >= [x y]
  (not (< x y)))

;(defn sqrt-iter [guess x]
;  (if (good-enough? guess x)
;    guess
;    (sqrt-iter (improve guess x)
;      x)))
;
;(defn improve [guess x]
;  (average guess (/ x guess)))
;
;(defn average [x y]
;  (/ (+ x y) 2))
;
;(defn good-enough? [guess x]
;  (< (abs (- (square guess) x)) 0.001))
;
;(defn sqrt [x]
;  (sqrt-iter 1.0 x))

(defn (sqrt x)
  (defn good-enough? [guess]
    (< (abs (- (square guess) x)) 0.001))
  (defn improve [guess]
    (average guess (/ x guess)))
  (defn sqrt-iter [guess]
    (if (good-enough? guess)
      guess
      (sqrt-iter (improve guess))))
  (sqrt-iter 1.0))

(defn factorial [n]
  (if (= n 1)
    1
    (* n (factorial (- n 1)))))

(map factorial (range 1 5))
; => (1 2 6 24)

;(factorial 0)
; throws StackOverflowError
; could do (if (< n 1) 1 ...)

(defn factorial [n]
  (defn fact-iter [product counter max-count]
    (if (> counter max-count)
      product
      (fact-iter (* counter product)
                 (+ counter 1)
                 max-count)))
  (fact-iter 1 1 n))

(map factorial (range 0 5))
; => (1 1 2 6 24)


(defn fib [n]
  (cond (= n 0) 0
        (= n 1) 1
        :else (+ (fib (- n 1))
                 (fib (- n 2)))))

(map fib (range 10))
; => (0 1 1 2 3 5 8 13 21 34)

(defn fib [n]
  (defn fib-iter [a b count]
    (if (= count 0)
      b
      (fib-iter (+ a b) a (- count 1))))
  (fib-iter 1 0 n))
(map fib (range 10))
; => (0 1 1 2 3 5 8 13 21 34)

;(fib-iter 1 0 0)
;(if (= 0 0) 0 (fib-iter (+ 1 0) 1 (- 0 1))))
;            0

;(fib-iter 1 0 1)
;(if (= 1 0) 1 (fib-iter (+ 1 0) 1 (- 1 1))))
;                  (fib-iter (+ 1 0) 1 (- 1 1)))
;                  (fib-iter 1 1 0))
;                  (if (= 0 0) 1 (fib-iter (+ 1 1) 1 (- 0 1))))
;                              1

;(fib-iter 1 0 2)
;(if (= 2 0) 0 (fib-iter (+ 1 0) 1 (- 2 1))))
;              (fib-iter (+ 1 0) 1 (- 2 1)))
;              (fib-iter    1    1    1   )
;              (if (= 1 0) 1 (fib-iter (+ 1 1) 1 (- 1 1))))
;                            (fib-iter (+ 1 1) 1 (- 1 1))))
;                            (fib-iter    2    1    0)))
;                            (if (= 0 0) 1 (fib-iter (+ 2 1) 2 (- 0 1))))
;                                        1
