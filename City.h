#ifndef CITY_H_
	#define CITY_H_

	#include "Kindergarten.h"
	#define FILE_NAME "DataFile.txt"

	typedef struct{
		KinderGarten** kinderGarten;
		int gartenCounter;
	}City;
	
	void readCity(City* city);
	void showCityGardens(City* city);
	void saveCity(City* city);
	void showSpecificGardenInCity(City* city);
	void birthdayToChild(City* city);
	void cityAddGarden(City* city);
	void addChildToSpecificGardenInCity(City* city);
	void ReleaseCity(City* city);
	int getKinderGarten(City* city);
	int countChova(City* city);
#endif