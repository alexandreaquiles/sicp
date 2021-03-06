(ns exercises.chapter1.exercise1_14)

;Exercise 1.14
; Draw the tree illustrating the process generated by the count-change procedure of Section 1.2.2 in making
;   change for 11 cents. What are the orders of growth of the space and number of steps used by this process
;   as the amount to be changed increases?

(defn count-change [amount]
  (defn first-denomination [kinds-of-coins]
    (cond (= kinds-of-coins 1) 1
          (= kinds-of-coins 2) 5
          (= kinds-of-coins 3) 10
          (= kinds-of-coins 4) 25
          (= kinds-of-coins 5) 50))
  (defn cc [amount kinds-of-coins]
    (println "(cc" amount kinds-of-coins ")")
    (cond (= amount 0) 1
          (or (< amount 0) (= kinds-of-coins 0)) 0
          :else (+ (cc amount (- kinds-of-coins 1))
                   (cc (- amount (first-denomination kinds-of-coins))
                       kinds-of-coins))))
  (cc amount))

(count-change 11)
; => 4

;(count-change 11)
;(cc 11 5)
;                    (+ (cc 11 4) (cc (- 11 50) 5))
;                       (cc 11 4)
;                                            (+ (cc 11 3) (cc (- 11 25) 4))
;                                            (cc 11 3)
;                                                                  (+ (cc 11 2) (cc (- 11 10) 3))
;                                                                  (cc 11 2)
;                                                                                        (+ (cc 11 1) (cc (- 11 5) 2))
;                                                                                            (cc 11 1)
;                                                                                                                  (+ (cc 11 0) (cc (- 11 1) 1))
;                                                                                                                     (cc 11 0)
;                                                                                                                       0
;                                                                                                                     (cc 10 1)
;                                                                                                                                       (+ (cc 10 0) (cc (- 10 1) 1))
;                                                                                                                                          (cc 10 0)
;                                                                                                                                              0
;                                                                                                                                          (cc 9 1)
;                                                                                                                                              ... => 1
;
;                                                                                             (cc 6 2)
;                                                                                                (+ (cc 6 1) (cc (- 6 5) 2))
;                                                                                                   (cc 6 1)
;                                                                                                       ... => 1
;                                                                                                   (cc 1 2)
;                                                                                                       ... => 1
;                                                                  (cc 1 3)
;                                                                      (+ (cc 1 2) (cc (- 1 10) 3))
;                                                                         (cc 1 2)
;                                                                             (+ (cc 1 1) (cc (- 1 5) 2))
;                                                                                (cc 1 1)
;                                                                                    (+ (cc 1 0) (cc (- 1 1) 1))
;                                                                                       (cc 1 0)
;                                                                                           0
;                                                                                       (cc 0 1)
;                                                                                           => 1
;                                                                                (cc -4 2)
;                                                                                    0
;                                                                         (cc -9 3)
;                                                                             0
;                                            (cc -14 4)
;                                              0
;                       (cc -39 5)
;                       0

;(count-change 11)
;(cc 11 5 )
;   (cc 11 4 )
;     (cc 11 3 )
;       (cc 11 2 )
;         (cc 11 1 )
;             (cc 11 0 )
;                 (cc 10 1 )
;                     (cc 10 0 )
;                 (cc 9 1 )
;                     (cc 9 0 )
;                     (cc 8 1 )
;                         (cc 8 0 )
;                         (cc 7 1 )
;                             (cc 7 0 )
;                             (cc 6 1 )
;                                 (cc 6 0 )
;                                 (cc 5 1 )
;                                    (cc 5 0 )
;                                    (cc 4 1 )
;                                       (cc 4 0 )
;                                       (cc 3 1 )
;                                           (cc 3 0 )
;                                           (cc 2 1 )
;                                               (cc 2 0 )
;                                               (cc 1 1 )
;                                                   (cc 1 0 )
;                                                   (cc 0 1 )
;         (cc 6 2 )
;             (cc 6 1 )
;                  (cc 6 0 )
;                  (cc 5 1 )
;                     (cc 5 0 )
;                     (cc 4 1 )
;                         (cc 4 0 )
;                         (cc 3 1 )
;                             (cc 3 0 )
;                             (cc 2 1 )
;                                 (cc 2 0 )
;                                 (cc 1 1 )
;                                     (cc 1 0 )
;                                     (cc 0 1 )
;                  (cc 1 2 )
;                     (cc 1 1 )
;                         (cc 1 0 )
;                         (cc 0 1 )
;                     (cc -4 2 )
;       (cc 1 3 )
;           (cc 1 2 )
;              (cc 1 1 )
;                 (cc 1 0 )
;                 (cc 0 1 )
;              (cc -4 2 )
;           (cc -9 3 )
;   (cc -14 4 )
;(cc -39 5 )

;SPACE: at any given point, must only keep track of nodes until the root of the tree.
; So, space complexity will be linearly proportional to the depth of the tree.
; O(depth of tree) = O(n), n being the amount to be changed
;
;STEPS:
; If there was only 1 kind of coin (1 cent), it would be somethign similar to:
;(cc 11 1 )
;   (cc 11 0 )
;   (cc 10 1 )
;     (cc 10 0 )
;     (cc 9 1 )
;       (cc 9 0 )
;       (cc 8 1 )
;         (cc 8 0 )
;         (cc 7 1 )
;           (cc 7 0 )
;           (cc 6 1 )
;             (cc 6 0 )
;             (cc 5 1 )
;               (cc 5 0 )
;               (cc 4 1 )
;                 (cc 4 0 )
;                 (cc 3 1 )
;                   (cc 3 0 )
;                   (cc 2 1 )
;                     (cc 2 0 )
;                     (cc 1 1 )
;                       (cc 1 0 )
;                       (cc 0 1 )
; So, for amount n, we'd have calls:
;   (cc n 1) ...to... (cc n-1 1) ...to... (cc 0 1)
;     and
;   (cc n 0) ...to... (cc n-1 0) ...to... (cc 1 0)
; Then, we have n+1 calls with kind-of-coins=1 and n calls with kind-of-coins=1.
; Total steps S for kind-of-coins=1 would be: S(n, k=1) = n+1 + n = 2n+1
;
; With another kind of coin (5 cents), we'd have:
;(cc 11 2 )
; (cc 11 1 )
;   (cc 11 0 )
;   (cc 10 1 )
;     (cc 10 0 )
;     (cc 9 1 )
;       (cc 9 0 )
;       (cc 8 1 )
;         (cc 8 0 )
;         (cc 7 1 )
;           (cc 7 0 )
;           (cc 6 1 )
;             (cc 6 0 )
;             (cc 5 1 )
;               (cc 5 0 )
;               (cc 4 1 )
;                 (cc 4 0 )
;                 (cc 3 1 )
;                   (cc 3 0 )
;                   (cc 2 1 )
;                     (cc 2 0 )
;                     (cc 1 1 )
;                       (cc 1 0 )
;                       (cc 0 1 )
; (cc 6 2 )
;   (cc 6 1 )
;     (cc 6 0 )
;     (cc 5 1 )
;       (cc 5 0 )
;       (cc 4 1 )
;          (cc 4 0 )
;          (cc 3 1 )
;             (cc 3 0 )
;             (cc 2 1 )
;               (cc 2 0 )
;               (cc 1 1 )
;                  (cc 1 0 )
;                  (cc 0 1 )
;   (cc 1 2 )
;     (cc 1 1 )
;       (cc 1 0 )
;       (cc 0 1 )
;     (cc -4 2 )
; So it's
;   (cc 11 2) -> 1 + (cc 11 1) + (cc 6 2) = (cc 11 2) -> 1 + (cc 11 1) + [1 + (cc 6 1) + (cc 1 2)] = 1 + (cc 11 1) + [1 + (cc 6 1) + { 1 + (cc 1 1) + (cc -4 2)}]
;   (cc 11 2) -> 1 + (cc 11 1) + [1 + (cc 6 1) + { 1 + (cc 1 1) + (cc -4 2)}]
; Total steps S for kind-of-coins=2, with denomination d=5, would be:
;   S = 1 + S(n,k=1) + [1 + S(n-5,k=1) + { 1 + S(n-2*5,k=1) + S(n-3*5,k=2) + ...}]
; For n < 15
;   S = 1 + (2n+1) + [1 + [2(n-5)+1] + {1 + [2(n-10)+1] + 1}
;   S = 1 + 2n + 1 + 1 + 2n -10 + 1 + 1 + 2n -20 + 1 + 1
;   S = 6n - 23

; With 3 kinds of coin (1 cent, 5 cents and 10 cents), we'd have:

; ????

;(defn count-change [amount kinds] ... )
;
;(count-change 11 1)                                         ; 23 Steps
;(count-change 11 2)                                         ; 43 Steps
;(count-change 11 3)                                         ; 51 Steps
;(count-change 22 3)                                         ; 191 Steps

; It seems to be a function of the number n, of kind-of-coins k and of denominatios d1, d2, d3, ..., dn, being proportional to (n-dn),...(n-d3),(n-d2),(n-d)

; Reference: https://www.ysagade.nl/2015/04/12/sicp-change-growth/
