package Tests.DBtests;

import Tests.BaseTest;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static GeneralHelpers.DBWorker.createMaxUserId;
import static GeneralHelpers.JDBCutil.insertRecordIntoDbUserTable;

/**
 * Created by DeBeers on 26.10.2015.
 */
public class InsertionOfNewUserToDB extends BaseTest {



    @Test(groups={"regress 1.0"})
    public static void InsertionOfNewUserToDB() throws InterruptedException, SQLException {

//        String insertTableSQL = "insert into `users` " +
//                 "(`id`, `first_name`, `nick_name`, `last_name`, `birthday`, `email`, `phone`, `is_user_deleted`, `password`, `password_salt`, `register_date`, `location_country`, `location_region`, `location_city`, `address`, `zip`, `biography`, `is_user_blocked`, `is_user_active`, `translite_name`, `person`, `pan`, `service_tax_number`, `user_role`, `social_id`, `last_visit`, `last_ip`, `no_commission`, `completed_orders`, `increase_profile_email_sent`, `warned_count`, `timezone_id`, `currency_id`) " +
//                 "values" +
//                 "('98765432','WriterUser','WriterWR','SuperWriter','1966-02-13','debeers@bigmir.net','+91-7607059383','0','$2a$10$YdQcM3RmhCDJbatSoBgFGuL/qRIRY38rH9o8zpxFTuQqjreOz44fS','$2a$10$YdQcM3RmhCDJbatSoBgFGw$','2015-08-11 06:45:10','28','751','76651','spttbiougis','555555','Dererer rerere rerere','0','1','','individual','TESTT8477T',NULL,'copywriter','','2015-10-26 14:48:57','213.186.202.162','0','193','sent','0','397','1')";
//
//        String insertTableSQL1 = "insert into `billing`.`bills` (`id`, `bill_type`, `name`, `user_id`, `balance`) values(NULL,'GU','General user account for WriterWR','98765432','0.00')";
//        String insertTableSQL2 = "insert into `billing`.`bills` (`id`, `bill_type`, `name`, `user_id`, `balance`) values(NULL,'BU','Blocked user account for WriterWR','98765432','0.00')";
//
//        insertRecordIntoDbUserTable(insertTableSQL);
//        insertRecordIntoDbUserTable(insertTableSQL1);
//        insertRecordIntoDbUserTable(insertTableSQL2);


        System.out.println(createMaxUserId());


    }
}
