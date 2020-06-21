(ns exercises.chapter1.text)

(defn square [x] (* x x))

;(defn square [x]
;  (exp (double (log x))))
;(defn double [x] (+ x x))
;PROBLEM: exp and log are not defined in Clojure Core

(defn sum-of-squares [x y]
  (+ (square x) (square y)))

(defn abs [x]
  (cond (< x 0) (- x)
        :else x))

(defn abs [x]
  (if (< x 0)
    (- x)
    x))

;(defn >= [x y]
;  (or (> x y) (= x y)))
;(defn >= [x y]
;  (not (< x y)))
;WARNING: operators >= and <= already defined in Clojure

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

(defn sqrt [x]
  (defn average [x y]
    (/ (+ x y) 2))
  (defn good-enough? [guess]
    (< (abs (- (square guess) x)) 0.001))
  (defn improve [guess]
    (average guess (/ x guess)))
  (defn sqrt-iter [guess]
    (if (good-enough? guess)
      guess
      (recur (improve guess))))
  (sqrt-iter 1.0))

(defn factorial [n]
  (if (= n 1)
    1
    (* n (factorial (- n 1)))))

(assert (= '(1 2 6 24 120 720) (map factorial (range 1 7))))

;(factorial 0)
; throws StackOverflowError
; could do (if (< n 1) 1 ...)

(defn factorial-iterative-sicp [n]
  (defn fact-iter [product counter max-count]
    (if (> counter max-count)
      product
      (recur (* counter product)
                 (+ counter 1)
                 max-count)))
  (fact-iter 1 1 n))

(map factorial-iterative-sicp (range 0 5))
; => (1 1 2 6 24)

(defn factorial-iterative [n]
  (defn fact-iter [fac n]
    (if (zero? n)
      fac
      (recur (* fac n) (dec n))))
  (fact-iter 1 n))

(assert (= '(1 2 6 24 120 720) (map factorial-iterative (range 1 7))))

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
      (recur(+ a b) a (- count 1))))
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


(defn count-change [amount]
  (defn first-denomination [kinds-of-coins]
    (cond (= kinds-of-coins 1) 1
          (= kinds-of-coins 2) 5
          (= kinds-of-coins 3) 10
          (= kinds-of-coins 4) 25
          (= kinds-of-coins 5) 50))
  (defn cc [amount kinds-of-coins]
    (cond (= amount 0) 1
          (or (< amount 0) (= kinds-of-coins 0)) 0
          :else (+ (cc amount (- kinds-of-coins 1))
                   (cc (- amount (first-denomination kinds-of-coins))
                       kinds-of-coins))))
  (cc amount 5))
(count-change 100)

(defn expt [b n]
  (if (= n 0)
    1
    (* b (expt b (- n 1)))))

(expt 2 3)
; => 8

(expt 2 4)
; => 16

(defn expt-iterative [b n]
  (defn expt-iter [b counter product]
    (if (= counter 0)
      product
      (expt-iter b (- counter 1) (* b product))))
  (expt-iter b n 1))

(expt-iterative 2 3)
; => 8

(expt-iterative 2 4)
; => 16

(defn fast-expt [b n]
  (cond (= n 0) 1
        (even? n) (square (fast-expt b (/ n 2)))
        :else (* b (fast-expt b (- n 1)))))

(fast-expt 2 3)
; => 8

;(fast-expt 2 3)
;(* 2 (fast-expt 2 2))
;(* 2 (square (fast-expt 2 1)))
;(* 2 (square (* 2 (fast-expt 2 0))))
;(* 2 (square (* 2 1)))

(fast-expt 2 4)
; => 16

;Already defined in Clojure
;(define (even? n)
;        (= (remainder n 2) 0))

(rem 28 16)
; => 12

(defn gcd [a b]
  (if (= b 0)
    a
    (recur b (rem a b))))

(gcd 28 16)
; => 4

(gcd 206 40)
; => 2

(defn divides? [a b] (= (rem b a) 0))

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (+ test-divisor 1))))

; The end test for find-divisor is based on the fact that if n is not prime it must have a divisor less than or equal to √n.
; Footnote: If d is a divisor of n, then so is n/d. But d and n/d cannot both be greater than √n.

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= n (smallest-divisor n)))

(filter prime? (range 1 20))
; => (1 2 3 5 7 11 13 17 19)

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
        :else (rem (* base (expmod base (- exp 1) m)) m)))

(defn fermat-test [n]
  (defn try-it [a]
    (= (expmod a n n) a))
  (try-it (+ 1 (rand-int (- n 1)))))

(defn fast-prime? [n times]
  (cond (= times 0) true
        (fermat-test n) (fast-prime? n (- times 1))
        :else false))

;(defn fermat-test [n]
;  (let [a (inc (rand-int (dec n)))]
;    (= (expmod a n n) a)))
;(defn fast-prime?
;  ([n] (fast-prime? n 50))
;  ([n num-tests]
;     (every? true? (take num-tests (repeatedly #(fermat-test n))))))
;from: http://www.sicpdistilled.com/section/1.2.6/

(fast-prime? 2 1000)
; => true

(fast-prime? 1999 1000)
; => true

(defn sum-integers [a b]
  (if (> a b)
    0
    (+ a (sum-integers (+ a 1) b))))
(sum-integers 1 5)
; => 15

(defn cube [x]
  (* x x x))

(defn sum-cubes [a b]
  (if (> a b)
    0
    (+ (cube a)
       (sum-cubes (+ a 1) b))))
(sum-cubes 1 5)
;=> 225

(+ (cube 1) (cube 2) (cube 3) (cube 4) (cube 5))
;=> 225

;just for fun
(defn sum-integers-iterative [a b]
  (assert (and (pos? a) (pos? b)))
  (loop [a a sum 0]
    (if (> a b)
      sum
      (recur (inc a) (+ sum a) ))))
(sum-integers-iterative 1 5)
; => 15

(defn pi-sum [a b]
  (if (> a b)
    0
    (+ (/ 1.0 (* a (+ a 2))) (pi-sum (+ a 4) b))))

;π/8 ~= 0.39269908169

(pi-sum 1 10000)
;=> 0.3926740816989741

(pi-sum 1 50000)
;=> 0.3926940816987261

(pi-sum 1 60000)                                            ;~max without StackOverflow
;=> 0.3926949150320586

(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

(defn sum-cubes [a b]
  (sum cube a inc b))

(sum-cubes 1 10)
; => 3025

(defn sum-integers [a b]
  (sum identity a inc b))

(sum-integers 1 10)
; => 55

(defn pi-sum [a b]
  (defn pi-term [x]
    (/ 1.0 (* x (+ x 2))))
  (defn pi-next [x]
    (+ x 4))
  (sum pi-term a pi-next b))

(* 8 (pi-sum 1 1000))
; => 3.139592655589783

(defn integral [f a b dx]
  (defn add-dx [x] (+ x dx))
  (* (sum f (+ a (/ dx 2.0)) add-dx b)
     dx))

(integral cube 0 1 0.01)
; => 0.24998750000000042

(integral cube 0 1 0.001)
; => 0.249999875000001
