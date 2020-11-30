(ns exercises.chapter2.exercise2_37)

; Exercise 2.37:
; Suppose we represent vectors v = (v i ) as sequences of numbers, and matrices
;   m = (m ij ) as sequences of vectors (the rows of the matrix).
; For example, the matrix
;
;    1 2 3 4 
;    4 5 6 6 
;    6 7 8 9 
;
; is represented as the sequence ((1 2 3 4) (4 5 6 6) (6 7 8 9)).
; With this representation, we can use sequence operations to concisely express
;   the basic matrix and vector operations.
; These operations (which are described in any book on matrix algebra) are the following:
;
; (dot-product v w) return the sum Σ i v i w i
; (matix-*-vector m v) return the vector t, where t i = Σ j m ij v j
; (matix-*-matrix m n) returns the matrix p, where p ij = Σ k m ik n kj
; (transpose m) returns the matrix n, where n ij = m ji
;
; We can define the dot product as 17
;
;   (define (dot-product v w)
;     (accumulate + 0 (map * v w)))
;
; Fill in the missing expressions in the following procedures for computing the
;   other matrix operations.
; (The procedure accumulate-n is defined in Exercise 2.36.)
;
; (define (matrix-*-vector m v)
;   (map ⟨ ?? ⟩ m))
;
; (define (transpose mat)
;   (accumulate-n ⟨ ?? ⟩ ⟨ ?? ⟩ mat))
;
; (define (matrix-*-matrix m n)
;   (let ((cols (transpose n)))
;     (map ⟨ ?? ⟩ m)))

(def append concat)

(defn accumulate [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))

(defn accumulate-n [op init seqs]
  (if (empty? (first seqs))
    nil
    (cons (accumulate op init (map first seqs))
          (accumulate-n op init (map rest seqs)))))

(defn dot-product [v w]
  (accumulate + 0 (map * v w)))

(def v '(1 2 3 4))
(def w '(4 5 6 6))
(def x '(6 7 8 9))

(def m '((1 2 3 4) (4 5 6 6) (6 7 8 9)))

(dot-product v w)
; => 56

(dot-product v v)
; => 30

(dot-product v x)
; => 80

(dot-product '(1 3 -5) '(4 -2 -1))
; => 3

(defn matrix-*-vector [m v]
  (map (fn [mi] (dot-product mi v)) m))

(matrix-*-vector m v)
; => (30 56 80)

(matrix-*-vector '((1 2 3) (4 5 6) (7 8 9))
                 '(2 1 3))
; => (13 31 49)

(matrix-*-vector '((7 9 11) (8 10 12)) '(1 2 3))
; => (58 64)

(defn transpose [mat]
  (accumulate-n cons nil mat))

(transpose m)
; => ((1 4 6) (2 5 7) (3 6 8) (4 6 9))

(transpose '((1 2) (3 4)))
; => ((1 3) (2 4))

(defn matrix-*-matrix [m n]
  (let [cols (transpose n)]
    (map (fn [mi] (matrix-*-vector cols mi)) m) ))

(matrix-*-matrix m m)
; => ((27 33 39 43) (60 75 90 100) (82 103 124 138))

(def lucas   '((1 2 3) (4 5 6)))
(def rodrigo '((7 8) (9 10) (11 12)))

(matrix-*-matrix lucas rodrigo)
; => ((58 64) (139 154))
