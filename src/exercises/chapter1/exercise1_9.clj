(ns exercises.chapter1.exercise1_9)

; Exercise 1.9.
;   Each of the following two procedures defines a method for adding two positive integers in terms of the procedures inc, which increments its argument by 1, and dec, which decrements its argument by 1.
;
;   (define (+ a b)
;     (if (= a 0)
;          b
;         (inc (+ (dec a) b))))
;
;   (define (+ a b)
;     (if (= a 0)
;          b
;         (+ (dec a) (inc b))))
;
;Using the substitution model, illustrate the process generated by each procedure in evaluating (+ 4 5). Are these processes iterative or recursive?

;Translating to Clojure
(defn sum [a b]
  (if (= a 0)
    b
    (inc (sum (dec a) b))))

; substitution model (applicative-order evaluation)
(sum 4 5)
;(if (= a 0) b (inc (sum (dec a) b)))
;(if (= 4 0) 5 (inc (sum (dec 4) 5)))
;(if  false  5 (inc (sum (dec 4) 5)))
;              (inc (sum (dec 4) 5))
;              (inc (sum    3    5))
;              (inc (if (= a 0) b (inc (sum (dec a) b))))
;              (inc (if (= 3 0) 5 (inc (sum (dec 3) 5))))
;              (inc (if  false  5 (inc (sum (dec 3) 5))))
;              (inc               (inc (sum (dec 3) 5)))
;              (inc               (inc (sum   2     5)))
;              (inc               (inc (if (= a 0) b (inc (sum (dec a) b)))))
;              (inc               (inc (if (= 2 0) 5 (inc (sum (dec 2) 5)))))
;              (inc               (inc (if  false  5 (inc (sum (dec 2) 5)))))
;              (inc               (inc               (inc (sum (dec 2) 5))))
;              (inc               (inc               (inc (sum    1    5))))
;              (inc               (inc               (inc (if (= a 0) b (inc (sum (dec a) b))))))
;              (inc               (inc               (inc (if (= 1 0) 5 (inc (sum (dec 1) 5))))))
;              (inc               (inc               (inc (if  false  5 (inc (sum (dec 1) 5))))))
;              (inc               (inc               (inc               (inc (sum (dec 1) 5)))))
;              (inc               (inc               (inc               (inc (sum    0    5)))))
;              (inc               (inc               (inc               (inc (if (= a 0) b (inc (sum (dec a) b)))))))
;              (inc               (inc               (inc               (inc (if (= 0 0) 5 (inc (sum (dec 0) 5)))))))
;              (inc               (inc               (inc               (inc (if  true   5 (inc (sum (dec 0) 5)))))))
;              (inc               (inc               (inc               (inc             5))))
;              (inc               (inc               (inc                  6)))
;              (inc               (inc                  7))
;              (inc                  8)
;              9

;This is a RECURSIVE PROCESS


(defn sum [a b]
  (if (= a 0)
    b
    (sum (dec a) (inc b))))

; substitution model (applicative-order evaluation)
(sum 4 5)
;(if (= a 0) b (sum (dec a) (inc b)))
;(if (= 4 0) 5 (sum (dec 4) (inc 5)))
;(if  false  5 (sum (dec 4) (inc 5)))
;              (sum (dec 4) (inc 5))
;              (sum    3    (inc 5))
;              (sum    3       6   )
;              (if (= a 0) b (sum (dec a) (inc b)))
;              (if (= 3 0) 6 (sum (dec 3) (inc 6)))
;              (if  false  6 (sum (dec 3) (inc 6)))
;                            (sum (dec 3) (inc 6))
;                            (sum    2    (inc 6))
;                            (sum    2       7   )
;                            (if (= a 0) b (sum (dec a) (inc b)))
;                            (if (= 2 0) 7 (sum (dec 2) (inc 7)))
;                            (if  false  7 (sum (dec 2) (inc 7)))
;                                          (sum (dec 2) (inc 7))
;                                          (sum    1    (inc 7))
;                                          (sum    1       8   )
;                                          (if (= a 0) b (sum (dec a) (inc b)))
;                                          (if (= 1 0) 8 (sum (dec 1) (inc 8)))
;                                          (if  false  8 (sum (dec 1) (inc 8)))
;                                                        (sum (dec 1) (inc 8))
;                                                        (sum     0   (inc 8))
;                                                        (sum     0      9   )
;                                                        (if (= a 0) b (sum (dec a) (inc b)))
;                                                        (if (= 0 0) 9 (sum (dec 0) (inc 9)))
;                                                        (if   true  9 (sum (dec 0) (inc 9)))
;                                                                    9
;This is an ITERATIVE PROCESS
