import api.serializationClasses.oneTime.OneTimeBookRequestInfo;
import api.serializationClasses.recurring.request.RecurringDailyBookRequestInfo;
import api.steps.LoginSteps;
import org.testng.annotations.Test;

public class TestClass {

    @Test
    public void authAsUserTest() {
        new LoginSteps().openLoginPage()
                .loginAsUser();
    }

    @Test
    public void bookRoomTest() {
        OneTimeBookRequestInfo oneTimeBookRequestInfo = new OneTimeBookRequestInfo();

        new LoginSteps().openLoginPage()
                .loginAsUser()
                .bookOneTimeRoom(oneTimeBookRequestInfo)
                .deleteOneTimeBook(oneTimeBookRequestInfo);
    }

    @Test
    public void deleteRecurringNearestBookTest() {
        RecurringDailyBookRequestInfo recurringDailyBookRequestInfo = new RecurringDailyBookRequestInfo();

        new LoginSteps().openLoginPage()
                .loginAsUser()
                .BookRoomRecurring(recurringDailyBookRequestInfo)
                .verifyRecurringDailyBookCreated(recurringDailyBookRequestInfo)
                .deleteNearestRecurringDailyBook(recurringDailyBookRequestInfo);
    }

    @Test
    public void changeOneTimeBookTitle() {
        OneTimeBookRequestInfo oneTimeBookRequestInfo = new OneTimeBookRequestInfo();

        new LoginSteps().openLoginPage()
                .loginAsUser()
                .bookOneTimeRoom(oneTimeBookRequestInfo)
                .verifyOneTimeBookCreated(oneTimeBookRequestInfo)
                .changeTitle(oneTimeBookRequestInfo, "NewTestTitle")
                .deleteOneTimeBook(oneTimeBookRequestInfo);
    }

    @Test
    public void book() {

    }
}