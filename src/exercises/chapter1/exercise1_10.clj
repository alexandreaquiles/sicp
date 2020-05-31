(ns exercises.chapter1.exercise1_10)

; Exercise 1.10.
;   The following procedure computes a mathematical function called Ackermann's function.
;
;   (define (A x y)
;     (cond ((= y 0) 0)
;           ((= x 0) (* 2 y))
;           ((= y 1) 2)
;           (else (A (- x 1)
;                   (A x (- y 1))))))
;
;What are the values of the following expressions?
;
;   (A 1 10)
;
;   (A 2 4)
;
;   (A 3 3)
;
;Consider the following procedures, where A is the procedure defined above:
;
;   (define (f n) (A 0 n))
;
;   (define (g n) (A 1 n))
;
;   (define (h n) (A 2 n))
;
;   (define (k n) (* 5 n n))
;
;Give concise mathematical definitions for the functions computed by the procedures f, g, and h for
; positive integer values of n.
;For example, (k n) computes 5n2.

;Translating to Clojure:
(defn A [x y]
 (cond (= y 0) 0
       (= x 0) (* 2 y)
       (= y 1) 2
       :else (A (- x 1)
                (A x (- y 1)))))

(A 1 10)
; => 1024
;substitution model
;(cond (= y 0) 0 (= x 0) (* 2 y) (= y 1) 2 :else (A (- x 1) (A x (- y 1))))
;(cond (= 10 0) 0 (= 1 0) (* 2 10) (= 10 1) 2 :else (A (- 1 1) (A 1 (- 10 1))))
;(A (- 1 1) (A 1 (- 10 1)))
;(A 0 (A 1 (- 10 1)))
;(A 0 (A 1 9))
;(A 0 (cond (= y 0) 0 (= x 0) (* 2 y) (= y 1) 2 :else (A (- x 1) (A x (- y 1)))))
;(A 0 (cond (= 9 0) 0 (= 1 0) (* 2 9) (= 9 1) 2 :else (A (- 1 1) (A 1 (- 9 1)))))
;(A 0 (A (- 1 1) (A 1 (- 9 1))))
;(A 0 (A 0 (A 1 (- 9 1))))
;(A 0 (A 0 (A 1 8)))
;...
;(A 0 (A 0 (A 0 (A 1 7))))
;...
;(A 0 (A 0 (A 0 (A 0 (A 1 6)))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 1 5))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 4)))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 3))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 2)))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 1))))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 2)))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 4))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 8)))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 16))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 32)))))
;(A 0 (A 0 (A 0 (A 0 64))))
;(A 0 (A 0 (A 0 128)))
;(A 0 (A 0 256))
;(A 0 512)
;1024

(A 2 4)
; => 65536
;substitution model
;(cond (= y 0) 0 (= x 0) (* 2 y) (= y 1) 2 :else (A (- x 1) (A x (- y 1))))
;(cond (= 4 0) 0 (= 2 0) (* 2 4) (= 4 1) 2 :else (A (- 2 1) (A 2 (- 4 1))))
;(A (- 2 1) (A 2 (- 4 1)))
;(A 1 (A 2 3))
;(A 1 (A 1 (A 2 2)))
;(A 1 (A 1 (A 1 (A 2 1))))
;(A 1 (A 1 (A 1 2)))
;(A 1 (A 1 (A 0 (A 1 1))))
;(A 1 (A 1 (A 0 2)))
;(A 1 (A 1 4))
;(A 1 (A 0 (A 1 3)))
;(A 1 (A 0 (A 0 (A 1 2))))
;(A 1 (A 0 (A 0 (A 0 (A 1 1)))))
;(A 1 (A 0 (A 0 (A 0 2))))
;(A 1 (A 0 (A 0 4)))
;(A 1 (A 0 8))
;(A 1 16)
;(A 0 (A 1 15))
;(A 0 (A 0 (A 1 14)))
;(A 0 (A 0 (A 0 (A 1 13))))
;(A 0 (A 0 (A 0 (A 0 (A 1 12)))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 1 11))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 10)))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 9))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 8)))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 7))))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 6)))))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 5))))))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 4)))))))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 3))))))))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 2)))))))))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 1))))))))))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 2)))))))))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 4))))))))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 8)))))))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 16))))))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 32)))))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 64))))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 128)))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 256))))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 512)))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 (A 0 1024))))))
;(A 0 (A 0 (A 0 (A 0 (A 0 2048)))))
;(A 0 (A 0 (A 0 (A 0 4096))))
;(A 0 (A 0 (A 0 8192)))
;(A 0 (A 0 16384))
;(A 0 32768)
;65536

(A 3 3)
; => 65536

;positive integer values of n

(defn k [n] (* 5 n n))
;concise: 5n^2

(defn f [n] (A 0 n))
;(cond (= y 0) 0 (= x 0) (* 2 y) (= y 1) 2 :else (A (- x 1) (A x (- y 1))))
;(cond (= n 0) 0 (= 0 0) (* 2 n) (= n 1) 2 :else (A (- 0 1) (A 0 (- n 1))))
;(* 2 n)
;concise: 2n

(f 1)
; => 2

(f 2)
; => 4

(f 3)
; => 6

(f 10)
; => 20

(defn g [n] (A 1 n))

;(cond (= y 0) 0 (= x 0) (* 2 y) (= y 1) 2 :else (A (- x 1) (A x (- y 1))))
;(cond (= n 0) 0 (= 1 0) (* 2 n) (= n 1) 2 :else (A (- 1 1) (A 1 (- n 1))))
;(A (- 1 1) (A 1 (- n 1)))
;(A     0   (A 1 (- n 1)))
;(*     2   (A 1 (- n 1)))
;...
;(*     2   (A 0 (A 1 (- (- n 1) 1))))
;(*     2   (* 2 (A 0 (A 1 (- (- (- n 1) 1) 1)))))
;(*     2   (* 2 (* 2 (A 1 (- (- (- n 1) 1) 1)))))
; So, it will go on until (= n 1). Then, we'll get 2.
;concise: 2^n

(g 1)
; => 2

(g 2)
; => 4

(g 3)
; => 8

(g 10)
; => 1024

(defn h [n] (A 2 n))
;(cond (= y 0) 0 (= x 0) (* 2 y) (= y 1) 2 :else (A (- x 1) (A x (- y 1))))
;(cond (= n 0) 0 (= 2 0) (* 2 n) (= n 1) 2 :else (A (- 2 1) (A 2 (- n 1))))
;(A (- 2 1) (A 2 (- n 1)))
;(A 1       (A 2 (- n 1)))
;(A (- 1 1) (A 1 (- (A 2 (- n 1)) 1)))
;(A    0    (A 1 (- (A 2 (- n 1)) 1)))
;(*    2    (A 1 (- (A 2 (- n 1)) 1)))
;(*    2    (A (- 1 1) (A 1 (- (- (A 2 (- n 1)) 1) 1)) ))
;(*    2    (A    0    (A 1 (- (- (A 2 (- n 1)) 1) 1)) ))
;(*    2    (*    2    (A 1 (- (- (A 2 (- n 1)) 1) 1)) ))
;(*    2    (*    2    (A (- 1 1) (- (- (- (A 2 (- n 1)) 1) 1) 1) ) ))
;(*    2    (*    2    (A    0    (- (- (- (A 2 (- n 1)) 1) 1) 1) ) ))
;(*    2    (*    2    (*    2    (- (- (- (A 2 (- n 1)) 1) 1) 1) ) ))
; concise: 2^2^2^(n-1)

(h 1)
; => 2
; 2

(h 2)
; => 4
; 2^2

(h 3)
; => 16
;2^2^2

(h 10)
;Execution error (StackOverflowError)
;null

;In computability theory, the Ackermann function, named after Wilhelm Ackermann, is one of the simplest
;   and earliest-discovered examples of a total computable function that is not primitive recursive.
;All primitive recursive functions are total and computable, but the Ackermann function illustrates that not all
;   total computable functions are primitive recursive.
;Its value grows rapidly, even for small inputs. For example, A(4, 2) is an integer of 19,729 decimal digits
;   (equivalent to 2^65536−3, or 2^2^2^2^2−3)
;
;Reference: https://en.wikipedia.org/wiki/Ackermann_function

;In computability theory, a primitive recursive function is roughly speaking a function that can be computed by
;   a computer program whose loops are all "for" loops (that is, an upper bound of the number of iterations of
;   every loop can be determined before entering the loop).
;Primitive recursive functions form a strict subset of those general recursive functions that are also total
;   functions.
;The importance of primitive recursive functions lies on the fact that most computable functions that are
;   studied in number theory (and more generally in mathematics) are primitive recursive.
;For example, addition and division, the factorial and exponential function, and the function which returns
;   the nth prime are all primitive recursive.
;In fact, for showing that a computable function is primitive recursive, it suffices to show that its
;   computational complexity is bounded above by a primitive recursive function of the input size.
;It follows that it is difficult to devise a computable function that is not primitive recursive,
;   although some are known (see the section on Limitations below).
;The set of primitive recursive functions is known as PR in computational complexity theory.
;
;[...] examples of total recursive but not primitive recursive functions are known:
;
;- The function that takes m to Ackermann(m,m) is a unary total recursive function that is not primitive recursive.
;- The Paris–Harrington theorem involves a total recursive function that is not primitive recursive. Because this function is motivated by Ramsey theory, it is sometimes considered more "natural" than the Ackermann function.
;- The Sudan function
;- The Goodstein function
;
;Reference: https://en.wikipedia.org/wiki/Primitive_recursive_function
