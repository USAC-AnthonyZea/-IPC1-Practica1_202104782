package practica1_pacman;

import java.util.Scanner;
import java.util.Random;

public class Practica1_PacMan {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Random random = new Random();

        int opcion;

        //Variables para almacenar punteo
        int punteo = 0;
        String nombre = "";

        //Arreglos para almacenar nombre y puntaje
        int[] punteoSave = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        String nombreSave[] = new String[10];

        //Contadores para tabla de posiciones
        int contadorArray = 0;

        do {

            //Creacion Menu//
            System.out.println(" ");
            System.out.println("PACMAN - IPC 1 - 2022");
            System.out.println("---------------------");
            System.out.println("1.      INICIAR JUEGO");
            System.out.println("2.   TABLA POSICIONES");
            System.out.println("3.              SALIR");
            System.out.println("---------------------");
            System.out.print("INGRESE UNA OPCION:  ");
            opcion = entrada.nextInt();

            switch (opcion) {

                //Opcion 1 - Iniciar juego
                case 1:

                    //Variables para Menu de pausa
                    int opcionMenu;

                    //Variables para ordenamiento
                    int pos,
                     auxOrdenamiento1;

                    String auxOrdenamiento2;

                    //Variables para inicio de juego
                    nombre = "";
                    int filas,
                     columnas,
                     comida,
                     paredes,
                     trampas,
                     icono;

                    int cantComida,
                     cantParedes,
                     cantTrampas;

                    //Variables para posicion de objetos
                    int contadorComida1 = 0,
                     contadorComida2 = 0,
                     contadorParedes = 0,
                     contadorTrampas = 0;

                    int comida1_x,
                     comida1_y,
                     comida2_x,
                     comida2_y,
                     comidaT1,
                     comidaT2;

                    int pared_x,
                     pared_y;

                    int trampa_x,
                     trampa_y;

                    //Variables al momento de estar jugando
                    punteo = 0;
                    int vidas = 3;

                    //Variables para el punteo dentro del juego
                    int puntosTotales = 0;

                    //Variable para salir del juego
                    int salirJuego = 0;

                    //Variable auxiliar
                    boolean aux = true;

                    System.out.print("\nIngrese su nombre: ");
                    entrada.nextLine();
                    nombre = entrada.nextLine();

                    //Tamaño del Tablero
                    do {
                        System.out.print("Ingrese el tamaño de su tablero (filas,columnas): ");
                        String coordenadas = entrada.next();

                        String[] tamaño = coordenadas.split(",");
                        filas = Integer.parseInt(tamaño[0]);
                        columnas = Integer.parseInt(tamaño[1]);

                        if (filas <= 0 || columnas <= 0) {
                            System.out.println("\nIngreso un valor incorrecto para filas o columnas");
                        } else {
                            aux = false;
                        }
                    } while (aux);

                    //Cantidad de Comida
                    comida = (filas * columnas) * 40 / 100;
                    System.out.print("Ingrese cantidad de comida [1-" + comida + "]: ");
                    cantComida = entrada.nextInt();

                    while ((cantComida < 1) || (cantComida > comida)) {
                        System.out.println("Debe ingresar la cantidad de comida, en el rango especificado");
                        System.out.print("Ingrese cantidad de comida [1-" + comida + "]: ");
                        cantComida = entrada.nextInt();
                    }

                    //Cantidad de Paredes
                    paredes = (filas * columnas) * 20 / 100;
                    System.out.print("Ingrese cantidad de paredes [1-" + paredes + "]: ");
                    cantParedes = entrada.nextInt();

                    while ((cantParedes < 1) || (cantParedes > paredes)) {
                        System.out.println("Debe ingresar la cantidad de paredes, en el rango especificado");
                        System.out.print("Ingrese cantidad de paredes [1-" + paredes + "]: ");
                        cantParedes = entrada.nextInt();
                    }

                    //Cantidad de Trampas
                    trampas = (filas * columnas) * 20 / 100;
                    System.out.print("Ingrese cantidad de trampas [1-" + trampas + "]: ");
                    cantTrampas = entrada.nextInt();

                    while ((cantTrampas < 1) || (cantTrampas > trampas)) {
                        System.out.println("Debe ingresar la cantidad de trampas, en el rango especificado");
                        System.out.print("Ingrese cantidad de trampas [1-" + trampas + "]: ");
                        cantTrampas = entrada.nextInt();
                    }

                    //Seleccion de Iconos
                    System.out.println(" 1.©,  2.H,  3.♥,  4.♦,  5.O,  6.æ,  7.♠,  8.╩,  9.¢,  10.¥");
                    System.out.print("Seleccione el numero del icono que desea utilizar [1-10]: ");
                    icono = entrada.nextInt();

                    while ((icono < 1) || (icono > 10)) {
                        System.out.println("\nDebe seleccionar un numero entre el rango de iconos");
                        System.out.println(" 1.©,  2.H,  3.♥,  4.♦,  5.O,  6.æ,  7.♠,  8.╩,  9.¢,  10.¥");
                        System.out.print("Seleccione el numero del icono que desea utilizar [1-10]: ");
                        icono = entrada.nextInt();
                    }

                    System.out.println("-----------------");
                    System.out.println("Jugador: " + nombre);

                    //Imprimir tablero en pantalla
                    //Calculos comida
                    comidaT1 = random.nextInt(cantComida) + 1;
                    comidaT2 = cantComida - comidaT1;

                    int marcoF = filas + 3,
                     marcoC = columnas + 2; //Variables para bordes

                    String tablero[][] = new String[marcoF][marcoC];

                    for (int i = 0; i < marcoF; i++) {
                        for (int j = 0; j < marcoC; j++) {
                            if ((i == 0) || (i == filas + 1)) {
                                tablero[i][j] = "-";
                            } else if ((j == 0) || (j == columnas + 1)) {
                                tablero[i][j] = "|";
                            } else {
                                tablero[i][j] = " ";
                            }
                        }
                    }

                    //Generando los objetos aleatorios dentro del tablero
                    for (int i = 0; i < marcoF; i++) {
                        for (int j = 0; j < marcoC; j++) {
                            if ((i == filas - 2) && (j == columnas - 2)) {

                                //Comida de tipo 1
                                do {
                                    comida1_x = random.nextInt(filas + 1);
                                    comida1_y = random.nextInt(columnas + 1);
                                    if (tablero[comida1_x][comida1_y] != " ") {
                                        //Significa que el espacio esta ocupado
                                    } else {
                                        tablero[comida1_x][comida1_y] = "@";
                                        contadorComida1++;
                                    }

                                } while (contadorComida1 < comidaT1);

                                //Comida de tipo 2
                                do {
                                    comida2_x = random.nextInt(filas + 1);
                                    comida2_y = random.nextInt(columnas + 1);
                                    if (tablero[comida2_x][comida2_y] != " ") {
                                        //Significa que el espacio esta ocupado
                                    } else {
                                        tablero[comida2_x][comida2_y] = "?";
                                        contadorComida2++;
                                    }
                                } while (contadorComida2 < comidaT2);
                            }

                            //Paredes aleatorias
                            if ((i == filas - 2) && (j == columnas - 2)) {
                                do {
                                    pared_x = random.nextInt(filas + 1);
                                    pared_y = random.nextInt(columnas + 1);
                                    if (tablero[pared_x][pared_y] != " ") {
                                        //Significa que el espacio esta ocupado
                                    } else {
                                        tablero[pared_x][pared_y] = "#";
                                        contadorParedes++;
                                    }
                                } while (contadorParedes < cantParedes);
                            }

                            //Trampas aleatorias
                            if ((i == filas - 2) && (j == columnas - 2)) {
                                do {
                                    trampa_x = random.nextInt(filas + 1);
                                    trampa_y = random.nextInt(columnas + 1);
                                    if (tablero[trampa_x][trampa_y] != " ") {
                                        //Significa que el espacio esta ocupado
                                    } else {
                                        tablero[trampa_x][trampa_y] = "X";
                                        contadorTrampas++;
                                    }
                                } while (contadorTrampas < cantTrampas);
                            }
                        }
                    }

                    //Asignando el icono al jugador
                    String iconoAsignado = "";
                    if (icono == 1) {
                        iconoAsignado = "©";
                    } else if (icono == 2) {
                        iconoAsignado = "H";
                    } else if (icono == 3) {
                        iconoAsignado = "♥";
                    } else if (icono == 4) {
                        iconoAsignado = "♦";
                    } else if (icono == 5) {
                        iconoAsignado = "O";
                    } else if (icono == 6) {
                        iconoAsignado = "æ";
                    } else if (icono == 7) {
                        iconoAsignado = "♠";
                    } else if (icono == 8) {
                        iconoAsignado = "╩";
                    } else if (icono == 9) {
                        iconoAsignado = "¢";
                    } else if (icono == 10) {
                        iconoAsignado = "¥";
                    }

                    //Imprimir el tablero en consola para elegir coordenada
                    for (int i = 0; i < marcoF; i++) {
                        for (int j = 0; j < marcoC; j++) {
                            if (i == marcoF - 1) {
                                System.out.print(String.valueOf(j) + " ");
                            } else {
                                System.out.print(tablero[i][j] + " ");
                            }
                        }

                        System.out.println("[" + String.valueOf(i) + "]" + " ");
                    }

                    //Coordenadas iniciales del jugador y mostrarlo
                    boolean condicion = true; //Variable auxiliar

                    int x = 0;
                    int y = 0;

                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            if ((i == filas - 2) && (j == columnas - 2)) {
                                do {
                                    System.out.print("\nIngrese la posicion donde desea iniciar (x,y): ");
                                    String xy = entrada.next();
                                    String[] posicion = xy.split(",");
                                    x = Integer.parseInt(posicion[0]);
                                    y = Integer.parseInt(posicion[1]);

                                    if (x <= 0 || y <= 0 || x > filas || y > columnas) {
                                        System.out.println("Ingreso una coordenada incorrecta"); //La variable auxiliar nunca se hace falsa
                                    } else {
                                        if (tablero[x][y] != " ") {
                                            System.out.println("La posicion esta ocupada"); //La variable auxiliar nunca se hace falsa
                                        } else {
                                            tablero[x][y] = iconoAsignado;
                                            condicion = false;
                                        }
                                    }
                                } while (condicion);

                                //Colocando el icono dentro del tablero e iniciar el juego
                                System.out.println("\n----------BIENVENIDO----------");
                                System.out.println("------------------------------");
                            }
                        }
                    }

                    //Imprimir el tablero con icono en tablero
                    for (int i = 0; i < marcoF; i++) {
                        for (int j = 0; j < marcoC; j++) {
                            if (i == marcoF - 1) {
                                System.out.print(String.valueOf(j) + " ");
                            } else {
                                System.out.print(tablero[i][j] + " ");
                            }
                        }

                        System.out.println("[" + String.valueOf(i) + "]" + " ");
                    }

                    //Movimiento del Pac-Man
                    String mov;
                    System.out.println("\nReglas basicas: ");
                    System.out.println("Movimiento izquierda: '4' o 'a'");
                    System.out.println("Movimiento derecha:   'd' o '6'");
                    System.out.println("Movimiento arriba:    'w' o '8'");
                    System.out.println("Movimiento abajo:     's' o '5'");

                    do {
                        System.out.println("");
                        System.out.print("Presione la tecla hacia donde se quiere mover: ");
                        mov = entrada.next();

                        switch (mov) {

                            //Movimiento a la izquierda
                            case "4":
                            case "a":
                            case "A":

                                for (int i = 0; i < filas; i++) {
                                    for (int j = 0; j < columnas; j++) {

                                        if (i == filas - 2 && j == columnas - 2) {

                                            //Validar que la casilla tenga una comida de tipo 1
                                            if (tablero[x][y - 1] == "@") {

                                                if (tablero[x][y] == "X") {
                                                    tablero[x][y - 1] = iconoAsignado;
                                                    tablero[x][y] = "X";
                                                    y--;
                                                    punteo += 5;

                                                } else {
                                                    tablero[x][y - 1] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    y--;
                                                    punteo += 5;
                                                }

                                                //Validar que la casilla tenga una comida de tipo 2
                                            } else if (tablero[x][y - 1] == "?") {

                                                if (tablero[x][y] == "X") {
                                                    tablero[x][y - 1] = iconoAsignado;
                                                    tablero[x][y] = "X";
                                                    y--;
                                                    punteo += 10;

                                                } else {
                                                    tablero[x][y - 1] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    y--;
                                                    punteo += 10;
                                                }

                                                //Validar que la casilla tenga una pared
                                            } else if (tablero[x][y - 1] == "#") {

                                                if (tablero[x][y] == "X") {
                                                    tablero[x][y] = "X";
                                                } else {
                                                    tablero[x][y] = iconoAsignado;
                                                }

                                                //Validar que la casilla tenga una trampa
                                            } else if (tablero[x][y - 1] == "X") {

                                                //Si la casilla actual es igual al icono entonces se asigna X a la izquierda
                                                if (tablero[x][y] == iconoAsignado) {
                                                    tablero[x][y - 1] = "X";
                                                    tablero[x][y] = " ";
                                                    vidas -= 1;
                                                    y--;

                                                    //Si la casilla actual es una X quiere decir que alapar hay otra X. por lo tanto no se borra
                                                } else if (tablero[x][y] == "X") {
                                                    tablero[x][y - 1] = "X";
                                                    tablero[x][y] = "X";
                                                    vidas -= 1;
                                                    y--;
                                                }

                                                //Si la casilla actual es una X, y a la izquierda hay una casilla vacia, se asigna el icono
                                            } else if (tablero[x][y] == "X") {
                                                tablero[x][y - 1] = iconoAsignado;
                                                tablero[x][y] = "X";
                                                y--;

                                                //Validaciones cuando la casilla a la izquierda sea un borde
                                            } else if (tablero[x][y - 1] == "|") {

                                                //Si al pasar el borde, hay una casilla vacia
                                                if (tablero[x][columnas] == " ") {
                                                    tablero[x][y] = " ";
                                                    tablero[x][columnas] = iconoAsignado;
                                                    y = columnas;

                                                    //Si al pasar el borde, hay una comida de tipo1
                                                } else if (tablero[x][columnas] == "@") {
                                                    tablero[x][columnas] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    y = columnas - 1;
                                                    punteo += 5;

                                                    //Si al pasar el borde, hay una comida de tipo2
                                                } else if (tablero[x][columnas] == "?") {
                                                    tablero[x][columnas] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    y = columnas;
                                                    punteo += 10;

                                                    //Si al pasar el borde, hay un muro
                                                } else if (tablero[x][columnas] == "#") {
                                                    tablero[x][y] = iconoAsignado;

                                                    //Si al pasar el borde hay una trampa
                                                } else if (tablero[x][columnas] == "X") {

                                                    //Si la casilla actual es igual al icono, entonces al pasar sera X
                                                    if (tablero[x][y] == iconoAsignado) {
                                                        tablero[x][columnas] = "X";
                                                        tablero[x][y] = " ";
                                                        vidas -= 1;
                                                        y = columnas;

                                                        //Si la casilla actual es igual a X, entonces al pasar tambien sera X
                                                    } else if (tablero[x][y] == "X") {
                                                        tablero[x][columnas] = "X";
                                                        tablero[x][y] = "X";
                                                        vidas -= 1;
                                                        y = columnas;
                                                    }

                                                } else if (tablero[x][columnas] == " ") {
                                                    tablero[x][y] = "X";
                                                    tablero[x][columnas] = iconoAsignado;
                                                    y = columnas;
                                                }

                                            } //Si no se cumple ninguno de los casos, solo se mueve a la izquierda 
                                            else {
                                                tablero[x][y - 1] = iconoAsignado;
                                                tablero[x][y] = " ";
                                                y--;
                                            }
                                        }

                                    }
                                }
                                break;

                            //Movimiento a la derecha
                            case "d":
                            case "6":
                            case "D":
                                for (int i = 0; i < filas; i++) {
                                    for (int j = 0; j < columnas; j++) {
                                        if (i == filas - 2 && j == columnas - 2) {

                                            //Validar que la casilla tenga una comida de tipo 1
                                            if (tablero[x][y + 1] == "@") {

                                                if (tablero[x][y] == "X") {
                                                    tablero[x][y + 1] = iconoAsignado;
                                                    tablero[x][y] = "X";
                                                    y++;
                                                    punteo += 5;

                                                } else {
                                                    tablero[x][y + 1] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    y++;
                                                    punteo += 5;
                                                }

                                                //Validar que la casilla tenga una comida de tipo 2
                                            } else if (tablero[x][y + 1] == "?") {

                                                if (tablero[x][y] == "X") {
                                                    tablero[x][y + 1] = iconoAsignado;
                                                    tablero[x][y] = "X";
                                                    y++;
                                                    punteo += 10;

                                                } else {
                                                    tablero[x][y + 1] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    y++;
                                                    punteo += 10;
                                                }

                                                //Validar que la casilla tenga una pared
                                            } else if (tablero[x][y + 1] == "#") {

                                                if (tablero[x][y] == "X") {
                                                    tablero[x][y] = "X";
                                                } else {
                                                    tablero[x][y] = iconoAsignado;
                                                }

                                                //Validar que la casilla tenga una trampa
                                            } else if (tablero[x][y + 1] == "X") {

                                                //Si la casilla actual es igual al icono entonces se asigna X a la derecha
                                                if (tablero[x][y] == iconoAsignado) {
                                                    tablero[x][y + 1] = "X";
                                                    tablero[x][y] = " ";
                                                    vidas -= 1;
                                                    y++;

                                                    //Si la casilla actual es una X quiere decir que alapar hay otra X. por lo tanto no se borra
                                                } else if (tablero[x][y] == "X") {
                                                    tablero[x][y + 1] = "X";
                                                    tablero[x][y] = "X";
                                                    vidas -= 1;
                                                    y++;
                                                }

                                                //Si la casilla actual es una X, y a la derecha hay una casilla vacia, se asigna el icono
                                            } else if (tablero[x][y] == "X") {
                                                tablero[x][y + 1] = iconoAsignado;
                                                tablero[x][y] = "X";
                                                y++;

                                            } else if (tablero[x][y + 1] == "|") {

                                                //Si al pasar el borde, hay una casilla vacia
                                                if (tablero[x][columnas - (columnas - 1)] == " ") {
                                                    tablero[x][y] = " ";
                                                    tablero[x][columnas - (columnas - 1)] = iconoAsignado;
                                                    y = columnas - (columnas - 1);

                                                    //Si al pasar el borde, hay una comida de tipo1
                                                } else if (tablero[x][columnas - (columnas - 1)] == "@") {
                                                    tablero[x][columnas - (columnas - 1)] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    y = columnas - (columnas - 1);
                                                    punteo += 5;

                                                    //Si al pasar el borde, hay una comida de tipo2
                                                } else if (tablero[x][columnas - (columnas - 1)] == "?") {
                                                    tablero[x][columnas - (columnas - 1)] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    y = columnas - (columnas - 1);
                                                    punteo += 10;

                                                    //Si al pasar el borde, hay un muro
                                                } else if (tablero[x][columnas - (columnas - 1)] == "#") {
                                                    tablero[x][y] = iconoAsignado;

                                                    //Si al pasar el borde hay una trampa
                                                } else if (tablero[x][columnas - (columnas - 1)] == "X") {

                                                    //Si la casilla actual es igual al icono, entonces al pasar sera X
                                                    if (tablero[x][y] == iconoAsignado) {
                                                        tablero[x][columnas - (columnas - 1)] = "X";
                                                        tablero[x][y] = " ";
                                                        vidas -= 1;
                                                        y = columnas - (columnas - 1);

                                                        //Si la casilla actual es igual a X, entonces al pasar tambien sera X
                                                    } else if (tablero[x][y] == "X") {
                                                        tablero[x][columnas - (columnas - 1)] = "X";
                                                        tablero[x][y] = "X";
                                                        vidas -= 1;
                                                        y = columnas - (columnas - 1);
                                                    }
                                                }
                                            } //Si no se cumple ninguno de los casos, solo se mueve a la derecha
                                            else {
                                                tablero[x][y + 1] = iconoAsignado;
                                                tablero[x][y] = " ";
                                                y++;
                                            }
                                        }
                                    }
                                }
                                break;

                            //Movimiento hacia abajo
                            case "s":
                            case "5":
                            case "S":
                                for (int i = 0; i < filas; i++) {
                                    for (int j = 0; j < columnas; j++) {
                                        if (i == filas - 2 && j == columnas - 2) {

                                            //Validar que la casilla tenga una comida de tipo 1
                                            if (tablero[x + 1][y] == "@") {

                                                if (tablero[x][y] == "X") {
                                                    tablero[x + 1][y] = iconoAsignado;
                                                    tablero[x][y] = "X";
                                                    x++;
                                                    punteo += 5;

                                                } else {
                                                    tablero[x + 1][y] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    x++;
                                                    punteo += 5;
                                                }

                                                //Validar que la casilla tenga una comida de tipo 2
                                            } else if (tablero[x + 1][y] == "?") {

                                                if (tablero[x][y] == "X") {
                                                    tablero[x + 1][y] = iconoAsignado;
                                                    tablero[x][y] = "X";
                                                    x++;
                                                    punteo += 10;

                                                } else {
                                                    tablero[x + 1][y] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    x++;
                                                    punteo += 10;
                                                }

                                                //Validar que la casilla tenga una pared
                                            } else if (tablero[x + 1][y] == "#") {

                                                if (tablero[x][y] == "X") {
                                                    tablero[x][y] = "X";
                                                } else {
                                                    tablero[x][y] = iconoAsignado;
                                                }

                                                //Validar que la casilla tenga una trampa
                                            } else if (tablero[x + 1][y] == "X") {

                                                //Si la casilla actual es igual al icono entonces se asigna X abajo
                                                if (tablero[x][y] == iconoAsignado) {
                                                    tablero[x + 1][y] = "X";
                                                    tablero[x][y] = " ";
                                                    vidas -= 1;
                                                    x++;

                                                    //Si la casilla actual es una X quiere decir que abajo hay otra X. por lo tanto no se borra
                                                } else if (tablero[x][y] == "X") {
                                                    tablero[x + 1][y] = "X";
                                                    tablero[x][y] = "X";
                                                    vidas -= 1;
                                                    x++;
                                                }

                                                //Si la casilla actual es una X, y abajo hay una casilla vacia, se asigna el icono
                                            } else if (tablero[x][y] == "X") {
                                                tablero[x + 1][y] = iconoAsignado;
                                                tablero[x][y] = "X";
                                                x++;

                                            } else if (tablero[x + 1][y] == "-") {

                                                //Si al pasar el borde, hay una casilla vacia
                                                if (tablero[filas - (filas - 1)][y] == " ") {
                                                    tablero[x][y] = " ";
                                                    tablero[filas - (filas - 1)][y] = iconoAsignado;
                                                    x = filas - (filas - 1);

                                                    //Si al pasar el borde, hay una comida de tipo1
                                                } else if (tablero[filas - (filas - 1)][y] == "@") {
                                                    tablero[filas - (filas - 1)][y] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    x = filas - (filas - 1);
                                                    punteo += 5;

                                                    //Si al pasar el borde, hay una comida de tipo2
                                                } else if (tablero[filas - (filas - 1)][y] == "?") {
                                                    tablero[filas - (filas - 1)][y] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    x = filas - (filas - 1);
                                                    punteo += 10;

                                                    //Si al pasar el borde, hay un muro
                                                } else if (tablero[filas - (filas - 1)][y] == "#") {
                                                    tablero[x][y] = iconoAsignado;

                                                    //Si al pasar el borde hay una trampa
                                                } else if (tablero[filas - (filas - 1)][y] == "X") {

                                                    //Si la casilla actual es igual al icono, entonces al pasar sera X
                                                    if (tablero[x][y] == iconoAsignado) {
                                                        tablero[filas - (filas - 1)][y] = "X";
                                                        tablero[x][y] = " ";
                                                        vidas -= 1;
                                                        x = filas - (filas - 1);

                                                        //Si la casilla actual es igual a X, entonces al pasar tambien sera X
                                                    } else if (tablero[x][y] == "X") {
                                                        tablero[filas - (filas - 1)][y] = "X";
                                                        tablero[x][y] = "X";
                                                        vidas -= 1;
                                                        x = filas - (filas - 1);
                                                    }
                                                }
                                            } //Si no se cumple ninguno de los casos, solo se mueve hacia abajo
                                            else {
                                                tablero[x + 1][y] = iconoAsignado;
                                                tablero[x][y] = " ";
                                                x++;
                                            }
                                        }
                                    }
                                }

                                break;

                            //Movimiento hacia arriba
                            case "w":
                            case "8":
                            case "W":

                                for (int i = 0; i < filas; i++) {
                                    for (int j = 0; j < columnas; j++) {
                                        if (i == filas - 2 && j == columnas - 2) {

                                            //Validar que la casilla tenga una comida de tipo 1
                                            if (tablero[x - 1][y] == "@") {

                                                if (tablero[x][y] == "X") {
                                                    tablero[x - 1][y] = iconoAsignado;
                                                    tablero[x][y] = "X";
                                                    x--;
                                                    punteo += 5;

                                                } else {
                                                    tablero[x - 1][y] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    x--;
                                                    punteo += 5;
                                                }

                                                //Validar que la casilla tenga una comida de tipo 2
                                            } else if (tablero[x - 1][y] == "?") {

                                                if (tablero[x][y] == "X") {
                                                    tablero[x - 1][y] = iconoAsignado;
                                                    tablero[x][y] = "X";
                                                    x--;
                                                    punteo += 10;

                                                } else {
                                                    tablero[x - 1][y] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    x--;
                                                    punteo += 10;
                                                }

                                                //Validar que la casilla tenga una pared
                                            } else if (tablero[x - 1][y] == "#") {

                                                if (tablero[x][y] == "X") {
                                                    tablero[x][y] = "X";
                                                } else {
                                                    tablero[x][y] = iconoAsignado;
                                                }

                                                //Validar que la casilla tenga una trampa
                                            } else if (tablero[x - 1][y] == "X") {

                                                //Si la casilla actual es igual al icono entonces se asigna X abajo
                                                if (tablero[x][y] == iconoAsignado) {
                                                    tablero[x - 1][y] = "X";
                                                    tablero[x][y] = " ";
                                                    vidas -= 1;
                                                    x--;

                                                    //Si la casilla actual es una X quiere decir que abajo hay otra X. por lo tanto no se borra
                                                } else if (tablero[x][y] == "X") {
                                                    tablero[x - 1][y] = "X";
                                                    tablero[x][y] = "X";
                                                    vidas -= 1;
                                                    x--;
                                                }

                                                //Si la casilla actual es una X, y abajo hay una casilla vacia, se asigna el icono
                                            } else if (tablero[x][y] == "X") {
                                                tablero[x - 1][y] = iconoAsignado;
                                                tablero[x][y] = "X";
                                                x--;
                                            } else if (tablero[x - 1][y] == "-") {

                                                //Si al pasar el borde, hay una casilla vacia
                                                if (tablero[filas][y] == " ") {
                                                    tablero[x][y] = " ";
                                                    tablero[filas][y] = iconoAsignado;
                                                    x = filas;

                                                    //Si al pasar el borde, hay una comida de tipo1
                                                } else if (tablero[filas][y] == "@") {
                                                    tablero[filas][y] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    x = filas;
                                                    punteo += 5;

                                                    //Si al pasar el borde, hay una comida de tipo2
                                                } else if (tablero[filas][y] == "?") {
                                                    tablero[filas][y] = iconoAsignado;
                                                    tablero[x][y] = " ";
                                                    x = filas;
                                                    punteo += 10;

                                                    //Si al pasar el borde, hay un muro
                                                } else if (tablero[filas][y] == "#") {
                                                    tablero[x][y] = iconoAsignado;

                                                    //Si al pasar el borde hay una trampa
                                                } else if (tablero[filas][y] == "X") {

                                                    //Si la casilla actual es igual al icono, entonces al pasar sera X
                                                    if (tablero[x][y] == iconoAsignado) {
                                                        tablero[filas][y] = "X";
                                                        tablero[x][y] = " ";
                                                        vidas -= 1;
                                                        x = filas;

                                                        //Si la casilla actual es igual a X, entonces al pasar tambien sera X
                                                    } else if (tablero[x][y] == "X") {
                                                        tablero[filas][y] = "X";
                                                        tablero[x][y] = "X";
                                                        vidas -= 1;
                                                        x = filas;
                                                    }
                                                }
                                            } //Si no se cumple ninguno de los casos, solo se mueve hacia abajo
                                            else {
                                                tablero[x - 1][y] = iconoAsignado;
                                                tablero[x][y] = " ";
                                                x--;
                                            }
                                        }
                                    }
                                }
                                break;

                            //Finalizar partida
                            case "e":
                            case "E":

                                System.out.println("\nHa finalizado el juego");
                                salirJuego = 5;   //Condicion solo para validar que se salga del juego
                                break;

                            //Para poner el juego en pausa
                            case "m":
                            case "M":
                                System.out.println("----MENU DE PAUSA----");
                                System.out.println("---------------------");
                                System.out.println("1.  Continuar partida");
                                System.out.println("2.   Tabla Posiciones");
                                System.out.println("3.      Salir partida");
                                System.out.println("---------------------");

                                System.out.println("Jugador: " + nombre + "," + " Punteo: " + punteo + "," + " Vidas: " + vidas);
                                System.out.print("\nIngrese una opcion:  ");
                                opcionMenu = entrada.nextInt();

                                switch (opcionMenu) {
                                    case 1:
                                        break;
                                    case 2:
                                        //Tabla de posiciones
                                        System.out.println("\n-----------------------");
                                        System.out.println("         Top 10:       ");
                                        System.out.println("-----------------------");
                                        System.out.println(" ");

                                        //Imprimir el arreglo de punteo
                                        for (int i = (punteoSave.length - 1); i >= 0; i--) {

                                            //if (nombreSave[i] != null) {

                                                System.out.println((punteoSave.length - i) + ". Nombre: " + nombreSave[i] + ", Punteo: " + punteoSave[i]);

                                            //}
                                        }
                                        break;
                                    case 3:
                                        System.out.println("\nHa finalizado el juego");
                                        salirJuego = 5;   //Condicion solo para validar que se salga del juego
                                        break;
                                }

                                break;

                            default:
                                System.out.println("\nValor incorrecto");
                        }

                        System.out.println("---------------------");
                        System.out.println("Jugador: " + nombre + "," + " Punteo: " + punteo + "," + " Vidas: " + vidas);
                        System.out.println("");

                        puntosTotales = (comidaT1 * 5) + (comidaT2 * 10);

                        for (int i = 0; i < marcoF; i++) {
                            for (int j = 0; j < marcoC; j++) {
                                if (i == marcoF - 1) {
                                    System.out.print(String.valueOf(j) + " ");
                                } else {
                                    System.out.print(tablero[i][j] + " ");
                                }
                            }

                            System.out.println("[" + String.valueOf(i) + "]" + " ");
                        }

                    } while (vidas != 0 && punteo < puntosTotales && salirJuego != 5);

                    System.out.println("---------------------");
                    System.out.println("Jugador: " + nombre + "," + " Punteo: " + punteo + "," + " Vidas: " + vidas);
                    System.out.println("");

                    //Imprimir el tablero con icono en tablero
                    for (int i = 0; i < marcoF; i++) {
                        for (int j = 0; j < marcoC; j++) {
                            if (i == marcoF - 1) {
                                System.out.print(String.valueOf(j) + " ");
                            } else {
                                System.out.print(tablero[i][j] + " ");
                            }
                        }

                        System.out.println("[" + String.valueOf(i) + "]" + " ");
                    }
                    
                    contadorArray++;

                    //Almacenar los puntajes dentro del arreglo
                    nombreSave[contadorArray] = nombre;
                    punteoSave[contadorArray] = punteo;
                    
                    //Metodo de ordenamiento por burbuja
                    for(int i = 0; i < (punteoSave.length - 1); i++){
                        for(int j = 0; j < (punteoSave.length - 1); j++){
                            if (punteoSave[j] > punteoSave[j + 1]){
                                
                                auxOrdenamiento1 = punteoSave[j];
                                punteoSave[j] = punteoSave[j + 1];
                                punteoSave[j+1] = auxOrdenamiento1;
                                
                                auxOrdenamiento2 = nombreSave[j];
                                nombreSave[j] = nombreSave[j + 1];
                                nombreSave[j+1] = auxOrdenamiento2;
                            }
                        }
                    }
                    

                  /*  //Metodo de ordenamiento por insercion
                    for (int i = 0; i < punteoSave.length; i++) {
                        pos = i;
                        auxOrdenamiento1 = punteoSave[i];
                        auxOrdenamiento2 = nombreSave[i];

                        while ((pos > 1) && (punteoSave[pos - 1] > auxOrdenamiento1)) {
                            punteoSave[pos] = punteoSave[pos - 1];
                            nombreSave[pos] = nombreSave[pos - 1];
                            pos--;
                        }
                        nombreSave[pos] = auxOrdenamiento2;
                        punteoSave[pos] = auxOrdenamiento1;
                    }
*/
                    break;
                    
                case 2:
                    //Tabla de posiciones
                    System.out.println("\n-----------------------");
                    System.out.println("         Top 10:       ");
                    System.out.println("-----------------------");
                    System.out.println(" ");

                    //Imprimir el arreglo de punteo
                    for (int i = (punteoSave.length - 1); i >= 0; i--) {

                        //if (nombreSave[i] != null) {

                            System.out.println((punteoSave.length - i) + ". Nombre: " + nombreSave[i] + ", Punteo: " + punteoSave[i]);

                        //}
                    }

                    break;

                case 3:
                    System.out.println(" ______________________________");
                    System.out.println("|  Gracias por jugar Pac-Usac   |");
                    System.out.println("|______________________________|");
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("\nDebe ingresar un numero correspondiente a una opcion");
            }
        } while (opcion != 3);
    }
}
