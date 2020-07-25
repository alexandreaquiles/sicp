(ns exercises.chapter1.exercise1_37)

; Exercise 1.37:
;   a. An infinite continued fraction is an expression of the form
;
; f = N1 / ( D1 + N2 / ( D2 + N3 / ( D3 + ...) ) )
;
; As an example, one can show that the infinite continued fraction expansion with
;   the Ni and the Di all equal to 1 produces 1/φ, where φ is the golden ratio (described in Section 1.2.2).
; One way to approximate an infinite continued fraction is to truncate the expansion after a given number of terms.
; Such a truncation— a so-called k-term finite continued fraction — has the form
;
; ; f = N1 / ( D1 + N2 / ( ... + Nk / Dk ) )
;
; Suppose that n and d are procedures of one argument (the term index i) that return the Ni and Di of the
;   terms of the continued fraction.
; Define a procedure cont-frac such that evaluating (cont-frac n d k) computes the value of the k-term finite
;   continued fraction.
; Check your procedure by approximating 1/φ using
;
;   (cont-frac (lambda (i) 1.0)
;              (lambda (i) 1.0)
;              k)
;
;   for successive values of k .
; How large must you make k in order to get an approximation that is accurate to 4 decimal places?
;
; b. If your cont-frac procedure generates a recursive process, write one that generates an iterative process.
; If it generates an iterative process, write one that generates a recursive process.

(defn cont-frac [n d k]
  (defn cont-frac-i [i]
    (if (= i k)
      (/ (n k) (d k))
      (/ (n i) (+ (d i) (cont-frac-i (inc i))))))
  (cont-frac-i 1))

; 1/φ ~= 0.6180

(cont-frac (fn [i] 1.0)
           (fn [i] 1.0)
           4)
; => 0.6000000000000001

(cont-frac (fn [i] 1.0)
           (fn [i] 1.0)
           10)
; => 0.6179775280898876

(cont-frac (fn [i] 1.0)
           (fn [i] 1.0)
           11)
; => 0.6180555555555556

(defn cont-frac-iter [n d k]
  (loop [i 1 result 0]
    ;(println result)
    (if (> i k)
      result
      (recur (inc i) (/ (n i) (+ (d i ) result))))))

(cont-frac-iter (fn [i] 1.0)
                (fn [i] 1.0)
                11)
;0
;1.0
;0.5
;0.6666666666666666
;0.6000000000000001
;0.625
;0.6153846153846154
;0.6190476190476191
;0.6176470588235294
;0.6181818181818182
;0.6179775280898876
;0.6180555555555556
; => 0.6180555555555556
