#ifndef CHILD_H_
	#define CHILD_H_
	#include <stdio.h>
	#include <stdlib.h>
	#include <string.h>
	#pragma warning(disable:4996)

	typedef struct {
		int id;
		int age;
		}Child;

	void addChild(Child* child, FILE* f);
	void printChild(Child* child);
	void saveChild(Child* child, FILE* file);
	Child* initChild();
#endif