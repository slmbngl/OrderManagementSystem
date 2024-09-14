package view;

import business.BasketController;
import business.CardController;
import business.ProductController;
import core.Helper;
import entity.Basket;
import entity.Card;
import entity.Customer;
import entity.Product;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CardUI extends JFrame {
    private JPanel container;
    private JLabel lbl_title;
    private JLabel lbl_customer_name;
    private JTextField fld_card_date;
    private JTextArea tarea_card_note;
    private JButton btn_card;
    private Customer customer;
    private BasketController basketController;
    private CardController cardController;
    private ProductController productController;
    public CardUI (Customer customer){
        this.customer = customer;
        this.basketController = new BasketController();
        this.cardController = new CardController();
        this.productController = new ProductController();
        this.add(container);
        this.setTitle("Sipariş Oluştur");
        this.setSize(300,350);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x,y);
        this.setVisible(true);
        if (customer.getId() == 0){
            Helper.showMsg("Lütfen Geçerli Bir Müşteri Seçiniz");
            dispose();
        }
        ArrayList<Basket> baskets = this.basketController.findAll();
        if (baskets.size() == 0){
            Helper.showMsg("Lütfen sepete ürün ekleyiniz");
            dispose();
        }
        this.lbl_customer_name.setText("Müşteri : " + this.customer.getName());
        btn_card.addActionListener(e -> {
            if (Helper.isFieldEmpty(this.fld_card_date)){
                Helper.showMsg("fill");
            }else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                for(Basket basket : baskets){
                    if (basket.getProduct().getStock() <= 0) continue;
                    Card card = new Card();
                    card.setCustomerId(this.customer.getId());
                    card.setProductId(basket.getProductId());
                    card.setPrice(basket.getProduct().getPrice());
                    card.setDate(LocalDate.parse(this.fld_card_date.getText(),formatter));
                    card.setNote(this.tarea_card_note.getText());
                    this.cardController.save(card);
                    Product unStockProduct = basket.getProduct();
                    unStockProduct.setStock(unStockProduct.getStock() - 1);
                    this.productController.update(unStockProduct);
                }
                this.basketController.clear();
                Helper.showMsg("done");
                dispose();
            }
        });
    }

    private void createUIComponents() throws ParseException {
        this.fld_card_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fld_card_date.setText(formatter.format(LocalDate.now()));
    }
}
