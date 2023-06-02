/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package co.unicauca.openmarket.client.presentation;

import co.unicauca.openmarket.client.domain.Category;
import co.unicauca.openmarket.client.domain.Location;
import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.domain.User;
import co.unicauca.openmarket.client.domain.services.CategoryService;
import co.unicauca.openmarket.client.domain.services.LocationService;
import co.unicauca.openmarket.client.domain.services.ProductService;
import co.unicauca.openmarket.client.infra.Messages;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Personal
 */
public class GuiUserSeller extends javax.swing.JFrame {
    
    private User user;
    private ProductService productService;
    private CategoryService categoryService;
    private LocationService locationService;
    int FilaSelec = -1;

    
    List<Location> lstLocations;
    List<Category> lstCategory;
    
    /**
     * Creates new form GuiUserSeller
     */
    public GuiUserSeller(User user, ProductService productService, CategoryService categoryService, LocationService locationService) throws Exception {
        
        initComponents();
        this.user = user;
        this.productService = productService;
        this.categoryService= categoryService;
        this.locationService= locationService;
        this.lstLocations= new ArrayList<>();
        this.lstCategory= new ArrayList<>();
        getCategoryAndLocation();
        llenarTablaProductos();
    }
    
    private void llenarTablaProductos() throws Exception {
            List<Product> lstProducts = productService.findByUserSeller(user.getUserId());
            Object matriz [][] = new Object [lstProducts.size()][8];
            for(int i=0;i<lstProducts.size();i++) {
                matriz [i][0] = lstProducts.get(i).getProductId();
                matriz [i][1] = lstProducts.get(i).getName();
                matriz [i][2] = lstProducts.get(i).getDescription();
                matriz [i][3] = lstProducts.get(i).getPrice();
                matriz [i][4] = lstProducts.get(i).getState();
                matriz [i][5] = lstProducts.get(i).getStock();
                Category category = findCategory(lstProducts.get(i).getCategoryId());
                matriz [i][6] = category.getName();
                Location location = findLocation(lstProducts.get(i).getLocation());
                matriz [i][7] = location.getPlace();
            }
            this.jTableProductos.setModel(new DefaultTableModel(
                                    matriz,
                new String [] {"Codigo","Nombre","Descripcion","Precio","Estado",
                    "Cantidad","Categoria","Ubicacion"}
            )); 
    }
    
    /**
     * Metodo para obtener los valores de una fila de la tabla correspondientes a un producto. 
     * @return 
     */
    public Product productSelected() {//posible modificacion
        //RECUPERAMOS LA INFORMACION DE LA FILA SECCIONADA EN LA TABLA

        FilaSelec = this.jTableProductos.getSelectedRow();
        Product product = new Product();
        if (FilaSelec >= 0) {
            product.setProductId(Long.parseLong(this.jTableProductos.getValueAt(FilaSelec, 0).toString()));
            product.setName(this.jTableProductos.getValueAt(FilaSelec, 1).toString());
            product.setDescription(this.jTableProductos.getValueAt(FilaSelec, 2).toString());
            product.setPrice(Double.parseDouble(this.jTableProductos.getValueAt(FilaSelec, 3).toString()));//recupero la informacion de la tabla 
            product.setState(this.jTableProductos.getValueAt(FilaSelec, 4).toString());//recupero la informacion de la tabla 
            product.setStock(Integer.parseInt(this.jTableProductos.getValueAt(FilaSelec, 5).toString()));//recupero la informacion de la tabla 
            Category category = lstCategory.stream().filter(x -> x.getName().equals(this.jTableProductos.getValueAt(FilaSelec, 6).toString())).findFirst().orElse(null);
            product.setCategoryId(category.getCategoryId());//recupero la informacion de la tabla 
            Location location = lstLocations.stream().filter(x -> x.getPlace().equals(this.jTableProductos.getValueAt(FilaSelec, 7).toString())).findFirst().orElse(null);
            product.setLocation((long)location.getLocationId());//recupero la informacion de la tabla 
            product.setUserSellerId(user.getUserId());

        } else {
            product=null;
        }
        
        return product;
    }
    
    private Location findLocation(long locationId){
       
        Location location = null;
        for (int i = 0; i < this.lstLocations.size(); i++) {
            if(this.lstLocations.get(i).getLocationId() == (int) locationId){
                location= this.lstLocations.get(i);
                break;
            }
        }
        return location;
    }
    
    private Category findCategory(long categoryId){
        Category category = null;
        for (int i = 0; i < this.lstCategory.size(); i++) {
            if(this.lstCategory.get(i).getCategoryId()==  categoryId){
                category= this.lstCategory.get(i);
                break;
            }
        }
        return category;
    }
    
    private void getCategoryAndLocation(){
        this.lstLocations= locationService.findAll();
        this.lstCategory= categoryService.findAllCategories();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelOptions = new javax.swing.JPanel();
        jButtonAgregar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        JButtonEliminar = new javax.swing.JButton();
        jButtonSuspender = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanelCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButtonLlenarTabla = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelOptions.setBackground(new java.awt.Color(0, 157, 243));

        jButtonAgregar.setBackground(new java.awt.Color(0, 70, 144));
        jButtonAgregar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButtonAgregar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAgregar.setText("Agregar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        jButtonModificar.setBackground(new java.awt.Color(0, 70, 144));
        jButtonModificar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButtonModificar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        JButtonEliminar.setBackground(new java.awt.Color(205, 40, 56));
        JButtonEliminar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        JButtonEliminar.setForeground(new java.awt.Color(255, 255, 255));
        JButtonEliminar.setText("Eliminar");
        JButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonEliminarActionPerformed(evt);
            }
        });

        jButtonSuspender.setBackground(new java.awt.Color(0, 70, 144));
        jButtonSuspender.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButtonSuspender.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSuspender.setText("Suspender");
        jButtonSuspender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuspenderActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Opciones");

        javax.swing.GroupLayout jPanelOptionsLayout = new javax.swing.GroupLayout(jPanelOptions);
        jPanelOptions.setLayout(jPanelOptionsLayout);
        jPanelOptionsLayout.setHorizontalGroup(
            jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSuspender, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addComponent(JButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelOptionsLayout.setVerticalGroup(
            jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOptionsLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jButtonAgregar)
                .addGap(18, 18, 18)
                .addComponent(jButtonModificar)
                .addGap(18, 18, 18)
                .addComponent(jButtonSuspender)
                .addGap(18, 18, 18)
                .addComponent(JButtonEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelCenter.setBackground(new java.awt.Color(0, 157, 243));

        jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Descripcion", "Precio ", "Estado", "Cantidad", "Categoria", "Ubicacion"
            }
        ));
        jScrollPane1.setViewportView(jTableProductos);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Listado de productos disponibles");

        jButtonLlenarTabla.setBackground(new java.awt.Color(0, 70, 144));
        jButtonLlenarTabla.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButtonLlenarTabla.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLlenarTabla.setText("Actualizar Tabla");
        jButtonLlenarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLlenarTablaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCenterLayout = new javax.swing.GroupLayout(jPanelCenter);
        jPanelCenter.setLayout(jPanelCenterLayout);
        jPanelCenterLayout.setHorizontalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                    .addGroup(jPanelCenterLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jButtonLlenarTabla)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelCenterLayout.setVerticalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLlenarTabla)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(0, 157, 243));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MARKET PLACE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Acción del boton de agregar un producto 
     * @param evt evento que dispara la acción. 
     */
    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        // TODO add your handling code here:
       
        try {
            callPanelAddAndUpdateProduct(null);
            llenarTablaProductos();
        } catch (Exception ex) {
            Logger.getLogger(GuiUserSeller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButtonAgregarActionPerformed
    
    private void callPanelAddAndUpdateProduct(Product objproduct){
//        JPanelAddProduct objJPAddProduct = new JPanelAddProduct(this.categoryService, this.locationService, this.user);
//        this.getContentPane().remove(this.jPanelCenter);
//        this.getContentPane().add(objJPAddProduct);
        
        JDialogAddProduct objJDAddProduct = new JDialogAddProduct(this,false, categoryService,locationService,user, productService, objproduct);
        objJDAddProduct.setVisible(true);
        objJDAddProduct.setLocationRelativeTo(null);
        
    }
    
    private void disableProduct(Product product) throws Exception{
        int result = JOptionPane.showConfirmDialog(this, " Deseas deshabilitar el producto? ", "Deshabilitar Producto", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            product.setState("Suspendido");
            if(this.productService.editProduct(product)){
                llenarTablaProductos();
                Messages.showMessageDialog("Producto Actualizado exitosamente", "Atención");
            }else{
                Messages.showMessageDialog("ERROR al actualizar el producto", "Atención");
            }
            
        } 
    }
    
    private void deleteProduct(Product product) throws Exception{
        int result = JOptionPane.showConfirmDialog(this, " Deseas eliminar el producto? ", "Deshabilitar Producto", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            
            if(this.productService.deleteProduct(product.getProductId())){
                llenarTablaProductos();
                Messages.showMessageDialog("Producto Eliminado exitosamente", "Atención");
            }else{
                Messages.showMessageDialog("ERROR al eliminar el producto", "Atención");
            }
            
        } 
    }
    
    
    
    /**
     * Acción del boton de modificar un producto 
     * @param evt evento que dispara la acción. 
     */
    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        
            Product productSelect = this.productSelected();
            try {
                if(productSelect!=null){
                callPanelAddAndUpdateProduct(productSelect);
                
                    llenarTablaProductos();
                }else{
                Messages.showMessageDialog("ERROR! no ha seleccionado ninguna fila de la tabla", "Atención");
                }     
            }catch (Exception ex) {
                    Logger.getLogger(GuiUserSeller.class.getName()).log(Level.SEVERE, null, ex);
                }
    }//GEN-LAST:event_jButtonModificarActionPerformed
    
    /**
     * Acción del boton de suspender un producto 
     * @param evt evento que dispara la acción. 
     */
    private void jButtonSuspenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuspenderActionPerformed
        Product productSelect = this.productSelected();
        try {
            if(productSelect!=null){
                disableProduct(productSelect);
            }else{
                Messages.showMessageDialog("ERROR! no ha seleccionado ninguna fila de la tabla", "Atención");
            }
        }catch (Exception ex) {
                Logger.getLogger(GuiUserSeller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButtonSuspenderActionPerformed
    
     
    /**
     * Acción del boton de eliminar un producto 
     * @param evt evento que dispara la acción. 
     */
    private void JButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonEliminarActionPerformed
        Product productSelect = this.productSelected();
        try {
            if(productSelect!=null){
                deleteProduct(productSelect);
            }else{
                Messages.showMessageDialog("ERROR! no ha seleccionado ninguna fila de la tabla", "Atención");
            }
        }catch (Exception ex) {
                Logger.getLogger(GuiUserSeller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JButtonEliminarActionPerformed

    private void jButtonLlenarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLlenarTablaActionPerformed
        try {
            llenarTablaProductos();
        } catch (Exception ex) {
            Logger.getLogger(GuiUserSeller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonLlenarTablaActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GuiUserSeller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GuiUserSeller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GuiUserSeller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GuiUserSeller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GuiUserSeller().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButtonEliminar;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonLlenarTabla;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonSuspender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProductos;
    // End of variables declaration//GEN-END:variables
}
