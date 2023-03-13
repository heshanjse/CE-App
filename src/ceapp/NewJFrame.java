package ceapp;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;
import com.github.anastaciocintra.escpos.barcode.BarCode;
import com.github.anastaciocintra.escpos.image.BitonalThreshold;
import com.github.anastaciocintra.escpos.image.CoffeeImage;
import com.github.anastaciocintra.escpos.image.EscPosImage;
import com.github.anastaciocintra.escpos.image.RasterBitImageWrapper;
import com.github.anastaciocintra.output.PrinterOutputStream;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import static java.awt.SystemColor.text;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.Sides;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.Node;

public class NewJFrame extends javax.swing.JFrame {

    Map<String, Object> product_map = new HashMap<>();

    public NewJFrame() {
        initComponents();
        initProductObjectmap();
        rdb_percentage.setSelected(true);
        rdb_fixed.setSelected(false);
        rdb_card.setSelected(false);
        rdb_cash.setSelected(true);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("CElogo.png")).getImage().getScaledInstance(256, 345, Image.SCALE_DEFAULT));
        lbl_logo.setIcon(imageIcon);
//        lbl_logo.setText("AAAA");
    }

    public void initProductObjectmap() {
        Product pro1 = new Product("Floral Puff Sleeve Mini Dress", "CE0020", 4480.00);
        Product pro2 = new Product("Strap it Back Printed Mini Dress", "CE0030", 4690.00);
        Product pro3 = new Product("Flutter Sleeve Low-cut Crop top", "CE0029", 3490.00);
        Product pro4 = new Product("V-neck Sleeveless Line Top", "CE0028", 3490.00);
        Product pro5 = new Product("Puff Sleeve Square Neck Linen Dress", "CE0027", 4690.00);
        Product pro6 = new Product("Triangle Back Linen Crop Top", "CE0025", 3890.00);
        Product pro7 = new Product("High-Low Printed Halter Dress", "CE0024", 5390.00);
        Product pro8 = new Product("Triangle Hem Surplice Midi Dress", "CE0023", 4580.00);
        Product pro9 = new Product("Spagatti Strap Floral Mini Dress", "CE0021", 4380.00);
        Product pro10 = new Product("Surplice Neck Linen Midi Dress", "CE0022", 4890.00);
        Product pro11 = new Product("Front Knot Linen Top", "CE0007", 3890.00);
        Product pro12 = new Product("Floral Printed Sleeveless Summer Top", "CE0009", 2890.00);
        Product pro13 = new Product("Printed Denim Cami Top", "CE0008", 2890.00);
        Product pro14 = new Product("Front Knot Printed Top", "CE0006", 3490.00);
        Product pro15 = new Product("Long Sleeve Peplum Top", "CE0005", 4690.00);
        Product pro16 = new Product("Indian Block Printed Sleeve Top", "CE0004", 3890.00);
        Product pro17 = new Product("Oversize Button Detailed Shirt", "CE0003", 4500.00);
        Product pro18 = new Product("Linen Button Down Midi Dress", "CE0002", 4690.00);
        Product pro19 = new Product("Puff sleeve button detailed maxi dress", "CE0001", 6690.00);

        product_map.put("CE0028", pro4);
        product_map.put("CE0027", pro5);
        product_map.put("CE0025", pro6);
        product_map.put("CE0024", pro7);
        product_map.put("CE0023", pro8);
        product_map.put("CE0021", pro9);
        product_map.put("CE0022", pro10);
        product_map.put("CE0007", pro11);
        product_map.put("CE0009", pro12);
        product_map.put("CE0008", pro13);
        product_map.put("CE0006", pro14);
        product_map.put("CE0005", pro15);
        product_map.put("CE0004", pro16);
        product_map.put("CE0003", pro17);
        product_map.put("CE0002", pro18);
        product_map.put("CE0020", pro1);
        product_map.put("CE0030", pro2);
        product_map.put("CE0029", pro3);
        product_map.put("CE0001", pro19);

    }

    public void addTable(String Name, Double Price) {

        String Qty = JOptionPane.showInputDialog("Enter Qty");
        Double tqty = Double.valueOf(Qty);
        Double Tot_Price = Price * tqty;

        DecimalFormat df = new DecimalFormat("00.00");
        String d11 = df.format(Tot_Price);

        DefaultTableModel dt = (DefaultTableModel) tbl_main.getModel();

        Vector v = new Vector();
        v.add(Name);
        v.add(Qty);
        v.add(d11);
        dt.addRow(v);

        cart_cal();
    }

    public void cart_cal() {

        int numofrow = tbl_main.getRowCount();
        double total = 0;
        for (int i = 0; i < numofrow; i++) {
            double value = Double.valueOf(tbl_main.getValueAt(i, 2).toString());
            total += value;

        }

        DecimalFormat df = new DecimalFormat("00.00");
        String d1 = df.format(total);
        lbl_subtotal.setText(d1);

    }

    public static String modifyString(String inputString, int length) {
        // If the length of the string is already equal to or greater than the given length, 
        // we return a substring of the input string up to the given length
        if (inputString.length() >= length - 3) {
            return inputString.substring(0, length) + "...";
        }

        // Otherwise, we add spaces to the front of the string until its length is equal to the given length
        while (inputString.length() < length) {
            inputString = " " + inputString;
        }

        return inputString;
    }

    public void printtest() {
        System.out.println("CELENE ESTILO");
        System.out.println("\n\n");
        System.out.println("No 28, palanwatte,");
        System.out.println("pannipitiya, Sri Lanka.");
        System.out.println("+94 70728 2266");
        System.out.println("WWW.CELENEESTILO.COM");
        System.out.println("    -------------------------------------------- ");
        System.out.println("    NAME        PRICE       QTY           AMOUNT");
        System.out.println("    -------------------------------------------- ");

        DefaultTableModel df = (DefaultTableModel) tbl_main.getModel();
        DecimalFormat dformat = new DecimalFormat("00.00");
        for (int i = 0; i < tbl_main.getRowCount(); i++) {
            String name = df.getValueAt(i, 0).toString();
            int qty = Integer.parseInt(df.getValueAt(i, 1).toString());
            double price = Double.parseDouble(df.getValueAt(i, 2).toString());
            double discount = Double.parseDouble(df.getValueAt(i, 3).toString());

            double amountd = qty * price;
            String amount = dformat.format(amountd);
            String sprice = String.valueOf(dformat.format(price));
            String displayamount = String.valueOf(modifyString(amount, 18));
            String displayqty = modifyString(String.valueOf(qty), 8);
            String displayPrice = modifyString(sprice, 22);
            if (!name.isEmpty() && name.length() > 40) {
                name = name.substring(0, 40);
                name = "    " + name + "... ";
            } else {
                name = "    " + name;
            }
            System.out.println(name);
            System.out.println(displayPrice + displayqty + displayamount);

            if (discount < 0) {
                System.out.println("    DISCOUNT" + modifyString(String.valueOf(dformat.format(discount)), 36));
            }

        }

        String subTotal = lbl_subtotal.getText();
        double orderdiscount = Double.parseDouble(txt_OrderDiscount.getText());
        String balance = lbl_balance.getText();
        String paymentType = rdb_card.isSelected() ? "CARD" : "CASH";
        String totalQty = lbl_itemcount.getText();
        double payment = Double.parseDouble(txt_payment.getText());
        String Grandtotal = lbl_grandTotal.getText();
        String saving = lbl_totalDiscount.getText();
        Date date = new Date();
        System.out.println("    --------------------------------------------");
        if (orderdiscount>0) {
            System.out.println("    SUB TOTAL" + modifyString(subTotal, 35));
            System.out.println("    DISCOUNT" + modifyString("-"+dformat.format(orderdiscount), 36));
            System.out.println("    NET TOTAL" + modifyString(Grandtotal, 35));

        } else {
            System.out.println("    NET TOTAL" + modifyString(Grandtotal, 35));
        }
        if (rdb_card.isSelected()) {
            System.out.println("    CARD" + modifyString(Grandtotal, 40));

        } else {
            System.out.println("    CASH" + modifyString(dformat.format(payment), 40));

        }
        System.out.println("    --------------------------------------------");
        System.out.println("    BALANCE" + modifyString(balance, 37));
        System.out.println("\n");
        System.out.println("    NO Of Items " + modifyString(totalQty, 30));

        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String strDate = formatter.format(date);
        System.out.println("    Date  " + modifyString(strDate, 36));
        System.out.println("YOUR SAVINGS " + saving);
        System.out.println("    --------------------------------------------");
        System.out.println("CELENE ESTILO");
        System.out.println("THANK YOU COME AGAIN");

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

    }

    public void printerText() {

        // get the printer service by name passed on command line...
        //this call is slow, try to use it only once and reuse the PrintService variable.
//        String[] s;
//        s = PrinterOutputStream.getListPrintServicesNames();
//        String ss = Arrays.toString(s);
//        System.out.println(Arrays.toString(s));
//        bill.setText(ss);
        PrintService printService = PrinterOutputStream.getPrintServiceByName("EPSON TM-T82 Receipt");
        EscPos escpos;
        try {
            escpos = new EscPos(new PrinterOutputStream(printService));

            Style title = new Style()
                    .setFontSize(Style.FontSize._3, Style.FontSize._3)
                    .setJustification(EscPosConst.Justification.Center);
            Style address = new Style()
                    .setFontSize(Style.FontSize._1, Style.FontSize._1)
                    .setJustification(EscPosConst.Justification.Center);

            Style subtitle = new Style(escpos.getStyle())
                    .setBold(true)
                    .setUnderline(Style.Underline.OneDotThick);
            Style bold = new Style(escpos.getStyle())
                    .setBold(true);

//            RasterBitImageWrapper imageWrapper = new RasterBitImageWrapper();
//        BufferedImage  githubBufferedImage = ImageIO.read(new File("../img/CElogo.png"));
//        EscPosImage escposImage = new EscPosImage((CoffeeImage) githubBufferedImage, new BitonalThreshold()); 
            // print smile image...
            DefaultTableModel df = (DefaultTableModel) tbl_main.getModel();
            DecimalFormat dformat = new DecimalFormat("00.00");

            escpos.writeLF(title, "CELENE ESTILO")
                    .feed(2)
                    .writeLF(address, "No 28, palanwatte,")
                    .writeLF(address, "pannipitiya, Sri Lanka.")
                    .writeLF(address, "+94 70728 2266")
                    .writeLF(address, "WWW.CELENEESTILO.COM")
                    .feed(1)
                    .writeLF("    -------------------------------------------- ")
                    .writeLF("    NAME        PRICE       QTY           AMOUNT")
                    .writeLF("    -------------------------------------------- ");

            for (int i = 0; i < tbl_main.getRowCount(); i++) {
                String name = df.getValueAt(i, 0).toString();
                int qty = Integer.parseInt(df.getValueAt(i, 1).toString());
                double price = Double.parseDouble(df.getValueAt(i, 2).toString());
                double discount = Double.parseDouble(df.getValueAt(i, 3).toString());

                double amountd = qty * price;
                String amount = dformat.format(amountd);
                String sprice = String.valueOf(dformat.format(price));
                String displayamount = String.valueOf(modifyString(amount, 18));
                String displayqty = modifyString(String.valueOf(qty), 8);
                String displayPrice = modifyString(sprice, 22);
                if (!name.isEmpty() && name.length() > 40) {
                    name = name.substring(0, 40);
                    name = "    " + name + "... ";
                } else {
                    name = "    " + name;
                }

                escpos.writeLF(name);
                escpos.writeLF(displayPrice + displayqty + displayamount);
                if (discount < 0) {
                    escpos.writeLF("    DISCOUNT" + modifyString(String.valueOf(dformat.format(discount)), 36));
                }
//                escpos.writeLF("    --------------------------------------------  ");
//
//                stringBuilder.append(name + "... \n");
//                stringBuilder.append("\t" + qt + "  " + prc + "" + amount + " \n");

            }
            String subTotal = lbl_subtotal.getText();
            double orderdiscount = Double.parseDouble(txt_OrderDiscount.getText());
            String balance = lbl_balance.getText();
            String paymentType = rdb_card.isSelected() ? "CARD" : "CASH";
            String totalQty = lbl_itemcount.getText();
            double payment = Double.parseDouble(txt_payment.getText());
            String Grandtotal = lbl_grandTotal.getText();
            String saving = lbl_totalDiscount.getText();
            Date date = new Date();

//            .writeLF("    V - neck Sleeveless Line Top")
//                    .writeLF("               4000.00       2           8000.00")
            escpos.writeLF("    --------------------------------------------");
            //                    .feed(1)
//            escpos.writeLF("    SUB TOTAL                            8000.00");
//            escpos.writeLF("    DISCOUNT                             0000.00");
//            escpos.writeLF("    PAYMENT                             10000.00");
//            escpos.writeLF("    --------------------------------------------    ");
//            escpos.writeLF("    BALANCE                              2000.00");
            escpos.writeLF("    --------------------------------------------");
            if (orderdiscount>0) {
                escpos.writeLF("    SUB TOTAL" + modifyString(subTotal, 35));
                escpos.writeLF("    DISCOUNT" + modifyString("-"+dformat.format(orderdiscount), 36));
                escpos.writeLF("    NET TOTAL" + modifyString(Grandtotal, 35));
            } else {
                escpos.writeLF("    NET TOTAL" + modifyString(Grandtotal, 35));
            }
            if (rdb_card.isSelected()) {
                escpos.writeLF("    CARD" + modifyString(Grandtotal, 40));
            } else {
                escpos.writeLF("    CASH" + modifyString(dformat.format(payment), 40));
            }
            escpos.writeLF("    --------------------------------------------");
            escpos.writeLF("    BALANCE" + modifyString(balance, 37));
            escpos.feed(1);

            escpos.writeLF("    NO Of Items " + modifyString(totalQty, 30));
            SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            String strDate = formatter.format(date);
            escpos.writeLF("    Date  " + modifyString(strDate, 36));
//            escpos.writeLF("    PAYMENT");

            //                    .write("Client: ");
            //                    .writeLF(subtitle, "John Doe")
            //                    .feed(3)
            ////                    .writeLF("Cup of coffee                      $1.00")
            //                    .writeLF("Botle of water                     $0.50")
            //                    .writeLF("----------------------------------------")
            //                    .feed(2)
            //                    .writeLF(bold, 
            //                             "TOTAL                              $1.50")
            escpos.writeLF(address, "YOUR SAVINGS " + saving);
            escpos.writeLF("    --------------------------------------------");
            escpos.writeLF(address, "CELENE ESTILO");
            escpos.writeLF(address, "THANK YOU COME AGAIN");
            escpos.feed(8);
            escpos.cut(EscPos.CutMode.FULL);

            escpos.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    private void bill_print() {

        try {
            if (tbl_main.getRowCount() > 0) {
                //printtest();
                printerText();
            }

//            EscPos escpos = new EscPos(outputStream);
//            StringBuilder header = new StringBuilder();
//            StringBuilder stringBuilder = new StringBuilder();
//            StringBuilder footer = new StringBuilder();
//
//
//            header.append("      CELENE ESTILO \n");
//            header.append("      No 28, pelawatta, \n");
//            header.append("      pannipitiya, Sri Lanka.\n");
//            header.append("      +94 70728 2266 \n");
//            header.append("----------------------------\n");
//            header.append(" Iteam Qty Price Amount\n");
//            header.append("----------------------------\n");
//
//            DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
//            for (int i = 0; i < jTable1.getRowCount(); i++) {
//
//                String name = df.getValueAt(i, 0).toString();
//                String qt = df.getValueAt(i, 1).toString();
//                String prc = df.getValueAt(i, 2).toString();
//                Float amountd = Integer.parseInt(qt)*Float.parseFloat(prc);
//                        
//                String amount = amountd.toString();
//                name = name.substring(0, 25);
//                stringBuilder.append(name + "... \n");
//                stringBuilder.append("\t" + qt + "  " + prc + ""+ amount+" \n");
//
//            }
//            stringBuilder.append("----------------------------\n");
//            stringBuilder.append(" SubTotal :\t" + subtotal.getText() + "\n");
//            stringBuilder.append(" Discount :\t" + discount.getText() + "% \n");
//            stringBuilder.append(" Total    :\t" + Total.getText() + "\n");
//            stringBuilder.append(" Cash     :\t" + Cash.getText() + "\n");
//            stringBuilder.append(" Change   :\t" + Bal.getText() + "\n");
//            footer.append("====================================\n");
//            footer.append("        CELENE ESTILO      " + "\n");
//
//
//            bill.setText(stringBuilder.toString());
//            boolean show = true;
//            MessageFormat headerFormat = new MessageFormat(header.toString());  //sets the page number
//            MessageFormat footerFormat = new MessageFormat(footer.toString());
//
//            PrintRequestAttributeSet attr_set = new HashPrintRequestAttributeSet();
//            attr_set.add(MediaSizeName.ISO_A8);
//            attr_set.add(Sides.ONE_SIDED);
//            bill.print(headerFormat, footerFormat, show, null, attr_set, show);
//                        bill.setText(" CELENE ESTILO \n");
//            bill.setText(bill.getText() + " No 28, palanwatte, \n");
//            bill.setText(bill.getText() + " pannipitiya, Srilanka.\n");
//            bill.setText(bill.getText() + " +94 70728 2266 \n");
//            bill.setText(bill.getText() + "----------------------------------------------------------------\n");
//            bill.setText(bill.getText() + " Iteam\tQty\tPrice\n");
//            bill.setText(bill.getText() + "----------------------------------------------------------------\n");
//            
//            DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
//            for (int i = 0; i < jTable1.getRowCount(); i++) {
//                
//                String name = df.getValueAt(i, 0).toString();
//                String qt = df.getValueAt(i, 1).toString();
//                String prc = df.getValueAt(i, 2).toString();
//                
//                bill.setText(bill.getText() + name+"\t"+qt+"\t"+prc+" \n");
//                
//            }
//            bill.setText(bill.getText() + "----------------------------------------------------------------\n");
//            bill.setText(bill.getText() + "SubTotal :\t"+subtotal.getText()+"\n");
//            bill.setText(bill.getText() + "Discount :\t"+dis.getText()+"% \n");
//            bill.setText(bill.getText() + "Total    :\t"+Total.getText()+"\n");
//            bill.setText(bill.getText() + "Cash     :\t"+Cash.getText()+"\n");
//            bill.setText(bill.getText() + "Change   :\t"+Bal.getText()+"\n");
//            bill.setText(bill.getText() + "====================================\n");
//            bill.setText(bill.getText() +"  CELENE ESTILO"+"\n");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void emptyAdditemset() {
        txt_quantity.setText("1");
        lbl_name.setText("");
        lbl_unitprice.setText("");
        txt_itemDiscount.setText("0");
        lbl_discountAmount.setText("0.00");
        lbl_itemsubTotal.setText("0.00");
        rdb_percentage.setSelected(true);
        rdb_fixed.setSelected(false);
    }

    private void completeOrder() {
        lbl_itemcount.setText("0");
        lbl_subtotal.setText("0.00");
        txt_OrderDiscount.setText("0.00");
        lbl_totalDiscount.setText("0.00");
        lbl_grandTotal.setText("0.00");
        txt_payment.setText("0.00");
        lbl_balance.setText("0.00");
        rdb_cash.setSelected(true);
        rdb_card.setSelected(false);
    }

    private void calculateDiscountAndSubTotal() {
        double unit_price = Double.parseDouble(lbl_unitprice.getText());
        int qty = Integer.parseInt(txt_quantity.getText());
        double Tot_Price = unit_price * qty;
        double discountAmount = 0;
        if (rdb_percentage.isSelected()) {
            discountAmount = Tot_Price * Double.parseDouble(txt_itemDiscount.getText()) * 0.01;
        } else if (rdb_fixed.isSelected()) {
            discountAmount = Double.parseDouble(txt_itemDiscount.getText());
        }
        lbl_discountAmount.setText("-" + String.format("%.02f", discountAmount));
        double bal = Tot_Price - discountAmount;
        DecimalFormat df = new DecimalFormat("00.00");
        lbl_itemsubTotal.setText(String.valueOf(df.format(bal)));
    }

    private void calculategrandTotal() {
        int rows = tbl_main.getRowCount();
//        lbl_itemcount.setText(String.valueOf(rows));
        DecimalFormat df = new DecimalFormat("00.00");
        double subtotal = 0.00;
        int totalItems = 0;
        double totalDisocunt = 0.00;
        double grandTotal = 0.00;
        double balance = 0.00;
        for (int i = 0; i < rows; i++) {
//            System.out.println(tbl_main.getValueAt(i, 0));
//            System.out.println(tbl_main.getValueAt(i, 1));
            int qunatity = Integer.parseInt(tbl_main.getValueAt(i, 1).toString());
            totalItems += qunatity;
//            System.out.println(tbl_main.getValueAt(i, 2));
//            System.out.println(tbl_main.getValueAt(i, 3));
            double discount = Double.parseDouble(tbl_main.getValueAt(i, 3).toString());
            totalDisocunt -= discount;
//            System.out.println(tbl_main.getValueAt(i, 4));
            double subttl = Double.parseDouble(tbl_main.getValueAt(i, 4).toString());
            subtotal += subttl;
        }
        lbl_itemcount.setText(String.valueOf(totalItems));
        lbl_subtotal.setText(String.valueOf(df.format(subtotal)));
        double orderDisocunt = Double.parseDouble(txt_OrderDiscount.getText());
        lbl_totalDiscount.setText(String.valueOf("-" + df.format(totalDisocunt + orderDisocunt)));
        lbl_grandTotal.setText(String.valueOf(df.format(subtotal - orderDisocunt)));
        double payment = Double.parseDouble(txt_payment.getText());
        if (payment > 0) {
            lbl_balance.setText(String.valueOf(df.format(payment - (subtotal - orderDisocunt))));
        }
    }

// public void printNode(Node node) {
//
//      Printer printer = Printer.getDefaultPrinter();
//
//      PrinterJob printerJob = PrinterJob.createPrinterJob(printer);
//
//      Paper paper = PrintHelper.createPaper("Roll80", 80, 590, Units.MM);
//
//      PageLayout pageLayout = printerJob.getPrinter().createPageLayout(paper, PageOrientation.PORTRAIT, 0, 0, 0, 0);
//
//      double height = node.getLayoutBounds().getHeight();
//
//      System.out.println("Height: " + height);
//
//      double scale = 0.791;
//
//      node.getTransforms().add(new Scale(scale, scale));
//
//      if (printerJob != null) {
//          boolean success = printerJob.printPage(pageLayout, node);
//          if (success) {
//             printerJob.endJob();Tab
//             System.exit(0);
//          }
//      }
//
//}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_logo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txt_itemCode = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_quantity = new javax.swing.JTextField();
        lbl_discount = new javax.swing.JLabel();
        txt_itemDiscount = new javax.swing.JTextField();
        btn_remove = new javax.swing.JButton();
        btn_additem = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lbl_unitprice = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbl_name = new javax.swing.JLabel();
        lbl_itemsubTotal = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        rdb_percentage = new javax.swing.JRadioButton();
        rdb_fixed = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        lbl_discountAmount = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_main = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_balance = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_OrderDiscount = new javax.swing.JTextField();
        btn_complete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lbl_subtotal = new javax.swing.JLabel();
        btn_print = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        rdb_cash = new javax.swing.JRadioButton();
        rdb_card = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        lbl_itemcount = new javax.swing.JLabel();
        discount = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbl_grandTotal = new javax.swing.JLabel();
        txt_payment = new javax.swing.JTextField();
        lbl_totalDiscount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_logo.setMaximumSize(new java.awt.Dimension(500, 500));
        lbl_logo.setMinimumSize(new java.awt.Dimension(100, 100));
        lbl_logo.setPreferredSize(new java.awt.Dimension(128, 172));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Item code ");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txt_itemCode.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txt_itemCode.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_itemCodeInputMethodTextChanged(evt);
            }
        });
        txt_itemCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_itemCodeActionPerformed(evt);
            }
        });
        txt_itemCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_itemCodeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_itemCodeKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Quantity ");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txt_quantity.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txt_quantity.setText("1");
        txt_quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quantityActionPerformed(evt);
            }
        });
        txt_quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_quantityKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_quantityKeyTyped(evt);
            }
        });

        lbl_discount.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbl_discount.setText("Discount %");
        lbl_discount.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txt_itemDiscount.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txt_itemDiscount.setText("0");
        txt_itemDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_itemDiscountActionPerformed(evt);
            }
        });
        txt_itemDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_itemDiscountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_itemDiscountKeyTyped(evt);
            }
        });

        btn_remove.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_remove.setText("Remove");
        btn_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeActionPerformed(evt);
            }
        });

        btn_additem.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_additem.setText("Add Item");
        btn_additem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_additemMouseClicked(evt);
            }
        });
        btn_additem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_additemActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Unit Price ");

        lbl_unitprice.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbl_unitprice.setText("0.00");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Name ");

        lbl_name.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbl_name.setText(" ");

        lbl_itemsubTotal.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbl_itemsubTotal.setText("0.00");
        lbl_itemsubTotal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lbl_itemsubTotalPropertyChange(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("Sub Total");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("Discount Type");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        rdb_percentage.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        rdb_percentage.setText("Percentage");
        rdb_percentage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdb_percentageActionPerformed(evt);
            }
        });

        rdb_fixed.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        rdb_fixed.setText("Fixed");
        rdb_fixed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdb_fixedActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setText("Discount Amount");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lbl_discountAmount.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbl_discountAmount.setText("0.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(90, 90, 90)
                        .addComponent(txt_quantity))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_discount)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_itemsubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_discountAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdb_percentage)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdb_fixed)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txt_itemDiscount)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_additem, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 155, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_unitprice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_itemCode))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_itemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10)
                    .addComponent(lbl_name, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lbl_unitprice))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdb_percentage)
                    .addComponent(rdb_fixed))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_itemDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_discountAmount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lbl_itemsubTotal))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_additem, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(btn_remove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(163, 163, 163))
        );

        tbl_main.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tbl_main.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Iteam", "Quantity", "Selling Price", "Discount", "Sub Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_main.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_mainMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_main);
        if (tbl_main.getColumnModel().getColumnCount() > 0) {
            tbl_main.getColumnModel().getColumn(0).setMinWidth(400);
            tbl_main.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Sub Total   ");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("BALANCE      ");

        lbl_balance.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbl_balance.setText("0.00");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("PAYMENT          ");

        txt_OrderDiscount.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txt_OrderDiscount.setText("0.00");
        txt_OrderDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_OrderDiscountActionPerformed(evt);
            }
        });
        txt_OrderDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_OrderDiscountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_OrderDiscountKeyTyped(evt);
            }
        });

        btn_complete.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btn_complete.setText("Complete");
        btn_complete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_completeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Total Discount          ");

        lbl_subtotal.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbl_subtotal.setText("0.00");
        lbl_subtotal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lbl_subtotalPropertyChange(evt);
            }
        });

        btn_print.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btn_print.setText("Print");
        btn_print.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_printMouseClicked(evt);
            }
        });
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("payment Type ");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        rdb_cash.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        rdb_cash.setText("Cash");
        rdb_cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdb_cashActionPerformed(evt);
            }
        });

        rdb_card.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        rdb_card.setText("Card");
        rdb_card.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdb_cardActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel14.setText("Total items");

        lbl_itemcount.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbl_itemcount.setText("0");
        lbl_itemcount.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lbl_itemcountPropertyChange(evt);
            }
        });

        discount.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        discount.setText("Order Discount");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel15.setText("Grand Total          ");

        lbl_grandTotal.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbl_grandTotal.setText("0.00");

        txt_payment.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txt_payment.setText("0.00");
        txt_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_paymentActionPerformed(evt);
            }
        });
        txt_payment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_paymentKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_paymentKeyTyped(evt);
            }
        });

        lbl_totalDiscount.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbl_totalDiscount.setText("0.00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel15))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_grandTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lbl_totalDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_balance, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_itemcount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_subtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(rdb_cash)
                                        .addGap(43, 43, 43)
                                        .addComponent(rdb_card)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(discount))
                                .addGap(80, 80, 80)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_OrderDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_print, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_complete, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(lbl_itemcount))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(lbl_subtotal, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(discount)
                            .addComponent(txt_OrderDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lbl_totalDiscount))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(lbl_grandTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdb_cash)
                            .addComponent(rdb_card))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_payment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(lbl_balance)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_print, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_complete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(lbl_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1415, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeActionPerformed
        DefaultTableModel tbmmodel = (DefaultTableModel) tbl_main.getModel();
        if (tbl_main.getSelectedRowCount() == 1) {
            tbmmodel.removeRow(tbl_main.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(this, "Select one record to remove!");
        }
        this.calculategrandTotal();
//        double sum = 0.0;
//        for (int i = 0; i < tbl_main.getRowCount(); i++) {
//            sum += Double.parseDouble((String) tbl_main.getValueAt(i, 2));
//        }
//        DecimalFormat df = new DecimalFormat("00.00");
//        lbl_subtotal.setText(df.format(sum));
    }//GEN-LAST:event_btn_removeActionPerformed

    private void btn_completeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_completeActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl_main.getModel();
        model.setRowCount(0);
        this.completeOrder();
    }//GEN-LAST:event_btn_completeActionPerformed

    private void txt_OrderDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_OrderDiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_OrderDiscountActionPerformed

    private void lbl_subtotalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lbl_subtotalPropertyChange
        // TODO add your handling code here:
//        Double tot = Double.valueOf(subtotal.getText());
//        Double discountval = Double.valueOf(discount.getText());
//        Double bal = tot - (tot * discountval * 0.01);
//        DecimalFormat df = new DecimalFormat("00.00");
//        Total.setText(String.valueOf(df.format(bal)));
    }//GEN-LAST:event_lbl_subtotalPropertyChange

    private void txt_itemCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_itemCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_itemCodeActionPerformed

    private void txt_itemCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_itemCodeKeyReleased
        this.emptyAdditemset();
        String inputetxt = txt_itemCode.getText();
        inputetxt = inputetxt.trim().toUpperCase();
        Product product = (Product) product_map.get(inputetxt);
        if (product != null) {

            String name = product.getName();
            Double price = product.getPrice();
            lbl_unitprice.setText(price.toString());
            lbl_name.setText(name);

            this.calculateDiscountAndSubTotal();
//            int qty = Integer.parseInt(txt_quantity.getText());
//            Double Tot_Price = price * qty;
//            Double discount = Double.valueOf(txt_itemDiscount.getText());
//            double discountAmount = 0.00;
//            if (rdb_percentage.isSelected()) {
//                discountAmount = discount * 0.01;
//            } else {
//                discountAmount = discount;
//            }
//            lbl_discountAmount.setText(String.format("%.02f", discountAmount));
//            Double bal = Tot_Price - (Tot_Price * discountAmount);
//            DecimalFormat formatter = new DecimalFormat("#0.00");
//            lbl_itemsubTotal.setText(formatter.format(bal));
        }

//        String Qty = JOptionPane.showInputDialog("Enter Qty");
//        Double tqty = Double.valueOf(Qty);
//        Double Tot_Price = price * tqty ;
//        
//     DecimalFormat df = new DecimalFormat("00.00");
//     String d11 = df.format(Tot_Price);
//       
//        
//        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
//        
//        Vector v = new Vector();
//        v.add(name);
//        v.add(Qty);
//        v.add(d11);
//        dt.addRow(v);
//        
//      cart_cal();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_itemCodeKeyReleased

    private void txt_itemCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_itemCodeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_itemCodeKeyTyped

    private void txt_itemCodeInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_itemCodeInputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_itemCodeInputMethodTextChanged

    private void txt_quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantityActionPerformed

    private void txt_quantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_quantityKeyReleased
        // TODO add your handling code here:
//        double unit_price = Double.parseDouble(lbl_unitprice.getText());
//        int qty = Integer.parseInt(txt_quantity.getText());
//        double Tot_Price = unit_price * qty;
//        double discountAmount = 0;
//        if (rdb_percentage.isSelected()) {
//            discountAmount = Tot_Price * Double.parseDouble(txt_itemDiscount.getText()) * 0.01;
//        } else {
//            discountAmount = Double.parseDouble(txt_itemDiscount.getText());
//        }
//        lbl_discountAmount.setText("-" + String.format("%.02f", discountAmount));
//        double bal = Tot_Price - discountAmount;
//        DecimalFormat df = new DecimalFormat("00.00");
//        lbl_itemsubTotal.setText(String.valueOf(df.format(bal)));
        this.calculateDiscountAndSubTotal();
    }//GEN-LAST:event_txt_quantityKeyReleased

    private void txt_quantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_quantityKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_quantityKeyTyped

    private void txt_itemDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_itemDiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_itemDiscountActionPerformed

    private void txt_itemDiscountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_itemDiscountKeyReleased
        this.calculateDiscountAndSubTotal();
//        double unit_price = Double.parseDouble(lbl_unitprice.getText());
//        int qty = Integer.parseInt(txt_quantity.getText());
//        double Tot_Price = unit_price * qty;
//        double discountAmount = 0;
//        if (rdb_percentage.isSelected()) {
//            discountAmount = Tot_Price * Double.parseDouble(txt_itemDiscount.getText()) * 0.01;
//        } else {
//            discountAmount = Double.parseDouble(txt_itemDiscount.getText());
//        }
//        lbl_discountAmount.setText("-" + String.format("%.02f", discountAmount));
//        Double bal = Tot_Price - discountAmount;
//        DecimalFormat df = new DecimalFormat("00.00");
//        lbl_itemsubTotal.setText(String.valueOf(df.format(bal)));
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_itemDiscountKeyReleased

    private void txt_itemDiscountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_itemDiscountKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_itemDiscountKeyTyped

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_printActionPerformed

    private void btn_additemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_additemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_additemActionPerformed

    private void btn_additemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_additemMouseClicked
        // TODO add your handling code here:
        DefaultTableModel dt = (DefaultTableModel) tbl_main.getModel();
        Double tot_price = Double.parseDouble(lbl_itemsubTotal.getText());
//        String formattotal = df.format(tot_price);
        Vector v = new Vector();
        v.add(lbl_name.getText());
        v.add(txt_quantity.getText());
        v.add(lbl_unitprice.getText());
        v.add(lbl_discountAmount.getText());
        v.add(lbl_itemsubTotal.getText());
//        v.add(formattotal);
        dt.addRow(v);
        txt_itemCode.setText("");
        this.emptyAdditemset();
        this.calculategrandTotal();

//        double subtotalval = Double.parseDouble(lbl_itemsubTotal.getText());
//        subtotalval += tot_price;
//        DecimalFormat df = new DecimalFormat("00.00");
//        lbl_subtotal.setText(String.valueOf(df.format(subtotalval)));

    }//GEN-LAST:event_btn_additemMouseClicked

    private void lbl_itemsubTotalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lbl_itemsubTotalPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_itemsubTotalPropertyChange

    private void tbl_mainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_mainMouseClicked
        // TODO add your handling code here:
//        DefaultTableModel tbmmodel = (DefaultTableModel) jTable1.getModel();
//    if(jTable1.getSelectedRowCount()==1){
//    tbmmodel.removeRow(jTable1.getSelectedRow());
//    } else {
//        JOptionPane.showMessageDialog(this, "Table is empty");
//    }


    }//GEN-LAST:event_tbl_mainMouseClicked

    private void txt_OrderDiscountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_OrderDiscountKeyReleased
        // TODO add your handling code here:
        this.calculategrandTotal();
//        double totalval = Double.parseDouble(lbl_totalDiscount.getText());
//        double cashval = Double.parseDouble(txt_OrderDiscount.getText());
//        double balance = cashval - totalval;
//        DecimalFormat df = new DecimalFormat("00.00");
//        lbl_balance.setText(String.valueOf(df.format(balance)));
    }//GEN-LAST:event_txt_OrderDiscountKeyReleased

    private void txt_OrderDiscountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_OrderDiscountKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_OrderDiscountKeyTyped

    private void btn_printMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_printMouseClicked
        // TODO add your handling code here:
//        discount.setText("0.00");
//        lbl_subtotal.setText("0.00");
//        lbl_totalDiscount.setText("00.00");
//        txt_OrderDiscount.setText("0.00");
//        lbl_balance.setText("0.00");
        bill_print();


    }//GEN-LAST:event_btn_printMouseClicked

    private void rdb_cashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdb_cashActionPerformed
        if (rdb_cash.isSelected()) {
            rdb_card.setSelected(false);
            txt_payment.setEditable(true);
            txt_payment.setText("0.00");
            lbl_balance.setText("0.00");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_rdb_cashActionPerformed

    private void rdb_cardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdb_cardActionPerformed
        if (rdb_card.isSelected()) {
            rdb_cash.setSelected(false);
            txt_payment.setEditable(false);
            txt_payment.setText("0.00");
            lbl_balance.setText("0.00");

        }        // TODO add your handling code here:
    }//GEN-LAST:event_rdb_cardActionPerformed

    private void rdb_percentageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdb_percentageActionPerformed
        // TODO add your handling code here:

//        double unit_price = Double.parseDouble(lbl_unitprice.getText());
//        int qty = Integer.parseInt(txt_quantity.getText());
//        Double Tot_Price = unit_price * qty;
//        double discountAmount = 0;
        if (rdb_percentage.isSelected()) {
            rdb_fixed.setSelected(false);
            lbl_discount.setText("Discount %");
//            discountAmount = Tot_Price * Double.parseDouble(txt_itemDiscount.getText()) * 0.01;
        }
        this.calculateDiscountAndSubTotal();
//        lbl_discountAmount.setText("-" + String.format("%.02f", discountAmount));
//        Double discount = Double.valueOf(txt_itemDiscount.getText());
//        Double bal = Tot_Price - (Tot_Price * discount * 0.01);
//        DecimalFormat df = new DecimalFormat("00.00");
//        lbl_itemsubTotal.setText(String.valueOf(df.format(bal)));
    }//GEN-LAST:event_rdb_percentageActionPerformed

    private void rdb_fixedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdb_fixedActionPerformed
        // TODO add your handling code here:

//        double unit_price = Double.parseDouble(lbl_unitprice.getText());
//        int qty = Integer.parseInt(txt_quantity.getText());
//        Double Tot_Price = unit_price * qty;
//        double discountAmount = 0;
        if (rdb_fixed.isSelected()) {
            rdb_percentage.setSelected(false);
            lbl_discount.setText("Discount ");
//            discountAmount = Double.parseDouble(txt_itemDiscount.getText());
        }
        this.calculateDiscountAndSubTotal();
//        lbl_discountAmount.setText("-" + String.format("%.02f", discountAmount));
//        Double discount = Double.valueOf(txt_itemDiscount.getText());
//        Double bal = Tot_Price - (Tot_Price * discount * 0.01);
//        DecimalFormat df = new DecimalFormat("00.00");
//        lbl_itemsubTotal.setText(String.valueOf(df.format(bal)));
    }//GEN-LAST:event_rdb_fixedActionPerformed

    private void lbl_itemcountPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lbl_itemcountPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_itemcountPropertyChange

    private void txt_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_paymentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_paymentActionPerformed

    private void txt_paymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_paymentKeyReleased
        // TODO add your handling code here:
        this.calculategrandTotal();
    }//GEN-LAST:event_txt_paymentKeyReleased

    private void txt_paymentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_paymentKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_paymentKeyTyped

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
//            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new NewJFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_additem;
    private javax.swing.JButton btn_complete;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_remove;
    private javax.swing.JLabel discount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_balance;
    private javax.swing.JLabel lbl_discount;
    private javax.swing.JLabel lbl_discountAmount;
    private javax.swing.JLabel lbl_grandTotal;
    private javax.swing.JLabel lbl_itemcount;
    private javax.swing.JLabel lbl_itemsubTotal;
    private javax.swing.JLabel lbl_logo;
    private javax.swing.JLabel lbl_name;
    private javax.swing.JLabel lbl_subtotal;
    private javax.swing.JLabel lbl_totalDiscount;
    private javax.swing.JLabel lbl_unitprice;
    private javax.swing.JRadioButton rdb_card;
    private javax.swing.JRadioButton rdb_cash;
    private javax.swing.JRadioButton rdb_fixed;
    private javax.swing.JRadioButton rdb_percentage;
    private javax.swing.JTable tbl_main;
    private javax.swing.JTextField txt_OrderDiscount;
    private javax.swing.JTextField txt_itemCode;
    private javax.swing.JTextField txt_itemDiscount;
    private javax.swing.JTextField txt_payment;
    private javax.swing.JTextField txt_quantity;
    // End of variables declaration//GEN-END:variables

}
