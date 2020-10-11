(ns exercises.chapter2.exercise2_38)

; Exercise 2.38:
; The accumulate procedure is also known as fold-right,
;   because it combines the first element of the sequence with the result
;   of combining all the elements to the right.
; There is also a fold-left , which is similar to fold-right , except that
;   it combines elements working in the opposite direction:
;
;   (define (fold-left op initial sequence)
;     (define (iter result rest)
;       (if (null? rest)
;         result
;         (iter (op result (car rest))
;                          (cdr rest))))
;     (iter initial sequence))
;
;What are the values of
;
;   (fold-right / 1 (list 1 2 3))
;   (fold-left / 1 (list 1 2 3))
;   (fold-right list nil (list 1 2 3))
;   (fold-left list nil (list 1 2 3))
;
; Give a property that op should satisfy to guarantee that fold-right and fold-left
;   will produce the same values for any sequence.

(defn accumulate [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))

(def fold-right accumulate)

(defn fold-left [op initial sequence]
 (loop [result initial remaining sequence]
   (if (empty? remaining)
     result
     (recur (op result (first remaining))
            (rest remaining)))))

(fold-right / 1 (list 1 2 3))
; => 3/2

(fold-left / 1 (list 1 2 3))
; => 1/6

(fold-right list nil (list 1 2 3))
; => (1 (2 (3 nil)))

(fold-left list nil (list 1 2 3))
; => (((nil 1) 2) 3)

; To have the same result, op should be cumutative, as with addition and multiplication:
;   x + y = y + x
;   x * y = y * x

(fold-right + 1 (list 1 2 3))
; => 7

(fold-left + 1 (list 1 2 3))
; => 7

(fold-right * 1 (list 1 2 3))
; => 6

(fold-left * 1 (list 1 2 3))
; => 6