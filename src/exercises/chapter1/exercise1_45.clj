(ns exercises.chapter1.exercise1_45)

; Exercise 1.45:
; We saw in Section 1.3.3 that attempting to compute square roots by naively finding a fixed point of y → x/y does not
;   converge, and that this can be fixed by average damping.
; The same method works for finding cube roots as fixed points of the average-damped y → x/y².
; Unfortunately, the process does not work for fourth roots — a single average damp is not enough to make a fixed-point
;   search for y → x/y³ converge.
; On the other hand, if we average damp twice (i.e., use the average damp of the average damp of y → x/y³)
;   the fixed-point search does converge.
; Do some experiments to determine how many average damps are required to compute nth roots as a fixed-point search
;   based upon repeated average damping of y → x/y^n−1.
; Use this to implement a simple procedure for computing nth roots using fixed-point, average-damp , and the repeated
;   procedure of Exercise 1.43.
; Assume that any arithmetic operations you need are available as primitives.

(defn abs [x]
  (if (< x 0)
    (- x)
    x))

(defn average [x y]
  (/ (+ x y) 2))

(defn square [x]
  (* x x))

(defn expt [b n]
  (loop [b b counter n product 1]
    (if (= counter 0)
      product
      (recur b (- counter 1) (* b product)))))

(defn compose [f g]
  (fn [x] (f (g x))))

(defn repeated [f n]
  (assert (>= n 1) "n should be >= 1")
  (println "repeated" n)
  (if (= n 1)
    (fn [x] (f x))
    (compose f (repeated f (dec n)))))

((repeated inc 5) 1)
; => 6

(def tolerance 0.00001)

(defn fixed-point [f first-guess]
  (defn close-enough? [v1 v2]
    (< (abs (- v1 v2)) tolerance))
  (defn try* [guess]
    (println guess)
    (let [next (f guess)]
      (if (close-enough? guess next)
        next
        (recur next))))
  (try* first-guess))

(defn sqrt-without-average-damp [x]
  (fixed-point #(/ x %) 1.0))

;(sqrt-without-average-damp 4)
; infinite loop (doesn't converge)
; loops among 1.0 and 4.0

(defn average-damp [f]
  (fn [x] (average x (f x))))

((average-damp square) 10)
; => 55

(defn sqrt [x]
  (fixed-point (average-damp (fn [y] (/ x y)))
               1.0))

(sqrt 4)
; => 2.000000000000002

(defn cube-root [x]
  (fixed-point (average-damp (fn [y] (/ x (square y))))
               1.0))

(cube-root 8)
; => 1.9999981824788517

(defn fourth-root [x]
  (fixed-point (average-damp (fn [y] (/ x (expt y 3))))
               1.0))

;(fourth-root 16)
; infinite loop (doesn't converge)
; loops among
;   2.0028086552649755
;   1.997203149914594
;   2.002808611045981
;   1.997203193762308
;   2.0028085668290734
;   1.9972032376079585

(defn fourth-root-with-two-average-damps [x]
  (fixed-point (average-damp (average-damp (fn [y] (/ x (expt y 3)))))
               1.0))

(fourth-root-with-two-average-damps 16)
; => 2.0000000000021965

(defn fifth-root-with-one-average-damp [x]
  (fixed-point (average-damp (fn [y] (/ x (expt y 4))))
               1.0))

;(fifth-root-with-one-average-damp 32)
; infinite loop (doesn't converge)
; loops among
;   2.880435013529815
;   1.672645084943273

(defn fifth-root-with-two-average-damps [x]
  (fixed-point (average-damp (average-damp (fn [y] (/ x (expt y 4)))))
               1.0))

(fifth-root-with-two-average-damps 32)
; => 2.0000015129957607

(defn tenth-root-with-one-average-damp [x]
  (fixed-point (average-damp (fn [y] (/ x (expt y 9))))
               1.0))

;(tenth-root-with-one-average-damp 1024)
; infinite loop (doesn't converge)
; loops among
;   2.416459403857477
;   1.390474201658885
;   27.046627862293004
;   13.52331393121261
;   6.76165699945343
;   3.3808458294541985
;   1.6992953258799115

(defn tenth-root-with-two-average-damps [x]
  (fixed-point (average-damp (average-damp (fn [y] (/ x (expt y 9)))))
               1.0))

(tenth-root-with-two-average-damps 1024)
; infinite loop (doesn't converge)
; loops among
;   1.8705424690870849
;   2.3160351646232864
;   1.8705424690870855
;   2.316035164623284
;   1.8705424690870849

(defn tenth-root-with-three-average-damps [x]
  (fixed-point (average-damp (average-damp (average-damp (fn [y] (/ x (expt y 9))))))
               1.0))

(tenth-root-with-three-average-damps 1024)
; => 2.000001183010332

(defn nth-root [n x]
  (assert (>= n 2))
  (fixed-point
    (repeated
      (average-damp
        (fn [y] (/ x (expt y (dec n)))))
      3)
    1.0))

(nth-root 2 4)
; => 2.0

(nth-root 3 8)
; => 1.9999995456190938

(nth-root 4 16)
; infinite loop (doesn't converge)
; loops among
;   2.001140372548683
;   1.998861579226812
;   2.001140363658383
;   1.9988615880866725
;   2.0011403547682907
;   1.998861596946325
;   2.001140345878406
;   1.9988616058057715

(nth-root 5 32)
; infinite loop (doesn't converge)
; loops among
;   1.672645084943273
;   2.880435013529815
; some problem with my recursive process implementation of repeated

(defn nth-root-without-repeated [n x]
  (assert (>= n 2))
  (fixed-point
    (average-damp
      (average-damp
        (average-damp
          (fn [y] (/ x (expt y (dec n)))))))
    1.0))

(map #(nth-root 2 %) '(1 4 9 16 25 36 49 64 81))
; => (1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0)

(map #(nth-root 3 %) '(1 8 27 64 125 216 343 512 729))
; =>
;(1.0
; 1.9999995456190938
; 2.9999998270063113
; 4.000000388661875
; 5.0000002827278
; 6.000000172281387
; 6.999999844121909
; 7.999999625995246
; 8.999999529937377)

;(map #(nth-root 4 %) '(1 16 81 256 625 1296 2401 4096 6561))
; infinite loop (doesn't converge)
; loops among

(map #(nth-root-without-repeated 4 %) '(1 16 81 256 625 1296 2401 4096 6561))
; =>
;(1.0
; 2.000005048018041
; 3.00000501889963
; 4.000006940393808
; 5.000008066382536
; 6.000005021306138
; 7.000007973224801
; 8.00000905550165
; 9.00000794532648)
