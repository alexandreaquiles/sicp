(ns exercises.chapter2.exercise2_43)

; Exercise 2.43:
; Louis Reasoner is having a terrible time doing Exercise 2.42.
; His queens procedure seems to work, but it runs extremely slowly.
; (Louis never does manage to wait long enough for it to solve even the 6 × 6 case.)
; When Louis asks Eva Lu Ator for help, she points out that he has interchanged the
;   order of the nested mappings in the flatmap, writing it as
;
;   (flatmap
;     (lambda (new-row)
;       (map (lambda (rest-of-queens)
;             (adjoin-position new-row k rest-of-queens))
;            (queen-cols (- k 1))))
;     (enumerate-interval 1 board-size))
;
; Explain why this interchange makes the program run slowly.
; Estimate how long it will take Louis’s program to solve the eight-queens puzzle,
; assuming that the program in Exercise 2.42 solves the puzzle in time T.

(def append concat)

(def accumulate reduce)

(def flatmap mapcat)

(defn enumerate-interval [low high] (range low (inc high)))

(def empty-board '())

(def row first)

(def horizontal-collision =)

(defn diagonal-collision [new-row rows-offset another-row]
  (or (= (- new-row rows-offset) another-row)
      (= (+ new-row rows-offset) another-row)))

(defn safe? [_ positions]
  (loop [new-row (row (first positions))
         rows-offset 1
         other-positions (rest positions)]
    (cond (empty? other-positions) true
          (let [another-row (row (first other-positions))
                horizontal-nok (horizontal-collision new-row another-row)
                diagonal-nok (diagonal-collision new-row rows-offset another-row)]
            (or horizontal-nok
                diagonal-nok)) false
          :else (recur new-row (inc rows-offset) (rest other-positions)))))

(defn adjoin-position [new-row k rest-of-queens]
  (cons (list new-row k) rest-of-queens))

(def x 0)

(defn queens [board-size]
  (defn queen-cols [k]
    (def x (inc x))
    (if (= k 0)
      (list empty-board)
      (filter
        (fn [positions] (safe? k positions))
        (flatmap
          (fn [new-row]
            (map (fn [rest-of-queens]
                   (adjoin-position
                     new-row k rest-of-queens))
                 (queen-cols (- k 1))))
          (enumerate-interval 1 board-size)))))
  (queen-cols board-size))

(println x)

(count (queens 4))
(count (queens 8))


; In the solution from Exercise 2.42, we call 'queen-cols' roughly N times.

; In Louis solution, we enumerate from 1 to board-size N, calling 'queen-cols' N times for N-1.
; For N-1 we call 'queen-cols' N times again. passing N-2. For N-2, N times again, now with N-3.
; Until N=0.
; So, we end up calling 'queen-cols' roughly N^N times.

