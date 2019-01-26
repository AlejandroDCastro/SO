#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
	// PID de los procesos ejec A B
	int pidarb, pidA, pidB;
	int estado, i;
	pid_t pid;

	pidarb = getpid(); // PID del padre

	pid = fork(); // Creamos hijo

	if(pid != 0) { // EJEC
		printf ("Soy el proceso arb: mi pid es %d\n", pidarb);
		wait(&estado); // Esperamos a que A muera
		printf ("Soy arb (%d) y muero\n", pidarb);
	}
	else { // A
		pidA = getpid();
		printf ("Soy el proceso A: mi pid es %d. Mi padre es %d\n", pidA, pidarb);
		// Creamos B
		pid = fork();
		if(pid != 0) {
			wait(&estado); // Esperamos a que B muera para que muera A
			printf ("Soy A (%d) y muero\n", pidA);
			exit(0);
		}
		else { // B
			pidB = getpid();
			printf ("Soy el proceso B: mi pid es %d. Mi padre es %d. Mi abuelo es %d\n", pidB, pidA, pidarb);
			// Creamos X Y Z
			for(i=0; i<3; i++) {
				pid = fork();
				if(pid == 0) {
					switch (i) {
						case 0: // X
						printf ("Soy el proceso X: mi pid es %d. Mi padre es %d. Mi abuelo es %d. Mi bisabuelo es %d\n", getpid(),  pidB, pidA, pidarb);
						sleep(15);
						printf("Soy X (%d) y muero\n", getpid());
						exit(0);
						break;

						case 1: // Y
						printf ("Soy el proceso Y: mi pid es %d. Mi padre es %d. Mi abuelo es %d. Mi bisabuelo es %d\n", getpid(),  pidB, pidA, pidarb);
						sleep(15);
						printf("Soy Y (%d) y muero\n", getpid());
						exit(0);
						break;

						case 2: // Z
						printf ("Soy el proceso Z: mi pid es %d. Mi padre es %d. Mi abuelo es %d. Mi bisabuelo es %d\n", getpid(),  pidB, pidA, pidarb);
						sleep(15);
						printf("Soy Z (%d) y muero\n", getpid());
						exit(0);
						break;
					}
				}
			}
			// Espera a que mueran los hijos y luego muere B
			for(i = 1; i <= 3; i++)
				wait(&estado); 
			printf("Soy B (%d) y muero\n", getpid());
			exit(0);
		}
	}
	return 0;
}