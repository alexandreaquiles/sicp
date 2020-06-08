(ns exercises.chapter1.exercise1_8)

; Exercise 1.8.
;   Newton's method for cube roots is based on the fact that if y is an approximation to the cube root of x,
;     then a better approximation is given by the value
;
;     (/ (+ (/ x (* y y)) (* 2 y)) 3)
;
;   Use this formula to implement a cube-root procedure analogous to the square-root procedure.
;   (In section 1.3.4 we will see how to implement Newton's method in general as an abstraction of these
;     square-root and cube-root procedures.)

; ANSWER: it's very similar to the sqrt, but good-enough? should use cube and improve should use the formula in the question.


(defn cube [x]
  (* x x x))

(defn square [x]
  (* x x))

(defn abs [x]
  (cond (< x 0) (- x)
        :else x))

(defn good-enough? [guess x]
  (< (abs (- (cube guess) x)) 0.001))

(defn improve [guess x]
  (/ (+ (/ x (square guess)) (* 2 guess)) 3))

(defn cbrt [x]
  (loop [guess 1.0]
    (println "cbrt-iter -> guess:" guess ", x:" x )
    (if (good-enough? guess x)
            guess
            (recur (improve guess x)))))

(println (cbrt 27))
; => 3.0000005410641766

(println (cbrt 9))
; => 2.0801035255095734
