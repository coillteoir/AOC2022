#!/bin/racket

#lang racket

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;PRIMING INPUTS FOR USE

(define (read-file filePath)
  (let ((line (read-line filePath 'any)))
	(if (eof-object? line)
	  '()
	  (cons line (read-file filePath)))))

(define x (call-with-input-file "input" read-file))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(define (get-total lst)
  (if (or (null? (cdr lst)) (equal? (car (cdr lst)) ""))
	(string->number (car lst))
	(+ (string->number (car lst)) (get-total (cdr lst)))))



(define (list-total lst newLst)
	(if (null? (cdr lst))
	  newLst
	  (if (equal? "" (car lst))
		(list-total (cdr lst) (cons (get-total (cdr lst)) newLst))
		(list-total (cdr lst) newLst))))

(define (findMax lst Max)
  (if (null? (cdr lst))
	Max
	(if (< Max (car lst))
	  (findMax (cdr lst) (car lst))
	  (findMax (cdr lst) Max))))

(define (sum-top-three lst)
  (define sLst (sort lst >))
  (+ (car sLst) (car (cdr sLst)) (car (cdr (cdr sLst)))))

(findMax (list-total x '()) 0)
(sum-top-three (list-total x '()))
