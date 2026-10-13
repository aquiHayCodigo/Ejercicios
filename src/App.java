import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class App {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        /*
         * String base =
         * "C:\\Users\\DAM2\\Documents\\DAM2_CLAU\\DANI-AccesoDatos\\NIO\\Ejercicios\\src\\";
         * System.out.println("dime el nombre del directorio raiz: ");
         * String nombreRaiz= sc.nextLine();
         * String nombreRaizBase= base + nombreRaiz;
         * //System.out.println("Dime elñ nombre de la subcarpeta: ");
         * //String nombreSubcarpeta1= sc.nextLine();
         * 
         * Path rutaRaiz= Paths.get( nombreRaizBase + nombreRaiz);
         * 
         * 
         * System.err.
         * println("Cuántas subcarpetas quieres crear dentro del directorio raiz: ");
         * int cant= Integer.parseInt(sc.nextLine());
         */

        // OrganizadorProyectos();
        BuscadorProyectos();

    }

    public static void OrganizadorProyectos() {
        System.out.println("Dime el nombre del directorio raiz: ");
        String nombreRaiz = sc.nextLine();
        Path rutaRaiz = Paths.get(nombreRaiz);

        // crear el directorio raiz si no existe
        crearCarpeta(rutaRaiz);

        System.out.println("Dime el numero de subcarpetas: ");
        int cant = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < cant; i++) {
            System.out.println("Dime el nombre de la subcarpeta: ");
            String nombreSubcarpeta = sc.nextLine();
            Path rutaSubcarpeta = rutaRaiz.resolve(nombreSubcarpeta); // combina una ruta base con otras (añade las
                                                                      // barras de unión "/")
            crearCarpeta(rutaSubcarpeta);
        }

        Path rutaReadme = Paths.get("ReadMe.md"); // = rutaRaiz.resolve("Readme.md");
        rutaRaiz.resolve(rutaReadme);

    }

    public static void crearCarpeta(Path ruta) {

        if (Files.exists(ruta)) {
            System.out.println("La carpeta ya existe: " + ruta);
        } else {
            try {
                Files.createDirectory(ruta);
                System.err.println("Se ha creado el fichero");
            } catch (IOException e) {
                System.err.println("Error creando el fichero");
            }
        }
    }

    public static void crearFichero(Path ruta) {
        System.out.println("Dime el nombre del directorio raiz: ");
        String nombreRaiz = sc.nextLine();
        Path rutaRaiz = Paths.get(nombreRaiz);

        // crear el directorio raiz si no existe
        if (Files.exists(rutaRaiz)) {
            System.out.println("La carpeta raiz ya existe: " + ruta);
        } else {
            try {
                Files.createFile(ruta);
                System.err.println("Se ha creado el fichero raiz");
            } catch (IOException e) {
                System.err.println("Error creando el fichero raiz");
            }
        }
    }

    public static void BuscadorProyectos() {
        System.out.println("Dime el nombre del directorio raiz: ");
        String nombreRaiz = sc.nextLine();
        Path rutaRaiz = Paths.get(nombreRaiz);

        // crear el directorio raiz si no existe
        if (Files.exists(rutaRaiz)) {
            System.out.println("La carpeta raiz ya existe");
        } else {
            try {
                Files.createDirectory(rutaRaiz);
                System.err.println("Se ha creado el fichero raiz");
            } catch (IOException e) {
                System.err.println("Error creando el fichero raiz");
            }
        }

        System.out.println("Dime el numero de subcarpetas: ");
        int cant = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < cant; i++) {
            System.out.println("Dime el nombre de la subcarpeta: ");
            String nombreSubcarpeta = sc.nextLine();
            Path rutaSubcarpeta = rutaRaiz.resolve(nombreSubcarpeta); // combina una ruta base con otras (añade las
                                                                      // barras de unión "/")
            crearCarpeta(rutaSubcarpeta);
        }

        Path rutaReadme = Paths.get("ReadMe.md"); // = rutaRaiz.resolve("Readme.md");
        rutaRaiz.resolve(rutaReadme);

        try (
            Stream<Path> rutas = Files.walk(rutaRaiz);) {
            // rutas.forEach(ruta -> System.out.println(ruta));
            rutas.forEach(
                (ruta) -> {
                    if(Files.isDirectory(ruta)){
                        System.out.println("Directorio: " + ruta);
                    }else{
                     System.out.println("Subcarpeta " + ruta);   
                    }
            });

        } catch (Exception e) {
            System.err.println(e);
        }

        Menu(rutaRaiz);
    }

    public static void Menu(Path rutaRaiz) {
        boolean salir = false;
        do {
            System.out.println("===============");
            System.out.println("1. CREAR");
            System.out.println("2. BORRAR");
            System.out.println("3. MOVER");
            System.out.println("===============");

            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("Dime si quieres crearrr un fichero (F) o una carpeta (C): ");
                    String dime= sc.nextLine();
                    switch (dime) {
                        case "C":
                            System.out.println("Vale, dime como llamarla: ");
                            String carpetNew= sc.nextLine();
                            Path carpeta= Paths.get(carpetNew);
                            if(Files.exists(carpeta)){
                                System.out.println("Esa carpeta ya existe");
                            }else{
                                try {
                                    Files.createDirectory(carpeta);
                                    System.out.println("Se ha creado el fichero");
                                } catch (IOException e) {
                                    System.err.println("Ha habido un error al crear la carpeta");
                                }
                            }
                        break;

                        case "F":
                            System.out.println("Vale, dime como llamarlo: ");
                            String fileNew= sc.nextLine();
                            Path ruta = rutaRaiz.resolve(fileNew);
                            // Files.createFile(fileNew);
                            System.out.println("El archivo " + ruta + "ha sido creado");
                        break;
                
                        default:
                            throw new AssertionError();
                    }

                break;

                case 2:
                    System.out.println("Dime el nombre de la carpeta que quieres borrar: ");
                    String borrar= sc.nextLine();
                    Path carpetaBorrar= Paths.get(borrar);
                    if(Files.exists(carpetaBorrar)){
                        try {
                            Files.delete(carpetaBorrar);
                        } catch (IOException e) {
                            System.err.println("Ha habido un error al borrar la carpeta");
                        }
                    }else{
                        System.out.println("Esa carpeta no existe");
                    }

                case 3:
                    System.out.println("Dime el nombre de la carpeta que quieres mover: ");
                    String mover= sc.nextLine();


                default:
                break;
            }

        } while (!salir);

    }
}
