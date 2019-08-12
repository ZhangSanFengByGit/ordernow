package ordernow.controller;

import ordernow.domain.Pair;
import ordernow.domain.*;
import ordernow.service.OrderTable;
import ordernow.service.RegistrationService;
import ordernow.service.ValidationService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
public class ServiceController {
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private CuisineRepo cuisineRepo;
    @Autowired
    private OrderTable orderTable;

// ---------------------------REGISTER
    @RequestMapping(value = "/registerManager", method = RequestMethod.POST)
    public ResponseEntity<?> registerManager(@RequestBody final ManagerAccount managerAccount){
        System.out.println("received managerAccount:  "+ managerAccount.toString());
        boolean ret = registrationService.registerManager(managerAccount.name,managerAccount.phoneNumber,
                                    managerAccount.badgeNumber, managerAccount.password);
        String retJson = new JSONObject().put("success",String.valueOf(ret)).toString();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(retJson);
    }

    @RequestMapping(value = "/registerCustomer", method = RequestMethod.POST)
    public ResponseEntity<?> registerCustomer(@RequestBody final CustomerAccount customerAccount){
        System.out.println("received customerAccount:  "+ customerAccount.toString());
        boolean ret = registrationService.registerCustomer(customerAccount.phoneNumber, customerAccount.password);
        String retJson = new JSONObject().put("success",String.valueOf(ret)).toString();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(retJson);
    }


// ---------------------------VALIDATION
    @RequestMapping(value = "/managerValidation", method = RequestMethod.POST)
    public ResponseEntity<?> managerValidation(@RequestBody final CustomerAccount customerAccount){
        System.out.println("received managerAccount:  "+ customerAccount.toString());
        boolean ret = validationService.validateManager(customerAccount.phoneNumber, customerAccount.password);
        String retJson = new JSONObject().put("success",String.valueOf(ret)).toString();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(retJson);
    }

    @RequestMapping(value = "/customerValidation", method = RequestMethod.POST)
    public ResponseEntity<?> customerValidation(@RequestBody final CustomerAccount customerAccount){
        System.out.println("received customerAccount:  "+ customerAccount.toString());
        boolean ret = validationService.validateCustomer(customerAccount.phoneNumber, customerAccount.password);
        String retJson = new JSONObject().put("success",String.valueOf(ret)).toString();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(retJson);
    }


//  -----------------------ADD CUISINE
    @RequestMapping(value = "/addCuisine", method = RequestMethod.POST)
    public ResponseEntity<?> addCuisine(@RequestBody final Cuisine cuisine){
        boolean ret = registrationService.registerCuisine(cuisine);
        String retJson = new JSONObject().put("success",String.valueOf(ret)).toString();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(retJson);
    }

//  -----------------------SHOW ALL CUISINE
    @GetMapping(value = "/showAllCuisines")
    public ResponseEntity<?> showAllCuisines(){
        List<Cuisine> cuisines = cuisineRepo.findAll();
        JSONObject retJson = new JSONObject();
        JSONArray menuArr = new JSONArray();
        for (Cuisine cuisine: cuisines){
            JSONObject cur = new JSONObject();
            cur.put("name",cuisine.name);
            cur.put("price",cuisine.price);
            cur.put("info",cuisine.info);
            menuArr.put(cur);
        }
        retJson.put("menu", menuArr);
        String ret = retJson.toString();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ret);
    }

// ---------------------MAKE AN ORDER
    @RequestMapping(value = "/makeOrder", method = RequestMethod.POST)
    public ResponseEntity<?> makeOrder(@RequestBody String requestStr){
        JSONObject request = new JSONObject(requestStr);
        String phoneNumber = request.getString("phoneNumber");
        JSONArray orders = request.getJSONArray("orders");
        ArrayList<Pair<String,String>> orderList = new ArrayList<>();
        for (int pos=0; pos<orders.length(); pos++){
            JSONObject curPair = orders.getJSONObject(pos);
            Pair<String,String> cur = new Pair<>(curPair.getString("name"),curPair.getString("quantity"));
            orderList.add(cur);
        }
        long id = orderTable.offer(phoneNumber, orderList);
        JSONObject ret = new JSONObject().put("id", String.valueOf(id));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ret.toString());
    }

// ----------------------FINISH AN ORDER
    @RequestMapping(value = "/finishOrder", method = RequestMethod.POST)
    public ResponseEntity<?> finishOrder(@RequestBody String orderStr){
        JSONObject order = new JSONObject(orderStr);
        boolean opr = orderTable.finish(Long.parseLong(order.getString("id")));
        JSONObject ret;
        if (opr) {
            ret = new JSONObject().put("status", "Operation Acknowledged");
        }else{
            ret = new JSONObject().put("status", "The order is not in unfinished list");
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ret.toString());
    }

// -----------------------PICK UP FINISHED ORDER
    @RequestMapping(value = "/pickUp", method = RequestMethod.POST)
    public ResponseEntity<?> pickUp(@RequestBody String orderStr){
        System.out.println(orderStr);
        JSONObject order = new JSONObject(orderStr);
        boolean opr = orderTable.pickUp(Long.parseLong(order.getString("id")));
        JSONObject ret;
        if (opr) {
            ret = new JSONObject().put("status", "Operation Acknowledged");
        }else{
            ret = new JSONObject().put("status", "The order is not in finished list");
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ret.toString());
    }

// -----------------------SHOW ALL FINISHED ORDERS
    @GetMapping(value = "/showAllFinishedOrders")
    public ResponseEntity<?> showAllFinishedOrders(){
        Collection<Order> orders = orderTable.getFinishedKeys();
        JSONArray retArr = new JSONArray();
        for (Order order: orders){
            JSONObject cur = new JSONObject();
            cur.put("id",String.valueOf(order.orderId));
            cur.put("phoneNumber", order.phoneNumber);
            retArr.put(cur);
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(retArr.toString());
    }

// ----------------------SHOW ALL UNFINISHED ORDERS
    @GetMapping(value = "/showAllUnfinishedOrders")
    public ResponseEntity<?> showAllUnfinishedOrders(){
        Collection<Order> unfinished = orderTable.getUnfinishedOrders();
        JSONArray retArr = new JSONArray();
        for (Order order: unfinished){
            JSONObject cur = new JSONObject();
            cur.put("id", String.valueOf(order.orderId));
            cur.put("phoneNumber", order.phoneNumber);
            JSONArray orders = new JSONArray();
            for (Pair pair: order.orderList){
                JSONObject curPair = new JSONObject();
                curPair.put("name",pair.getKey());
                curPair.put("quantity",pair.getValue());
                orders.put(curPair);
            }
            cur.put("orders", orders);
            retArr.put(cur);
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(retArr.toString());
    }
}
