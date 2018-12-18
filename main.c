#include "City.h"
#define READ_CITY 1
#define SHOW_CITY 2
#define SHOW_GARDEN 3
#define WRITE_CITY 4
#define ADD_GARDEN 5
#define ADD_CHILD 6
#define CHILD_BIRTHDAY 7
#define COUNT_GRADUATE 8
#define EXIT 0

int menu() {
	int choosenInt;
	printf("\n==========================\n");
	printf("Select:\n\n");
	printf("\t1. Read City information from file.\n");
	printf("\t2. Show all Kindergartens.\n");
	printf("\t3. Show a specific Kindergarten.\n");
	printf("\t4. Save City information to file.\n");
	printf("\t5. Add a Kindergarten.\n");
	printf("\t6. Add a Child.\n");
	printf("\t7. Birthday to a Child.\n");
	printf("\t8. Count Hova childres.\n");
	printf("\t0. Exit.\n");
	scanf("%d", &choosenInt);
	return choosenInt;
}

int main()
{
	City utz = { NULL,0 };
	int uReq;

	//first time read
	readCity(&utz);
	do
	{
		uReq = menu();
		switch (uReq)
		{
		case  READ_CITY:
			readCity(&utz);
			break;

		case  SHOW_CITY:
			showCityGardens(&utz);
			break;

		case  SHOW_GARDEN:
			showSpecificGardenInCity(&utz);
			break;

		case  WRITE_CITY:
			saveCity(&utz);
			break;

		case  ADD_GARDEN:
			cityAddGarden(&utz);
			break;

		case  ADD_CHILD:
			addChildToSpecificGardenInCity(&utz);
			break;

		case  CHILD_BIRTHDAY:
			birthdayToChild(&utz);
			break;

		case COUNT_GRADUATE:
			printf("There are %d children going to school next year\n", countChova(&utz));
			break;

		}
	} while (uReq != EXIT);

	ReleaseCity(&utz);//free all allocations

	return 1;
}