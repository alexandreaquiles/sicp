(ns exercises.chapter1.exercise1_6)

; Exercise 1.6.
;   Alyssa P. Hacker doesn't see why if needs to be provided as a special form.
;   ``Why can't I just define it as an ordinary procedure in terms of cond?'' she asks.
;   Alyssa's friend Eva Lu Ator claims this can indeed be done, and she defines a new version of if:
;
;       (define (new-if predicate then-clause else-clause)
;         (cond (predicate then-clause)
;           (else else-clause)))
;
;   Eva demonstrates the program for Alyssa:
;
;      (new-if (= 2 3) 0 5)
;         5
;
;      (new-if (= 1 1) 0 5)
;         0
;
;   Delighted, Alyssa uses new-if to rewrite the square-root program:
;
;     (define (sqrt-iter guess x)
;       (new-if (good-enough? guess x)
;         guess
;         (sqrt-iter (improve guess x)
;           x)))
;
;   What happens when Alyssa attempts to use this to compute square roots?
;   Explain.
;
;     (define (sqrt-iter guess x)
;       (if (good-enough? guess x)
;         guess
;         (sqrt-iter (improve guess x)
;           x)))

; ANSWER: Alyssa will get an infinite recursion, because the call to sqrt-iter the else-clause is evaluated before
;   entering the new-if function. As it is recursive, it will go on until Alyssa gets a StackOverflowError.
; If we defer the execution pass an anonymous function, it will evaluated only inside new-if.
; But then, the problem happens when the clause passed as an argument is not a function, but a simple value.
; So, a more elaborate new-if would check if the clauses are values or functions.

(defn square [x]
  (* x x))
;(square 2)
; => 4

(defn average [x y]
  (/ (+ x y) 2))
;(average 10 5)
; => 15/2

(defn improve [guess x]
  (average guess (/ x guess)))

;(improve 1 2)
; => 3/2

;(improve 1.5 2)
; => 1.4166666666666665

(defn abs [x]
  (cond (< x 0) (- x)
        :else x))

;(abs -1)
; => 1

;(abs 1)
; => 1

;(abs 0)
; => 1

(defn good-enough? [guess x]
  ;(println "good-enough? guess:" guess ", x:" x)
  (< (abs (- (square guess) x)) 0.001))

;(good-enough? 1.4167 2)
; => false

;(good-enough? 1.4142 2)
; => true

;(defn new-if [predicate then-clause else-clause]
;  (println "new-if -> predicate: " predicate ", then-clause: " then-clause ", else-clause: " else-clause)
;  (cond predicate then-clause
;        :else else-clause))

;(new-if (= 2 3) 0 5)
; => 5

;(new-if (= 1 1) 0 5)
; => 0

;(defn sqrt-iter [guess x]
;  ;(println "sqrt-iter -> guess:" guess ", x:" x)
;  (new-if (good-enough? guess x)
;          guess
;          (sqrt-iter (improve guess x)
;                 x)))


(defn new-if [predicate then-clause else-clause]
  ;(println "new-if -> predicate: " predicate ", then-clause: " then-clause ", else-clause: " else-clause)
  (defn call-if-it-is-a-function [statement]
    (cond (ifn? statement) (statement) :else statement))
  (cond predicate
        (call-if-it-is-a-function then-clause)
        :else (call-if-it-is-a-function else-clause)))

(println (new-if (= 2 3) 0 5))
; => 5

(println (new-if (= 1 1) 0 5))
; => 0

(defn sqrt [x]
  (defn sqrt-iter [guess x]
    ;(println "sqrt-iter -> guess:" guess ", x:" x)
    (new-if (good-enough? guess x)
            guess
            #(sqrt-iter (improve guess x)
                        x)))
  (sqrt-iter 1.0 x))

(println (sqrt 2))
; => 1.4142156862745097
