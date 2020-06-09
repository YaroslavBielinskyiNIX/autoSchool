import api.serializationClasses.oneTime.OneTimeBookRequestInfo;
import api.serializationClasses.recurring.request.RecurringDailyBookRequestInfo;
import api.steps.BookSteps;
import api.steps.LoginSteps;
import org.testng.annotations.Test;

public class TestClass {

    @Test
    public void authAsUserTest() {
        new LoginSteps().openLoginPage()
                .loginAsUser();
    }

    @Test
    public void oneTimeBookTest() {
        OneTimeBookRequestInfo oneTimeBookRequestInfo = new OneTimeBookRequestInfo();

        new LoginSteps().openLoginPage()
                .loginAsUser()
                .bookRoom(oneTimeBookRequestInfo)
                .deleteBook(oneTimeBookRequestInfo);
    }

    @Test
    public void deleteRecurringNearestBookTest() {
        RecurringDailyBookRequestInfo recurringDailyBookRequestInfo = new RecurringDailyBookRequestInfo();

        new LoginSteps().openLoginPage()
                .loginAsUser()
                .bookRoom(recurringDailyBookRequestInfo)
                .verifyBookCreated(recurringDailyBookRequestInfo)
                .deleteNearestRecurringDailyBook(recurringDailyBookRequestInfo)
                .deleteBook(recurringDailyBookRequestInfo);
    }

    @Test
    public void changeOneTimeBookTitleTest() {
        OneTimeBookRequestInfo oneTimeBookRequestInfo = new OneTimeBookRequestInfo();

        new LoginSteps().openLoginPage()
                .loginAsUser()
                .bookRoom(oneTimeBookRequestInfo)
                .verifyBookCreated(oneTimeBookRequestInfo)
                .changeTitle(oneTimeBookRequestInfo, "NewTestTitle")
                .deleteBook(oneTimeBookRequestInfo);
    }

    @Test
    public void emailRemainderTest() {
        OneTimeBookRequestInfo oneTimeBookRequestInfo = new OneTimeBookRequestInfo();

        new LoginSteps()
                .openLoginPage()
                .loginAsUser()
                .bookRoom(oneTimeBookRequestInfo)
                .addEmailRemainder(oneTimeBookRequestInfo)
                .deleteBook(oneTimeBookRequestInfo);
    }

    @Test
    public void setFavouriteBuildingTest() {
        new LoginSteps().openLoginPage()
                .loginAsUser()
                .setFavouriteBuilding(1);
    }

    @Test
    public void dailyRecurringBookTest() {
        RecurringDailyBookRequestInfo recurringDailyBookRequestInfo = new RecurringDailyBookRequestInfo();

        new LoginSteps().openLoginPage()
                .loginAsUser()
                .bookRoom(recurringDailyBookRequestInfo)
                .deleteBook(recurringDailyBookRequestInfo);
    }

    @Test
    public void bookWithSizeSelectTest() {
        BookSteps bookSteps = new LoginSteps().openLoginPage()
                .loginAsUser();

        OneTimeBookRequestInfo oneTimeBookRequestInfo = bookSteps.getRoomsWithSize("1");

        bookSteps.bookRoom(oneTimeBookRequestInfo)
                .verifyBookCreated(oneTimeBookRequestInfo)
                .deleteBook(oneTimeBookRequestInfo);

    }

}