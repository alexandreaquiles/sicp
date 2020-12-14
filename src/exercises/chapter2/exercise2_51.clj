(ns exercises.chapter2.exercise2_51
  (:use exercises.picture)
  (:use exercises.chapter2.exercise2_23)
  (:use exercises.chapter2.exercise2_46)
  (:use exercises.chapter2.exercise2_47)
  (:use exercises.chapter2.exercise2_48)
  (:use exercises.chapter2.exercise2_49)
  (:use exercises.chapter2.exercise2_50))

; Exercise 2.51:
; Define the 'below' operation for painters.
; 'below' takes two painters as arguments.
; The resulting painter, given a frame, draws with the first painter in the
;   bottom of the frame and with the second painter in the top.
; Define 'below' in two different ways:
;   first by writing a procedure that is analogous to the 'beside' procedure given above,
;   and again in terms of beside and suitable rotation operations (from Exercise 2.50).

(defn belows [painter1 painter2]
  (let [split-point (make-vect 0.0 0.5)
        paint-bottom (transform-painter painter1
                                        (make-vect 0.0 0.0)
                                        (make-vect 1.0 0.0)
                                        split-point)
        paint-top (transform-painter painter2
                                     split-point
                                     (make-vect 1.0 0.5)
                                     (make-vect 0.0 1.0))]
    (fn [frame]
      (paint-bottom frame)
      (paint-top frame))))

;(draw (belows opposite-corners george) a-whole-window-frame)

(defn belows [painter1 painter2]
  (rotates-90 (besides (rotates-270 painter1)
                       (rotates-270 painter2))))

;(draw (belows opposite-corners george) a-whole-window-frame)
