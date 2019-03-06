/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Educho
 */
public class HDT6Estruc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Map<String, String> CartasT;
        Map<String, String> CartasJ;
        ArrayList<String> list=new ArrayList();
        ArrayList<String> personas=new ArrayList();
        ArrayList<String> cartas =new ArrayList();
        Scanner scan = new Scanner(System.in);
        MapFactory myfactory=new MapFactory();
        int n, mons=0, tramp=0, hech=0;
        String choice="5", monst="Tus monstruos son: ", tramps="Tus trampas son: ", hechis="Tus hechizos son: ",
                monsts = "Los monstruos totales son: ", trampas = "Las trampas totales son: ", hechizo = "Los hechizos totales son: ";
        System.out.println("Introduzca el tipo del Map con el cual desea trabajar");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");

        try{
            n=scan.nextInt();
        } catch(InputMismatchException e){
            System.out.println("No haz ingresado un numero. El programa se ejecutara con HashMap\n");
            n = 1;
        }
        CartasT=myfactory.creadorMap(n);
        CartasJ=myfactory.creadorMap(n);

        try{
            Stream<String> lines = Files.lines(
                    Paths.get("cards_desc.txt"),
                    StandardCharsets.UTF_8
            );
            
            lines.forEach(a -> list.add(a));
        } catch (IOException e){
            System.out.println("Error!");
        }
        System.out.println(list);
        
        for (int i=0; i<list.size(); i++){
           String car=(list.get(i));
            String[] parts=car.split("[|]");
            cartas.add(parts[0]);
            CartasT.put(parts[0],parts[1]);
            
        }
        //System.out.println(cartas);
     
         while (!choice.equals("7")){
             if (!choice.equals("")){
            System.out.println("Escoge una accion que deseas realizar");
            System.out.println("1. Escoger una carta del mazo y agregarla a la tuya");
            System.out.println("2. Mostrar el tipo de una carta específica");
            System.out.println("3. Mostrar el nombre, tipo y cantidad de cada carta que el usuario tiene en su colección");
            System.out.println("4. Mostrar el nombre, tipo y cantidad de cada carta que el usuario tiene en su colección pero ordenadas por tipo");
            System.out.println("5. Mostrar nombre y tipo de todas la cartas");
            System.out.println("6. Mostrar nombre y ordenadas por su tipo de todas la cartas del mazo ");
            System.out.println("7. Salir del juego");
         }
            choice=scan.nextLine();
            if (choice.equals("1")){
                System.out.println("Ingrese el nombre de la carta que desea. Cuidado con faltas ortográficas");
                String personaje=scan.nextLine();
                if (CartasT.containsKey(personaje)==true){
                    personas.add(personaje);
                    String value=CartasT.get(personaje);
                    CartasT.remove(personaje);
                    CartasJ.put(personaje, value);
                    System.out.println("Este es tu mazo por el momennto: ");
                    System.out.println(CartasJ);
                }else{
                    System.out.println("ERROR ESA CARTA NO EXISTE");
                }
                
            }else if (choice.equals("2")){
                System.out.println("Ingrese el nombre de la carta que desea saber su tipo. Cuidado con faltas ortográficas");
                String personaje=scan.nextLine();
                System.out.println("Es un "+CartasT.get(personaje));
            }else if (choice.equals("3")){
                Object[] tipos=CartasJ.values().toArray();
                
                for (int e=0; e<tipos.length; e++){
                    if (tipos[e].equals("Monstruo")){
                        mons++;
                    }else if (tipos[e].equals("Trampa")){
                        tramp++;
                    }else{
                        hech++;
                    }
                }
                System.out.println("Tus cartas son:");
                System.out.println(CartasJ);
                System.out.println("Tus tipos de carta son:");
                System.out.println("    Monstruos:"+mons);
                System.out.println("    Trampas:"+tramp);
                System.out.println("    Hechizos:"+hech);
                mons=0;
                tramp=0;
                hech=0;
            }else if (choice.equals("4")){
                for (int l=0; l<personas.size (); l++){
                    String nombre=personas.get(l);
                    String tipo=CartasJ.get(nombre);
                    if (tipo.equals("Monstruo")){
                        mons++;
                        monst=monst+"\n"+nombre;
                    }else if (tipo.equals("Trampa")){
                        tramp++;
                        tramps=tramps+"\n"+nombre;
                    }else{
                        hech++;
                        hechis=hechis+"\n"+nombre;
                    }
                }
                System.out.println("Cantidad de monstruos: "+mons);
                System.out.println(monst);
                System.out.println("Cantidad de trampas: "+tramp);
                System.out.println(tramps);
                System.out.println("Cantidad de hechizos: "+hech);
                System.out.println(hechis);
                mons = 0;
                tramp = 0;
                hech = 0;
            }else if (choice.equals("5")){
                System.out.println("Las cartas disponibles en el mazo son: ");
                System.out.println(CartasT);
            }else if (choice.equals("6")){
                for (int c = 0; c< cartas.size(); c++){
                    String nombre = cartas.get(c);
                    String tipo = CartasT.get(nombre);
                    if (tipo.equals("Monstruo")){
                        monsts=monsts+"\n"+nombre;
                    }else if (tipo.equals("Trampa")){
                        trampas=trampas+"\n"+nombre;
                    }else{
                        hechizo=hechizo+"\n"+nombre;
                    }
                }
                System.out.println(monsts);
                System.out.println(trampas);
                System.out.println(hechizo);
                
            }
         
            
        }
        
    }
    
}
