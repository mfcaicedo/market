package co.unicauca.openmarket.server.infra.tcpip;
import co.unicauca.openmarket.client.domain.Category;
import co.unicauca.openmarket.client.domain.Location;
import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.domain.SellerIncome;
import co.unicauca.openmarket.client.domain.Shopping;
import co.unicauca.openmarket.client.domain.User;
import co.unicauca.openmarket.commons.infra.Protocol;
import co.unicauca.openmarket.server.domain.services.CategoryService;
import co.unicauca.strategyserver.infra.ServerHandler;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.server.access.UserRepository;
import co.unicauca.openmarket.server.domain.services.LocationService;
import co.unicauca.openmarket.server.domain.services.ProductService;
import co.unicauca.openmarket.server.domain.services.SellerIncomeService;
import co.unicauca.openmarket.server.domain.services.ShoppingService;
import co.unicauca.openmarket.server.domain.services.UserService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class OpenMarketHandler extends ServerHandler {
    /**
     * Servicio de categoria
     * servicio de producto
     */
    private static CategoryService service;
    private static ProductService serviceProduc;
    private static LocationService serviceLocation;
    private static UserService serviceUser;
    private static ShoppingService serviceShopping;
    private static SellerIncomeService serviceSellerIncome;
    
    public OpenMarketHandler() {
    }

    /**
     * Procesar la solicitud que proviene de la aplicación cliente
     *
     */
    @Override
    public String processRequest(String requestJson) {
        Gson gson = new Gson();  
        Protocol protocolRequest;
        protocolRequest = gson.fromJson(requestJson, Protocol.class);
        String response="";
        switch (protocolRequest.getResource()) {
            case "category":
                if (protocolRequest.getAction().equals("get")) {
                    // Consultar una categoria
                    response = processGetCategory(protocolRequest);
                }

                if (protocolRequest.getAction().equals("post")) {
                    // Agregar una categoria    
                    response = processPostCategory(protocolRequest);
                }
                if (protocolRequest.getAction().equals("edit")){
                    // Editar categoria
                    response = processEditCategory(protocolRequest);
                } 
                if(protocolRequest.getAction().equals("delete")){
                    //Eliminar categoria
                    response = processDeleteCategory(protocolRequest);
                }
                if(protocolRequest.getAction().equals("listCategory")){
                    response = processListCategory();
                }
                if(protocolRequest.getAction().equals("getListCategory")){
                    response = processGetListCategory(protocolRequest);
                }
                break;
                
            case "product":
                if (protocolRequest.getAction().equals("get")) {
                    // Consultar un producto por id
                    response = processGetProduct(protocolRequest);
                }
                if (protocolRequest.getAction().equals("post")) {
                    // Agregar un nuevo producto  
                    response = processPostProduct(protocolRequest);
                }
                if (protocolRequest.getAction().equals("edit")){
                    // Editar un producto
                    response = processEditproduct(protocolRequest);
                }
                if (protocolRequest.getAction().equals("delete")){
                    // Eliminar un producto
                    response = processDeleteProduct(protocolRequest);
                }
                if (protocolRequest.getAction().equals("findAll")){
                    // listar productos
                    response = processfinAllproduct();
                }
                if (protocolRequest.getAction().equals("findAllBySearch")){
                    // Busqueda por nombre y descripcion en productos
                    response = processfindAllByNameAndDescription(protocolRequest);
                }
                if (protocolRequest.getAction().equals("findAllByUserSeller")){
                    // Busqueda por nombre y descripcion en productos
                    response = processfindAllByUserSeller(protocolRequest);
                }
                break;
            case "user":
                if (protocolRequest.getAction().equals("login")){
                    // autenticacion
                    response = processLoginUser(protocolRequest);
                }
                if (protocolRequest.getAction().equals("editScore")){
                    // listar compras
                    response = processScoreSeller(protocolRequest);
                }
                break;
            case "shopping":
                if (protocolRequest.getAction().equals("post")){
                    // crear una compra
                    response = processSaveShopping(protocolRequest);
                }
                if (protocolRequest.getAction().equals("findAllShopping")){
                    // listar compras
                    response = processFindAllShopping();
                }
                
                break;
            case "sellerIncome":
                if (protocolRequest.getAction().equals("post")){
                    // guargar ganancias
                    response = processSaveSellerIncome(protocolRequest);
                }
                if (protocolRequest.getAction().equals("findAllSellerIncome")){
                    // listar ganancias
                    response = processFindAllSellerIncome();
                }
                break;
            case "location":
                if (protocolRequest.getAction().equals("findAllLocation")){
                    // listar ubicaciones
                    response = processFindAllLocation();
                }
                break;
        }
        return response;
    }
    
    /**
     * Procesa la solicitud de consultar una categoria
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private String processGetCategory(Protocol protocolRequest) {
        // Extraer el id del primer parámetro
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue()) ;
        Category category = service.findById(id);
        if (category == null) {
            String errorJson = generateNotFoundErrorJson();
            return errorJson;
        } else {
            return objectToJSON(category);
        }
    }
  
    /**
     * Procesa la solicitud de agregar una categoria
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private String processPostCategory(Protocol protocolRequest) {
        Category category = new Category();
        // Reconstruir La categoria a partir de lo que viene en los parámetros
        category.setCategoryId(Long.parseLong(protocolRequest.getParameters().get(0).getValue()));
        category.setName(protocolRequest.getParameters().get(1).getValue());
        boolean response = getService().save(category);
        String respuesta=String.valueOf(response);
        return respuesta;
    }
     // Editar el name de la categoria
    private String processEditCategory(Protocol protocolRequest){
      
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        String name = protocolRequest.getParameters().get(1).getValue();
        Category newCategory = new Category(id, name);
        boolean response = service.edit(id, newCategory);
        String respuesta=String.valueOf(response);
        return respuesta;
    }
     // Eliminar una categoria
    private String processDeleteCategory(Protocol protocolRequest){
       
       Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
       boolean response = service.delete(id);
       String respuesta=String.valueOf(response);
       return respuesta;
    }
    // Lista de todas las categorias
    private String processListCategory(){
       List<Category> category;
       category = service.findAll();
       return objectToJSON(category);
    }
    
    // Buscar por nombre   
    private String processGetListCategory(Protocol protocolRequest){
           
       return "";
    }
    
    
    /**
     * Controlador que invoca el servicio de buscar por id Product
     * @param protocolRequest
     * @return 
     */
    private String processGetProduct(Protocol protocolRequest) {
        // Extraer el id del primer parámetro
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue()) ;
        Product producto = serviceProduc.findById(id);
        if (producto == null) {
            String errorJson = generateNotFoundErrorJson();
            return errorJson;
        } else {
            return objectToJSON(producto);
        }
    }
    /**
     * Controlador que invoca el servicio de guargar producto
     * @param protocolRequest
     * @return 
     */
    private String processPostProduct(Protocol protocolRequest) {
        
        Product producto = new Product();
        // Reconstruir el prodcucto  a partir de lo que viene en los parámetros
        //producto.setProductId(Long.parseLong(protocolRequest.getParameters().get(0).getValue()));
        producto.setName(protocolRequest.getParameters().get(0).getValue());
        producto.setDescription(protocolRequest.getParameters().get(1).getValue());
        producto.setPrice(Double.parseDouble(protocolRequest.getParameters().get(2).getValue()));
        System.out.println("PRUEBAAAA: "+protocolRequest.getParameters().get(3).getValue());
        producto.setState(protocolRequest.getParameters().get(3).getValue());
        producto.setStock(Integer.parseInt(protocolRequest.getParameters().get(4).getValue()));
        producto.setCategoryId(Long.parseLong(protocolRequest.getParameters().get(5).getValue()));
        producto.setLocation(Long.parseLong(protocolRequest.getParameters().get(6).getValue()));
        producto.setUserSellerId(Long.parseLong(protocolRequest.getParameters().get(7).getValue()));
        
        boolean response = this.getServiceProduc().save(producto);
        String respuesta=String.valueOf(response);
        return respuesta;
    }
    /**
     * Controlador que invoca el servicio de editar un producto (PUT)
     * @param protocolRequest
     * @return 
     */
    private String processEditproduct(Protocol protocolRequest){
       
        Product producto = new Product();
        
        producto.setProductId( Long.parseLong(protocolRequest.getParameters().get(0).getValue()));
        producto.setName(protocolRequest.getParameters().get(1).getValue());
        producto.setDescription(protocolRequest.getParameters().get(2).getValue());
        producto.setPrice(Double.parseDouble(protocolRequest.getParameters().get(3).getValue()));
        producto.setState(protocolRequest.getParameters().get(4).getValue());
        producto.setStock(Integer.parseInt(protocolRequest.getParameters().get(5).getValue()));
        producto.setCategoryId(Long.parseLong(protocolRequest.getParameters().get(6).getValue()));
        producto.setLocation(Long.parseLong(protocolRequest.getParameters().get(7).getValue()));
        producto.setUserSellerId(Long.parseLong(protocolRequest.getParameters().get(8).getValue()));
       
        boolean response = serviceProduc.edit(producto);
        String respuesta=String.valueOf(response);
        return respuesta;
    }
    /**
     * Controlador que invoca el servicio de listar todos los productos
     * @param protocolRequest
     * @return 
     */
    private String processfinAllproduct(){
        List<Product> products;
        products = serviceProduc.findAll();
       return objectToJSON(products);
    }
    /**
     * Controlador que invoca el servicio de buscar productos por descripcion y nombre
     * @param protocolRequest
     * @return 
     */
    private String processfindAllByNameAndDescription(Protocol protocolRequest) {
        String search = protocolRequest.getParameters().get(0).getValue();
        List<Product> products;
        products = serviceProduc.findAllByNameAndDescription(search);
        return objectToJSON(products);
    }
    
    private String processfindAllByUserSeller(Protocol protocolRequest){
        Long userSellerId = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        List<Product> products;
        products = serviceProduc.findByUserSeller(userSellerId);
        return objectToJSON(products);
    }
    
    /**
     * Controlador que invoca el servicio de eliminar producto por Id
     * @param protocolRequest
     * @return 
     */
    private String processDeleteProduct(Protocol protocolRequest){
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
       boolean response = serviceProduc.delete(id);
       String respuesta=String.valueOf(response);
       return respuesta;
    }
    /**
     * Controlador que invoca el servicio de login
     * @param protocolRequest
     * @return 
     */
    private String processLoginUser(Protocol protocolRequest){
        String username =  protocolRequest.getParameters().get(0).getValue();
        String password =  protocolRequest.getParameters().get(1).getValue();
        User user = new User();
        user = serviceUser.findByUsernameAndPassword(username, password);
      return objectToJSON(user);
    }
    private String processScoreSeller(Protocol protocolRequest){
        
        User user = new User();
        
        user.setUserId(Long.parseLong(protocolRequest.getParameters().get(0).getValue()));
        user.setScore(Float.parseFloat(protocolRequest.getParameters().get(1).getValue()));
        boolean response = serviceUser.edit(user);
        String respuesta=String.valueOf(response);
        return respuesta;
    }
    /**
     * Controlador que invoca el servicio de guardar la compra 
     * @param protocolRequest json con la información a guardar.
     * @return String cadena con la información de la comprar a guardar. 
     */
    private String processSaveShopping(Protocol protocolRequest){
        Shopping shopping = new Shopping();
        // Reconstruir el prodcucto  a partir de lo que viene en los parámetros
     //   shopping.setShoppingId(Long.parseLong(protocolRequest.getParameters().get(0).getValue()));
        shopping.setUserBuyerId(Long.parseLong(protocolRequest.getParameters().get(0).getValue()));
        shopping.setProductId(Long.parseLong(protocolRequest.getParameters().get(1).getValue()));
        
        boolean response = serviceShopping.save(shopping);
        String respuesta = String.valueOf(response);
        return respuesta;
    }
    /**
     * Controllador que invoca el servicio de listar todos las compras
     * @return 
     */
    private String processFindAllShopping(){
        List<Shopping> shoppings;
        shoppings = serviceShopping.findAll();
       return objectToJSON(shoppings);
    }
    /**
     * Controllador que invoca el servicio de guardar las ganancias
     * @return 
     */
    private String processSaveSellerIncome(Protocol protocolRequest){
        SellerIncome seller = new SellerIncome();
        // Reconstruir el prodcucto  a partir de lo que viene en los parámetros
        //seller.setSellerIncomeId(Long.parseLong(protocolRequest.getParameters().get(0).getValue()));
        seller.setIncome(Double.parseDouble(protocolRequest.getParameters().get(0).getValue()));
        seller.setShoppingId(Long.parseLong(protocolRequest.getParameters().get(1).getValue()));
        
        boolean response = serviceSellerIncome.save(seller);
        String respuesta = String.valueOf(response);
        return respuesta;
    }
    /**
     * Controllador que invoca el servicio de listar todos las ganancias
     * @return 
     */
    private String processFindAllSellerIncome(){
        List<SellerIncome> sellers;
        sellers = serviceSellerIncome.findAll();
       return objectToJSON(sellers);
    }
    
    private String processFindAllLocation(){
        List<Location> locations;
        locations = serviceLocation.findAll();
       return objectToJSON(locations);
    }
    
    /**
     * Genera un ErrorJson de cliente no encontrado
     *
     * @return error en formato json
     */
    private String generateNotFoundErrorJson() {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("404");
        error.setError("NOT_FOUND");
        error.setMessage("La clase no es encontrada. ID obvio no debe de existe");
        errors.add(error);

        Gson gson = new Gson();
        String errorsJson = gson.toJson(errors);

        return errorsJson;
    }
    
     /**
     * @return the service
     */
    public CategoryService getService() {
        return service;
    }
    public ProductService getServiceProduc() {
        return serviceProduc;
    }
    public void setServiceProduct(ProductService serviceProduc) {
        this.serviceProduc = serviceProduc;
    } 
    /**
     * @param service the service to set
     */
    public void setService(CategoryService service) {
        this.service = service;
    } 

    public LocationService getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(LocationService serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public UserService getServiceUser() {
        return serviceUser;
    }

    public void setServiceUser(UserService serviceUser) {
        this.serviceUser = serviceUser;
    }

    public ShoppingService getServiceShopping() {
        return serviceShopping;
    }

    public void setServiceShopping(ShoppingService serviceShopping) {
        this.serviceShopping = serviceShopping;
    }

    public SellerIncomeService getServiceSellerIncome() {
        return serviceSellerIncome;
    }

    public void setServiceSellerIncome(SellerIncomeService serviceSellerIncome) {
        this.serviceSellerIncome = serviceSellerIncome;
    }
    
    
    
   
}
