#include "Child.h"

void addChild(Child* child, FILE* f) {
	fscanf(f, "%d %d", &child->id, &child->age);
}

void printChild(Child* child) {
	printf("ID:%d   Age:%d\n", child->id, child->age);
}

void saveChild(Child* child, FILE* file) {
	fprintf(file, "%d %d\n", child->id, child->age);
}
Child* initChild() {
	Child* child;
	child = (Child*)malloc(sizeof(Child));
	printf("ID No.:\n");
	scanf("%d", &(child->id));
	printf("Age:\n");
	scanf("%d", &(child->age));
	return child;
}