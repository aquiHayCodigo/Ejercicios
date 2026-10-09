import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class App {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        
        /* String base = "C:\\Users\\DAM2\\Documents\\DAM2_CLAU\\DANI-AccesoDatos\\NIO\\Ejercicios\\src\\";
        System.out.println("dime el nombre del directorio raiz: ");
        String nombreRaiz= sc.nextLine();
        String nombreRaizBase= base + nombreRaiz;
        //System.out.println("Dime elñ nombre de la subcarpeta: ");
        //String nombreSubcarpeta1= sc.nextLine();

        Path rutaRaiz= Paths.get( nombreRaizBase + nombreRaiz);


        System.err.println("Cuántas subcarpetas quieres crear dentro del directorio raiz: ");
        int cant= Integer.parseInt(sc.nextLine()); */ 

        //OrganizadorProyectos();
        BuscadorProyectos();
       
    }

    public static void OrganizadorProyectos(){
        System.out.println("Dime el nombre del directorio raiz: ");
        String nombreRaiz= sc.nextLine();
        Path rutaRaiz= Paths.get(nombreRaiz);

        // crear el directorio raiz si no existe
        crearCarpeta(rutaRaiz);

        System.out.println("Dime el numero de subcarpetas: ");
        int cant= Integer.parseInt(sc.nextLine());

        for(int i=0; i<cant; i++){
            System.out.println("Dime el nombre de la subcarpeta: ");
            String nombreSubcarpeta= sc.nextLine();
            Path rutaSubcarpeta= rutaRaiz.resolve(nombreSubcarpeta); //combina una ruta base con otras (añade las barras de unión "/")
            crearCarpeta(rutaSubcarpeta);
        }

        Path rutaReadme= Paths.get("ReadMe.md"); // = rutaRaiz.resolve("Readme.md");
        rutaRaiz.resolve(rutaReadme);


    }

    public static void crearCarpeta(Path ruta){

        if(Files.exists(ruta)){
            System.out.println("La carpeta ya existe: " + ruta );
        }else{
            try {
                Files.createDirectory(ruta);
                System.err.println("Se ha creado el fichero");
            } catch (IOException e) {
                System.err.println("Error creando el fichero");
            }
        }
    }

    public static void crearFichero(Path ruta){
        System.out.println("Dime el nombre del directorio raiz: ");
        String nombreRaiz= sc.nextLine();
        Path rutaRaiz= Paths.get(nombreRaiz);

        // crear el directorio raiz si no existe
        if(Files.exists(rutaRaiz)){
            System.out.println("La carpeta raiz ya existe: " + ruta );
        }else{
            try {
                Files.createFile(ruta);
                System.err.println("Se ha creado el fichero raiz");
            } catch (IOException e) {
                System.err.println("Error creando el fichero raiz");
            }
        }
    }

    public static void BuscadorProyectos(){
        System.out.println("Dime el nombre del directorio raiz: ");
        String nombreRaiz= sc.nextLine();
        Path rutaRaiz= Paths.get(nombreRaiz);

        // crear el directorio raiz si no existe
        if(Files.exists(rutaRaiz)){
            System.out.println("La carpeta raiz ya existe");
        }else{
            try {
                Files.createDirectory(rutaRaiz);
                System.err.println("Se ha creado el fichero raiz");
            } catch (IOException e) {
                System.err.println("Error creando el fichero raiz");
            }
        }

        System.out.println("Dime el numero de subcarpetas: ");
        int cant= Integer.parseInt(sc.nextLine());

        for(int i=0; i<cant; i++){
            System.out.println("Dime el nombre de la subcarpeta: ");
            String nombreSubcarpeta= sc.nextLine();
            Path rutaSubcarpeta= rutaRaiz.resolve(nombreSubcarpeta); //combina una ruta base con otras (añade las barras de unión "/")
            crearCarpeta(rutaSubcarpeta);
        }

        Path rutaReadme= Paths.get("ReadMe.md"); // = rutaRaiz.resolve("Readme.md");
        rutaRaiz.resolve(rutaReadme);

        try (
            Stream<Path> rutas = Files.walk(rutaRaiz);
        ) {
            rutas.forEach ( ruta -> System.out.println(ruta));
            
        } catch (Exception e) {
            // TODO: handle exception
        }


    }
}
