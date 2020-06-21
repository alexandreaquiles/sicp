(ns exercises.chapter1.exercise1_29)

;Exercise 1.29.
;
; Simpson's Rule is a more accurate method of numerical integration than the method illustrated above.
;
; Using Simpson's Rule, the integral of a function f between a and b is approximated as
;
; h/3(y0 + 4.y1 + 2.y2 + 4.y3 + 2.y4 + · · · + 2.yn−2 + 4.yn−1 + yn ),
;
;   where
;
; h = (b - a)/n, for some even integer n,
;
; and
;
; yk = f(a + kh).
;
; (Increasing n increases the accuracy of the approximation.)
;
; Define a procedure that takes as arguments f, a, b, and n and returns the value of the integral, computed
;   using Simpson's Rule.
; Use your procedure to integrate cube between 0 and 1 (with n = 100 and n = 1000), and compare the results to
;   those of the integral procedure shown above.

;ANSWER: We can write Simpson's Rule as follows:
;
; h/3(y0 + 4.(y1 + y3 · · · + yn−1) + 2.(y2 + y4 + ... + yn−2) + yn ),
;
; From: https://www.intmath.com/integration/6-simpsons-rule.php

(defn cube [x]
  (* x x x))

(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

(defn simpson-integral [f a b n]
  (defn h [] (/ (- b a) n))                                    ;could be a 'let'
  (defn y [k] (f (+ a (* k (h)))))
  (* (/ (h) 3)
     (+ (y 0)
        (* 4 (sum #(y %) 1 #(+ % 2) (- n 1)) )
        (* 2 (sum #(y %) 2 #(+ % 2) (- n 2)) )
        (y n))))



(simpson-integral cube 0 1 100)
; => 1/4

(simpson-integral cube 0 1 1000)
; => 1/4

; As said in the text:
;   (The exact value of the integral of cube between 0 and 1 is 1/4.)
