(ns exercises.chapter2.exercise2_58)

; Exercise 2.58: Suppose we want to modify the differentiation program so that it works with ordinary mathematical
;   notation, in which + and * are infix rather than prefix operators.
; Since the differentiation program is defined in terms of abstract data, we can modify it to work with different
;   representations of expressions solely by changing the predicates, selectors, and constructors that define the
;   representation of the algebraic expressions on which the differentiator is to operate.
; a. Show how to do this in order to differentiate algebraic expressions presented in infix form, such as
;   (x + (3 * (x + (y + 2)))).
; To simplify the task, assume that + and * always take two arguments and that expressions are fully parenthesized.
;b. The problem becomes substantially harder if we allow standard algebraic notation, such as
;   (x + 3 * (x + y + 2)),
; which drops unnecessary parentheses and assumes that multiplication is done before addition.
; Can you design appropriate predicates, selectors, and constructors for this notation such that our derivative
;   program still works?

(defn =number? [exp num] (and (number? exp) (= exp num)))

(defn variable? [x] (symbol? x))

(defn same-variable? [v1 v2]
  (and (variable? v1) (variable? v2) (= v1 v2)))

(defn sum? [x] (and (seq? x) (seq? (rest x)) (= (first (rest x)) '+)))

(sum? '(+ x y))
; => false

(sum? '(x + y))
; => true

(defn make-sum [a1 a2]
  (cond (=number? a1 0) a2
        (=number? a2 0) a1
        (and (number? a1) (number? a2)) (+ a1 a2)
        :else (list a1 '+ a2)))

(defn addend [s] (first s))

(addend '(x + y))
; => x

(defn augend [s] (first (rest (rest s))))

(augend '(x + y))
; => y

(defn product? [x] (and (seq? x) (seq? (rest x)) (= (first (rest x)) '*)))

(product? '(* x y))
; => false

(product? '(x * y))
; => true

(defn make-product [m1 m2]
  (cond (or (=number? m1 0) (=number? m2 0)) 0
        (=number? m1 1) m2
        (=number? m2 1) m1
        (and (number? m1) (number? m2)) (* m1 m2)
        :else (list m1 '* m2)))

(defn multiplier [p] (first p))

(multiplier '(x * y))
; => x

(defn multiplicand [p] (first (rest (rest p))))

(multiplicand '(x * y))
; => y

(defn exponentiation? [x] (and (seq? x) (seq? (rest x)) (= (first (rest x)) '**)))

(exponentiation? '(** u n))
; => false

(exponentiation? '(u ** n))
; => true

(defn make-exponentiation [base exponent]
  (cond (=number? exponent 0) 1
        (=number? exponent 1) base
        :else (list base '** exponent)))

(defn base [e] (first e))

(base '(u ** n))
; => u

(defn exponent [e] (first (rest (rest e))))

(exponent '(u ** n))
; => n

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

(deriv '(x + (3 * (x + (y + 2)))) 'x)
; => 4

(deriv '(x + 3) 'x)
; => 1

(deriv '(x + y) 'x)
; => 1

(deriv '(x * y) 'x)
; => y

(deriv '((x * y) * (x + 3)) 'x)
; => ((x * y) + (y * (x + 3)))

(deriv '(x ** 3) 'x)
; => (3 * (x ** 2))

(deriv '(x ** y) 'x)
; => (y * (x ** (y -1)))

(deriv '(x ** 1) 'x)
; => 1

(deriv '(x + 3 * (x + y + 2)) 'x)
; => 1
; WRONG
