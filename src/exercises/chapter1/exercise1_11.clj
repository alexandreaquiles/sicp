(ns exercises.chapter1.exercise1_11)

; Exercise 1.11.
;   A function f is defined by the rule that
;     f(n) = n if n < 3
;   and
;     f(n) = f(n - 1) + 2f(n - 2) + 3f(n - 3) if n >= 3.
;
;   Write a procedure that computes f by means of a recursive process.
;   Write a procedure that computes f by means of an iterative process.

; RECURSIVE PROCESS
(defn f [n]
  (if (< n 3)
      n
      (+ (f (- n 1)) (* 2 (f (- n 2))) (* 3 (f (- n 3))))))

(map f (range 10))
; => (0 1 2 4 11 25 59 142 335 796)

; ITERATIVE PROCESS
(defn f [n]
  (loop [a 0 b 1 c 2 count n]
    (if (= count 0)
      a
      (recur b c (+ c (* 2 b) (* 3 a) ) (dec count)))))

;Based on fib-iter
;Reference: http://community.schemewiki.org/?sicp-ex-1.11

(f 0)
; => 0

(f 1)
; => 1

(f 2)
; => 2

(f 3)
;f(2) + 2f(1) + 3f(0)
;2 + 2 + 0
; => 4

(assert (= '(0 1 2 4 11 25 59 142 335 796) (map f (range 10))))
; => (0 1 2 4 11 25 59 142 335 796)
