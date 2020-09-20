(ns exercises.chapter2.exercise2_14
  (:use exercises.chapter2.exercise2_7
        exercises.chapter2.exercise2_8
        exercises.chapter2.exercise2_10
        exercises.chapter2.exercise2_11
        exercises.chapter2.exercise2_12))

; Exercise 2.14:
; Demonstrate that Lem is right.
; Investigate the behavior of the system on a variety of arithmetic expressions.
; Make some intervals A and B, and use them in computing the expressions A/A and A/B.
; You will get the most insight by using intervals whose width is a small percentage of the center value.
; Examine the results of the computation in center-percent form (see Exercise 2.12).

(defn par1 [r1 r2]
  (div-interval (mul-interval r1 r2)
                (add-interval r1 r2)))

(defn par2 [r1 r2]
  (let [one (make-interval 1 1)]
    (div-interval
      one (add-interval (div-interval one r1)
                        (div-interval one r2)))))

(def resistor-a (make-center-percent 3.5 0.01))
(def resistor-b (make-center-percent 5.0 0.01))

(def resistor-a-par1-b (par1 resistor-a resistor-b))

(center resistor-a-par1-b)
;=> 2.0588236117647067

(percent resistor-a-par1-b)
;=> 0.029999999199993577

(def resistor-a-par2-b (par2 resistor-a resistor-b))

(center resistor-a-par2-b)
;=> 2.0588235294117645

(percent resistor-a-par2-b)
;=> 0.009999999999999534

(def one (make-interval 1 1))

(center one)
;=> 1

(percent one)
;=> 0

(width one)
;=> 0

(def should-be-one (div-interval resistor-a resistor-a))

(center should-be-one)
; => 1.0000000200000003

(width should-be-one)
; => 2.0000000200004475E-4

(percent should-be-one)
; => 0.019999999800004473

; there is a problem with identity

(def resistor-a-div-b (div-interval resistor-a resistor-b))

(center resistor-a-div-b)
; => 0.7000000140000002

(width resistor-a-div-b)
; => 1.400000013999647E-4

(percent resistor-a-div-b)
; => 0.019999999799994957
