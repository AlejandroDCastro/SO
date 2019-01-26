
// Primero se imprime la familia, cuando llega a Z, envía señales SIGUSR1 a todos los procesos para ejecutar el 'ls' o 'pstree', y
// después envía señales SIGUSR2 a 'X' e 'Y' para que se impriman en el orden correcto los últimos 'printf'.


#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>

void despierta() {
	// no hagas nada
}

void ejecutals() {
	if(fork() == 0)
		execlp("ls","ls",NULL); // Ejecuta exec
	else
		wait(NULL);
}

void ejecutapstree() {
	if(fork() == 0)
		execlp("pstree","pstree",NULL); // Ejecuta exec
	else
		wait(NULL);
}

int main(int argc, char *argv[]) {
	// PID de los procesos ejec A B
	int pidejec, pidA, pidB;
	int tiempo;
	char proceso;
	pid_t pid;

	pidejec = getpid(); // PID del padre
	tiempo = atoi(argv[2]); // Segundos a esperar por el proceso Z
	proceso = argv[1][0]; // Proceso que recibe la señal de Z tiene que realizar el exec

	pid = fork(); // Creamos hijo

	if(pid != 0) { // EJEC
		printf("Soy el proceso ejec: mi pid es %d\n", pidejec);
		wait(NULL); // Esperamos a que A muera
		printf("Soy ejec (%d) y muero\n",pidejec);
	}
	else { // A
		pidA = getpid();
		printf("Soy el proceso A: mi pid es %d. Mi padre es %d\n", pidA, pidejec);
		// Creamos B
		pid = fork();
		if(pid != 0) {
			signal(SIGUSR1,despierta); // Preparo el proceso A por si le llega una señal SIGUSR1
			pause();

			if(proceso == 65) {
				printf("Soy el proceso A con pid %d, he recibido la señal.\n",pidA);
				ejecutapstree();
			}

			wait(NULL); // Esperamos a que B muera
			printf("Soy A (%d) y muero\n",pidA);
			exit(0);
		}
		else { // B
			pidB = getpid();
			printf ("Soy el proceso B: mi pid es %d. Mi padre es %d. Mi abuelo es %d\n", pidB, pidA, pidejec);
			// Creamos los hijos X Y Z
			for(int i=0; i<3; i++) {
				pid = fork();
				if(pid == 0) {
					switch (i) {
						case 0: // X
						printf ("Soy el proceso X: mi pid es %d. Mi padre es %d. Mi abuelo es %d. Mi bisabuelo es %d\n", getpid(),  pidB, pidA, pidejec);
						signal(SIGUSR1,despierta); // Preparo el proceso X por si le llega una señal SIGUSR1
						pause(); // Hasta entonces, lo duermo.
						if(proceso == 88) {
							printf("Soy el proceso X con pid %d, he recibido la señal.\n",getpid());
							ejecutals();
						}

						signal(SIGUSR2,despierta); // Preparo el proceso X para que le llege una señal SIGUSR2
						pause();

						printf("Soy X (%d) y muero\n",getpid());
						exit(0);
						break;

						case 1: // Y
						printf ("Soy el proceso Y: mi pid es %d. Mi padre es %d. Mi abuelo es %d. Mi bisabuelo es %d\n", getpid(),  pidB, pidA, pidejec);
						signal(SIGUSR1,despierta); // Preparo el proceso X por si le llega una señal SIGUSR1
						pause(); // Hasta entonces duerme
						if(proceso == 89) {
							printf("Soy el proceso Y con pid %d, he recibido la señal.\n",getpid());
							ejecutals();
						}

						signal(SIGUSR2,despierta); // Preparo el proceso Y para que le llege una señal SIGUSR2
						pause();

						printf("Soy Y (%d) y muero\n",getpid());
						exit(0);
						break;

						case 2:
						printf ("Soy el proceso Z: mi pid es %d. Mi padre es %d. Mi abuelo es %d. Mi bisabuelo es %d\n", getpid(),  pidB, pidA, pidejec);
						signal(SIGALRM,despierta);    // Especificamos un manejador de señales para
						alarm(tiempo);                // que capte una señal de tipo alarma con el tiempo integrado en argv[1]
						pause();                      // Hasta entonces se pausa el programa
						// Despierta
						kill(pidA,SIGUSR1); // Señal SIGUSR1 enviada a A
						kill(pidB,SIGUSR1); // Señal SIGUSR1 enviada a B           // Estas señales son para ver cual debe ejecutar un exec
						kill(getpid()-2,SIGUSR1); // Señal SIGUSR1 enviada a X
						kill(getpid()-1,SIGUSR1); // Señal SIGUSR1 enviada a Y

						printf("Soy Z (%d) y muero\n",getpid());

						kill((getpid()-1),SIGUSR2); // Señal SIGUSR2 enviada a Y
						kill((getpid()-2),SIGUSR2); // Señal SIGUSR2 enviada a X     // Estas señales son para matar a los procesos en orden descendente

						exit(0);
						break;
					}
				}
			}
			signal(SIGUSR1,despierta); // Preparo el proceso B por si le llega una señal SIGUSR1
			pause();
			if(proceso == 66) {
				printf("Soy el proceso B con pid %d, he recibido la señal.\n",pidB);
				ejecutapstree();
			}

			wait(NULL); // Espero a que mueran los hijos X Y Z

			printf("Soy B (%d) y muero\n",pidB);
			exit(0);
		}
	}
	return 0;
}