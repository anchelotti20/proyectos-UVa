package sdis.utils;
// ESTA CLASE ES IGUAL QUE SESION CLIENTE QUE NOS HA DADO EL PROFE!!!!!!!!!!!!!!
public class Digest {
    private java.security.MessageDigest md;

    public Digest(String algoritmo) {

        try{
            this.md=java.security.MessageDigest.getInstance(algoritmo);
        }catch(java.security.NoSuchAlgorithmException e){
            throw new RuntimeException("Digest no se encuentra disponible "+algoritmo,e);
        }

    }


    public String generateHash(String texto) {
        md.update(texto.getBytes());

        byte[] digest = md.digest();

        StringBuilder hexString = new StringBuilder();

        for(byte b:digest){
            hexString.append(String.format("%02x",b));
        }

        return hexString.toString();

    }


    public static void main(String []args){
        /*
        TODO
        falta por imlementar ya que debemos usar el concurrent para la estructura de token y aun no lo tego puesto
        se podria investigar, cual de los 3 algoritmos es mas eficiente para explicarselo en la practica y quedar como unos señores
        sha-256 es el más seguro pero tarda entre un 20-30% de tiempo


         */

    }




}
