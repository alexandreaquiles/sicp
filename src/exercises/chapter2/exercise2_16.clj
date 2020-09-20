(ns exercises.chapter2.exercise2_16
  (:use exercises.chapter2.exercise2_7
        exercises.chapter2.exercise2_8
        exercises.chapter2.exercise2_10
        exercises.chapter2.exercise2_11
        exercises.chapter2.exercise2_12))

; Exercise 2.16:
; Explain, in general, why equivalent algebraic expressions may lead to different answers.
; Can you devise an interval-arithmetic package that does not have this shortcoming, or is this task impossible?
; (Warning: This problem is very difficult.)

; distributive property => a(b+c) = ab + ac

(def a (make-interval 2 4))
(def b (make-interval -2 0))
(def c (make-interval 3 8))

; a(b + c)
(def x (mul-interval a (add-interval b c)))

; ab + ac
(def y (add-interval (mul-interval a b)
                     (mul-interval a c)))

(lower-bound x)
;=> 2

(upper-bound x)
;=> 32

(lower-bound y)
;=> -2

(upper-bound y)
;=> 32

; x != y

; Site on Interval Computations:
; http://www.cs.utep.edu/interval-comp/main.html

; via http://wiki.drewhess.com/wiki/SICP_exercise_2.16
