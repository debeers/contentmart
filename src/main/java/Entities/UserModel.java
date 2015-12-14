package Entities;

/**
 * Created by DeBeers on 30.11.2015.
 */
public class UserModel {


    public UserModel(){}

    public UserModel(
                     int userId, String userFirstName, String userNickName, String userLastName, String userBirthday,
                     String userEmail, String userPhone, Boolean isUserDeleted, String userPassword, String userPasswordSalt,
                     String userRegisterDate, int userCountry, int userRegion, int userCity, String userAddress, String userZip,
                     String userBiography, Boolean isUserBlocked, Boolean isUserActive, String userTransliteName, String userPerson,
                     String userPan, String userServiceTax, String userRole, String userSocialId, String userLastVisit, String userLastIP,
                     Boolean userNoCommission, int userCompletedOrders, String userIncreaseProfileEmailSent, int userWarnedCount, int userTimeZone,
                     int userCurrency, Boolean userIsServiceTaxPayer) {

        this.userId                       = userId;
        this.userFirstName                = userFirstName;
        this.userNickName                 = userNickName;
        this.userLastName                 = userLastName;
        this.userBirthday                 = userBirthday;
        this.userEmail                    = userEmail;
        this.userPhone                    = userPhone;
        this.isUserDeleted                = isUserDeleted;
        this.userPassword                 = userPassword;
        this.userPasswordSalt             = userPasswordSalt;
        this.userRegisterDate             = userRegisterDate;
        this.userCountry                  = userCountry;
        this.userRegion                   = userRegion;
        this.userCity                     = userCity;
        this.userAddress                  = userAddress;
        this.userZip                      = userZip;
        this.userBiography                = userBiography;
        this.isUserBlocked                = isUserBlocked;
        this.isUserActive                 = isUserActive;
        this.userTransliteName            = userTransliteName;
        this.userPerson                   = userPerson;
        this.userPan                      = userPan;
        this.userServiceTax               = userServiceTax;
        this.userRole                     = userRole;
        this.userSocialId                 = userSocialId;
        this.userLastVisit                = userLastVisit;
        this.userLastIP                   = userLastIP;
        this.userNoCommission             = userNoCommission;
        this.userCompletedOrders          = userCompletedOrders;
        this.userIncreaseProfileEmailSent = userIncreaseProfileEmailSent;
        this.userWarnedCount              = userWarnedCount;
        this.userTimeZone                 = userTimeZone;
        this.userCurrency                 = userCurrency;
        this.userIsServiceTaxPayer        = userIsServiceTaxPayer;
    }


    public int         userId;
    public String      userFirstName;
    public String      userNickName;
    public String      userLastName;
    public String      userBirthday;
    public String      userEmail;
    public String      userPhone;
    public Boolean     isUserDeleted;
    public String      userPassword;
    public String      userPasswordSalt;
    public String      userRegisterDate;
    public int         userCountry;
    public int         userRegion;
    public int         userCity;
    public String      userAddress;
    public String      userZip;
    public String      userBiography;
    public Boolean     isUserBlocked;
    public Boolean     isUserActive;
    public String      userTransliteName;
    public String      userPerson;
    public String      userPan;
    public String      userServiceTax;
    public String      userRole;
    public String      userSocialId;
    public String      userLastVisit;
    public String      userLastIP;
    public Boolean     userNoCommission;
    public int         userCompletedOrders;
    public String      userIncreaseProfileEmailSent;
    public int         userWarnedCount;
    public int         userTimeZone;
    public int         userCurrency;
    public Boolean     userIsServiceTaxPayer;


    public String getUserPasswordSalt() {
        return userPasswordSalt;
    }

    public void setUserPasswordSalt(String userPasswordSalt) {
        this.userPasswordSalt = userPasswordSalt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Boolean getIsUserDeleted() {
        return isUserDeleted;
    }

    public void setIsUserDeleted(Boolean isUserDeleted) {
        this.isUserDeleted = isUserDeleted;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRegisterDate() {
        return userRegisterDate;
    }

    public void setUserRegisterDate(String userRegisterDate) {
        this.userRegisterDate = userRegisterDate;
    }

    public int getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(int userCountry) {
        this.userCountry = userCountry;
    }

    public int getUserRegion() {
        return userRegion;
    }

    public void setUserRegion(int userRegion) {
        this.userRegion = userRegion;
    }

    public int getUserCity() {
        return userCity;
    }

    public void setUserCity(int userCity) {
        this.userCity = userCity;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserZip() {
        return userZip;
    }

    public void setUserZip(String userZip) {
        this.userZip = userZip;
    }

    public String getUserBiography() {
        return userBiography;
    }

    public void setUserBiography(String userBiography) {
        this.userBiography = userBiography;
    }

    public Boolean getIsUserBlocked() {
        return isUserBlocked;
    }

    public void setIsUserBlocked(Boolean isUserBlocked) {
        this.isUserBlocked = isUserBlocked;
    }

    public Boolean getIsUserActive() {
        return isUserActive;
    }

    public void setIsUserActive(Boolean isUserActive) {
        this.isUserActive = isUserActive;
    }

    public String getUserTransliteName() {
        return userTransliteName;
    }

    public void setUserTransliteName(String userTransliteName) {
        this.userTransliteName = userTransliteName;
    }

    public String getUserPerson() {
        return userPerson;
    }

    public void setUserPerson(String userPerson) {
        this.userPerson = userPerson;
    }

    public String getUserPan() {
        return userPan;
    }

    public void setUserPan(String userPan) {
        this.userPan = userPan;
    }

    public String getUserServiceTax() {
        return userServiceTax;
    }

    public void setUserServiceTax(String userServiceTax) {
        this.userServiceTax = userServiceTax;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserSocialId() {
        return userSocialId;
    }

    public void setUserSocialId(String userSocialId) {
        this.userSocialId = userSocialId;
    }

    public String getUserLastVisit() {
        return userLastVisit;
    }

    public void setUserLastVisit(String userLastVisit) {
        this.userLastVisit = userLastVisit;
    }

    public String getUserLastIP() {
        return userLastIP;
    }

    public void setUserLastIP(String userLastIP) {
        this.userLastIP = userLastIP;
    }

    public Boolean getUserNoCommission() {
        return userNoCommission;
    }

    public void setUserNoCommission(Boolean userNoCommission) {
        this.userNoCommission = userNoCommission;
    }

    public int getUserCompletedOrders() {
        return userCompletedOrders;
    }

    public void setUserCompletedOrders(int userCompletedOrders) {
        this.userCompletedOrders = userCompletedOrders;
    }

    public String getUserIncreaseProfileEmailSent() {
        return userIncreaseProfileEmailSent;
    }

    public void setUserIncreaseProfileEmailSent(String userIncreaseProfileEmailSent) {
        this.userIncreaseProfileEmailSent = userIncreaseProfileEmailSent;
    }

    public int getUserWarnedCount() {
        return userWarnedCount;
    }

    public void setUserWarnedCount(int userWarnedCount) {
        this.userWarnedCount = userWarnedCount;
    }

    public int getUserTimeZone() {
        return userTimeZone;
    }

    public void setUserTimeZone(int userTimeZone) {
        this.userTimeZone = userTimeZone;
    }

    public int getUserCurrency() {
        return userCurrency;
    }

    public void setUserCurrency(int userCurrency) {
        this.userCurrency = userCurrency;
    }

    public Boolean getUserIsServiceTaxPayer() {
        return userIsServiceTaxPayer;
    }

    public void setUserIsServiceTaxPayer(Boolean userIsServiceTaxPayer) {
        this.userIsServiceTaxPayer = userIsServiceTaxPayer;
    }

}