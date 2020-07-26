(ns exercises.chapter1.exercise1_38)

; Exercise 1.38:
; In 1737, the Swiss mathematician Leonhard Euler published a memoir De Fractionibus Continuis, which
;   included a continued fraction expansion for e−2, where e is the base of the natural logarithms.
; In this fraction, the Ni are all 1, and the Di are successively 1, 2, 1, 1, 4, 1, 1, 6, 1, 1, 8, ....
; Write a program that uses your cont-frac procedure from Exercise 1.37 to approximate e, based on Euler’s expansion.

(defn cont-frac-iter [n d k]
  (loop [i k result 0]
    (if (= i 0)
      result
      (recur (dec i) (/ (n i) (+ (d i ) result))))))

(Math/exp 1)
; => 2.718281828459045
; ~= e
; e - 2 ~= 0.718281828459045

(defn d [i]
  (if (= (rem i 3) 2)
    (/ (+ i 1) 1.5)
    1))

(assert (= '(1 2.0 1 1 4.0 1 1 6.0 1 1 8.0 1 1 10.0 1 1 12.0 1 1 14.0) (map d (range 1 21))))

(cont-frac-iter (fn [i] 1.0)
                d
                10)
; => 0.7182817182817183
