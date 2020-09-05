(ns exercises.chapter2.exercise2_7)

;Exercise 2.7:
; Alyssa’s program is incomplete because she has not specified the
;   implementation of the interval abstraction.
; Here is a definition of the interval constructor:
;
;   (define (make-interval a b) (cons a b))
;
; Define selectors upper-bound and lower-bound to complete the
;   implementation.

(defn make-interval [a b]
  (assert (<= a b))
  (cons a (cons b '())))

(assert (= '(6.12 7.48) (make-interval 6.12 7.48)))

(make-interval 6.12 7.48)
;=> (6.12 7.48)

(defn lower-bound [interval]
  (first interval))

(assert (= 6.12 (lower-bound (make-interval 6.12 7.48))))

(lower-bound (make-interval 6.12 7.48))
;=> 6.12

(defn upper-bound [interval]
  (first (rest interval)))

(assert (= 7.48 (upper-bound (make-interval 6.12 7.48))))

(upper-bound (make-interval 6.12 7.48))
;=> 7.48

(def resistor-1 (make-interval 6.12 7.48))
(def resistor-2 (make-interval 4.465 4.935))

(defn add-interval [x y]
  (make-interval (+ (lower-bound x) (lower-bound y))
                 (+ (upper-bound x) (upper-bound y))))

(assert (= '(10.585 12.415)
          (add-interval resistor-1 resistor-2)))

(add-interval resistor-1 resistor-2)
;=> (10.585 12.415)

(defn mul-interval [x y]
  (let [p1 (* (lower-bound  x) (lower-bound y))
        p2 (* (lower-bound  x) (upper-bound y))
        p3 (* (upper-bound x) (lower-bound y))
        p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))

(assert (= '(27.3258 36.9138)
          (mul-interval resistor-1 resistor-2)))

(mul-interval resistor-1 resistor-2)
;=> (27.3258 36.9138)

(defn div-interval [x y]
  (mul-interval x
                (make-interval (/ 1.0 (upper-bound y))
                               (/ 1.0 (lower-bound y)))))

(div-interval resistor-1 resistor-2)
;=> (1.2401215805471126 1.6752519596864504)