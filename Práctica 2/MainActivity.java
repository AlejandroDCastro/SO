package com.example.eps.myapplication;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText numA,numB,sumaNumeros;
    private Button botonSuma, botonMult, botonResta, botonDivide, botonAND, botonOR, botonXOR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos una referencia a los controles de la interfaz gráfica
        numA = (EditText)findViewById(R.id.numA);
        numB = (EditText) findViewById(R.id.numB);
        sumaNumeros = (EditText)findViewById(R.id.sumaNumeros);
        botonSuma = (Button) findViewById(R.id.botonSuma);
        botonResta = (Button) findViewById(R.id.botonResta);
        botonMult = (Button) findViewById(R.id.botonMult);
        botonDivide = (Button) findViewById(R.id.botonDivide);
        botonAND = (Button) findViewById(R.id.botonAND);
        botonOR = (Button) findViewById(R.id.botonOR);
        botonXOR = (Button) findViewById(R.id.botonXOR);

        //Implementamos el evento click del botón
        botonSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Comprobamos que se han introducido valores
                if(numA == null || numB == null) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Introduce todos los números.",
                                    Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else {
                    boolean error = false;
                    int longitud1 = numA.getText().toString().length();
                    int longitud2 = numB.getText().toString().length();
                    int sumaNum, sumaNum1=0, sumaNum2=0;

                    //Realizamos la suma
                    String resultadoFinal;
                    String valor1=numA.getText().toString();
                    String valor2=numB.getText().toString();
                    int numero1=Integer.parseInt(valor1);
                    int numero2=Integer.parseInt(valor2);
                    for (int i = 0; i < longitud1; i++) {
                        int num;

                        if (numero1%10 > 1) {
                            error = true;
                            break;
                        }
                        num = (numero1 % 10) * ((int) (Math.pow(2, i)));
                        numero1 = numero1 / 10;
                        sumaNum1 = sumaNum1 + num;
                    }
                    for (int i = 0; i < longitud2; i++) {
                        int num;

                        if (numero2%10 > 1) {
                            error = true;
                            break;
                        }
                        num = (numero2 % 10) * ((int) (Math.pow(2, i)));
                        numero2 = numero2 / 10;
                        sumaNum2 = sumaNum2 + num;
                    }

                    //Hacemos la comprobación
                    if (error) {
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Error: Los números introducidos no son binarios.",
                                        Toast.LENGTH_SHORT);
                        toast1.show();
                    } else {
                        sumaNum = sumaNum1 + sumaNum2;
                        if (sumaNum > 255 || sumaNum < 0) {
                            Toast toast2 =
                                    Toast.makeText(getApplicationContext(),
                                            "Error: La operación realizada produce un OVERFLOW.",
                                            Toast.LENGTH_SHORT);
                            toast2.show();
                        } else {
                            int posicion = 1, resultado = 0;

                            while (sumaNum > 1) {
                                resultado = resultado + (sumaNum % 2) * posicion;
                                sumaNum = sumaNum / 2;
                                posicion = posicion * 10;
                                if (sumaNum == 1) {
                                    resultado = resultado + sumaNum * posicion;
                                    sumaNum = 0;
                                }
                            }
                            if (sumaNum == 1) resultado = resultado + sumaNum * posicion;
                            resultadoFinal = String.valueOf(resultado);
                            sumaNumeros.setText(resultadoFinal);
                        }
                    }
                }
            }
        });










        botonResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Comprobamos que se han introducido valores
                if(numB == null || numA == null) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Introduce todos los números",
                                    Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else {
                    boolean error = false;
                    int longitud1 = numA.getText().toString().length();
                    int longitud2 = numB.getText().toString().length();
                    int sumaNum, sumaNum1=0, sumaNum2=0;

                    //Realizamos la resta
                    String valor1=numA.getText().toString();
                    String valor2=numB.getText().toString();
                    int numero1=Integer.parseInt(valor1);
                    int numero2=Integer.parseInt(valor2);
                    for (int i = 0; i < longitud1; i++) {
                        int num;

                        if (numero1%10 > 1) {
                            error = true;
                            break;
                        }
                        num = (numero1 % 10) * ((int) (Math.pow(2, i)));
                        numero1 = numero1 / 10;
                        sumaNum1 = sumaNum1 + num;
                    }
                    for (int i = 0; i < longitud2; i++) {
                        int num;

                        if (numero2%10 > 1) {
                            error = true;
                            break;
                        }
                        num = (numero2 % 10) * ((int) (Math.pow(2, i)));
                        numero2 = numero2 / 10;
                        sumaNum2 = sumaNum2 + num;
                    }

                    //Hacemos la comprobación
                    if (error) {
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Error: Los números introducidos no son binarios.",
                                        Toast.LENGTH_SHORT);
                        toast1.show();
                    } else {
                        sumaNum = sumaNum1 - sumaNum2;
                        if (sumaNum > 255 || sumaNum < 0) {
                            Toast toast2 =
                                    Toast.makeText(getApplicationContext(),
                                            "Error: La operación realizada produce un OVERFLOW",
                                            Toast.LENGTH_SHORT);
                            toast2.show();
                        } else {
                            int posicion = 1, resultado = 0;
                            String resultadoFinal;

                            while (sumaNum > 1) {
                                resultado = resultado + (sumaNum % 2) * posicion;
                                sumaNum = sumaNum / 2;
                                posicion = posicion * 10;
                                if (sumaNum == 1) {
                                    resultado = resultado + sumaNum * posicion;
                                    sumaNum = 0;
                                }
                            }
                            if (sumaNum == 1) resultado = resultado + sumaNum * posicion;
                            resultadoFinal = String.valueOf(resultado);
                            sumaNumeros.setText(resultadoFinal);
                        }
                    }
                }
            }
        });









        botonMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Comprobamos que se han introducido valores
                if(numA == null || numB == null) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Introduce todos los números",
                                    Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else {
                    boolean error = false;
                    int longitud1 = numA.getText().toString().length();
                    int longitud2 = numB.getText().toString().length();
                    int sumaNum, sumaNum1=0, sumaNum2=0;

                    //Realizamos la multiplicación
                    String valor1=numA.getText().toString();
                    String valor2=numB.getText().toString();
                    int numero1=Integer.parseInt(valor1);
                    int numero2=Integer.parseInt(valor2);
                    for (int i = 0; i < longitud1; i++) {
                        int num;

                        if (numero1%10 > 1) {
                            error = true;
                            break;
                        }
                        num = (numero1 % 10) * ((int) (Math.pow(2, i)));
                        numero1 = numero1 / 10;
                        sumaNum1 = sumaNum1 + num;
                    }
                    for (int i = 0; i < longitud2; i++) {
                        int num;

                        if (numero2%10 > 1) {
                            error = true;
                            break;
                        }
                        num = (numero2 % 10) * ((int) (Math.pow(2, i)));
                        numero2 = numero2 / 10;
                        sumaNum2 = sumaNum2 + num;
                    }

                    //Hacemos la comprobación
                    if (error) {
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Error: Los números introducidos no son binarios.",
                                        Toast.LENGTH_SHORT);
                        toast1.show();
                    } else {
                        sumaNum = sumaNum1 * sumaNum2;
                        if (sumaNum > 255 || sumaNum < 0) {
                            Toast toast2 =
                                    Toast.makeText(getApplicationContext(),
                                            "Error: La operación realizada produce un OVERFLOW",
                                            Toast.LENGTH_SHORT);
                            toast2.show();
                        } else {
                            int posicion = 1, resultado = 0;
                            String resultadoFinal;

                            while (sumaNum > 1) {
                                resultado = resultado + (sumaNum % 2) * posicion;
                                sumaNum = sumaNum / 2;
                                posicion = posicion * 10;
                                if (sumaNum == 1) {
                                    resultado = resultado + sumaNum * posicion;
                                    sumaNum = 0;
                                }
                            }
                            if (sumaNum == 1) resultado = resultado + sumaNum * posicion;
                            resultadoFinal = String.valueOf(resultado);
                            sumaNumeros.setText(resultadoFinal);
                        }
                    }
                }
            }
        });










        botonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Comprobamos que se han introducido valores
                if(numA == null || numB == null) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Introduce todos los números",
                                    Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else {
                    boolean error = false;
                    int longitud1 = numA.getText().toString().length();
                    int longitud2 = numB.getText().toString().length();
                    int sumaNum, sumaNum1=0, sumaNum2=0;

                    //Realizamos la división
                    String valor1=numA.getText().toString();
                    String valor2=numB.getText().toString();
                    int numero1=Integer.parseInt(valor1);
                    int numero2=Integer.parseInt(valor2);
                    for (int i = 0; i < longitud1; i++) {
                        int num;

                        if (numero1%10 > 1) {
                            error = true;
                            break;
                        }
                        num = (numero1 % 10) * ((int) (Math.pow(2, i)));
                        numero1 = numero1 / 10;
                        sumaNum1 = sumaNum1 + num;
                    }
                    for (int i = 0; i < longitud2; i++) {
                        int num;

                        if (numero2%10 > 1 || (longitud1==1 && numero2==0)) {
                            error = true;
                            break;
                        }
                        num = (numero2 % 10) * ((int) (Math.pow(2, i)));
                        numero2 = numero2 / 10;
                        sumaNum2 = sumaNum2 + num;
                    }

                    //Hacemos la comprobación
                    if (error) {
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Error: Los números introducidos no son binarios.",
                                        Toast.LENGTH_SHORT);
                        toast1.show();
                    } else {
                        sumaNum = sumaNum1 / sumaNum2;
                        if (sumaNum > 255 || sumaNum < 0) {
                            Toast toast2 =
                                    Toast.makeText(getApplicationContext(),
                                            "Error: La operación realizada produce un OVERFLOW",
                                            Toast.LENGTH_SHORT);
                            toast2.show();
                        } else {
                            int posicion = 1, resultado = 0;
                            String resultadoFinal;

                            while (sumaNum > 1) {
                                resultado = resultado + (sumaNum % 2) * posicion;
                                sumaNum = sumaNum / 2;
                                posicion = posicion * 10;
                                if (sumaNum == 1) {
                                    resultado = resultado + sumaNum * posicion;
                                    sumaNum = 0;
                                }
                            }
                            if (sumaNum == 1) resultado = resultado + sumaNum * posicion;
                            resultadoFinal = String.valueOf(resultado);
                            sumaNumeros.setText(resultadoFinal);
                        }
                    }
                }
            }
        });







        botonAND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Comprobamos que se han introducido valores
                if(numA == null || numB == null) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Introduce todos los números.",
                                    Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else {
                    boolean error = false;
                    int longitud1 = numA.getText().toString().length();
                    int longitud2 = numB.getText().toString().length();
                    int maxLong = longitud1;

                    // Mayor longitud
                    if (longitud1 > longitud2) maxLong = longitud1;
                    if (longitud1 < longitud2) maxLong = longitud2;

                    String valor1 = numA.getText().toString();
                    String valor2 = numB.getText().toString();
                    int numero1 = Integer.parseInt(valor1);
                    int numero2 = Integer.parseInt(valor2);
                    char[] resultado = new char[maxLong];

                    for (int i = maxLong-1; i >= 0; i--) {
                        int n1 = numero1%10, n2 = numero2%10;

                        //Comprobamos que los números son binarios
                        if (n1 > 1 || n2 > 1) {
                            error = true;
                            break;
                        }

                        if (longitud1 > 0 && longitud2 > 0) {
                            if (n1 == 1 && n2 == 1)
                                resultado[i] = 49;
                            else
                                resultado[i] = 48;
                        }
                        else
                            resultado[i] = 48;
                        numero1 = numero1/10;
                        numero2 = numero2/10;
                        longitud1--;
                        longitud2--;
                    }

                    // Si no son binarios -> ejecuta Toast
                    if (error) {
                        Toast toast2 =
                                Toast.makeText(getApplicationContext(),
                                        "Error: Los números introducidos no son binarios.",
                                        Toast.LENGTH_SHORT);
                        toast2.show();
                    } else {
                        String resultadoFinal = String.copyValueOf(resultado);
                        sumaNumeros.setText(resultadoFinal);
                    }
                }
            }
        });








        botonOR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Comprobamos que se han introducido valores
                if(numA == null || numB == null) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Introduce todos los números.",
                                    Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else {
                    boolean error = false;
                    int longitud1 = numA.getText().toString().length();
                    int longitud2 = numB.getText().toString().length();
                    int maxLong = longitud1;

                    // Mayor longitud
                    if (longitud1 > longitud2) maxLong = longitud1;
                    if (longitud1 < longitud2) maxLong = longitud2;

                    String valor1 = numA.getText().toString();
                    String valor2 = numB.getText().toString();
                    int numero1 = Integer.parseInt(valor1);
                    int numero2 = Integer.parseInt(valor2);
                    char[] resultado = new char[maxLong];

                    for (int i = maxLong-1; i >= 0; i--) {
                        int n1 = numero1%10, n2 = numero2%10;

                        //Comprobamos que los números son binarios
                        if (n1 > 1 || n2 > 1) {
                            error = true;
                            break;
                        }
                        // Calculamos
                        if (n1 == 1 || n2 == 1)
                            resultado[i] = 49;
                        else
                            resultado[i] = 48;
                        numero1 = numero1/10;
                        numero2 = numero2/10;
                        longitud1--;
                        longitud2--;
                    }

                    // Si no son binarios -> ejecuta Toast
                    if(error) {
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Error: Los números introducidos no son binarios.",
                                        Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                    else {
                        String resultadoFinal = String.copyValueOf(resultado);
                        sumaNumeros.setText(resultadoFinal);
                    }
                }
            }
        });








        botonXOR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Comprobamos que se han introducido valores
                if(numA == null || numB == null) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Introduce todos los números.",
                                    Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else {
                    boolean error = false;
                    int longitud1 = numA.getText().toString().length();
                    int longitud2 = numB.getText().toString().length();
                    int maxLong = longitud1;

                    // Mayor longitud
                    if (longitud1 > longitud2) maxLong = longitud1;
                    if (longitud1 < longitud2) maxLong = longitud2;

                    String valor1 = numA.getText().toString();
                    String valor2 = numB.getText().toString();
                    int numero1 = Integer.parseInt(valor1);
                    int numero2 = Integer.parseInt(valor2);
                    char[] resultado = new char[maxLong];

                    for (int i = maxLong-1; i >= 0; i--) {
                        int n1 = numero1%10, n2 = numero2%10;

                        //Comprobamos que los números son binarios
                        if (n1 > 1 || n2 > 1) {
                            error = true;
                            break;
                        }
                        // Calculamos
                        if ((n1 == 1 && n2 == 0) || (n1 == 0 && n2 ==1))
                            resultado[i] = 49;
                        else
                            resultado[i] = 48;
                        numero1 = numero1/10;
                        numero2 = numero2/10;
                        longitud1--;
                        longitud2--;
                    }

                    // Si no son binarios -> ejecuta Toast
                    if(error) {
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Error: Los números introducidos no son binarios.",
                                        Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                    else {
                        String resultadoFinal = String.valueOf(resultado);
                        sumaNumeros.setText(resultadoFinal);
                    }
                }
            }
        });
    }
}
