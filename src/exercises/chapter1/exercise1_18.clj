(ns exercises.chapter1.exercise1_18)

;Exercise 1.18:
; Using the results of Exercise 1.16 and Exercise 1.17, devise a procedure that generates an iterative process
;   for multiplying two integers in terms of adding, doubling, and halving and uses a logarithmic number of steps. 40

;Footnote 40:
; This algorithm, which is sometimes known as the “Russian peasant method” of multiplication, is ancient.
; Examples of its use are found in the Rhind Papyrus, one of the two oldest mathematical documents in existence,
; written about 1700 B.C. (and copied from an even older document) by an Egyptian scribe named A'h-mose.

(defn double [x]
  (+ x x))
;WARNING: double already refers to: #'clojure.core/double in namespace: exercises.chapter1.exercise1_18, being replaced by: #'exercises.chapter1.exercise1_18/double


(defn halve [x]
  (/ x 2))

(defn fast-multiply-iterative [a b]
  (loop [a a b b c 0]
    (cond (<= b 0) c
          (even? b) (recur (double a) (halve b) c)
          :else (recur a (dec b) (+ a c)))))

(assert (= '(0 2 4 6 8) (map (partial fast-multiply-iterative 2) (range 5))))
