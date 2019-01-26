#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>

void despiertaNormal(){}

void ejecutals(){
    if(fork() == 0)
       execlp("ls", "ls", NULL);
    else
    	wait(NULL);
}

void ejecutapstree(){
    if(fork() == 0)
        execlp("pstree", "pstree", "-p", NULL);
    else
        wait(NULL);
}

int main(int argc, char * argv[]){
	int pid, pidarb, pida, pidb, pidx, pidy, pidz, i;
	int tiempo, pidSeleccionado;
    char procesoSeleccionado;

	if(argc == 3) {
		tiempo = atoi(argv[2]);
		procesoSeleccionado = argv[1][0];
		pidarb = getpid();

		if(procesoSeleccionado == 65)
			pidSeleccionado = pidarb+1;
		if(procesoSeleccionado == 66)
			pidSeleccionado = pidarb+2;
		if(procesoSeleccionado == 88)
			pidSeleccionado = pidarb+3;
		if(procesoSeleccionado == 89)
			pidSeleccionado = pidarb+4;

		printf("Soy el proceso ejec: mi pid es %d\n", pidarb);

		for(i = 1; i <= 2; i++){
			pid = fork();
			if(pid != 0){
				break;
    		}
        	else{
        	     if(i == 1){
        	        pida = getpid();
        	        printf("Soy el proceso A: mi pid es %d. Mi padre es %d\n", pida, pidarb);   
				}
     			if(i == 2){
     			    pidb = getpid();
     			    printf("Soy el proceso B: mi pid es %d. Mi padre es %d. Mi abuelo es %d\n", pidb, pida, pidarb);   
				}
    		}
    	}
		if(i == 1){
		    wait(NULL);
		    printf("Soy ejec (%d) y muero\n", pidarb);
		}
		if(i == 2){
			signal(SIGUSR1, ejecutapstree);
			signal(SIGUSR2, despiertaNormal);
			wait(NULL);
			printf("Soy A (%d) y muero\n", pida);
		} 
		if(i == 3){
			for(i = 1; i <= 3; i++){
			    pid = fork();
			    if(pid != 0){
			    	switch(i){
			    	    case 1:
			    	    pidx = pid;
			    	    break;

			    	    case 2:
			    	    pidy = pid;
			    	    break;    

			    	    case 3:
			    	    pidz = pid;
			    	    break;
			    	}        
			    }     
			    if(pid == 0){
			    	switch(i){
			    	    case 1:
			    	    pidx = getpid();
			    	    printf("Soy el proceso X: mi pid es %d. Mi padre es %d. Mi abuelo es %d. Mi bisabuelo es %d\n", getpid(), pidb, pida, pidarb);
			    	    signal(SIGUSR1, ejecutals);
			    	    signal(SIGUSR2, despiertaNormal);
			    	    pause();
			    	    break;

						case 2:
						pidy = getpid();
						printf("Soy el proceso Y: mi pid es %d. Mi padre es %d. Mi abuelo es %d. Mi bisabuelo es %d\n", getpid(), pidb, pida, pidarb);
						signal(SIGUSR1, ejecutals);
						signal(SIGUSR2, despiertaNormal);
						pause();
						break;

						case 3:
						printf("Soy el proceso Z: mi pid es %d. Mi padre es %d. Mi abuelo es %d. Mi bisabuelo es %d\n", getpid(), pidb, pida, pidarb);
						signal(SIGALRM, despiertaNormal);
						alarm(tiempo);
						pause();
						printf("Soy el proceso %c con pid %d, he recibido la señal.\n", procesoSeleccionado, pidSeleccionado);
						switch(procesoSeleccionado){
						    case 'X': kill(pidx, SIGUSR1);
						              signal(SIGALRM, despiertaNormal);
						              alarm(tiempo);
						              pause();
						              kill(pidy, SIGUSR2);
						              break;

						    case 'Y': kill(pidy, SIGUSR1);
						              signal(SIGALRM, despiertaNormal);
						              alarm(tiempo);
						              pause();
						              kill(pidx, SIGUSR2);
						              break;

						    case 'A': kill(pida, SIGUSR1);
						              signal(SIGALRM, despiertaNormal);
						              alarm(tiempo);
						              pause();
						              kill(pidy, SIGUSR2);
						              kill(pidx, SIGUSR2);
						              break;

						    case 'B': kill(pidb, SIGUSR1);
						              signal(SIGALRM, despiertaNormal);
						              alarm(tiempo);
						              pause();
						              kill(pidx, SIGUSR2);
						              kill(pidy, SIGUSR2);
						              break;
						}
						break;
				    }

				    switch(i){
				    	case 1: printf("Soy X (%d) y muero\n", pidx);
				    	break;

				    	case 2: printf("Soy Y (%d) y muero\n", pidy);
				    	break;

				    	case 3: printf("Soy Z (%d) y muero\n", (pidy+1));
				    	break;
				    }
				    break;
				}
			}
			if(i == 4){
				signal(SIGUSR1, ejecutapstree);
				signal(SIGUSR2, despiertaNormal);
				for(i = 1; i <= 3; i++){
					wait(NULL);
				}
				printf("Soy B (%d) y muero\n", pidb);
			}
		}
	}
	return 0;   
}