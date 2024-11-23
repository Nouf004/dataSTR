#include<stdio.h>
#include<string.h>
#include<stdlib.h>

struct Applicant{
int id;
char applicant[20];
char education[20];
char major[5];
int Experience;
float GPA;
float points;
char state[10]; 
};
void addApplicant();
void calculatePoints();
void setApplicantState(char *MajorList);
void writeAssignedApplicants();
void printList();

struct Applicant *list;
int line ;




int main(){
    addApplicant();
    calculatePoints();
    setApplicantState ("CSC,InS,MSc SWE,CEN" ) ;
    writeAssignedApplicants();
    printList();
  

}

void addApplicant() {
    FILE *fp = fopen("Applicants.txt", "r");

    if (fp == NULL)
        return;

    line = 0;

    int ch;
    while ((ch = getc(fp)) != EOF) {
        if (ch == '\n')
            line++;
    }

    fclose(fp);
    line = line - 2; 

    list = (struct Applicant *)calloc(line, sizeof(struct Applicant));
    if (list == NULL)
        return; 

    FILE *ff = fopen("Applicants.txt", "r");
    if (ff == NULL)
        return;

    
    while ((ch = getc(ff)) != '\n');
    while ((ch = getc(ff)) != '\n');

    for (int i = 0; i < line; i++) {
       fscanf(ff, "%d %s %s %d %s %f %s ", &list[i].id , list[i].applicant,list[i].education , &list[i].Experience , list[i].major ,&list[i].GPA ,list[i].state);
    }

    fclose(ff);
} // end method 

void calculatePoints(){

    for (int i = 0; i < line; i++) {
        if (strcmp(list[i].education, "MSc") == 0) {
            list[i].points = list[i].GPA + (list[i].Experience + 10);
        } else {
            list[i].points = list[i].GPA + list[i].Experience;
        }
    }
}

void printList(){

    printf("The Applicants List is:\n");
    printf("id\tName\tEducation\tExperience\tMajor\tGPA\tState\n");
    for (int i = 0; i < line; i++) {
        printf("%d\t%s\t%s\t\t%d\t\t%s\t%.2f\t%s\n", list[i].id, list[i].applicant, list[i].education, list[i].Experience, list[i].major, list[i].GPA, list[i].state);
    }

    printf("\nThe Candidates List is:\n");
    printf("id\tName\tEducation\tExperience\tMajor\tGPA\tState\n");
    for (int i = 0; i < line; i++) {
        if (strcmp(list[i].state, "candidate") == 0) 
            printf("%d\t%s\t%s\t\t%d\t\t%s\t%.2f\t%s\n",  list[i].id, list[i].applicant, list[i].education, list[i].Experience, list[i].major, list[i].GPA, list[i].state);
    }

    printf("\nThe Assigned Applicants List is:\n");
    printf("id\tName\tEducation\tExperience\tMajor\tGPA\tState\n");
    for (int i = 0; i < line; i++) {
        if (strcmp(list[i].state, "Assigned") == 0) 
            printf("%d\t%s\t%s\t\t%d\t\t%s\t%.2f\t%s\n", list[i].id, list[i].applicant, list[i].education, list[i].Experience, list[i].major, list[i].GPA, list[i].state);
    }

}

void setApplicantState (char* MajorList ){
float maxpoint = 0 ;
int i=0 ;
int j=0;

while( i < line ){
if( strstr( MajorList , list[i].major ) != NULL ){
strcpy( list[i].state , "candidate");
if( list[i].points > maxpoint )
maxpoint = list[i].points ;
i++;
}
}// end while1

while(j < line) {
if( list[i].points == maxpoint)
   if(strcmp( list[i].state,"candidate" ) == 0 )
    {strcpy(list[i].state , "Assigned") ;
          break;
                }//end inner if
 j++; }// end while2
}

void writeAssignedApplicants(){
   FILE *outFile;
   outFile=fopen("Applicants.txt","w");

   fprintf(outFile,"The applicants are:\n");
   fprintf(outFile,"%0s %20s %15s %20s %20s %20s %20s %20s %5s \n","id","Name","Education","Experience","Major","GPA","State");
   for(int i=0;i<line;i++){
      if( strcmp( list[i].state , "Assigned") == 0 )  
         fprintf(outFile,"%0d %20s %15s %20s %20d %20f %20s %20f %5s \n", &list[i].id , list[i].applicant,list[i].education , &list[i].Experience , list[i].major ,&list[i].GPA ,list[i].state);
   fprintf(outFile,"%0d %20s %15s %20s %20d %20f %20s %20f %5s \n", &list[i].id , list[i].applicant,list[i].education , &list[i].Experience , list[i].major ,&list[i].GPA ,"OWL");
   }
   fprintf(outFile,"----------\n");

   fprintf(outFile,"\n The Candicate list is: \n %0s %5s %3s %5s %5s %4s","id","Applicant","Education","Major","Points","State");
   for(int i=0;i<line;i++){
      if (strcmp(list[i].state, "Assigned") || strcmp(list[i].state, "candidate")) {  
         fprintf(outFile," %0d %5s %3s %5s %5s %4s \n ",&list[i].id,list[i].applicant,list[i].education ,list[i].major,list[i].points,list[i].state );
      }
   }
      fprintf(outFile,"----------\n");
   
   
      fprintf(outFile,"\n Assigned Application is: \n");
      for(int i=0;i<line;i++){
         if (strcmp(list[i].state, "Assigned"))
            fprintf(outFile," %0d %5s %3s %5s %5s %4s \n", &list[i].id,list[i].applicant,list[i].education ,list[i].major,list[i].points,list[i].state );
      }
   
      fprintf(outFile,"----------\n");
   fclose(outFile);
   
   }//end write method

