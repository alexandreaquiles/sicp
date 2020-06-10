(ns exercises.chapter1.exercise1_16)

;Exercise 1.16: Design a procedure that evolves an iterative exponentiation process that uses successive
; squaring and uses a logarithmic number of steps, as does fast-expt.
;(Hint: Using the observation that (b^n/2)^2 = (b^2)^n/2 , keep, along with the exponent n and the base b,
; an additional state variable a, and define the state transformation in such a way that the product ab^n is
; unchanged from state to state.
;At the beginning of the process a is taken to be 1, and the answer is given by the value of a at the end of the
; process.
;In general, the technique of defining an invariant quantity that remains unchanged from state to state is a
; powerful way to think about the design of iterative algorithms.)

(defn square [x]
  (* x x))

(defn fast-expt-iterative [b n]
  (loop [n n, b b, a 1]
    (cond (= n 0) a
          (even? n) (recur (/ n 2) (square b) a)
          :else (recur (dec n) b (* b a)))))

(assert (= '(1 2 4 8 16) (map (partial fast-expt-iterative 2) (range 5))))

(assert (= '(1 3 9 27 81) (map (partial fast-expt-iterative 3) (range 5))))
