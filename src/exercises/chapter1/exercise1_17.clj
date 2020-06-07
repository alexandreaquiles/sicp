(ns exercises.chapter1.exercise1_17)

;Exercise 1.17:
; The exponentiation algorithms in this section are based on performing exponentiation by means
;   of repeated multiplication. In a similar way, one can perform integer multiplication by means of repeated
;   addition. The following multiplication procedure (in which it is assumed that our language can only add, not
;   multiply) is analogous to the expt procedure:
;
;  (define (* a b)
;   (if (= b 0)
;   0
;   (+ a (* a (- b 1)))))
;
; This algorithm takes a number of steps that is linear in b.
; Now suppose we include, together with addition, operations double , which doubles an integer, and halve,
;   which divides an (even) integer by 2. Using these, design a multiplication procedure analogous to fast-expt
;   that uses a logarithmic number of steps.

(defn multiply [a b]
  (if (= b 0)
    0
    (+ a (multiply a (- b 1)))))

(assert (= '(0 2 4 6 8) (map (partial multiply 2) (range 5))))

(defn double [x]
  (+ x x))
;WARNING: double already refers to: #'clojure.core/double in namespace: exercises.chapter1.exercise1_17, being replaced by: #'exercises.chapter1.exercise1_17/double

(defn halve [x]
  (/ x 2))

(defn fast-multiply [a b]
  (cond (= b 0) 0
        (even? b) (double (fast-multiply a (halve b)))
        :else (+ a (multiply a (- b 1)))))
(assert (= '(0 2 4 6 8) (map (partial fast-multiply 2) (range 5))))
