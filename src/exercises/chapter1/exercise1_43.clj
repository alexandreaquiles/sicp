(ns exercises.chapter1.exercise1_43)

; Exercise 1.43:
;
; If f is a numerical function and n is a positive integer, then we can form the nth repeated application
;   of f , which is defined to be the function whose value at x is f (f (. . . (f (x )) . . . )).
; For example, if f is the function x → x + 1, then the nth repeated application of f is the
;   function x → x + n.
; If f is the operation of squaring a number, then the nth repeated application of f is the function
;   that raises its argument to the 2^n-th power.
; Write a procedure that takes as inputs a procedure that computes f and a positive integer n and
;   returns the procedure that computes the nth repeated application of f.
; Your procedure should be able to be used as follows:
;
;   ((repeated square 2) 5)
;   625
;
;Hint: You may find it convenient to use compose from Exercise 1.42.

(defn compose [f g]
  (fn [x] (f (g x))))

(defn repeated [f n]
  (assert (>= n 1) "n should be >= 1")
  (if (= n 1)
    (fn [x] (f x))
    (compose f (repeated f (dec n)))))

(defn square [x]
  (* x x))

((repeated square 2) 5)
; => 625