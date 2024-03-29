package dev.mvc.order_item;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.order_item.Order_itemProc")
public class Order_itemProc implements Order_itemProcInter {
  @Autowired
  private Order_itemDAOInter order_itemDAO;
  
  @Override
  public int create(Order_itemVO order_itemVO) {
    int cnt = this.order_itemDAO.create(order_itemVO);
    return cnt;
  }
  
  @Override
  public List<Order_itemVO> list_by_memberno(HashMap<String, Object> map) {
    List<Order_itemVO> list = null;
    list = this.order_itemDAO.list_by_memberno(map);
    return list;
  } 

}

