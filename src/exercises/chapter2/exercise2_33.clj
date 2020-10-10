(ns exercises.chapter2.exercise2_33)

; Exercise 2.33: Fill in the missing expressions to complete the following definitions of
;   some basic list-manipulation operations as accumulations:
;
;   (define (map p sequence)
;     (accumulate (lambda (x y)
;                   ⟨ ?? ⟩ ) nil sequence))
;
;   (define (append seq1 seq2)
;     (accumulate cons
;                  ⟨ ?? ⟩ ⟨ ?? ⟩ ))
;
;   (define (length sequence)
;     (accumulate
;                 ⟨ ?? ⟩ 0 sequence))

(defn accumulate [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))

(defn map [p sequence]
  (accumulate (fn [x y] (cons (p x) y))
              nil sequence))

(defn square [x] (* x x))

(map square '(1 2 3 4 5))
; 5 nil
; 4 (25)
; 3 (16 25)
; 2 (9 16 25)
; 1 (4 9 16 25)
; => (1 4 9 16 25)

(defn append [seq1 seq2]
  (accumulate cons seq2 seq1))

(def squares (list 1 4 9 16 25))
(def odds (list 1 3 5 7))

(append squares odds)
; => (1 4 9 16 25 1 3 5 7)

(defn length [sequence]
  (accumulate (fn [_ y] (inc y)) 0 sequence))

(length '())
; => 0

(length '(1 2 3))
; => 3