(ns exercises.chapter2.exercise2_57)

; Exercise 2.57:
; Extend the differentiation program to handle sums and products of arbitrary numbers of (two or more)
;   terms.
; Then the last example above could be expressed as
;
;   (deriv '(* x y (+ x 3)) 'x)
;
; Try to do this by changing only the representation for sums and products, without changing the deriv procedure at all.
; For example, the addend of a sum would be the first term, and the augend would be the sum of the rest of the terms.


(def accumulate reduce)

(defn =number? [exp num] (and (number? exp) (= exp num)))

(defn variable? [x] (symbol? x))

(defn same-variable? [v1 v2]
  (and (variable? v1) (variable? v2) (= v1 v2)))

(defn make-sum [a1 a2]
  (cond (=number? a1 0) a2
        (=number? a2 0) a1
        (and (number? a1) (number? a2)) (+ a1 a2)
        :else (list '+ a1 a2)))

(defn sum? [x] (and (seq? x) (= (first x) '+)))

(defn addend [s] (first (rest s)))

(defn augend [s] (accumulate make-sum 0 (rest (rest s))))

(defn make-product [m1 m2]
  (cond (or (=number? m1 0) (=number? m2 0)) 0
        (=number? m1 1) m2
        (=number? m2 1) m1
        (and (number? m1) (number? m2)) (* m1 m2)
        :else (list '* m1 m2)))

(defn product? [x] (and (seq? x) (= (first x) '*)))

(defn multiplier [p] (first (rest p)))

(defn multiplicand [s] (accumulate make-product 1 (rest (rest s))))

(defn make-exponentiation [base exponent]
  (cond (=number? exponent 0) 1
        (=number? exponent 1) base
        :else (list '** base exponent)))

(defn exponentiation? [x] (and (seq? x) (= (first x) '**)))

(defn base [e] (first (rest e)))

(defn exponent [e] (first (rest (rest e))))

(defn dec-exponent [exponent]
  (cond
    (number? exponent) (dec exponent)
    :else (list exponent '-1)))

(defn deriv [exp var]
  (cond (number? exp) 0
        (variable? exp) (if (same-variable? exp var) 1 0)
        (sum? exp) (make-sum (deriv (addend exp) var)
                             (deriv (augend exp) var))
        (product? exp) (make-sum
                         (make-product (multiplier exp)
                                       (deriv (multiplicand exp) var))
                         (make-product (deriv (multiplier exp) var)
                                       (multiplicand exp)))
        (exponentiation? exp) (make-product
                                (make-product
                                  (exponent exp)
                                  (make-exponentiation (base exp) (dec-exponent (exponent exp))))
                                (deriv (base exp) var))
        :else (throw (IllegalArgumentException. (str "unknown expression type: DERIV" exp)))))

(deriv '(* x y (+ x 3)) 'x)
; => (+ (* x y) (* y (+ x 3)))

(deriv '(+ x y z w) 'x)
; => 1