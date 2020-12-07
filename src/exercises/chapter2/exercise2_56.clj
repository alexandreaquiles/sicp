(ns exercises.chapter2.exercise2_56)

; Exercise 2.56: Show how to extend the basic differentiator to handle more kinds of expressions.
; For instance, implement the differentiation rule
;
; d(u^n) / dx = n u^n-1 du/dx
;
; by adding a new clause to the deriv program and defining appropriate procedures
;   'exponentiation?', 'base', 'exponent', and 'make-exponentiation'.
; (You may use the symbol ** to denote exponentiation.)
;
; Build in the rules that anything raised to the power 0 is 1 and anything raised to the power 1 is the thing itself.

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

(defn augend [s] (first (rest (rest s))))

(defn make-product [m1 m2]
  (cond (or (=number? m1 0) (=number? m2 0)) 0
        (=number? m1 1) m2
        (=number? m2 1) m1
        (and (number? m1) (number? m2)) (* m1 m2)
        :else (list '* m1 m2)))

(defn product? [x] (and (seq? x) (= (first x) '*)))

(defn multiplier [p] (first (rest p)))

(defn multiplicand [p] (first (rest (rest p))))

(defn make-exponentiation [base exponent]
  (cond (=number? exponent 0) 1
        (=number? exponent 1) base
        :else (list '** base exponent)))

(make-exponentiation 'u 'n)
; => (** u n)

(make-exponentiation 'u 0)
; => (** u n)

(make-exponentiation 'u 1)
; => (** u n)

(defn exponentiation? [x] (and (seq? x) (= (first x) '**)))

(exponentiation? '(** x 2))
; => true

(exponentiation? '(+ x 2))
; => false

(exponentiation? 1)
; => false

(defn base [e] (first (rest e)))

(defn exponent [e] (first (rest (rest e))))

(defn dec-exponent [exponent]
  (cond
    (number? exponent) (dec exponent)
    :else (list exponent '-1)))

(dec-exponent 2)
; => 1

(dec-exponent 'x)
; => (x -1)

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

(deriv '(** x 3) 'x)
; => (* 3 (** x 2))

(deriv '(** x y) 'x)
; => (* y (** x (y -1)))

(deriv '(** x 1) 'x)
; => 1