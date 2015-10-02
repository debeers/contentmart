package Tests.CreateNewOrderActions;

import Actions.Client.ClientGoToCreateNewOrder;
import DataProviders.CreateNewOrderActionsDataProvider;
import Entities.LoginObject;
import Entities.OrderObject;
import PageObjects.General.OrderInfoAndActions;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by DeBeers on 17.09.2015.
 */
public class PublishOrderFromTheDraft extends BaseTest{





    @Test(groups={"regress 1.0"}, dataProvider= "CreateNewOrderActions", dataProviderClass = CreateNewOrderActionsDataProvider.class)
    public  void PublishOrderFromTheDraft(Object clientLoginObject, Object orderObject) throws Exception {

        LoginObject clientLogin = (LoginObject) clientLoginObject;
        OrderObject orderObj = (OrderObject) orderObject;

        OrderInfoAndActions orderInfoAndActions = ClientGoToCreateNewOrder.andSaveAsDraft(driver, clientLogin, orderObj);
        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Drafted");
        orderInfoAndActions.andClickOnPublishOrderButtonTop();
        assertEquals(orderInfoAndActions.getTextFromOrderStatus(), "Published");



    }














}
