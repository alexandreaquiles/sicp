(ns exercises.chapter2.exercise2_15)

; Exercise 2.15:
; Eva Lu Ator, another user, has also noticed the different intervals
;   computed by different but algebraically equivalent expressions.
; She says that a formula to compute with intervals using Alyssa's
;   system will produce tighter error bounds if it can be written
;   in such a form that no variable that represents an uncertain
;   number is repeated.
; Thus, she says, par2 is a "better" program for parallel resistances
;   than par1. Is she right? Why?

; By treating each distinct use of r1 and r2 in the computation as distinct uncertain
;   values, par1 overcompensates.
; The two distinct occurrences of r1 in the calculation refer to one actual resistor,
;   not two resistors with the same uncertainty.
; Stated another way, the value that r1 may take is somewhere within its interval,
;   but whatever value it does take, it's the same value for both occurrences of r1
;   in the procedure.
; The interval arithmetic system we've devised doesn't have a way of communicating
;   that the uncertainty of any given value should only be accounted for once in the
;   computation.
;
; By using the values of r1 and r2 only once each in its computation, procedure par2
;   does not overcompensate for the range of uncertainty of these values.
; Each value's uncertainty is introduced into the calculation only once.
; par2 does use the interval one in several places, but this interval has zero
;   uncertainty, so repeated uses of one don't add any uncertainty to the computation.

; From: http://wiki.drewhess.com/wiki/SICP_exercise_2.15