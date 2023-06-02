package co.unicauca.openmarket.client.main; 

import co.unicauca.openmarket.client.access.Factory;
import co.unicauca.openmarket.client.access.ICategoryAccess;
import co.unicauca.openmarket.client.access.ILocationAccess;
import co.unicauca.openmarket.client.access.IProductAccess;
import co.unicauca.openmarket.client.access.ISellerIncomeAccess;
import co.unicauca.openmarket.client.access.IShoppingAccess;
import co.unicauca.openmarket.client.access.IUserAccess;
import co.unicauca.openmarket.client.domain.services.CategoryService;
import co.unicauca.openmarket.client.domain.services.LocationService;
import co.unicauca.openmarket.client.domain.services.ProductService;
import co.unicauca.openmarket.client.domain.services.SellerIncomeService;
import co.unicauca.openmarket.client.domain.services.ShoppingService;
import co.unicauca.openmarket.client.domain.services.UserService;
import co.unicauca.openmarket.client.presentation.GUILogin;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IProductAccess repositoryProduct = Factory.getInstance().getProductRepository("default");
        ICategoryAccess repositoryCategory =  Factory.getInstance().getCategoryRepository("default");
        ILocationAccess repositoryLocation =  Factory.getInstance().getLocationRepository("default");
        IShoppingAccess repositoryShopping =  Factory.getInstance().getShoppingRepository("default");
        IUserAccess repositoryUser =  Factory.getInstance().getUserRepository("default");
        ISellerIncomeAccess repositorySellerIncome =  Factory.getInstance().getSellerIncomeRepository("default");
        
        
        ProductService productService = new ProductService(repositoryProduct);
        CategoryService categoryService = new CategoryService(repositoryCategory);
        LocationService locationService = new LocationService(repositoryLocation);
        ShoppingService shoppingService = new ShoppingService(repositoryShopping);
        UserService userService=new UserService(repositoryUser);
        SellerIncomeService sellerIncomeService = new SellerIncomeService(repositorySellerIncome);
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUILogin guiLogin = new GUILogin(userService,productService, categoryService,locationService, 
                        shoppingService, sellerIncomeService);
                guiLogin.setVisible(true);
                guiLogin.setLocationRelativeTo(null); //centrar panel
            }
        });
    }

//        GUICategory instance1=new GUICategory(categoryService);
//        instance1.setVisible(true);
//        instance1.setSize(596, 380);
//        instance1.setLocation(0,0);
//        GUIProducts instance2 = new GUIProducts(productService);
//        instance2.setVisible(true);
//        instance2.setSize(596, 380);
//        instance2.setLocation(590, 0);
            
}