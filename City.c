#include "City.h"

void readCity(City* city) {
	int i;
	FILE* f = fopen(FILE_NAME, "r+");
	if (f == NULL) {
		printf("Cannot Open File '%s'", FILE_NAME);
		return;
	}
	fscanf(f, "%d", &city->gartenCounter);
	city->kinderGarten = (KinderGarten**)malloc(sizeof(KinderGarten*)*city->gartenCounter);
	for (i = 0;i < city->gartenCounter;i++) {
		city->kinderGarten[i] = (KinderGarten*)malloc(sizeof(KinderGarten));
		addKinderGarten(city->kinderGarten[i], f);
	}
	fclose(f);
}

void showCityGardens(City* city) {
	int i;
	for (i = 0;i < city->gartenCounter;i++) {
		printf("\nKindergarten %d:\n", i+1);
		printKinderGarten(city->kinderGarten[i]);
	}
}

void saveCity(City* city) {
	int i;
	FILE* file = fopen(FILE_NAME, "w+");
	if (file == NULL) {
		printf("Saving Failed!");
		return;
	}
	fprintf(file, "%d\n", city->gartenCounter);
	for (i = 0; i < city->gartenCounter; i++)
		saveKindenGraden(city->kinderGarten[i], file);
	fclose(file);
}

void showSpecificGardenInCity(City* city) {
	int index;
	index = getKinderGarten(city);
	if (index == -1) {
		printf("No such Kindergarten\n");
		return;
	}
	printKinderGarten(city->kinderGarten[index]);
}

void birthdayToChild(City* city) {
	int index,id;
	Child* child;
	index = getKinderGarten(city);
	if (index == -1) {
		printf("No such Kindergarten\n");
		return;
	}
	printf("Enter child id:\n");
	scanf("%d", &id);
	child = findChildByID(city->kinderGarten[index], id);
	if (child == NULL) {
		printf("No such child");
		return;
	}
	child->age++;
}

void ReleaseCity(City* city) {
	int i, j;
	KinderGarten* kinderGarten;
	for (i = 0;i < city->gartenCounter;i++) {
		kinderGarten = city->kinderGarten[i];
		for (j = 0;j < kinderGarten->kinderNumOfChild;j++)
			free(kinderGarten->kinderChild[j]);
		free(kinderGarten);
	}
}

void addChildToSpecificGardenInCity(City* city) {
	int index;
	KinderGarten* kinderGarten;
	index = getKinderGarten(city);
	if (index == -1) {
		printf("No such Kindergarten\n");
		return;
	}
	kinderGarten = city->kinderGarten[index];
	kinderGarten->kinderNumOfChild++;
	kinderGarten->kinderChild = (Child**)realloc(kinderGarten->kinderChild, sizeof(Child*)*(kinderGarten->kinderNumOfChild));
	kinderGarten->kinderChild[kinderGarten->kinderNumOfChild-1] = initChild();
}

void cityAddGarden(City* city) {
	KinderGarten* kinderGarten = (KinderGarten*)malloc(sizeof(KinderGarten));
	city->gartenCounter++;
	city->kinderGarten = (KinderGarten**)realloc(city->kinderGarten, sizeof(KinderGarten*)*(city->gartenCounter));
	initKinderGarten(kinderGarten);
	city->kinderGarten[city->gartenCounter - 1] = kinderGarten;
}

int getKinderGarten(City* city) {
	int i;
	char name[20];
	printf("Give me the Kindergarten Name:\n");
	scanf("%s", name);
	for (i = 0; i < city->gartenCounter; i++)
		if (strcmp(name, city->kinderGarten[i]->kinderName) == 0)
			return i;
	return -1;
}

int countChova(City* city) {
	int i, counter = 0;
	KinderGarten* kinderGarten;
	for (i = 0;i < city->gartenCounter;i++) {
		kinderGarten = city->kinderGarten[i];
		if (kinderGarten->kinderType == 0)
			counter += kinderGarten->kinderNumOfChild;
	}
	return counter;
}
