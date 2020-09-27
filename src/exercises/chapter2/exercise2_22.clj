(ns exercises.chapter2.exercise2_22)

; Exercise 2.22:
; Louis Reasoner tries to rewrite the first square-list procedure of
;   Exercise 2.21 so that it evolves an iterative process:
;
;   (define (square-list items)
;     (define (iter things answer)
;       (if (null? things)
;         answer
;         (iter (cdr things)
;               (cons (square (car things))
;                      answer))))
;    (iter items nil))
;
; Unfortunately, defining square-list this way produces the answer list
;   in the reverse order of the one desired.
; Why?
;
; Louis then tries to fix his bug by interchanging the arguments to cons :
;
; (define (square-list items)
;   (define (iter things answer)
;     (if (null? things)
;       answer
;       (iter (cdr things)
;             (cons answer
;                 (square (car things))))))
;   (iter items nil))
;
; This doesn’t work either. Explain.

(defn square [x] (* x x))

(defn square-list [items]
  (defn iter [things answer]
    (if (empty? things)
      answer
      (iter (rest things)
            (cons (square (first things))
                  answer))))
  (iter items nil))

(square-list (list 1 2 3 4))
; => (16 9 4 1)

; The order is reversed because every 'cons' put the square at the head of the list.

(defn square-list [items]
  (defn iter [things answer]
    (if (empty? things)
      answer
      (iter (rest things)
            (cons answer (square (first things))))))
  (iter items nil))

(square-list (list 1 2 3 4))
; Execution error (IllegalArgumentException)
; Don't know how to create ISeq from: java.lang.Long

; In Clojure, 'cons' can't accept a list and a number as arguments.
; The second argument should be a sequence.

; In Scheme, if the second argument is not a list, we get a pair.
; So, we end up with pairs of pairs of pairs:
;
; => ((((() . 1) . 4) . 9) . 16)