(ns exercises.chapter2.exercise2_39)

; Exercise 2.39: Complete the following definitions of reverse (Exercise 2.18)
;   in terms of fold-right and fold-left from Exercise 2.38:
;
;   (define (reverse sequence)
;     (fold-right (lambda (x y) ⟨ ?? ⟩ ) nil sequence))
;
;   (define (reverse sequence)
;     (fold-left (lambda (x y) ⟨ ?? ⟩ ) nil sequence))

(def append concat)

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

(defn reverse-with-fold-right [sequence]
  (fold-right (fn [x y] (append y (list x)))  nil sequence))

(reverse-with-fold-right (list 1 4 9 16 25))
; => (25 16 9 4 1)

(reverse-with-fold-right (list 1))
; => (1)

(reverse-with-fold-right '())
; => ()

(defn reverse-with-fold-left [sequence]
  (fold-left (fn [x y] (append (list y) x)) nil sequence))

(reverse-with-fold-left (list 1 4 9 16 25))
; => (25 16 9 4 1)

(reverse-with-fold-left (list 1))
; => (1)

(reverse-with-fold-left '())
; => ()
