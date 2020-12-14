(ns exercises.chapter2.exercise2_52
  (:use exercises.picture)
  (:use exercises.chapter2.exercise2_23)
  (:use exercises.chapter2.exercise2_46)
  (:use exercises.chapter2.exercise2_47)
  (:use exercises.chapter2.exercise2_48)
  (:use exercises.chapter2.exercise2_49)
  (:use exercises.chapter2.exercise2_50)
  (:use exercises.chapter2.exercise2_51))

; Exercise 2.52:
; Make changes to the square limit of wave shown in Figure 2.9
;   by working at each of the levels described above.
; In particular:
; a. Add some segments to the primitive wave painter of Exercise
;   2.49 (to add a smile, for example).
; b. Change the pattern constructed by corner-split (for example,
;   by using only one copy of the up-split and right-split
;   images instead of two).
; c. Modify the version of square-limit that uses square-of-four
;   so as to assemble the corners in a different pattern.
;   (For example, you might make the big Mr. Rogers look outward
;     from each corner of the square.)

;a
(def smile-segments
  (list (make-segment (make-vect 0.4 0.9) (make-vect 0.45 0.9))
        (make-segment (make-vect 0.6 0.9) (make-vect 0.55 0.9))
        (make-segment (make-vect 0.4 0.8) (make-vect 0.5  0.7))
        (make-segment (make-vect 0.6 0.8) (make-vect 0.5  0.7))))
; from https://wizardbook.wordpress.com/2010/12/03/exercise-2-52/

(def george-with-smile-segments
  (concat george-segments smile-segments))

(def george-with-smile
  (segments->painter george-with-smile-segments))

;(draw george-with-smile a-whole-window-frame)
;(draw george-with-smile a-small-frame)
;(draw george-with-smile a-twisted-frame)

;b

(defn right-split [p n]
  (if (= n 0)
    p
    (let [smaller (right-split p (dec n))]
      (besides p (belows smaller smaller)))))

;(draw (right-split george-with-smile 4) a-whole-window-frame)

(defn up-split [p n]
  (if (= n 0)
    p
    (let [smaller (up-split p (dec n))]
      (belows p (besides smaller smaller)))))

;(draw (up-split george-with-smile 4) a-whole-window-frame)

(defn corner-split [p n]
  (if (= n 0)
    p
    (let [up (up-split p (dec n))
          right (right-split p (dec n))
          top-left (besides up up)
          bottom-right (belows right right)
          top-right (corner-split p (dec n))]
      (besides (belows p top-left)
              (belows bottom-right top-right)))))

;(draw (corner-split george-with-smile 4) a-whole-window-frame)

(defn changed-corner-split [p n]
  (if (= n 0)
    p
    (let [up (up-split p (dec n))
          right (right-split p (dec n))
          top-left up
          bottom-right right
          top-right (corner-split p (dec n))]
      (besides (belows p top-left)
               (belows bottom-right top-right)))))

;(draw (changed-corner-split george-with-smile 4) a-whole-window-frame)

;c

(defn square-of-four [tl tr bl br]
  (fn [p]
    (let [top (besides (tl p) (tr p))
          bottom (besides (bl p) (br p))]
      (belows bottom top))))

(defn square-limit [painter n]
  (let [combine4 (square-of-four flip-horizontally identity
                                 rotates-180 flip-vertically)]
    (combine4 (corner-split painter n))))

;(draw (square-limit george-with-smile 4) a-whole-window-frame)

(defn modified-square-limit [painter n]
  (let [combine4 (square-of-four rotates-270 flip-vertically
                                 rotates-180 flip-horizontally)]
    (combine4 (corner-split painter n))))

;(draw (modified-square-limit george-with-smile 4) a-whole-window-frame)
