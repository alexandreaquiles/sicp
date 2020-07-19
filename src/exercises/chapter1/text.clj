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

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn good-enough? [guess x]
  (< (abs (- (square guess) x)) 0.001))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x)
               x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

(sqrt 9)
; => 3.00009155413138

(sqrt 2)
; => 1.4142156862745097

(defn sqrt [x]
  (defn internal-average [x y]
    (/ (+ x y) 2))
  (defn internal-good-enough? [guess]
    (< (abs (- (square guess) x)) 0.001))
  (defn internal-improve [guess]
    (internal-average guess (/ x guess)))
  (defn internal-sqrt-iter [guess]
    (if (internal-good-enough? guess)
      guess
      (recur (internal-improve guess))))
  (internal-sqrt-iter 1.0))

(sqrt 9)
; => 3.00009155413138

(sqrt 2)
; => 1.4142156862745097

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
  (loop [fac 1 n n]
    (if (zero? n)
      fac
      (recur (* fac n) (dec n)))))

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
      (recur (+ a b) a (- count 1))))
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
;=> 292

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
      (recur (inc a) (+ sum a)))))

(sum-integers-iterative 1 5)
; => 15

(defn pi-sum [a b]
  (if (> a b)
    0
    (+ (/ 1.0 (* a (+ a 2))) (pi-sum (+ a 4) b))))

;π/8 ~= 0.39269908169

(pi-sum 1 10000)
;=> 0.3926740816989741

;(pi-sum 1 50000)
;can throw StackOverflowError in the first run
;=> 0.3926940816987261

;(pi-sum 1 60000) ;~max without StackOverflow
;can throw StackOverflowError in the first run
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

(fn [x] (+ x 4))

(fn [x] (/ 1.0 (* x (+ x 2))))

(defn pi-sum [a b]
  (sum (fn [x] (/ 1.0 (* x (+ x 2))))
       a
       (fn [x] (+ x 4))
       b))

(pi-sum 1 10000)
;=> 0.3926740816989741

(defn integral [f a b dx]
  (* (sum f
          (+ a (/ dx 2.0))
          (fn [x] (+ x dx))
          b)
     dx))

(integral cube 0 1 0.01)
; => 0.24998750000000042

(defn plus4 [x] (+ x 4))

(plus4 10)
; => 14

(def plus4-with-def (fn [x] (+ x 4)))

(plus4-with-def 10)
; => 14

((fn [x y z] (+ x y (square z)))
 1 2 3)
; => 12

;Footnote 53
; It would be clearer and less intimidating to people learning Lisp if a name more
;   obvious than 'lambda' , such as 'make-procedure' , were used.
; But the convention is firmly entrenched.
; The notation is adopted from the λ-calculus, a mathematical formalism introduced by
;   the mathematical logician Alonzo Church (1941).
; Church developed the λ-calculus to provide a rigorous foundation for studying the
;   notions of function and function application.
; The λ-calculus has become a basic tool for mathematical investigations of the
;   semantics of programming languages.

;   f (x , y) = x(1 + xy)² + y(1 − y) + (1 + xy)(1 − y)
; could also express as
;   a = 1 + xy,
;   b = 1 − y,
;   f (x , y) = xa² + yb + ab.

(defn f [x y]
  (defn f-helper [a b]
    (+ (* x (square a))
       (* y b)
       (* a b)))
  (f-helper (+ 1 (* x y))
            (- 1 y)))

(f 10 20)
; => 399811

(defn f [x y]
  ((fn [a b]
     (+ (* x (square a))
        (* y b)
        (* a b)))
   (+ 1 (* x y))
   (- 1 y)))

(f 10 20)
; => 399811

(defn f [x y]
  (let [a (+ 1 (* x y))
        b (- 1 y)]
    (+ (* x (square a))
       (* y b)
       (* a b))))

(f 10 20)
; =>

; From SICP:
; No new mechanism is required in the interpreter in order to provide local variables.
; A 'let' expression is simply syntactic sugar for the underlying 'lambda' application.

; But 'let' is a macro in Clojure: there's the destructuring of the vector.

(let [x 5]
  (+ (let [x 3]
       (+ x (* x 10)))                                      ; internally x is 3, and the result is 33
     x))                                                    ; externally x is 5
; => 38

(let [x 2]
  (let [x 3 y (+ x 2)]
    (* x y)))
; => 15
; From SICP: will have the value 12 because, inside the body of the 'let' , x will
;   be 3 and y will be 4 (which is the outer x plus 2)
; But, in Clojure, y will be computed using the inner x

(let [x 2]
  (let [y (+ x 2) x 3]
    (* x y)))
; => 12
; If we invert the expressions, we'll get 12

(defn f [x y]
  (def a (+ 1 (* x y)))
  (def b (- 1 y))
  (+ (* x (square a))
     (* y b)
     (* a b)))

(f 10 20)
; => 399811

;In Clojure, when 'def' is used, there's no lexical scope


(defn close-enough? [x y]
  (< (abs (- x y)) 0.001))

(defn positive? [x]
  (> x 0))

(defn negative? [x]
  (< x 0))

(defn search [f neg-point pos-point]
  (let [midpoint (average neg-point pos-point)]
    (if (close-enough? neg-point pos-point)
      midpoint
      (let [test-value (f midpoint)]
        (cond (positive? test-value) (search f neg-point midpoint)
              (negative? test-value) (search f midpoint pos-point)
              :else midpoint)))))

(defn half-interval-method [f a b]
  (let [a-value (f a)
        b-value (f b)]
    (cond (and (negative? a-value) (positive? b-value)) (search f a b)
          (and (negative? b-value) (positive? a-value)) (search f b a)
          :else (throw (ex-info "Values are not of opposite sign" {:a a :b b})))))

;(half-interval-method Math/sin 2.0 4.0)
;  Syntax error compiling at (text.clj:509:1).
;  Unable to find static field: sin in class java.lang.Math

(half-interval-method #(Math/sin %) 2.0 4.0)
; => 3.14111328125

;x³ − 2x − 3 = 0
(half-interval-method (fn [x] (- (* x x x) (* 2 x) 3)) 1.0 2.0)
; => 1.89306640625

;A number x is called a fixed point of a function f if x satisfies the equation f (x) = x

(def tolerance 0.00001)
(defn fixed-point [f first-guess]
  (defn close-enough? [v1 v2]
    (< (abs (- v1 v2)) tolerance))
  (defn try* [guess]
    (let [next (f guess)]
      (if (close-enough? guess next)
        next
        (try* next))))
  (try* first-guess))

; 'try' is a function for error handling in clojure.core

(fixed-point #(Math/cos %) 1.0)
; => 0.7390822985224024

(fixed-point #(+ (Math/sin %) (Math/cos %)) 1.0)
; => 1.2587315962971173

(defn fixed-point-sqrt [x]
  (fixed-point #(/ x %) 1.0))

(fixed-point-sqrt 4)
; Execution error (StackOverflowError)

(defn fixed-point-sqrt-with-avg [x]
  (fixed-point #(average % (/ x %)) 1.0))

(fixed-point-sqrt-with-avg 4)
; => 2.000000000000002