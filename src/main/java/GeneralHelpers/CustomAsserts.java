package GeneralHelpers;

import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import org.testng.Assert;

/**
 * Created by CMG_TEST on 08.10.2015.
 */
public class CustomAsserts {

    public static void orderParametersCheck(OrderObject order, OrderInfoAndActions orderInfoAndActions, String status){

        Assert.assertEquals(order.getEntityOrderSystemID(), orderInfoAndActions.getsystemOrderID());
        Assert.assertEquals(order.getDesc(), orderInfoAndActions.getorderDescription());
        //Assert.assertEquals(order.getEntityOrderDeadLine(), orderInfoAndActions.getorderDeadline()); need multi timezone implementation
        Assert.assertEquals(order.getEntityOrderName(), orderInfoAndActions.getorderName());
        Assert.assertEquals(order.getWordsReq(), orderInfoAndActions.getWordsRequire());
        Assert.assertEquals(orderInfoAndActions.getorderStatus(), status);
    }

}
