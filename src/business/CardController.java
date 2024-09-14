package business;

import dao.CardDao;
import entity.Card;

import java.util.ArrayList;

public class CardController {
    private final CardDao cardDao = new CardDao();

    public boolean save(Card card){
        return this.cardDao.save(card);
    }
    public ArrayList<Card> findAll(){
        return this.cardDao.finAll();
    }
}
