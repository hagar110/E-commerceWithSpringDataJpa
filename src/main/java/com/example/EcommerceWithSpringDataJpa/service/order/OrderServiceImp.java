package com.example.EcommerceWithSpringDataJpa.service.order;
import com.example.EcommerceWithSpringDataJpa.entity.*;
import com.example.EcommerceWithSpringDataJpa.enums.OrderStatus;
import com.example.EcommerceWithSpringDataJpa.repository.CustomerRepository;
import com.example.EcommerceWithSpringDataJpa.repository.OrderRepository;
import com.example.EcommerceWithSpringDataJpa.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService{

    OrderRepository orderRepository;
    ShoppingCartRepository shoppingCartRepository;
    CustomerRepository customerRepository;
    ProductRepository productRepository;

    public OrderServiceImp(OrderRepository orderRepository,ShoppingCartRepository shoppingCartRepository,CustomerRepository customerRepository,ProductRepository productRepository)
    {
        this.orderRepository=orderRepository;
        this.customerRepository=customerRepository;
        this.shoppingCartRepository=shoppingCartRepository;
        this.productRepository=productRepository;
    }
    @Override
    public List<Order> getOrders(int userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Optional<Order> getOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<OrderDetails> getOrderDetails(int orderId) {
        return orderRepository.findById(orderId).get().getOrderDetails();
    }

    @Override
    public OrderStatus checkOrderStatus(int orderId) {
        return orderRepository.findById(orderId).get().getStatus();
    }

    @Override
    public void updateStatus(int orderId, OrderStatus status) {
        orderRepository.updateStatus(orderId, status);
    }

    /**
     *
     * @param userId : logged in customer
     * @return boolean : true for success and false for failure
     */
    @Override
    public boolean checkOut(int userId) {
        List<ShoppingCartProducts> shoppingCartItems = shoppingCartRepository.findAll();
        Optional<Customer> customer = customerRepository.findById(userId);
        if(shoppingCartItems.size()>0){
            List<OrderDetails> orderDetailsList = new ArrayList<>();
            double totalOrderPrice=0;
            Order order = new Order();
            for (ShoppingCartProducts CartItem: shoppingCartItems) {
                Product product= productRepository.getById(CartItem.getProduct().getId());
                //check if required Qty is available in stock
                if(CartItem.getProductQuantity()<=product.getAvailableQuantity()){
                    //create orderDetails
                    OrderDetails orderItem = new OrderDetails(order, CartItem.getProduct(), CartItem.getProductQuantity());
                    orderDetailsList.add(orderItem);
                    //remove shopping cart
                    shoppingCartRepository.delete(CartItem);
                    //decrease product available Qty
                    product.setAvailableQuantity(product.getAvailableQuantity()-CartItem.getProductQuantity());
                    //calculate total order price
                    totalOrderPrice+=CartItem.getProductQuantity()*CartItem.getProduct().getPrice();
                }
                else {
                    return false;
                }
            }
            //add order info.
            order.setAddress(customer.get().getAddresses().get(0));
            order.setCustomer(customer.get());
            order.setStatus(OrderStatus.placed);
            order.setDate(LocalDate.now());
            order.setTotal(totalOrderPrice);
            order.setOrderDetails(orderDetailsList);
            orderRepository.save(order);
        }else{
            return false;
        }
        return true;
    }

    @Override
    public void deleteOrder(int id) {orderRepository.deleteById(id);}
}
