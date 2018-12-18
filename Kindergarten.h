#ifndef KINDERGARTEN_H_
	#define KINDERGARTEN_H_
	#include "Child.h"
	//enum kinderGardenTypes { Hova, Trom, TromTrom };
	typedef struct {
		char* kinderName;
		int kinderType;
		int kinderNumOfChild;
		Child** kinderChild;
	}KinderGarten;

	void addKinderGarten(KinderGarten* kinderGarten, FILE* f);
	void printKinderGarten(KinderGarten* kinderGarten);
	void saveKindenGraden(KinderGarten* kidergarden, FILE* file);
	void initKinderGarten(KinderGarten* kinderGarten);
	Child* findChildByID(KinderGarten* kinderGarten, int id);
#endif