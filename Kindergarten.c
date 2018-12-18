#include "Kindergarten.h"
const char *KINDER_TYPES[] = { "Chova","Trom","TromTrom" };
void addKinderGarten(KinderGarten* kinderGarten, FILE* f) {
	int i;
	char temp[100];
	fscanf(f, "%s %d %d", temp, &kinderGarten->kinderType, &kinderGarten->kinderNumOfChild);
	kinderGarten->kinderName = strdup(temp);
	kinderGarten->kinderChild = (Child**)malloc(sizeof(Child*)*kinderGarten->kinderNumOfChild);
	for (i = 0;i < kinderGarten->kinderNumOfChild;i++) {
		kinderGarten->kinderChild[i] = (Child*)malloc(sizeof(Child*));
		addChild(kinderGarten->kinderChild[i], f);
	}
}

void printKinderGarten(KinderGarten* kinderGarten) {
	int i, numOfChilds = kinderGarten->kinderNumOfChild;
	printf("Name:%-11sType:%-11s%d Children:\n", kinderGarten->kinderName, KINDER_TYPES[kinderGarten->kinderType], numOfChilds);
	for (i = 0;i < numOfChilds;i++)
		printChild(kinderGarten->kinderChild[i]);
}

void saveKindenGraden(KinderGarten* kidergarden, FILE* file) {
	int i;
	fprintf(file, "%s %d %d\n", kidergarden->kinderName, kidergarden->kinderType, kidergarden->kinderNumOfChild);
	for (i = 0; i < kidergarden->kinderNumOfChild; i++)
		saveChild(kidergarden->kinderChild[i], file);
}

void initKinderGarten(KinderGarten* kinderGarten) {
	int i;
	char temp[100];
	printf("Name:\n");
	scanf("%s",temp);
	kinderGarten->kinderName = strdup(temp);
	printf("Graden type:\nEnter 0 for Chova\nEnter 1 for Trom Chova\nEnter 2 for Trom Trom Chova\n");
	scanf("%d", &(kinderGarten->kinderType));
	printf("Enter children Details:\n\nChildren count:\n");
	scanf("%d", &(kinderGarten->kinderNumOfChild));
	kinderGarten->kinderChild = (Child**)malloc(sizeof(Child*)*(kinderGarten->kinderNumOfChild));

	for (i = 0; i < kinderGarten->kinderNumOfChild; i++)
		kinderGarten->kinderChild[i] = initChild();
}

Child* findChildByID(KinderGarten* kinderGarten, int id) {
	int i;
	Child* child;
	for (i = 0;i < kinderGarten->kinderNumOfChild;i++) {
		child = kinderGarten->kinderChild[i];
		if (id == child->id)
			return child;
	}
	return NULL;
}