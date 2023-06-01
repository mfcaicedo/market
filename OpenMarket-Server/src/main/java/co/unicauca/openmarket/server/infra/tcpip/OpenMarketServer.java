package co.unicauca.openmarket.server.infra.tcpip;
import co.unicauca.openmarket.server.access.CategoryRepository;
import co.unicauca.openmarket.server.access.LocationRepository;
import co.unicauca.openmarket.server.access.ProductRepository;
import co.unicauca.openmarket.server.access.SellerIncomeRepository;
import co.unicauca.openmarket.server.access.ShoppingRepository;
import co.unicauca.openmarket.server.access.UserRepository;
import co.unicauca.openmarket.server.domain.services.CategoryService;
import co.unicauca.openmarket.server.domain.services.LocationService;
import co.unicauca.openmarket.server.domain.services.ProductService;
import co.unicauca.openmarket.server.domain.services.SellerIncomeService;
import co.unicauca.openmarket.server.domain.services.ShoppingService;
import co.unicauca.openmarket.server.domain.services.UserService;
import co.unicauca.strategyserver.infra.ServerSocketMultiThread;
import java.util.Scanner; 


public class OpenMarketServer {
     /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el puerto de escucha ");
        int port = teclado.nextInt();
        ServerSocketMultiThread myServer = new ServerSocketMultiThread(port);
        OpenMarketHandler myHandler = new OpenMarketHandler();
       
        //servicio de categorias
        myHandler.setService(new CategoryService(new CategoryRepository()));
        //Servicio de productos
        myHandler.setServiceProduct(new ProductService(new ProductRepository()));
        //servicio de usuarios
        myHandler.setServiceUser(new UserService(new UserRepository()));
        //servicio de location
        myHandler.setServiceLocation(new LocationService(new LocationRepository()));
        //servicio de Shopping
        myHandler.setServiceShopping(new ShoppingService(new ShoppingRepository()));
        //servicio de SellerIncome
        myHandler.setServiceSellerIncome(new SellerIncomeService(new SellerIncomeRepository()));
        
        myServer.setServerHandler(myHandler);
        myServer.startServer();
    }
}
