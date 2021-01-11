(ns exercises.chapter2.exercise2_42)

; Exercise 2.42:
; The “eight-queens puzzle” asks how to place eight queens on a chessboard so that no queen
;   is in check from any other (i.e., no two queens are in the same row, column, or diagonal).
; One possible solution is shown in Figure 2.8.
; One way to solve the puzzle is to work across the board, placing a queen in each column.
; Once we have placed k − 1 queens, we must place the k th queen in a position where it
;   does not check any of the queens already on the board.
; We can formulate this approach recursively: Assume that we have already generated the
;   sequence of all possible ways to place k − 1 queens in the first k − 1 columns of the
;   board.
; For each of these ways, generate an extended set of positions by placing a queen in each
;   row of the k th column.
; Now filter these, keeping only the positions for which the queen in the k th column is
;   safe with respect to the other queens.
; This produces the sequence of all ways to place k queens in the first k columns.
; By continuing this process, we will produce not only one solution, but all solutions to
;   the puzzle.
; We implement this solution as a procedure queens , which returns a sequence of all solutions
;   to the problem of placing n queens on an n × n chessboard.
; queens has an internal procedure queen-cols that returns the sequence of all ways to place
;   queens in the first k columns of the board.
;
; (define (queens board-size)
;   (define (queen-cols k)
;     (if (= k 0)
;       (list empty-board)
;       (filter
;         (lambda (positions) (safe? k positions))
;         (flatmap
;           (lambda (rest-of-queens)
;             (map (lambda (new-row)
;                  (adjoin-position
;                   new-row k rest-of-queens))
;             (enumerate-interval 1 board-size)))
;           (queen-cols (- k 1))))))
; (queen-cols board-size))
;
; In this procedure rest-of-queens is a way to place k − 1 queens in the first k −1 columns,
;   and new-row is a proposed row in which to place the queen for the k th column.
; Complete the program by implementing the representation for sets of board positions,
;   including the procedure adjoin-position , which adjoins a new row-column position to a
;   set of positions, and empty-board , which represents an empty set of positions.
; You must also write the procedure safe? , which determines for a set of positions, whether
;   the queen in the k th column is safe with respect to the others.
; (Note that we need only check whether the new queen is safe — the other queens are already
;   guaranteed safe with respect to each other.)

(def append concat)

(def accumulate reduce)

(def flatmap mapcat)

(defn enumerate-interval [low high] (range low (inc high)))

(def empty-board '())

(def row first)

(defn safe-horizontally? [positions]
  (loop [new-row (row (first positions)) other-positions (rest positions)]
    (cond (empty? other-positions) true
          (= new-row (row (first other-positions))) false
          :else (recur new-row (rest other-positions)))))

(assert (= (safe-horizontally? '((2 2) (1 1))) true))
(assert (= (safe-horizontally? '((3 2) (1 1))) true))
(assert (= (safe-horizontally? '((3 3) (3 2) (1 1))) false))
(assert (= (safe-horizontally? '((4 3) (3 2) (1 1))) true))

(defn safe-diagonally? [positions]
  (loop [new-row (row (first positions))
         number-of-rows 1
         other-positions (rest positions)]
    (cond (empty? other-positions) true
          (= (- new-row number-of-rows) (row (first other-positions))) false
          (= (+ new-row number-of-rows) (row (first other-positions))) false
          :else (recur new-row (inc number-of-rows) (rest other-positions)))))

(assert (= (safe-diagonally? '((1 2) (1 1))) true))
(assert (= (safe-diagonally? '((2 2) (1 1))) false))
(assert (= (safe-diagonally? '((1 2) (2 1))) false))
(assert (= (safe-diagonally? '((2 2) (2 1))) true))

(assert (= (safe-diagonally? '((3 3) (4 2) (1 1))) false))
(assert (= (safe-diagonally? '((2 3) (4 2) (1 1))) true))
(assert (= (safe-diagonally? '((2 3) (2 2) (4 1))) false))
(assert (= (safe-diagonally? '((3 3) (1 2) (4 1))) true))

(defn safe? [_ positions]
  (and (safe-horizontally? positions)
       (safe-diagonally? positions)))

(defn adjoin-position [new-row k rest-of-queens]
  (cons (list new-row k) rest-of-queens))

(defn queens [board-size]
  (defn queen-cols [k]
    (if (= k 0)
      (list empty-board)
      (filter
        (fn [positions] (safe? k positions))
        (flatmap
          (fn [rest-of-queens]
            (map (fn [new-row]
                   (adjoin-position
                     new-row k rest-of-queens))
                 (enumerate-interval 1 board-size)))
          (queen-cols (- k 1))))))
  (queen-cols board-size))

(queens 4)
; => (((3 4) (1 3) (4 2) (2 1))
;    ((2 4) (4 3) (1 2) (3 1)))

(count (queens 4))
; => 2

(assert (= (count (queens 4)) 2))

(count (queens 8))
; => 92

(assert (= (count (queens 8)) 92))
